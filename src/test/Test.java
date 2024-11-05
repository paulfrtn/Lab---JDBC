package test;

import java.util.Arrays;
import model.DataAccess;
import model.ConnectionDb;

/**
 *
 * @author Jean-Michel Busca
 */
public class Test {

  /**
   * @param args the command line arguments
   *
   * @throws java.lang.Exception
   */
  public static void main(String[] args) throws Exception {

// Test ConnectionDb
    ConnectionDb connectionDb = new ConnectionDb();
    System.out.println("ConnectionDb: OK");
  }
}
