/*
 * Membres du projet :
 *
 * - FORTUNA Paul-Alexandre
 * - DING Armand
 * - FEKLER David
 * - CHANZY Alexandre
 * */

package model.Query;

import model.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryDaoImpl implements QueryDao {

    public List<String> executeQuery(String query) {
        List<String> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionDb.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Récupération des noms des colonnes
            StringBuilder header = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                header.append(rsmd.getColumnName(i));
                if (i < columnCount) header.append("\t");
            }
            result.add(header.toString());

            // Récupération des données
            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(resultSet.getString(i));
                    if (i < columnCount) row.append("\t");
                }
                result.add(row.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            result.add("Erreur lors de l'exécution de la requête : " + e.getMessage());
        } finally {
            // Fermeture des ressources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                // Fermer la connexion si nécessaire
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public List<String> executeStatement(String statementStr) {
        List<String> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionDb.getConnection();
            statement = connection.createStatement();
            boolean hasResultSet = statement.execute(statementStr);

            if (hasResultSet) {
                // C'est une requête SELECT
                resultSet = statement.getResultSet();
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnCount = rsmd.getColumnCount();

                // Récupération des noms des colonnes
                StringBuilder header = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    header.append(rsmd.getColumnName(i));
                    if (i < columnCount) header.append("\t");
                }
                result.add(header.toString());

                // Récupération des données
                while (resultSet.next()) {
                    StringBuilder row = new StringBuilder();
                    for (int i = 1; i <= columnCount; i++) {
                        row.append(resultSet.getString(i));
                        if (i < columnCount) row.append("\t");
                    }
                    result.add(row.toString());
                }
            } else {
                // C'est une mise à jour (INSERT, UPDATE, DELETE)
                int updateCount = statement.getUpdateCount();
                result.add("Nombre de tuples affectés : " + updateCount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            result.add("Erreur lors de l'exécution de l'instruction : " + e.getMessage());
        } finally {
            // Fermeture des ressources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                // Fermer la connexion si nécessaire
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
