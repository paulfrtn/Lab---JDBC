/*
 * Membres du projet :
 *
 * - FORTUNA Paul-Alexandre
 * - DING Armand
 * - FEKLER David
 * - CHANZY Alexandre
 * */

package model.Employee;

import java.util.List;

public interface EmployeeDao {
    //Interface pour les méthodes de récupération des employés et augmentation de salaire avec ou sans requêtes préparées
    public List<Employee> getEmployees();
    public List<Employee> getEmployeesPS();
    public boolean raiseSalary(String job, float amount);
    public boolean raiseSalaryPS(String job, float amount);
}
