package model;

import java.sql.Date;

public class Mission {
    private final int mid;
    private final int eid;
    private final String cname;
    private final String mloc;
    private final Date endd;

    public Mission(int mid, int eid, String cname, String mloc, Date endd){
        this.mid = mid;
        this.eid = eid;
        this.cname = cname;
        this.mloc = mloc;
        this.endd = endd;
    }

    public int getMid() {
        return mid;
    }

    public int getEid() {
        return eid;
    }

    public String getCname() {
        return cname;
    }

    public String getMloc() {
        return mloc;
    }

    public Date getEndd() {
        return endd;
    }
}
