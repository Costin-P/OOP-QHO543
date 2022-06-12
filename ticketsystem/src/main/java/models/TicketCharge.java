/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import javax.sql.DataSource;

public class TicketCharge {
    
    public static String getTicketType(DataSource ds, LocalDate d, LocalTime t) throws SQLException {
        String peak = "peak", offPeak = "offpeak";
        if (d.getDayOfWeek().equals(DayOfWeek.SATURDAY) || d.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return offPeak;
        }
        TicketCharge ticketCharge = TicketCharge.getTicketChargeList(ds).get(0);
        if (t.isBefore(ticketCharge.pickfromtime) || t.isAfter(ticketCharge.picktotime)) {
            return offPeak;
        }
        String sql = "select exists(select 1 from holiday where d=?)";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setObject(1, d);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1) ? offPeak : peak;
                }
            }
        }
        return peak;
    }

    public static Double getTicketPrice(DataSource ds, String ticketType, Integer zonecount) throws SQLException {
        TicketCharge ticketCharge = TicketCharge.getTicketChargeList(ds).get(0);
        switch (ticketType) {
            case "peak":
                return zonecount * ticketCharge.pickcharge;
            case "offpeak":
                return zonecount * ticketCharge.offpickcharge;
            default:
                break;
        }
        return zonecount * ticketCharge.pickcharge;
    }
    
    public static ArrayList<TicketCharge> getTicketChargeList(DataSource ds) throws SQLException {
        try (Connection con = ds.getConnection()) {
            return getTicketChargeList(con);
        }
    }
    
    public static ArrayList<TicketCharge> getTicketChargeList(Connection con) throws SQLException {
        String sql = "select * from ticketcharge";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            ArrayList<TicketCharge> list = new ArrayList();
            while (rs.next()) {
                list.add(new TicketCharge(rs));
            }
            return list;
        }
    }
    
    public static TicketCharge getTicketCharge(DataSource ds, Integer id) throws SQLException {
        String sql = "select * from ticketcharge where id=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new TicketCharge(rs) : null;
            }
        }
    }
    
    public static int updTicketCharge(DataSource ds, TicketCharge e) throws SQLException {
        String sql = "update ticketcharge set pickcharge=?,offpickcharge=?,pickfromtime=?,picktotime=?"
                + " where id=?";
        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            pstmt.setDouble(1, e.pickcharge);
            pstmt.setDouble(2, e.offpickcharge);
            pstmt.setObject(3, e.pickfromtime);
            pstmt.setObject(4, e.picktotime);
            pstmt.setInt(5, e.id);
            int res = pstmt.executeUpdate();
            con.commit();
            return res;
        }
    }
    
    Integer id;
    Double pickcharge;
    Double offpickcharge;
    LocalTime pickfromtime,picktotime;
    
    public TicketCharge(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.pickcharge = rs.getDouble("pickcharge");
        this.offpickcharge = rs.getDouble("offpickcharge");
        this.pickfromtime=rs.getTime("pickfromtime").toLocalTime();
        this.picktotime=rs.getTime("picktotime").toLocalTime();
    }
    
    public TicketCharge() {
    }

    public TicketCharge(Double pickcharge, Double offpickcharge, LocalTime pickfromtime, LocalTime picktotime) {
        this.pickcharge = pickcharge;
        this.offpickcharge = offpickcharge;
        this.pickfromtime = pickfromtime;
        this.picktotime = picktotime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPickcharge() {
        return pickcharge;
    }

    public void setPickcharge(Double pickcharge) {
        this.pickcharge = pickcharge;
    }

    public Double getOffpickcharge() {
        return offpickcharge;
    }

    public void setOffpickcharge(Double offpickcharge) {
        this.offpickcharge = offpickcharge;
    }

    public LocalTime getPickfromtime() {
        return pickfromtime;
    }

    public void setPickfromtime(LocalTime pickfromtime) {
        this.pickfromtime = pickfromtime;
    }

    public LocalTime getPicktotime() {
        return picktotime;
    }

    public void setPicktotime(LocalTime picktotime) {
        this.picktotime = picktotime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final TicketCharge other = (TicketCharge) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TicketCharge{" + "id=" + id + ", pickcharge=" + pickcharge + ", offpickcharge=" + offpickcharge + ", pickfromtime=" + pickfromtime + ", picktotime=" + picktotime + '}';
    }
}
