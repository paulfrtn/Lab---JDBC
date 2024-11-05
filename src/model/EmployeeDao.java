package model;

import java.util.List;

public interface EmployeeDao {

    public EmployeeInfo getEmployeeById(int id);

    public List<EmployeeInfo> getAllEmployees();
}
