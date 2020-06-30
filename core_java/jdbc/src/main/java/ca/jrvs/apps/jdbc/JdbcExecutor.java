package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcExecutor {

  final Logger logger = LoggerFactory.getLogger(DatabaseConnectionManager.class);

  /**
   * This is main. Handles DAO and connections.
   *
   * @param args takes inputs from commandline
   */
  public static void main(String[] args) {
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hplussport",
        "postgres", "password");
    try {
      Connection connection = dcm.getConnection();
      Orderdao orderdao = new Orderdao(connection);
      Order order = orderdao.findById(1000);
      System.out.println(order);
      //Connection connection = dcm.getConnection();
      //Customerdao customerdao = new Customerdao(connection);
      //Customer customer = new Customer();
      //customer.setFirstName("George");
      //customer.setLastName("Washington");
      //customer.setEmail("george.washington@wh.gov");
      //customer.setPhone("(555) 555-6543");
      //customer.setAddress("1234 Main St");
      //customer.setCity("Mount Vernon");
      //customer.setState("VA");
      //customer.setZipCode("22121");
      //customerdao.create(customer);
    } catch (SQLException ex) {
      dcm.logger.error("Invalid input", ex);
      throw new RuntimeException(ex);
    }
  }
}