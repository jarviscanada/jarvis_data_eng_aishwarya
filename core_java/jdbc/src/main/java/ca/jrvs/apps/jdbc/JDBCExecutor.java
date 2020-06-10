package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCExecutor {
  final Logger logger = LoggerFactory.getLogger(DatabaseConnectionManager.class);
  public static void main(String[] args) {
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost","hplussport","postgres","password");
    try{
      Connection connection = dcm.getConnection();
      OrderDAO orderDAO = new OrderDAO(connection);
      Order order = orderDAO.findById(1000);
      System.out.println(order);
//      Connection connection = dcm.getConnection();
//      CustomerDAO customerDAO = new CustomerDAO(connection);
//      Customer customer = new Customer();
//      customer.setFirstName("George");
//      customer.setLastName("Washington");
//      customer.setEmail("george.washington@wh.gov");
//      customer.setPhone("(555) 555-6543");
//      customer.setAddress("1234 Main St");
//      customer.setCity("Mount Vernon");
//      customer.setState("VA");
//      customer.setZipCode("22121");
//
//      customerDAO.create(customer);
    }catch(SQLException ex){
      dcm.logger.error(ex.getMessage(),ex);
      throw new RuntimeException(ex);
      //ex.printStackTrace();
    }
  }
}