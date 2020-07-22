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

//  @Before
//  public void setup() {
//    quoteDao.deleteAll();
//  }

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
  public void updateMarketData() {
    quoteService.updateMarketData();
    System.out.println("MarketData updated");
  }

  @Test
  public void saveQuotes() {
    QuoteData quote1 = new QuoteData();
    quote1.setTicker("AAPL");
    quote1.setBidPrice(10.0);
    quote1.setBidSize(100);
    quote1.setAskPrice(10.5);
    quote1.setAskSize(100);
    quote1.setLastPrice(10.4);
    QuoteData quote2 = new QuoteData();
    quote2.setTicker("FB");
    quote2.setBidPrice(25.0);
    quote2.setBidSize(25);
    quote2.setAskPrice(25.5);
    quote2.setAskSize(25);
    quote2.setLastPrice(25.4);
    List<QuoteData> quoteData = new ArrayList<>();
    quoteData.add(quote1);
    quoteData.add(quote2);
    List<QuoteData> quotes = quoteDao.saveAll(quoteData);
    assertEquals(2, quotes.size());
    assertEquals("AAPL", quotes.get(0).getTicker());
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
    QuoteData quote1 = new QuoteData();
    quote1.setTicker("FB");
    quote1.setBidPrice(10.0);
    quote1.setBidSize(10);
    quote1.setAskPrice(10.5);
    quote1.setAskSize(10);
    quote1.setLastPrice(10.4);
    QuoteData quoteData = quoteDao.save(quote);
    QuoteData quoteData1 = quoteDao.save(quote1);
    assertEquals("AAPL", quoteData.getTicker());
  }

  @Test
  public void findAllQuotes() {
    List<QuoteData> quoteDataList = quoteDao.findAll();
    assertEquals(2, quoteDataList.size());
    assertEquals("AAPL", quoteDataList.get(0).getTicker());
  }
}