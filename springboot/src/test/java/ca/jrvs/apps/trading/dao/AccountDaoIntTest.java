package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
public class AccountDaoIntTest {

  @Autowired
  private AccountDao accountDao;
  @Autowired
  private TraderDao traderDao;

  private Account savedAccount;
  private Trader savedTrader;

  @Before
  public void insertOne() {
    Trader trader = new Trader();
    trader.setFirstName("John");
    trader.setLastName("Jackson");
    trader.setCountry("Canada");
    trader.setDob(new Date(System.currentTimeMillis()));
    trader.setEmail("john_jackson01@email.com");
    savedTrader = traderDao.save(trader);

    Account account = new Account();
    account.setTraderId(1);
    account.setAmount(500.0);
    savedAccount = accountDao.save(account);

    assertEquals(account.getAmount(),savedAccount.getAmount());
    assertEquals(account.getTraderId(),savedAccount.getTraderId());
  }

  @After
  public void deleteOne() {
    accountDao.deleteById(savedAccount.getId());
    traderDao.deleteById(savedTrader.getId());
    System.out.println("Deleted");
  }

  @Test
  public void FindAllById() {
    List<Account> accounts = Lists
        .newArrayList(accountDao.findAllById(Arrays.asList(savedAccount.getId())));
    assertEquals(1, accounts.size());
    assertEquals(savedAccount.getTraderId(), accounts.get(0).getTraderId());
  }
}