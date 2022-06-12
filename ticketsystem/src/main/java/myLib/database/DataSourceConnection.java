/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myLib.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataSourceConnection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (Connection con = getUpwork1Connection()) {
            System.out.println(con.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getUpwork1Connection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/login?allowPublicKeyRetrieval=true&useSSL=false";
        String user = "root";
        String password = "root";
        Connection con = DriverManager.getConnection(url, user, password);
        con.setAutoCommit(false);
        return con;
    }
}
