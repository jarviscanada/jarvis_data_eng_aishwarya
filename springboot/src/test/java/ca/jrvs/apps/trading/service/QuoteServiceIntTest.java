package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.QuoteData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
//@Sql({"classpath:schema.sql"})
public class QuoteServiceIntTest {

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteDao quoteDao;

  @Before
  public void setup() {
    quoteDao.deleteAll();
  }

  @Test
  public void findIexQuoteByTicker() {
    //happy path
    IexQuote quote = quoteService.findIexQuoteByTicker("AAPL");
    assertEquals("AAPL", quote.getQuote().getSymbol());

    //sad path
    try {
      quote = quoteService.findIexQuoteByTicker("FB2");
      fail();
    } catch (IllegalArgumentException ex) {
      assertTrue(true);
    } catch (Exception ex) {
      fail();
    }
  }

  @Test
  public void saveQuotes() {
    List<String> tickers = Arrays.asList(new String[]{"FB", "AMD"});
    List<QuoteData> quoteData = quoteService.saveQuotes(tickers);
    assertEquals(2, quoteData.size());
    assertEquals("FB", quoteData.get(0).getTicker());
  }

  @Test
  public void saveQuote() {
    QuoteData quote = new QuoteData();
    quote.setTicker("AAPL");
    quote.setBidPrice(15.0);
    quote.setBidSize(15);
    quote.setAskPrice(15.5);
    quote.setAskSize(15);
    quote.setLastPrice(15.4);
    QuoteData quoteData = quoteService.saveQuote(quote);
    assertEquals("AAPL", quoteData.getTicker());
  }

  @Test
  public void findAllQuotes() {
    List<QuoteData> quoteDataList = quoteDao.findAll();
    assertEquals(0, quoteDataList.size());
    //assertEquals("FB", quoteDataList.get(0).getTicker());
  }

  @Test
  public void updateMarketData() {
    List<QuoteData> quoteDataList = quoteService.updateMarketData();
    System.out.println("MarketData updated");
    assertEquals(0, quoteDataList.size());
  }
}