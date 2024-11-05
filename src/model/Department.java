package model;

public class Department {
    private final int did;
    private final String dname;
    private final String dloc;

    public Department(int did, String dname, String dloc){
        this.did = did;
        this.dname = dname;
        this.dloc = dloc;
    }

    @Override
    public String toString() {
        return "Department [ID=" + did + ", Name=" + dname + ", Location=" + dloc + "]";
    }

    public int getDid() {
        return did;
    }

    public String getDname() {
        return dname;
    }

    public String getDloc() {
        return dloc;
    }
}
