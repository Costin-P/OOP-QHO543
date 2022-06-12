
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import javax.sql.DataSource;

public class TicketMachine {

    public static ArrayList<TicketMachine> getTktMachinesDetails(DataSource ds) throws SQLException {
        String sql = "select * from ticketmachine order by machineid";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            ArrayList<TicketMachine> list = new ArrayList();
            while (rs.next()) {
                list.add(new TicketMachine(rs));
            }
            return list;
        }
    }

    public static ArrayList<TicketMachine> findAllTktMachineByGate(DataSource datasource, GateModel gate) throws SQLException {
        String sql = "select * from ticketmachine where zonenumber=? and stationname=? order by machineid";
        try (Connection con = datasource.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, gate.getzonenumber());
            pstmt.setString(2, gate.getStationname());
           
            try (ResultSet rs = pstmt.executeQuery()) {
                ArrayList<TicketMachine> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new TicketMachine(rs));
                }
                return list;
            }
        }
    }

    public static TicketMachine getTicketMachineDetails(DataSource ds, String machineid) throws SQLException {
        String sql = "select * from ticketmachine where machineid=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, machineid);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new TicketMachine(rs) : null;
            }
        }
    }

    public static int insertTktMachine(DataSource ds, TicketMachine e) throws SQLException {
        String sql = "insert into ticketmachine(machineid,zonenumber,stationname,)values(?,?,?)";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            pstmt.setString(1, e.machineid);
            pstmt.setInt(2, e.zonenumber);
            pstmt.setString(3, e.stationName);
            
            System.out.println(pstmt.toString());
            int res = pstmt.executeUpdate();
            con.commit();
            return res;
        }
    }

    public static int updateTktMachine(DataSource ds, TicketMachine e) throws SQLException {
        String sql = "update ticketmachine set machineid=?,zonenumber=?,stationname=? where id=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            pstmt.setString(1, e.machineid);
            pstmt.setInt(2, e.zonenumber);
            pstmt.setString(3, e.stationName);
            
            pstmt.setInt(4, e.id);
            int res = pstmt.executeUpdate();
            con.commit();
            return res;
        }
    }

    public static int deleteTktMachine(DataSource ds, TicketMachine e) throws SQLException {
        String sql = "delete from ticketmachine where machineid=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            pstmt.setString(1, e.machineid);
            int res = pstmt.executeUpdate();
            con.commit();
            return res;
        }
    }

    Integer id;
    String machineid;
    Integer zonenumber;
    String stationName;
    

    public TicketMachine(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.machineid = rs.getString("machineid");
        this.zonenumber = rs.getInt("zonenumber");
        this.stationName = rs.getString("stationname");
        
    }

    public TicketMachine() {
    }

    public TicketMachine(String machineid, Integer zoneid, String stationname, String gateno) {
        this.machineid = machineid;
        this.zonenumber = zonenumber;
        this.stationName = stationname;
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMachineid() {
        return machineid;
    }

    public void setMachineid(String machineid) {
        this.machineid = machineid;
    }

    public Integer getzonenumber() {
        return zonenumber;
    }

    public void setzonenumber(Integer zonenumber) {
        this.zonenumber = zonenumber;
    }

    public String getStationname() {
        return stationName;
    }

    public void setStationname(String stationname) {
        this.stationName = stationname;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.machineid);
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
        final TicketMachine other = (TicketMachine) obj;
        if (!Objects.equals(this.machineid, other.machineid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TicketMachine{" + "id=" + id + ", machineid=" + machineid + ", zonenumber=" + zonenumber + ", stationname=" + stationName;
    }

    
}
