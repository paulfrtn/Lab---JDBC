package model;

import model.ConnectionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {


    public EmployeeInfo getEmployeeById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        EmployeeInfo employeeInfo = null;
        try {
            connection = ConnectionDb.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employeeInfo = new EmployeeInfo(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getFloat("salary"), resultSet.getInt("department_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employeeInfo;
    }

    public List<EmployeeInfo> getAllEmployees() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<EmployeeInfo> employees = new ArrayList<EmployeeInfo>();
        try {
            connection = ConnectionDb.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM employee");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(new EmployeeInfo(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getFloat("salary"), resultSet.getInt("department_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employees;
    }
}