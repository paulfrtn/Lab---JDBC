package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {

    private static final String url = "jdbc:mysql:/database-ece.cfii8uky06cj.eu-north-1.rds.amazonaws.com/company"; // URL de la base de données
    private static final String user = "admin"; // Nom d'utilisateur de la base de données
    private static final String password = "TXSGywzIQlsWgLnqlEb0"; // Mot de passe de la base de données

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password); // Retourne la connexion établie avec les informations d'URL, d'utilisateur et de mot de passe
    }
}