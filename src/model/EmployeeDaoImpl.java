package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    // Méthode utilisant Statement
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM EMP";

        try (Connection connection = ConnectionDb.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

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
            e.printStackTrace();
        }

        return employees;
    }

    // Méthode utilisant PreparedStatement
    public List<Employee> getEmployeesPS() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM EMP";

        try (Connection connection = ConnectionDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

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
            e.printStackTrace();
        }

        return employees;
    }

    public boolean raiseSalary(String job, float amount) {
        Connection connection = null;
        Statement statement = null;
        boolean success = false;

        try {
            connection = ConnectionDb.getConnection();
            statement = connection.createStatement();

            // Créer la requête SQL
            String query = "UPDATE EMP SET sal = sal + " + amount + " WHERE job = '" + job + "'";
            System.out.println("Executing query: " + query);

            // Exécuter la requête
            int rowsUpdated = statement.executeUpdate(query);
            success = rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }
    public boolean raiseSalaryPS(String job, float amount) {
        String query = "UPDATE EMP SET sal = sal + ? WHERE job = ?";
        boolean success = false;

        try (Connection connection = ConnectionDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Définir les paramètres du PreparedStatement
            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, job);

            // Exécuter la requête
            int rowsUpdated = preparedStatement.executeUpdate();
            success = rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}

