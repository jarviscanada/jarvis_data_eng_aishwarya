package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * MarketDataDao is responsible for getting Quotes from IEX.
 */
@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

  private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
  private final String IEX_BATCH_URL;

  private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private HttpClientConnectionManager httpClientConnectionManager;

  @Autowired
  public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager,
      MarketDataConfig marketDataConfig) {
    this.httpClientConnectionManager = httpClientConnectionManager;
    IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
  }

  @Override
  public <S extends IexQuote> S save(S entity) {
    return null;
  }

  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> entities) {
    return null;
  }

  /**
   * Retrieves an entity by its id. Get an IexQuote (helper method which class findAllById)
   *
   * @param ticker must not be {@literal null}.
   * @return the entity with the given id or {@literal Optional#empty()} if none found
   * @throws DataRetrievalFailureException if a given ticker is invalid
   * @throws IllegalArgumentException      if HTTP request failed
   */
  @Override
  public Optional<IexQuote> findById(String ticker) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

    if (quotes.size() == 0) {
      return Optional.empty();
    } else if (quotes.size() == 1) {
      iexQuote = Optional.of(quotes.get(0));
    } else {
      throw new DataRetrievalFailureException("Unexpected number of quotes");
    }
    return iexQuote;
  }

  @Override
  public boolean existsById(String s) {
    return false;
  }

  @Override
  public Iterable<IexQuote> findAll() {
    return null;
  }

  /**
   * Returns all instances of the type with the given IDs. Get quotes from IEX
   *
   * @param tickers is a list of tickers
   * @return a list of IexQuote object
   * @throws IllegalArgumentException      if any ticker is invalid or tickers is empty
   * @throws DataRetrievalFailureException if HTTP request failed
   */
  @Override
  public List<IexQuote> findAllById(Iterable<String> tickers) {
    if (tickers == null) {
      throw new IllegalArgumentException("Tickers is empty");
    }
    List<IexQuote> quoteList = new ArrayList<>();
    String symbols = StreamSupport
        .stream(tickers.spliterator(), false)
        .collect(Collectors.joining(","));

    String url = String.format(IEX_BATCH_URL, symbols);
    String jsonStr = executeHttpGet(url)
        .orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));
    ;

    //Array of JSON documents
    JSONObject iexQuotesJson = new JSONObject(jsonStr);

    //Indicating presence of Invalid tickers if any in the passed tickers
    if (iexQuotesJson.length() == 0) {
      throw new IllegalArgumentException("Invalid tickers");
    }

    // Adding valid tickers to quoteList
    for (String ticker : tickers) {
      if (iexQuotesJson.has(ticker)) {
        ObjectMapper m = new ObjectMapper();
        IexQuote quote = null;
        try {
          quote = m.readValue(iexQuotesJson.get(ticker).toString(), IexQuote.class);
        } catch (IOException ex) {
          logger.error("Fail to map to IexQuote object", ex);
        }
        quoteList.add(quote);
        //quoteList.add((IexQuote)iexQuotesJson.get(ticker));
      } else {
        throw new IllegalArgumentException("Invalid ticker: " + ticker);
      }
    }

    return quoteList;
  }

  /**
   * Execute a get and return http entity/body as a string.
   *
   * @param url resource URL
   * @return http response body or Optional.empty for 404 response
   * @throws DataRetrievalFailureException if HTTP failed or status code is unexpected
   */
  private Optional<String> executeHttpGet(String url) {
    HttpClient httpClient = getHttpClient();
    HttpGet request = new HttpGet(url);
    HttpResponse response = null;
    int status = 0;
    try {
      response = httpClient.execute(request);
      status = response.getStatusLine().getStatusCode();
    } catch (IOException ex) {
      throw new DataRetrievalFailureException("HTTP failed with status: " + status, ex);
    }

    if (status == 404) {
      return Optional.empty();
    }
    //Convert Response Entity to string
    String jsonStr = null;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException ex) {
      logger.error("Failed to convert the json to string", ex);
    }
    return Optional.ofNullable(jsonStr);
  }

  /**
   * Borrow a HTTP client from the httpClientConnectionManager.
   *
   * @return a httpClient
   */
  private CloseableHttpClient getHttpClient() {
    return HttpClients.custom().setConnectionManager(httpClientConnectionManager)
        //prevents connectionManager shutdown when calling httpClient.close()
        .setConnectionManagerShared(true).build();
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(String s) {

  }

  @Override
  public void delete(IexQuote entity) {

  }

  @Override
  public void deleteAll(Iterable<? extends IexQuote> entities) {

  }

  @Override
  public void deleteAll() {

  }
}