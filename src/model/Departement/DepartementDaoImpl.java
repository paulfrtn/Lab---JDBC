/*
 * Membres du projet :
 *
 * - FORTUNA Paul-Alexandre
 * - DING Armand
 * - FEKLER David
 * - CHANZY Alexandre
 * */

package model.Departement;

import model.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartementDaoImpl implements DepartementDao {

    // Implémentation avec Statement
    @Override
    public List<Department> getDepartments(Integer id, String name, String location) {
        List<Department> departments = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM DEPT WHERE 1=1");

        // Construction de la requête en fonction des critères
        if (id != null) {
            query.append(" AND did = ").append(id);
        }
        if (name != null) {
            query.append(" AND dname = '").append(name).append("'");
        }
        if (location != null) {
            query.append(" AND dloc = '").append(location).append("'");
        }

        try (Connection connection = ConnectionDb.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query.toString())) {

            while (resultSet.next()) {
                departments.add(new Department(
                        resultSet.getInt("did"),
                        resultSet.getString("dname"),
                        resultSet.getString("dloc")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departments;
    }

    // Implémentation avec PreparedStatement
    @Override
    public List<Department> getDepartmentsPS(Integer id, String name, String location) {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM DEPT WHERE (did = ? OR ? IS NULL) AND (dname = ? OR ? IS NULL) AND (dloc = ? OR ? IS NULL)";

        try (Connection connection = ConnectionDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Définir les paramètres en fonction des critères
            preparedStatement.setObject(1, id, Types.INTEGER);
            preparedStatement.setObject(2, id, Types.INTEGER);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, name);
            preparedStatement.setString(5, location);
            preparedStatement.setString(6, location);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    departments.add(new Department(
                            resultSet.getInt("did"),
                            resultSet.getString("dname"),
                            resultSet.getString("dloc")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departments;
    }
}
