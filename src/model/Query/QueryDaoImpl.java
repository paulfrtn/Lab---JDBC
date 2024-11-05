/*
 * Membres du projet :
 *
 * - FORTUNA Paul-Alexandre
 * - DING Armand
 * - FEKLER David
 * - CHANZY Alexandre
 */

package model.Query;

import model.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryDaoImpl implements QueryDao {

    public List<String> executeQuery(String query) {
        // On initialise la liste qui contiendra les résultats de la requête
        List<String> result = new ArrayList<>();

        // Déclaration des objets nécessaires à la connexion et à l'exécution de la requête
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // On récupère la connexion à la base de données
            connection = ConnectionDb.getConnection();

            // On crée d'un Statement pour exécuter la requête SQL
            statement = connection.createStatement();

            // On exécute la requête et on récupère les résultats
            resultSet = statement.executeQuery(query);

            // On récupère les métadonnées du ResultSet pour obtenir les noms des colonnes
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // On met en place l'entête
            StringBuilder header = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                header.append(rsmd.getColumnName(i));
                if (i < columnCount) {
                    header.append("\t");
                }
            }
            result.add(header.toString()); // Ajout de l'en-tête à la liste des résultats

            // On tente de parcourir chaque ligne du ResultSet pour récupérer les données
            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(resultSet.getString(i));
                    if (i < columnCount) {
                        row.append("\t");
                    }
                }
                result.add(row.toString()); // Ajout de la ligne à la liste des résultats
            }

        } catch (SQLException e) {
            // Affichage de l'erreur en cas d'exception lors de l'exécution de la requête
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
            e.printStackTrace();
            result.add("Erreur lors de l'exécution de la requête : " + e.getMessage());
        } finally {
            // Bloc finally pour assurer la fermeture des ressources ouvertes

            // Fermeture du ResultSet
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture du ResultSet : " + e.getMessage());
                e.printStackTrace();
            }

            // Fermeture du Statement
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture du Statement : " + e.getMessage());
                e.printStackTrace();
            }

            // Fermeture de la Connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }

    public List<String> executeStatement(String statementStr) {
        // On initilaise de la liste qui contiendra les résultats de l'instruction
        List<String> result = new ArrayList<>();

        // On déclare nos objets nécessaires à la connexion et à l'exécution de l'instruction
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // On établit la connexion à la base de données
            connection = ConnectionDb.getConnection();

            // On crée un Statement pour exécuter l'instruction SQL
            statement = connection.createStatement();

            // Exécution de l'instruction SQL
            boolean hasResultSet = statement.execute(statementStr);

            if (hasResultSet) {
                // Si l'instruction retourne un ResultSet (une reqête SELECT)
                resultSet = statement.getResultSet();

                // On récupère les métadonnées du ResultSet pour obtenir les noms des colonnes
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnCount = rsmd.getColumnCount();

                // On prépare l'en-tête du résultat
                StringBuilder header = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    header.append(rsmd.getColumnName(i));
                    if (i < columnCount) {
                        header.append("\t");
                    }
                }
                result.add(header.toString()); // Ajout de l'en-tête à la liste des résultats

                // On essaye de parcourir chaque ligne du ResultSet pour récupérer les données
                while (resultSet.next()) {
                    StringBuilder row = new StringBuilder();
                    for (int i = 1; i <= columnCount; i++) {
                        row.append(resultSet.getString(i));
                        if (i < columnCount) {
                            row.append("\t");
                        }
                    }
                    result.add(row.toString()); // On ajoute de la ligne à la liste des résultats
                }
            } else {
                // Si l'instruction ne retourne pas de ResultSet (INSERT, UPDATE, DELETE)
                int updateCount = statement.getUpdateCount();
                result.add("Nombre de tuples affectés : " + updateCount);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'exécution de l'instruction : " + e.getMessage());
            e.printStackTrace();
            result.add("Erreur lors de l'exécution de l'instruction : " + e.getMessage());
        } finally {
            // Bloc finally pour assurer les fermetures

            // Fermeture du ResultSet
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture du ResultSet : " + e.getMessage());
                e.printStackTrace();
            }

            // Fermeture du Statement
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture du Statement : " + e.getMessage());
                e.printStackTrace();
            }

            // Fermeture de la Connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }
}
