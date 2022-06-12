

package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import javax.sql.DataSource;

public class HolidayModel {

    public static ArrayList<HolidayModel> getAllHolidays(DataSource ds) throws SQLException {
        String sel = "select * from holiday order by d";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sel);
                ResultSet rs = pstmt.executeQuery()) {
            ArrayList<HolidayModel> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new HolidayModel(rs));
            }
            return list;
        }
    }

    public static HolidayModel getHoliday(DataSource ds, LocalDate d) throws SQLException {
        String sql = "select * from holiday where d=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setObject(1, d);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new HolidayModel(rs) : null;
            }
        }
    }

    public static int insertHoliday(DataSource ds, HolidayModel e) throws SQLException {
        String sql = "insert into holiday(d,remark)values(?,?)";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            pstmt.setObject(1, e.d);
            pstmt.setString(2, e.remark);
            int res = pstmt.executeUpdate();
            con.commit();
            return res;
        }
    }

    public static int updateHoliday(DataSource ds, HolidayModel e) throws SQLException {
        String sql = "update holiday set remark=? where d=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            pstmt.setString(1, e.remark);
            pstmt.setObject(2, e.d);
            int res = pstmt.executeUpdate();
            con.commit();
            return res;
        }
    }

    public static int deleteHoliday(DataSource ds, HolidayModel e) throws SQLException {
        String sql = "delete from holiday where d=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            pstmt.setObject(1, e.d);
            int res = pstmt.executeUpdate();
            con.commit();
            return res;
        }
    }

    LocalDate d;
    String remark;

    public HolidayModel(ResultSet rs) throws SQLException {
        this.d = rs.getObject("d", LocalDate.class);
        this.remark = rs.getString("remark");
    }

    public HolidayModel() {
    }

    public HolidayModel(LocalDate d, String remark) {
        this.d = d;
        this.remark = remark;
    }

    public LocalDate getD() {
        return d;
    }

    public void setD(LocalDate d) {
        this.d = d;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.d);
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
        final HolidayModel other = (HolidayModel) obj;
        if (!Objects.equals(this.d, other.d)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Holiday{" + "d=" + d + ", remark=" + remark + '}';
    }
}
