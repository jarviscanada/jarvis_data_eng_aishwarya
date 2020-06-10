package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.DriverManager;

public class DatabaseConnectionManager {

  private String hostname;
  private String database;
  private String username;
  private String password;
  private final String url="jdbc:postgresql://";
  final Logger logger= LoggerFactory.getLogger(DatabaseConnectionManager.class);

  public DatabaseConnectionManager(String hostname, String database, String username, String password) {
    this.hostname=hostname;
    this.database=database;
    this.username=username;
    this.password=password;
  }

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url+this.hostname+"/"+this.database,this.username,this.password);
  }
}