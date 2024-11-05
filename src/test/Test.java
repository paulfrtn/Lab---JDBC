package test;

import model.ConnectionDb;

/**
 *
 * @author Jean-Michel Busca
 */
public class Test {
  public static void main(String[] args) throws Exception {

    // Test ConnectionDb
    ConnectionDb connectionDb = new ConnectionDb();
    System.out.println("ConnectionDb: OK");

    // Test ConnectionDb.getConnection()
    connectionDb.getConnection();
    System.out.println("ConnectionDb.getConnection(): OK");

    // Test ConnectionDb.close()
    connectionDb.close();

  }
}
