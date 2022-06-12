
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import javax.sql.DataSource;

public class Station {

    public static ArrayList<Station> getStationList(DataSource ds) throws SQLException {
        String sel = "select * from station order by zonenumber,stationname";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sel);
                ResultSet rs = pstmt.executeQuery()) {
            ArrayList<Station> list = new ArrayList();
            while (rs.next()) {
                list.add(new Station(rs));
            }
            return list;
        }
    }

    public static ArrayList<Station> getStationsByZone(DataSource ds, ZoneModel zone) throws SQLException {
        String sel = "select * from station where zonenumber=? order by stationname";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sel)) {
            pstmt.setInt(1, zone.getId());
            try (ResultSet rs = pstmt.executeQuery()) {
                ArrayList<Station> list = new ArrayList();
                while (rs.next()) {
                    list.add(new Station(rs));
                }
                return list;
            }
        }
    }

    public static Station getStation(DataSource ds, Integer zoneid, String stationname) throws SQLException {
        String sel = "select * from station where zonenumber=? and stationname=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sel)) {
            
            pstmt.setString(2, stationname);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new Station(rs) : null;
            }
        }
    }

    public static int insStation(DataSource ds, Station e) throws SQLException {
        String sql = "insert into station(zonenumber,stationname)values(?,?)";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            pstmt.setInt(1, e.zonenumber);
            pstmt.setString(2, e.stationname);
            int res = pstmt.executeUpdate();
            con.commit();
            return res;
        }
    }

    public static int updStation(DataSource ds, Station e) throws SQLException {
        String sql = "update station set zonename=?,stationname=? where id=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            pstmt.setInt(1, e.zonenumber);
            pstmt.setString(2, e.stationname);
            pstmt.setInt(3, e.id);
            int res = pstmt.executeUpdate();
            con.commit();
            return res;
        }
    }

    public static int delStation(DataSource ds, Station e) throws SQLException {
        String del = "delete from station where id=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(del)) {
            con.setAutoCommit(false);
            pstmt.setInt(1, e.id);
            int res = pstmt.executeUpdate();
            con.commit();
            return res;
        }
    }

    Integer id;
    Integer zonenumber;
    String stationname;

    public Station(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.zonenumber = rs.getInt("zonenumber");
        this.stationname = rs.getString("stationname");
    }

    public Station() {
    }

    public Station(Integer zonenumber, String stationname) {
        this.zonenumber = zonenumber;
        this.stationname = stationname;
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

    public void setzonenumber(Integer zoneid) {
        this.zonenumber = zonenumber;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.zonenumber);
        hash = 61 * hash + Objects.hashCode(this.stationname);
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
        final Station other = (Station) obj;
        if (!Objects.equals(this.stationname, other.stationname)) {
            return false;
        }
        if (!Objects.equals(this.zonenumber, other.zonenumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Station{" + "id=" + id + ", zonenumber=" + zonenumber + ", stationname=" + stationname + '}';
    }
}