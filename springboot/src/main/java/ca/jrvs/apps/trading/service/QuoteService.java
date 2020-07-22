package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.QuoteData;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class QuoteService {

  private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);


  private QuoteDao quoteDao;
  private MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
    this.quoteDao = quoteDao;
    this.marketDataDao = marketDataDao;
  }

  /**
   * Find an IexQuote.
   *
   * @param ticker id
   * @return IexQuote object
   * @throws IllegalArgumentException if ticker is invalid
   */
  public IexQuote findIexQuoteByTicker(String ticker) {
    return marketDataDao.findById(ticker)
        .orElseThrow(() -> new IllegalArgumentException(ticker + "is invalid"));
  }

  /**
   * Update quote table against IEX source.
   * - get all quotes from the database
   * - foreach ticker get iexQuote
   * - convert iexQuote to quote entity
   * - persist quote to database
   *
   * @throws org.springframework.dao.DataAccessException if unable to retrieve data
   * @throws IllegalArgumentException for invalid input or ticker is not found from IEX
   */
  public void updateMarketData() {
    //Getting all quotes from the database
    List<QuoteData> quotes = quoteDao.findAll();

    //Getting IexQuote for each ticker
    List<IexQuote> iexQuotes = quotes.stream().map(quote -> findIexQuoteByTicker(quote.getTicker()))
        .collect(Collectors.toList());

    //Converting IeXQuote to quote Entity
    List<QuoteData> quoteFromIex = iexQuotes.stream().map(QuoteService::buildQuoteFromTexQuote)
        .collect(Collectors.toList());

    //persist quote to database
    quoteDao.saveAll(quoteFromIex);

  }

  /**
   * Helper method. Map a IexQuote to a Quote entity. Note: `iexQuote.getLatestPrice() == null` if
   * the stock market is closed. Make sure set a default value for number field(s).
   */
  protected static QuoteData buildQuoteFromTexQuote(IexQuote iexQuote) {
    QuoteData quote = new QuoteData();
    quote.setTicker(iexQuote.getQuote().getSymbol());
    quote.setLastPrice(Optional.of(iexQuote.getQuote().getLatestPrice()).orElse(0.0));
    quote.setAskPrice(Optional.of(iexQuote.getQuote().getIexAskPrice()).orElse(0.0));
    quote.setAskSize(Optional.of(iexQuote.getQuote().getIexAskSize()).orElse(0));
    quote.setBidPrice(Optional.of(iexQuote.getQuote().getIexBidPrice()).orElse(0.0));
    quote.setBidSize(Optional.of(iexQuote.getQuote().getIexBidSize()).orElse(0));
    return quote;
  }
}
