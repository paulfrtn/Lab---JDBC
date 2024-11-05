/*
* Membres du projet :
*
* - FORTUNA Paul-Alexandre
* - DING Armand
* - FEKLER David
* - CHANZY Alexandre
* */


package test;

import model.Query.QueryDaoImpl;
import model.Employee.*;
import model.Departement.*;

import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {

        //Appel des méthodes de la classe EmployeeDaoImpl et DepartementDaoImpl
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        DepartementDaoImpl departementDao = new DepartementDaoImpl();


        //Exercice 2
        // Récupération et affichage de la liste des employés
        List<Employee> employees = employeeDao.getEmployees();
        System.out.println("------------------Liste des employés sans requête préparée ---------------------");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        System.out.println("----------------------------------------------------------------------------------");

        //Exercice 3
        // Augmentation du salaire des employés ayant le job 'CLERK'
        employeeDao.raiseSalary("CLERK", 100);
        //Exemple d'injection SQL
        /*
        * Cette injection SQL permet d'augmenter le salaire de tous les employés de 1000
        * qu'importe la nature du job, donc pas besoin de connaître le nom des jobs ou même la structure
        * de la table pour effectuer cette opération.
        * */
        //Il faut décommenter la ligne suivante pour tester l'injection
        //employeeDao.raiseSalaryPS("' OR '1'='1", 250);

        //Exercice 4
        // Récupération et affichage de la liste des employés en utilisant une requête préparée

        System.out.println("------------------Liste des employés avec requête préparée ---------------------");
        List<Employee> employeesPS = employeeDao.getEmployeesPS();
        for (Employee employeePS : employees) {
            System.out.println(employeePS);
        }
        System.out.println("----------------------------------------------------------------------------------");

        //Augmentation du salaire des employés ayant le job 'CLERK' en utilisant une requête préparée
        //employeeDao.raiseSalaryPS("CLERK", 100);

        //On test une injection SQL une nouvelle fois
        /*
        * En testant cette injection, on remarque que l'opération ne s'effectue pas
        * car les paramètres sont bien définis et la requête est préparée.
        * L'injection est bloquée et aucun salaire n'est augmenté.
        * */
        //Il faut décommenter la ligne suivante pour tester l'injection
        //employeeDao.raiseSalaryPS("' OR '1'='1", 3000);


        //Exercice 5
        List<Department> departments = departementDao.getDepartments(null, null, null);
        System.out.println("------------------Liste des départements sans requête préparée (avec null partout) ---------------------");
        for (Department department : departments) {
            System.out.println(department);
        }
        List<Department> departments2 = departementDao.getDepartments(null, null, "NEW-YORK");
        System.out.println("----------------------------------------------------------------------------------");

        System.out.println("------------------Liste des départements sans requête préparée (avec null et location 'NEW-YORK') ---------------------");
        for (Department department : departments) {
            System.out.println(department);
        }
        System.out.println("----------------------------------------------------------------------------------");

        List<Department> departments3 = departementDao.getDepartments(null, "RESEARCH", "DALLAS");
        System.out.println("------------------Liste des départements sans requête préparée (avec null en ID, name 'RESEARCH' et location 'DALLAS') ---------------------");
        for (Department department : departments) {
            System.out.println(department);
        }
        System.out.println("----------------------------------------------------------------------------------");

        //Exercice 6
        //On récupère et affiche la liste des départements en utilisant une requête préparée
        QueryDaoImpl queryExecute = new QueryDaoImpl();
        List<String> results = queryExecute.executeStatement("SELECT ename, sal FROM EMP WHERE job = 'SALESMAN'");
        System.out.println("------------------Liste des employés ayant le job 'SALESMAN'---------------------");
        for (String result : results) {
            System.out.println(result);
        }
        System.out.println("----------------------------------------------------------------------------------");

        //On UPDATE le salaire des employés ayant le job 'CLERK' en retirant 150
        QueryDaoImpl statementExecute = new QueryDaoImpl();
        List<String> results2 = statementExecute.executeStatement("UPDATE EMP SET sal = sal - 150 WHERE job = 'CLERK'");
        System.out.println("------------------Baisse du salaire des 'CLERK' de 150---------------------");
        for (String result2 : results2) {
            System.out.println(result2);
        }
        System.out.println("----------------------------------------------------------------------------------");

    }
}
