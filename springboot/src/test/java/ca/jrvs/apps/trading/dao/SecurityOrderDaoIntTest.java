package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.QuoteData;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.util.Lists;
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
public class SecurityOrderDaoIntTest {

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
  private QuoteData savedQuote;

  @Before
  public void insertOne() {
    QuoteData quote = new QuoteData();
    quote.setAskPrice(10d);
    quote.setAskSize(10);
    quote.setBidPrice(10.2d);
    quote.setBidSize(10);
    quote.setId("AAPL");
    quote.setLastPrice(10.1d);
    savedQuote = quoteDao.save(quote);
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

    SecurityOrder securityOrder = new SecurityOrder();
    securityOrder.setAccountId(1);
    securityOrder.setNotes("created");
    securityOrder.setPrice(10.0);
    securityOrder.setSize(10);
    securityOrder.setStatus("SUCCESS");
    securityOrder.setTicker("AAPL");
    order = securityOrderDao.save(securityOrder);
    assertEquals("AAPL", order.getTicker());
    assertEquals("SUCCESS", order.getStatus());
  }

  @After
  public void deleteOne() {
    securityOrderDao.deleteById(order.getId());
    accountDao.deleteById(savedAccount.getId());
    traderDao.deleteById(savedTrader.getId());
    quoteDao.deleteById(savedQuote.getId());
    System.out.println("Deleted");
  }

  @Test
  public void FindAllById() {
    List<SecurityOrder> orders = Lists
        .newArrayList(securityOrderDao.findAllById(Arrays.asList(order.getId())));
    assertEquals(1, orders.size());
    assertEquals(order.getAccountId(), orders.get(0).getAccountId());
  }

}