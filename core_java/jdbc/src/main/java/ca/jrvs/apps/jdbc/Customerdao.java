package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customerdao extends DataAccessObject<Customer> {

  final Logger logger = LoggerFactory.getLogger(DataAccessObject.class);
  private static final String INSERT = "INSERT INTO Customer (first_name, last_name,"
      + "email, phone, address, city, state, zipcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

  public Customerdao(Connection connection) {
    super(connection);
  }

  @Override
  public Customer findById(long id) {
    return null;
  }

  @Override
  public List<Customer> findALl() {
    return null;
  }

  @Override
  public Customer update(Customer dto) {
    return null;
  }

  @Override
  public Customer create(Customer dto) {
    try (PreparedStatement statement = this.connection.prepareStatement(INSERT);) {
      statement.setString(1, dto.getFirstName());
      statement.setString(2, dto.getLastName());
      statement.setString(3, dto.getEmail());
      statement.setString(4, dto.getPhone());
      statement.setString(5, dto.getAddress());
      statement.setString(6, dto.getCity());
      statement.setString(7, dto.getState());
      statement.setString(8, dto.getZipCode());
      statement.execute();
      return null;
    } catch (SQLException ex) {
      this.logger.error("Invalid input", ex);
      throw new RuntimeException(ex);
    }
  }

  @Override
  public void delete(long id) {

  }
}