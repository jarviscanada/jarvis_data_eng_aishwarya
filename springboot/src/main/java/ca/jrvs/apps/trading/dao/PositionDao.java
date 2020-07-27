package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDao {

  private static final Logger logger = LoggerFactory.getLogger(SecurityOrderDao.class);

  private final String TABLE_NAME = "security_order";
  private final String COLUMN = "status";
  private final String GROUP = "account_id, ticker";

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public PositionDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public void ObtainPositionInfo() {
    String selectSql =
        "SELECT account_id, ticker, sum(size) AS position FROM " + TABLE_NAME + " WHERE " + COLUMN
            + "='FILLED' GROUP BY " + GROUP;
    jdbcTemplate.execute(selectSql);
  }

  public List<Position> getPositions(Integer id) {
    String selectSql =
        "SELECT account_id, ticker, sum(size) AS position FROM " + TABLE_NAME
            + " WHERE account_id=? GROUP BY " + GROUP;
    ;
    List<Position> positions = jdbcTemplate.query(selectSql,
        BeanPropertyRowMapper.newInstance(Position.class), id);

    return positions;
  }
}