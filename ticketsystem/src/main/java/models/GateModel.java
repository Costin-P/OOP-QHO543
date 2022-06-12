
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import javax.sql.DataSource;

public class GateModel {

    public static ArrayList<GateModel> getGateDetails(DataSource ds) throws SQLException {
        String sql = "select * from gate order by zonenumber,stationname";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            ArrayList<GateModel> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new GateModel(rs));
            }
            return list;
        }
    }

    public static ArrayList<GateModel> findAllGateByStnName(DataSource ds, Station stn) throws SQLException {
        String sql = "select * from gate where zoneid=? and stationname=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, stn.getzonenumber());
            pstmt.setString(2, stn.getStationname());
            try (ResultSet rs = pstmt.executeQuery()) {
                ArrayList<GateModel> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new GateModel(rs));
                }
                return list;
            }
        }
    }

    public static GateModel gateDetails(DataSource ds, Integer zoneid,String stationname, String gateno) throws SQLException {
        String sql = "select * from gate where zonenumber=? and stationname=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, zoneid);
            pstmt.setString(2, stationname);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new GateModel(rs) : null;
            }
        }
    }

    public static int insertGateData(DataSource ds, GateModel e) throws SQLException {
        String sql = "insert into gate(zonenumber,stationname)values(?,?)";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            pstmt.setInt(1, e.zonenumber);
            pstmt.setString(2, e.stnName);
            pstmt.setString(3, e.gatenumber);
            int res = pstmt.executeUpdate();
            con.commit();
            return res;
        }
    }

    public static int updateGateData(DataSource ds, GateModel e) throws SQLException {
        String sql = "update gate set zonenumber=?,stationname=?,where id=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            pstmt.setInt(1, e.zonenumber);
            pstmt.setString(2, e.stnName);
            pstmt.setString(3, e.gatenumber);
            pstmt.setInt(4, e.id);
            int res = pstmt.executeUpdate();
            con.commit();
            return res;
        }
    }

    public static int deleteGate(DataSource ds, GateModel e) throws SQLException {
        String sql = "delete from gate where id=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            pstmt.setInt(1, e.id);
            int res = pstmt.executeUpdate();
            con.commit();
            return res;
        }
    }
    
    Integer id;
    Integer zonenumber;
    String stnName;
    String gatenumber;

    public GateModel(ResultSet rs) throws SQLException {
        this.id=rs.getInt("id");
        this.zonenumber = rs.getInt("zonenumber");
        this.stnName = rs.getString("stationname");
        
    }

    public GateModel() {
    }

    public GateModel(Integer zoneid, String stationname) {
        this.zonenumber = zonenumber;
        this.stnName = stationname;
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getzonenumber() {
        return zonenumber;
    }

    public void setZoneid(Integer zoneid) {
        this.zonenumber = zonenumber;
    }

    public String getStationname() {
        return stnName;
    }

    public void setStationname(String stationname) {
        this.stnName = stationname;
    }

   

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.zonenumber);
        hash = 97 * hash + Objects.hashCode(this.stnName);
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
        final GateModel other = (GateModel) obj;
        if (!Objects.equals(this.stnName, other.stnName)) {
            return false;
        }

        if (!Objects.equals(this.zonenumber, other.zonenumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Gate{" + "id=" + id + ", zonenumber=" + zonenumber + ", stationname=" + stnName;
    }

    
}
