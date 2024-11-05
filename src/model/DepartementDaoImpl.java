package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartementDaoImpl implements DepartementDao {

    // Méthode pour ajouter un département
    @Override
    public void addDepartement(DepartmentInfo departmentInfo) {
        String query = "INSERT INTO DEPT (deptno, dname, loc) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionDb.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, departmentInfo.getId());
            pstmt.setString(2, departmentInfo.getName());
            pstmt.setString(3, departmentInfo.getLocation());

            pstmt.executeUpdate();
            System.out.println("Department added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un département par ID
    @Override
    public void deleteDepartementById(int id) {
        String query = "DELETE FROM DEPT WHERE deptno = ?";

        try (Connection connection = ConnectionDb.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Department deleted successfully.");
            } else {
                System.out.println("Department not found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour un département
    @Override
    public void updateDepartement(DepartmentInfo departmentInfo) {
        String query = "UPDATE DEPT SET dname = ?, loc = ? WHERE deptno = ?";

        try (Connection connection = ConnectionDb.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, departmentInfo.getName());
            pstmt.setString(2, departmentInfo.getLocation());
            pstmt.setInt(3, departmentInfo.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Department updated successfully.");
            } else {
                System.out.println("Department not found with ID: " + departmentInfo.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer un département par ID
    @Override
    public DepartmentInfo getDepartementById(int id) {
        String query = "SELECT deptno, dname, loc FROM DEPT WHERE deptno = ?";
        DepartmentInfo departmentInfo = null;

        try (Connection connection = ConnectionDb.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int deptNo = rs.getInt("deptno");
                String name = rs.getString("dname");
                String location = rs.getString("loc");

                departmentInfo = new DepartmentInfo(deptNo, name, location);
            } else {
                System.out.println("Department not found with ID: " + id);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departmentInfo;
    }

    // Méthode pour récupérer tous les départements
    @Override
    public List<DepartmentInfo> getAllDepartements() {
        List<DepartmentInfo> departments = new ArrayList<>();
        String query = "SELECT deptno, dname, loc FROM DEPT";

        try (Connection connection = ConnectionDb.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int deptNo = rs.getInt("deptno");
                String name = rs.getString("dname");
                String location = rs.getString("loc");

                DepartmentInfo departmentInfo = new DepartmentInfo(deptNo, name, location);
                departments.add(departmentInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departments;
    }
}
