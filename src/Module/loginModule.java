/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author chamuditha
 */
public class loginModule {
    
    
    Connection conn;
    
    public loginModule(){
         conn = Module.dbConnection.getConnection();
        
    
}

    public static String login(String uName, String pWord) {


        String msg = "";
      

        return msg;
    }
    }
    

