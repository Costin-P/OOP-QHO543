/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myLib.database;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class DbUtilities {

    // for String set values
    public static void setValue(PreparedStatement stmt, Integer pos, String val) throws SQLException {
        stmt.setString(pos, val);
    }

    public static void setValueOrNull(PreparedStatement stmt, Integer pos, String val) throws SQLException {
        if (val == null) {
            stmt.setNull(pos, java.sql.Types.VARCHAR);
        } else {
            stmt.setString(pos, val);
        }
    }

    public static void setValue(CallableStatement stmt, Integer pos, String val) throws SQLException {
        stmt.setString(pos, val);
    }

    public static void setValueOrNull(CallableStatement stmt, Integer pos, String val) throws SQLException {
        if (val == null) {
            stmt.setNull(pos, java.sql.Types.VARCHAR);
        } else {
            stmt.setString(pos, val);
        }
    }

    // for Integer set values
    public static void setValue(PreparedStatement pstmt, Integer pos, Integer val) throws SQLException {
        pstmt.setInt(pos, val);
    }

    public static void setValueOrNull(PreparedStatement pstmt, Integer pos, Integer val) throws SQLException {
        if (val == null) {
            pstmt.setNull(pos, java.sql.Types.INTEGER);
        } else {
            pstmt.setInt(pos, val);
        }
    }

    public static void setValue(CallableStatement cstmt, Integer pos, Integer val) throws SQLException {
        cstmt.setInt(pos, val);
    }

    public static void setValueOrNull(CallableStatement cstmt, Integer pos, Integer val) throws SQLException {
        if (val != null) {
            cstmt.setInt(pos, val);
        } else {
            cstmt.setNull(pos, java.sql.Types.INTEGER);
        }
    }

    // for Long
    public static void setValue(PreparedStatement pstmt, Integer pos, Long val) throws SQLException {
        pstmt.setLong(pos, val);
    }

    public static void setValueOrNull(PreparedStatement pstmt, Integer pos, Long val) throws SQLException {
        if (val == null) {
            pstmt.setNull(pos, java.sql.Types.BIGINT);
        } else {
            pstmt.setLong(pos, val);
        }
    }

    public static void setValue(CallableStatement cstmt, Integer pos, Long val) throws SQLException {
        cstmt.setLong(pos, val);
    }

    public static void setValueOrNull(CallableStatement cstmt, Integer pos, Long val) throws SQLException {
        if (val != null) {
            cstmt.setLong(pos, val);
        } else {
            cstmt.setNull(pos, java.sql.Types.BIGINT);
        }
    }

    // for Double
    public static void setValue(PreparedStatement pstmt, Integer pos, Double val) throws SQLException {
        pstmt.setDouble(pos, val);
    }

    public static void setValueOrNull(PreparedStatement pstmt, Integer pos, Double val) throws SQLException {
        if (val == null) {
            pstmt.setNull(pos, java.sql.Types.DOUBLE);
        } else {
            pstmt.setDouble(pos, val);
        }
    }

    public static void setValue(CallableStatement pstmt, Integer pos, Double val) throws SQLException {
        pstmt.setDouble(pos, val);
    }

    public static void setValueOrNull(CallableStatement pstmt, Integer pos, Double val) throws SQLException {
        if (val == null) {
            pstmt.setNull(pos, java.sql.Types.DOUBLE);
        } else {
            pstmt.setDouble(pos, val);
        }
    }

    // for Boolean
    public static void setValue(PreparedStatement cstmt, Integer pos, Boolean val) throws SQLException {
        cstmt.setBoolean(pos, val);
    }

    public static void setValueOrNull(PreparedStatement cstmt, Integer pos, Boolean val) throws SQLException {
        if (val != null) {
            cstmt.setBoolean(pos, val);
        } else {
            cstmt.setNull(pos, java.sql.Types.BOOLEAN);
        }
    }

    public static void setValue(CallableStatement cstmt, Integer pos, Boolean val) throws SQLException {
        cstmt.setBoolean(pos, val);
    }

    public static void setValueOrNull(CallableStatement cstmt, Integer pos, Boolean val) throws SQLException {
        if (val != null) {
            cstmt.setBoolean(pos, val);
        } else {
            cstmt.setNull(pos, java.sql.Types.BOOLEAN);
        }
    }

    // for LocalDateTime
    public static void setValue(PreparedStatement pstmt, Integer pos, LocalDateTime val) throws SQLException {
        pstmt.setObject(pos, val);
    }

    public static void setValueOrNull(PreparedStatement pstmt, Integer pos, LocalDateTime val) throws SQLException {
        if (val != null) {
            pstmt.setObject(pos, val);
        } else {
            pstmt.setNull(pos, java.sql.Types.TIMESTAMP);
        }
    }

    public static void setValue(CallableStatement cstmt, Integer pos, LocalDateTime val) throws SQLException {
        cstmt.setObject(pos, val);
    }

    public static void setValueOrNull(CallableStatement cstmt, Integer pos, LocalDateTime val) throws SQLException {
        if (val != null) {
            cstmt.setObject(pos, val);
        } else {
            cstmt.setNull(pos, java.sql.Types.TIMESTAMP);
        }
    }

    // for LocalDate
    public static void setValue(PreparedStatement cstmt, Integer pos, LocalDate val) throws SQLException {
        cstmt.setObject(pos, val);
    }

    public static void setValueOrNull(PreparedStatement cstmt, Integer pos, LocalDate val) throws SQLException {
        if (val != null) {
            cstmt.setObject(pos, val);
        } else {
            cstmt.setNull(pos, java.sql.Types.TIMESTAMP);
        }
    }

    public static void setValue(CallableStatement cstmt, Integer pos, LocalDate val) throws SQLException {
        cstmt.setObject(pos, val);
    }

    public static void setValueOrNull(CallableStatement cstmt, Integer pos, LocalDate val) throws SQLException {
        if (val != null) {
            cstmt.setObject(pos, val);
        } else {
            cstmt.setNull(pos, java.sql.Types.TIMESTAMP);
        }
    }

    // for LocalTime set values
    public static void setValue(PreparedStatement cstmt, Integer pos, LocalTime val) throws SQLException {
        cstmt.setObject(pos, val);
    }

    public static void setValueOrNull(PreparedStatement cstmt, Integer pos, LocalTime val) throws SQLException {
        if (val != null) {
            cstmt.setObject(pos, val);
        } else {
            cstmt.setNull(pos, java.sql.Types.TIMESTAMP);
        }
    }

    public static void setValue(CallableStatement cstmt, Integer pos, LocalTime val) throws SQLException {
        cstmt.setObject(pos, val);
    }

    public static void setValueOrNull(CallableStatement cstmt, Integer pos, LocalTime val) throws SQLException {
        if (val != null) {
            cstmt.setObject(pos, val);
        } else {
            cstmt.setNull(pos, java.sql.Types.TIMESTAMP);
        }
    }

}
