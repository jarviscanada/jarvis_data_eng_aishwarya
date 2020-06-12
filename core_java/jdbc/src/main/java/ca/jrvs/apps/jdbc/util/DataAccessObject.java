package ca.jrvs.apps.jdbc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DataAccessObject<T extends DataTransferObject> {

  final Logger logger = LoggerFactory.getLogger(DataTransferObject.class);
  protected final Connection connection;
  protected static final  String LAST_VAL = "SELECT last_value FROM ";
  protected static final String CUSTOMER_SEQUENCE = "hp_customer_seq";

  public DataAccessObject(Connection connection) {
    super();
    this.connection = connection;
  }

  public abstract T findById(long id);

  public abstract List<T> findALl();

  public abstract T update(T dto);

  public abstract T create(T dto);

  public abstract void delete(long id);

  protected int getLastVal(String sequence) {
    int key = 0;
    String sql = LAST_VAL + sequence;
    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        key = rs.getInt(1);
      }
      return key;
    } catch (SQLException ex) {
      logger.error("Invalid input", ex);
      throw new RuntimeException(ex);
    }
  }
}