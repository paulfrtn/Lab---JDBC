package model;

import java.util.List;

public interface DepartementDao {

    public void addDepartement(DepartmentInfo departmentInfo);

    public void deleteDepartementById(int id);

    public void updateDepartement(DepartmentInfo departmentInfo);

    public DepartmentInfo getDepartementById(int id);

    public List<DepartmentInfo> getAllDepartements();
}
