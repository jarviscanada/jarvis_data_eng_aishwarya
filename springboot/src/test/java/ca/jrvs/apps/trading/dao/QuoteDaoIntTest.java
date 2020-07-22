package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.QuoteData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {

  @Autowired
  private QuoteDao quoteDao;

  private QuoteData savedQuote;


  @Before
  public void insertOne() {
    savedQuote = new QuoteData();
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setId("aapl");
    savedQuote.setLastPrice(10.1d);
    QuoteData saveQuote = quoteDao.save(savedQuote);
    System.out.println("saved Quote " + saveQuote.getTicker());
    savedQuote.setBidPrice(10.5d);
    savedQuote.setLastPrice(10.4d);
    QuoteData saveQuote2 = quoteDao.save(savedQuote);
    System.out.println("saved and updated Quote " + saveQuote2.getTicker());
  }

  @Test
  public void operation() {
    System.out.println("TEST");
  }

  @After
  public void deleteOne() {
    quoteDao.deleteById(savedQuote.getId());
    System.out.println("Deleted Quote " + savedQuote.getTicker());
  }
}