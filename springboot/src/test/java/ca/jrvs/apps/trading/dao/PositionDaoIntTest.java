package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.QuoteData;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
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
public class PositionDaoIntTest {

  @Autowired
  private PositionDao positionDao;

  @Autowired
  private SecurityOrderDao securityOrderDao;

  @Autowired
  private AccountDao accountDao;

  @Autowired
  private TraderDao traderDao;

  @Autowired
  private QuoteDao quoteDao;

  private SecurityOrder order;
  private Account savedAccount;
  private Trader savedTrader;


  @Before
  public void insertOne() {
    QuoteData quote1 = new QuoteData();
    quote1.setAskPrice(10d);
    quote1.setAskSize(10);
    quote1.setBidPrice(10.2d);
    quote1.setBidSize(10);
    quote1.setId("FB");
    quote1.setLastPrice(10.1d);
    quoteDao.save(quote1);
    QuoteData quote2 = new QuoteData();
    quote2.setAskPrice(20d);
    quote2.setAskSize(20);
    quote2.setBidPrice(20.2d);
    quote2.setBidSize(20);
    quote2.setId("AAPL");
    quote2.setLastPrice(20.1d);
    quoteDao.save(quote2);
    System.out.println("Quote created");

    Trader trader = new Trader();
    trader.setFirstName("John");
    trader.setLastName("Jackson");
    trader.setCountry("Canada");
    trader.setDob(new Date(System.currentTimeMillis()));
    trader.setEmail("john_jackson01@email.com");
    savedTrader = traderDao.save(trader);
    System.out.println("Trader created");

    Account account = new Account();
    account.setTraderId(1);
    account.setAmount(500.0);
    savedAccount = accountDao.save(account);
    System.out.println("Account created");

    SecurityOrder securityOrder1 = new SecurityOrder();
    securityOrder1.setAccountId(1);
    securityOrder1.setNotes("created");
    securityOrder1.setPrice(10.0);
    securityOrder1.setSize(10);
    securityOrder1.setStatus("FILLED");
    securityOrder1.setTicker("FB");
    order = securityOrderDao.save(securityOrder1);
    SecurityOrder securityOrder2 = new SecurityOrder();
    securityOrder2.setAccountId(1);
    securityOrder2.setNotes("created");
    securityOrder2.setPrice(10.0);
    securityOrder2.setSize(10);
    securityOrder2.setStatus("FILLED");
    securityOrder2.setTicker("AAPL");
    order = securityOrderDao.save(securityOrder2);
    System.out.println("security order created");
  }

  @After
  public void deleteOne() {
    securityOrderDao.deleteAll();
    accountDao.deleteById(savedAccount.getId());
    traderDao.deleteById(savedTrader.getId());
    quoteDao.deleteAll();
    System.out.println("Deleted");
  }

  @Test
  public void PositionIfo() {
    positionDao.ObtainPositionInfo();
    System.out.println("Created Position");
  }
}