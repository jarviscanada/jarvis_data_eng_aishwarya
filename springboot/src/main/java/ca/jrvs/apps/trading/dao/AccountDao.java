package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao extends JdbcCrudDao<Account> {

  private static final Logger logger = LoggerFactory.getLogger(AccountDao.class);

  private final String TABLE_NAME = "account";
  private final String ID_COLUMN = "id";

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public AccountDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
        .usingGeneratedKeyColumns(ID_COLUMN);
  }

  @Override
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  @Override
  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return simpleJdbcInsert;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return ID_COLUMN;
  }

  @Override
  Class<Account> getEntityClass() {
    return Account.class;
  }

  @Override
  public int updateOne(Account entity) {
    String updateSql = "UPDATE " + getTableName() + " SET trader_id=?, amount=?"
        + " WHERE " + getIdColumnName() + "=?";
    return jdbcTemplate.update(updateSql, makeUpdateValue(entity));
  }

  private Object[] makeUpdateValue(Account entity) {
    if (!existsById(entity.getId())) {
      throw new RuntimeException("trader not found:" + entity.getId());
    }
    Object[] values = new Object[]{entity.getTraderId(), entity.getAmount(), entity.getId()};

    return values;
  }

  public Account findByTraderId(Integer traderId) {
    Account account = new Account();
    String selectSql = "SELECT * FROM " + getTableName() + " WHERE trader_id=?";
    try {
      account = getJdbcTemplate().queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(getEntityClass()), traderId);
    } catch (EmptyResultDataAccessException ex) {
      logger.debug("Can't find trader id: " + traderId, ex);
    }
    return account;
  }

  public Account updateAccountById(Integer id, Double amount) {
    String updateSql = "UPDATE " + getTableName() + " SET amount=?"
        + " WHERE " + getIdColumnName() + "=?";

    int update = jdbcTemplate.update(updateSql, amount, id);
    Optional<Account> account = findById(id);
    return account.get();
  }
}
