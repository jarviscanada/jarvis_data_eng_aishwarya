package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import java.util.ArrayList;
import java.util.List;
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
public class SecurityOrderDao extends JdbcCrudDao<SecurityOrder> {

  private static final Logger logger = LoggerFactory.getLogger(SecurityOrderDao.class);

  private final String TABLE_NAME = "security_order";
  private final String ID_COLUMN = "id";

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public SecurityOrderDao(DataSource dataSource) {
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
  Class<SecurityOrder> getEntityClass() {
    return SecurityOrder.class;
  }

  @Override
  public int updateOne(SecurityOrder entity) {
    String updateSql = "UPDATE " + getTableName()
        + " SET account_id=?, status=?, ticker=?, size=?, price=?, notes=?"
        + " WHERE " + getIdColumnName() + "=?";
    return jdbcTemplate.update(updateSql, makeUpdateValue(entity));
  }

  private Object[] makeUpdateValue(SecurityOrder entity) {
    if (!existsById(entity.getId())) {
      throw new RuntimeException("trader not found:" + entity.getId());
    }
    Object[] values = new Object[]{entity.getAccountId(), entity.getStatus(), entity.getTicker(),
        entity.getSize(), entity.getPrice(), entity.getNotes(), entity.getId()};

    return values;
  }

  public List<SecurityOrder> findByAccountId(Integer accountId) {
    List<SecurityOrder> order = new ArrayList<>();
    String selectSql = "SELECT * FROM " + getTableName() + " WHERE account_id=?";
    try {
      order = getJdbcTemplate().query(selectSql,
          BeanPropertyRowMapper.newInstance(getEntityClass()), accountId);
    } catch (EmptyResultDataAccessException ex) {
      logger.debug("Can't find trader id: " + accountId, ex);
    }
    return order;
  }
}
