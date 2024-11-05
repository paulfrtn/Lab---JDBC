/*
 * Membres du projet :
 *
 * - FORTUNA Paul-Alexandre
 * - DING Armand
 * - FEKLER David
 * - CHANZY Alexandre
 * */

package model.Employee;

import java.util.Date;

/**
 * @author Jean-Michel Busca
 */
public class Employee {

    private final int eid;
    private final String ename;
    private final String job;
    private final int mgr;
    private final Date hired;
    private final float sal;
    private final float comm;
    private final int did;

    public Employee(int eid, String ename, String job, int mgr, Date hired, float sal, float comm, int did) {
        this.eid = eid;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hired = hired;
        this.sal = sal;
        this.comm = comm;
        this.did = did;
    }

    @Override
    public String toString() {
        return "EmployeeInfo{" + "id=" + eid + ", name=" + ename + ", salary=" + sal + "}\n";
    }


    public int getEid() {
        return eid;
    }

    public String getEname() {
        return ename;
    }

    public String getJob() {
        return job;
    }

    public int getMgr() {
        return mgr;
    }

    public Date getHired() {
        return hired;
    }

    public float getSal() {
        return sal;
    }

    public float getComm() {
        return comm;
    }

    public int getDid() {
        return did;
    }

}
