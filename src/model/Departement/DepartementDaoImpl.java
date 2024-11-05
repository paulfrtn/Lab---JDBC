/*
 * Membres du projet :
 *
 * - FORTUNA Paul-Alexandre
 * - DING Armand
 * - FEKLER David
 * - CHANZY Alexandre
 */

package model.Departement;

import model.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartementDaoImpl implements DepartementDao {

    public List<Department> getDepartments(Integer id, String name, String location) {
        // On crée de la liste qui prendra les départements
        List<Department> departments = new ArrayList<>();

        // Construction dynamique de la requête SQL en fonction des critères fournis
        StringBuilder query = new StringBuilder("SELECT * FROM DEPT WHERE 1=1");

        // On crée des conditions à la requête au cas où les paramètres ne sont pas null
        if (id != null) {
            query.append(" AND did = ").append(id);
        }
        if (name != null) {
            query.append(" AND dname = '").append(name).append("'");
        }
        if (location != null) {
            query.append(" AND dloc = '").append(location).append("'");
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // On établit notre connexion à la base de données
            connection = ConnectionDb.getConnection();
            // On crée d'un Statement pour exécuter la requête SQL
            statement = connection.createStatement();
            // On exécute la requête et obtention des résultats dans un ResultSet
            resultSet = statement.executeQuery(query.toString());

            // On parcours le ResultSet pour créer des objets Department
            while (resultSet.next()) {
                departments.add(new Department(
                        resultSet.getInt("did"),
                        resultSet.getString("dname"),
                        resultSet.getString("dloc")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des départements : " + e.getMessage());
            e.printStackTrace();
        } finally {
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
        return departments;
    }

    public List<Department> getDepartmentsPS(Integer id, String name, String location) {
        // On initialise la liste qui prendra les départements
        List<Department> departments = new ArrayList<>();

        // Requête SQL avec des paramètres pour éviter les injections SQL
        String query = "SELECT * FROM DEPT WHERE (did = ? OR ? IS NULL) AND (dname = ? OR ? IS NULL) AND (dloc = ? OR ? IS NULL)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // On établit la connexion à la base de données
            connection = ConnectionDb.getConnection();
            // On crée un PreparedStatement pour exécuter la requête SQL
            preparedStatement = connection.prepareStatement(query);

            // Définition des paramètres du PreparedStatement
            preparedStatement.setObject(1, id, Types.INTEGER);
            preparedStatement.setObject(2, id, Types.INTEGER);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, name);
            preparedStatement.setString(5, location);
            preparedStatement.setString(6, location);

            // Affichage de la requête exécutée avec les paramètres pour vérification
            System.out.println("Exécution de la requête préparée avec les paramètres : " +
                    "did=" + id + ", dname='" + name + "', dloc='" + location + "'");

            // On exécute la requête et récupère les résultats dans ResultSet
            resultSet = preparedStatement.executeQuery();

            // On essaye de parcourir du ResultSet pour créer des objets Department
            while (resultSet.next()) {
                departments.add(new Department(
                        resultSet.getInt("did"),
                        resultSet.getString("dname"),
                        resultSet.getString("dloc")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des départements (PreparedStatement) : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fermeture du ResultSet
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture du ResultSet : " + e.getMessage());
                e.printStackTrace();
            }

            // Fermeture du PreparedStatement
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture du PreparedStatement : " + e.getMessage());
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
        return departments;
    }
}
