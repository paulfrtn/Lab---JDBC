package model;

import java.sql.*;

public class DataAccess {

  private Connection connection;

  public DataAccess(String url, String login, String password) throws
      SQLException {
    connection = DriverManager.getConnection(url, login, password);
    System.out.println("connected to " + url);
  }

}
