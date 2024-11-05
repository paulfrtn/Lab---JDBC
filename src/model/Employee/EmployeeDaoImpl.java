/*
 * Membres du projet :
 *
 * - FORTUNA Paul-Alexandre
 * - DING Armand
 * - FEKLER David
 * - CHANZY Alexandre
 */

package model.Employee;

import model.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM EMP";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // On établit la connexion à la base de données
            connection = ConnectionDb.getConnection();
            statement = connection.createStatement();

            // On exécute la requête SQL
            resultSet = statement.executeQuery(query);

            // On tente de parcourir le ResultSet pour créer des objets Employee
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getInt("eid"),
                        resultSet.getString("ename"),
                        resultSet.getString("job"),
                        resultSet.getInt("mgr"),
                        resultSet.getDate("hired"),
                        resultSet.getFloat("sal"),
                        resultSet.getFloat("comm"),
                        resultSet.getInt("did")
                ));
            }

        } catch (SQLException e) {
            // Gestion des exceptions SQL
            System.err.println("Erreur lors de la récupération des employés : " + e.getMessage());
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

        return employees;
    }

    public List<Employee> getEmployeesPS() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM EMP";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // On établit la connexion à la base de données
            connection = ConnectionDb.getConnection();
            preparedStatement = connection.prepareStatement(query);

            // ON exécute la requête SQL
            resultSet = preparedStatement.executeQuery();

            // On parcours notre ResultSet pour créer des objets Employee
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getInt("eid"),
                        resultSet.getString("ename"),
                        resultSet.getString("job"),
                        resultSet.getInt("mgr"),
                        resultSet.getDate("hired"),
                        resultSet.getFloat("sal"),
                        resultSet.getFloat("comm"),
                        resultSet.getInt("did")
                ));
            }

        } catch (SQLException e) {
            // Gestion des exceptions SQL
            System.err.println("Erreur lors de la récupération des employés (PreparedStatement) : " + e.getMessage());
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

        return employees;
    }

    public boolean raiseSalary(String job, float amount) {
        String query = "UPDATE EMP SET sal = sal + " + amount + " WHERE job = '" + job + "'";
        boolean success = false;

        Connection connection = null;
        Statement statement = null;

        try {
            // On établit la connexion à la base de données
            connection = ConnectionDb.getConnection();
            statement = connection.createStatement();

            // Affichage de la requête exécutée (utile pour le débogage)
            System.out.println("Exécution de la requête : " + query);

            // Exécution de la mise à jour
            int rowsUpdated = statement.executeUpdate(query);
            success = rowsUpdated > 0;

        } catch (SQLException e) {
            // Gestion des exceptions SQL
            System.err.println("Erreur lors de la mise à jour des salaires : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fermeture des ressources dans l'ordre inverse de leur ouverture

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

        return success;
    }

    public boolean raiseSalaryPS(String job, float amount) {
        String query = "UPDATE EMP SET sal = sal + ? WHERE job = ?";
        boolean success = false;

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // On ébtablit la connexion à la base de données
            connection = ConnectionDb.getConnection();
            preparedStatement = connection.prepareStatement(query);

            // On définit des paramètres du PreparedStatement
            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, job);

            // On affiche notre requête exacte pour vérifier que il n'y a pas de bug
            System.out.println("Exécution de la requête : UPDATE EMP SET sal = sal + " + amount + " WHERE job = '" + job + "'");

            // On met à jour
            int rowsUpdated = preparedStatement.executeUpdate();
            success = rowsUpdated > 0;

        } catch (SQLException e) {
            // Gestion des exceptions SQL
            System.err.println("Erreur lors de la mise à jour des salaires (PreparedStatement) : " + e.getMessage());
            e.printStackTrace();
        } finally {
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

        return success;
    }
}
