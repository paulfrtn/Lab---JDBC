package test;

import model.ConnectionDb;
import model.*;

import java.util.List;


/**
 *
 * @author Jean-Michel Busca
 */
public class Test {
  public static void main(String[] args) throws Exception {

    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    List<Employee> employees = employeeDao.getEmployees();

    System.out.println("Liste des employés :");
    for (Employee employee : employees) {
      System.out.println(employee);
    }

    employeeDao.raiseSalaryPS("' OR '1'='1", 1000);

  }
}
