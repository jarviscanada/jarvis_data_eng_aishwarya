package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.QuoteData;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
public class TraderAccountServiceIntTest {

  private TraderAccountView savedView;

  @Autowired
  private TraderAccountService traderAccountService;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;
  @Autowired
  private QuoteDao quoteDao;
  @Autowired
  private SecurityOrderDao securityOrderDao;

  private Trader trader;
  private SecurityOrder order;
  private List<SecurityOrder> orderList = new ArrayList<>();

  @Before
  public void createTraderAndAccount() {

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

    trader = new Trader();
    trader.setFirstName("John");
    trader.setLastName("Jackson");
    trader.setCountry("Canada");
    trader.setDob(new Date(System.currentTimeMillis()));
    trader.setEmail("john_jackson01@email.com");
    savedView = traderAccountService.createTraderAndAccount(trader);
    assertEquals(trader.getFirstName(), savedView.getTrader().getFirstName());
    assertEquals(trader.getId(), savedView.getAccount().getTraderId());

    SecurityOrder securityOrder1 = new SecurityOrder();
    securityOrder1.setAccountId(1);
    securityOrder1.setNotes("created");
    securityOrder1.setPrice(10.0);
    securityOrder1.setSize(10);
    securityOrder1.setStatus("FILLED");
    securityOrder1.setTicker("FB");
    order = securityOrderDao.save(securityOrder1);
    orderList.add(order);
    SecurityOrder securityOrder2 = new SecurityOrder();
    securityOrder2.setAccountId(1);
    securityOrder2.setNotes("created");
    securityOrder2.setPrice(10.0);
    securityOrder2.setSize(10);
    securityOrder2.setStatus("FILLED");
    securityOrder2.setTicker("AAPL");
    order = securityOrderDao.save(securityOrder2);
    System.out.println("security order created");
    orderList.add(order);
  }

  @Test
  public void depositAndWithrawal() {
    savedView.getAccount().setAmount(100.0);
    Account account = traderAccountService.deposit(trader.getId(), 100.0);
    assertEquals(savedView.getAccount().getAmount(), account.getAmount());

    savedView.getAccount().setAmount(0.0);
    account = traderAccountService.withdraw(trader.getId(), 100.0);
    assertEquals(savedView.getAccount().getAmount(), account.getAmount());
  }

  @After
  public void deleteTraderById() {
    try {
      traderAccountService.deleteTraderById(trader.getId());
      fail();
    } catch (IllegalArgumentException ex) {
      assertTrue(true);
    }
    orderList.forEach(order -> {
      order.setSize(0);
      securityOrderDao.updateOne(order);
    });
    traderAccountService.deleteTraderById(trader.getId());
    System.out.println("Deleted");
  }
}