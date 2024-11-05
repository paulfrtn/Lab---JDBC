/*
 * Membres du projet :
 *
 * - FORTUNA Paul-Alexandre
 * - DING Armand
 * - FEKLER David
 * - CHANZY Alexandre
 * */

package model.Departement;

import java.util.List;

public interface DepartementDao {
    //Interface pour les méthodes de récupération des départements avec ou sans requêtes préparées
    List<Department> getDepartments(Integer id, String name, String location);
    List<Department> getDepartmentsPS(Integer id, String name, String location);
}
