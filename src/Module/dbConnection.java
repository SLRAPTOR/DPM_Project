/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.sql.*;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;*/
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chamuditha
 */
public class dbConnection {

    private static Connection dbConnection;

    public static Connection getConnection() {

        if (dbConnection == null) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3307/dpmdb?useSSL=false", "root", "pasindu12236");
            } catch (Exception ex) {
                Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
                
                dbConnection= null;
                
            }
        }

        return dbConnection;
    }

    public static void main(String[] args) throws SQLException {

        System.out.println(getConnection().getCatalog());
    }

}
