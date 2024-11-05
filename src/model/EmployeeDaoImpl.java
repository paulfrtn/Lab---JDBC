package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    public List<Employee> getAllEmployees() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Employee> employees = new ArrayList<>();
        try {
            connection = ConnectionDb.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM employee";
            resultSet = statement.executeQuery(query);
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
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employees;
    }
}