package test;

import model.ConnectionDb;
import model.*;

import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {

        QueryDaoImpl queryDao = new QueryDaoImpl();
        List<String> results = queryDao.executeStatement("SELECT ename, sal FROM EMP WHERE job = 'CLERK'");
        for (String result : results) {
            System.out.println(result);
        }


//    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
//    List<Employee> employees = employeeDao.getEmployees();
//
//
//
//    DepartementDaoImpl departementDao = new DepartementDaoImpl();
//    List<Department> departments = departementDao.getDepartments(null, null, null);
//
//    System.out.println("Liste des départements :");
//    for (Department department : departments) {
//      System.out.println(department);
//    }

//    System.out.println("Liste des employés :");
//    for (Employee employee : employees) {
//      System.out.println(employee);
//    }
//
//    employeeDao.raiseSalaryPS("' OR '1'='1", 1000);

    }
}
