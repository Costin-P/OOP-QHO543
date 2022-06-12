
 
package com.ticket.ticketsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import myLib.database.DataSourceConnection;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String sel = "select * from zone",
                sel2 = "select * from station where zonenumber=?",
                ins = "insert into gate(zonenumber,stationname)values(?,?)",
                ins2="insert into ticketmachine(machineid,zonenumber,stationname)values(?,?,?)";
        try (Connection con = DataSourceConnection.getUpwork1Connection();
                PreparedStatement pstmt = con.prepareStatement(sel);
                PreparedStatement pstmt2 = con.prepareStatement(sel2);
                PreparedStatement pstmtins = con.prepareStatement(ins);
                PreparedStatement pins2=con.prepareStatement(ins2);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Integer zonenumber = rs.getInt("number");
                System.out.println(zonenumber);
                pstmt2.setInt(1, zonenumber);
                try (ResultSet rs2 = pstmt2.executeQuery()) {
                    while (rs2.next()) {
                        String stationname = rs2.getString("stationname");
                        System.out.println(stationname);
                        for (Integer i = 1; i <= 5; i++) {
                            pstmtins.setInt(1, zonenumber);
                            pstmtins.setString(2, stationname);
                            pstmtins.setInt(3, i);
                            pstmtins.executeUpdate();
                            for(Integer j=1;j<=3;j++){
                                String machineid=zonenumber+"-"+stationname+"-"+i+"-"+j;
                                pins2.setString(1, machineid);
                                pins2.setInt(2, zonenumber);
                                pins2.setString(3, stationname);
                                pins2.setString(4, i.toString());
                                pins2.executeUpdate();
                            }
                        }

                    }
                }
            }
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
