package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnectionManager {

  private final String hostname;
  private final String database;
  private final String username;
  private final String password;
  private final String url = "jdbc:postgresql://";
  final Logger logger = LoggerFactory.getLogger(DatabaseConnectionManager.class);

  /**
   * This method is a constructor which initializes data for postgresSQL connection.
   *
   * @param hostname hostname
   * @param database database
   * @param username username
   * @param password password
   */
  public DatabaseConnectionManager(String hostname, String database, String username,
      String password) {
    this.hostname = hostname;
    this.database = database;
    this.username = username;
    this.password = password;
  }

  /**
   * This method establish connection to postgresSQL.
   *
   * @return Connection
   * @throws SQLException if no input are invalid.
   */
  public Connection getConnection() throws SQLException {
    return DriverManager
        .getConnection(url + this.hostname + "/" + this.database, this.username, this.password);
  }
}