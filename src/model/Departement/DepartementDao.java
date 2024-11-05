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
    List<Department> getDepartments(Integer id, String name, String location);
    List<Department> getDepartmentsPS(Integer id, String name, String location);
}
