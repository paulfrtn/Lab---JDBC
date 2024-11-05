package model;

import java.util.List;

public interface DepartementDao {
    List<Department> getDepartments(Integer id, String name, String location);
    List<Department> getDepartmentsPS(Integer id, String name, String location);
}
