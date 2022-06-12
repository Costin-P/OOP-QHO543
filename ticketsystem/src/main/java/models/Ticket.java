
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.sql.DataSource;

public class Ticket {

    public static Ticket getTicket(DataSource ds, Integer id) throws SQLException {
        String sql = "select * from ticket where id=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new Ticket(rs) : null;
            }
        }
    }

    public static int saveTicket(DataSource ds, Ticket e) throws SQLException {
        String sql = "insert into ticket(fzonenumber,fstationname,machineid,"
                + "tzonenumber,tstationname,"
                + "dt,tickettype,zonecount,validdt,price)values"
                + "(?,?,?,?,?, ?,?,?,?,?, ?)returning id";
        int id = 0;
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            int i = 1;
            pstmt.setInt(i++, e.fzonenumber);
            pstmt.setString(i++, e.fstationname);
            pstmt.setString(i++, e.machineid);
            pstmt.setInt(i++, e.tzonenumber);
            pstmt.setString(i++, e.tstationname);
            pstmt.setObject(i++, e.dt);
            pstmt.setString(i++, e.tickettype);
            pstmt.setInt(i++, e.zonecount);
            pstmt.setObject(i++, e.validdt);
            pstmt.setDouble(i, e.price);
            System.out.println(pstmt.toString());
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                id = rs.getInt(1);
            }
            con.commit();
            return id;
        }
    }

    Integer id;
    Integer fzonenumber;
    String fstationname;
    String machineid;
    Integer tzonenumber;
    String tstationname;
    LocalDateTime dt;
    String tickettype;
    Integer zonecount;
    LocalDateTime validdt;
    Double price;

    public Ticket(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.fzonenumber = rs.getInt("fzonenumber");
        this.fstationname=rs.getString("fstationname");
        this.machineid = rs.getString("machineid");
        this.tzonenumber = rs.getInt("tzonenumber");
        this.tstationname=rs.getString("tstationname");
        this.dt = rs.getTimestamp("dt").toLocalDateTime();
        this.tickettype = rs.getString("tickettype");
        this.zonecount = rs.getInt("zonecount");
        this.validdt = rs.getTimestamp("validdt").toLocalDateTime();
        this.price = rs.getDouble("price");
    }

    public Ticket() {
    }

    public Ticket(Integer fzonenumber, String fstationname, String machineid, Integer tzonenumber, String tstationname, LocalDateTime dt, String tickettype, Integer zonecount, LocalDateTime validdt, Double price) {
        this.fzonenumber = fzonenumber;
        this.fstationname = fstationname;
        this.machineid = machineid;
        this.tzonenumber = tzonenumber;
        this.tstationname = tstationname;
        this.dt = dt;
        this.tickettype = tickettype;
        this.zonecount = zonecount;
        this.validdt = validdt;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFzonenumber() {
        return fzonenumber;
    }

    public void setFzonenumber(Integer fzonenumber) {
        this.fzonenumber = fzonenumber;
    }

    public String getFstationname() {
        return fstationname;
    }

    public void setFstationname(String fstationname) {
        this.fstationname = fstationname;
    }


    public String getMachineid() {
        return machineid;
    }

    public void setMachineid(String machineid) {
        this.machineid = machineid;
    }

    public Integer getTzonenumber() {
        return tzonenumber;
    }

    public void setTzoneid(Integer tzonenumber) {
        this.tzonenumber = tzonenumber;
    }

    public String getTstationname() {
        return tstationname;
    }

    public void setTstationname(String tstationname) {
        this.tstationname = tstationname;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public void setDt(LocalDateTime dt) {
        this.dt = dt;
    }

    public String getTickettype() {
        return tickettype;
    }

    public void setTickettype(String tickettype) {
        this.tickettype = tickettype;
    }

    public Integer getZonecount() {
        return zonecount;
    }

    public void setZonecount(Integer zonecount) {
        this.zonecount = zonecount;
    }

    public LocalDateTime getValiddt() {
        return validdt;
    }

    public void setValiddt(LocalDateTime validdt) {
        this.validdt = validdt;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ticket other = (Ticket) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", fzonenumber=" + fzonenumber + 
               ", fstationname=" + fstationname + ", machineid=" + machineid + 
               ", tzonenumber=" + tzonenumber + ", tstationname=" + tstationname + 
               ", dt=" + dt + ", tickettype=" + tickettype + ", zonecount=" + zonecount + 
               ", validdt=" + validdt + ", price=" + price + '}';
    }

    
}
