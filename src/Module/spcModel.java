/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chamuditha
 */
public class spcModel {

    Connection conn;

    public spcModel() {
        conn = Module.dbConnection.getConnection();
    }

    public String insertuser(String nic, String name, String cat, String email, String connNo, String password, boolean active) {
        String msg = "";

        String query = "INSERT INTO specialist (idspecialist, name, special_for, mobile, email, password, is_active) VALUES (?,?,?,?,?,?,?) ";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nic);

            ps.setString(2, name);
            ps.setString(3, cat);

            ps.setString(4, connNo);

            ps.setString(5, email);

            ps.setString(6, password);

            ps.setBoolean(7, active);

            ps.execute();

            msg = "Success";

        } catch (Exception e) {

            e.printStackTrace();

            msg = "Error" + e.getMessage();

        }

        return msg;
    }

    public String updateuser(String nic, String name, String cat, String email, String connNo, String password, boolean active) {
        String msg = "";

        String query = "UPDATE dpmdb.specialist SET    idspecialist=?, name=?, special_for=?, mobile=?,email=?,  password=?, is_Active=? WHERE (idspecialist=?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, nic);

            ps.setString(2, name);
            ps.setString(3, cat);
           

            ps.setString(4, connNo);
            
             ps.setString(5, email);

            ps.setString(6, password);

            ps.setBoolean(7, active);

            ps.setString(8, nic);

            ps.execute();

            msg = "Success";

        } catch (Exception e) {

            e.printStackTrace();

            msg = "Error" + e.getMessage();

        }

        return msg;
    }
    public Object[] getSelectedUserDatabyId(String userid) {
        String runningQuery = "SELECT * FROM specialist WHERE idspecialist like ? ";

        try {
            PreparedStatement ps = conn.prepareStatement(runningQuery);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            Object[] rowData = null;

            while (rs.next()) {
                String id_User = rs.getString("idspecialist");
                String name = rs.getString("name");
                String category = rs.getString("special_for");
                
                 String mobile = rs.getString("mobile");
                
                String email = rs.getString("email");

               

                String password = rs.getString("password");
                boolean isActive = rs.getBoolean("is_Active");

                rowData = new Object[]{id_User, name, category,  mobile, email, password, isActive};
            }
            return rowData;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }
    
    public void loadTable(String keyword, int categoryId, DefaultTableModel userTableModel) {

        String runningQuery = "SELECT * FROM specialist WHERE idspecialist like ? ";

        try {
            PreparedStatement ps = conn.prepareStatement(runningQuery);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            Object[] rowData;

            userTableModel.setRowCount(0);

            while (rs.next()) {

                String id_User = rs.getString("idspecialist");
                String name = rs.getString("name");
                String category = rs.getString("special_for");
                
                 String mobile = rs.getString("mobile");
                
                String email = rs.getString("email");

               

                //String password = rs.getString("password");
                String isActive = rs.getString("is_Active");

                rowData = new Object[]{id_User, name, category,  mobile,email, isActive};

                userTableModel.addRow(rowData);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
