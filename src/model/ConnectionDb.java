package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {

    private static final String url = "jdbc:mysql://database-ece.cfii8uky06cj.eu-north-1.rds.amazonaws.com:3306/company"+"?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
    private static final String user = "admin";
    private static final String password = "TXSGywzIQlsWgLnqlEb0";

    // Variable pour stocker la connexion
    private static Connection connection = null;

    // Méthode pour obtenir la connexion
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    // Méthode pour fermer la connexion
    public static void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
            System.out.println("Connection closed");
        }
    }
}
