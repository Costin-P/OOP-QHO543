
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import javax.sql.DataSource;

public class ZoneModel {

    public static ArrayList<ZoneModel> getZoneList(DataSource ds) throws SQLException {
        String sel = "select * from zone order by id";
        ArrayList<ZoneModel> list = new ArrayList<>();
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sel);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new ZoneModel(rs));
            }
            return list;
        }
    }

    public static ZoneModel getZoneDetails(DataSource datasource, Integer id) throws SQLException {
        String sel = "select * from zone where id=?";
        try (Connection con = datasource.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sel)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new ZoneModel(rs) : null;
            }
        }
    }

    Integer number;

    public ZoneModel(ResultSet rs) throws SQLException {
        this.number = rs.getInt("number");
    }

    public ZoneModel() {
    }

    public ZoneModel(Integer number) {
        this.number = number;
    }

    public Integer getId() {
        return number;
    }

    public void setId(Integer number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.number);
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
        final ZoneModel other = (ZoneModel) obj;
        //if (!Objects.equals(this.number, other.number)) {
            return false;
        
       
    }

    @Override
    public String toString() {
        return "Zone{" + "number=" + number + '}';
    }

}
