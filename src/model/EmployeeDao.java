package model;

import java.util.List;

public interface EmployeeDao {
    public List<Employee> getEmployees();
    public List<Employee> getEmployeesPS();
    public boolean raiseSalary(String job, float amount);
    public boolean raiseSalaryPS(String job, float amount);
}
