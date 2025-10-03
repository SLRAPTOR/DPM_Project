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
public class userModel {

    Connection conn;

    public userModel() {
        conn = Module.dbConnection.getConnection();
    }

    public String insertuser(String nic, String name, String cat, String email, String connNo, String password, boolean active) {
        String msg = "";

        String query = "INSERT INTO user (id_User, name, category, email, mobile, password, isActive) VALUES (?,?,?,?,?,?,?) ";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nic);

            ps.setString(2, name);
            ps.setString(3, cat);
            ps.setString(4, email);

            ps.setString(5, connNo);

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

    public void loadTable(String keyword, int categoryId, DefaultTableModel userTableModel) {

        String runningQuery = "SELECT * FROM user WHERE id_User like ? ";

        try {
            PreparedStatement ps = conn.prepareStatement(runningQuery);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            Object[] rowData;

            userTableModel.setRowCount(0);

            while (rs.next()) {

                String id_User = rs.getString("id_User");
                String name = rs.getString("name");
                String category = rs.getString("category");
                String email = rs.getString("email");

                String mobile = rs.getString("mobile");

                //String password = rs.getString("password");
                String isActive = rs.getString("isActive");

                rowData = new Object[]{id_User, name, category, email, mobile, isActive};

                userTableModel.addRow(rowData);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public Object[] getSelectedUserDatabyId(String userid) {
        String runningQuery = "SELECT * FROM user WHERE id_User like ? ";

        try {
            PreparedStatement ps = conn.prepareStatement(runningQuery);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            Object[] rowData = null;

            while (rs.next()) {
                String id_User = rs.getString("id_User");
                String name = rs.getString("name");
                String category = rs.getString("category");
                String email = rs.getString("email");

                String mobile = rs.getString("mobile");

                String password = rs.getString("password");
                boolean isActive = rs.getBoolean("isActive");

                rowData = new Object[]{id_User, name, category, email, mobile, password, isActive};
            }
            return rowData;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public String updateuser(String nic, String name, String cat, String email, String connNo, String password, boolean active) {
        String msg = "";

        String query = "UPDATE dpmdb.user SET    id_User=?, name=?, category=?, email=?, mobile=?, password=?, isActive=? WHERE (id_User=?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, nic);

            ps.setString(2, name);
            ps.setString(3, cat);
            ps.setString(4, email);

            ps.setString(5, connNo);

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

    public String deleteStudent(String Id) {

        String msg = null;
        String Query = "DELETE FROM user WHERE id_User =? ";
        try {
            PreparedStatement psm = conn.prepareStatement(Query);
            psm.setString(1, Id);

            psm.execute();

            msg = "Success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error" + e.getMessage();
        }

        return msg;

    }

    public String login(String username, String password, int type) {

        //Receptionist
        String msg = null;
        String runningQuery = null;

        String LOadDataQuery1 = "SELECT * FROM user WHERE id_User = ? AND password=? AND category='Admin' ";
        
        String LOadDataQuery2 = "SELECT * FROM user WHERE id_User = ? AND password=? AND category='Nurse' ";

        String LOadDataQuery12 = "SELECT * FROM user WHERE id_User = ? AND password =? AND category='Lab Asst'";

        String LOadDataQuery3 = "SELECT * FROM specialist WHERE idspecialist=? AND password=?";
        if (type == 0) {
            runningQuery = LOadDataQuery1;
        } else if (type == 1) {
            runningQuery = LOadDataQuery3;
        } else if (type == 2) {
            runningQuery = LOadDataQuery12;
        }else if (type == 3) {
            runningQuery = LOadDataQuery2;
        }

        try {

            PreparedStatement psm = conn.prepareStatement(runningQuery);
            psm.setString(1, username);
            psm.setString(2, password);

            ResultSet rs = psm.executeQuery();

            while (rs.next()) {
                if (runningQuery == LOadDataQuery1) {

                    msg = "welcome admin";
                    //   new lecturer().setVisible(true);
                }  else if (runningQuery == LOadDataQuery3) {
                    msg = "welcome Doctor";

                }else if (runningQuery == LOadDataQuery12) {
                    msg = "welcome Lab Asst";

                    //   new StudentProfile().setVisible(true);
                }else if (runningQuery == LOadDataQuery2) {
                    msg = "welcome nurse";

                    //   new StudentProfile().setVisible(true);
                }
                
                else{
                    
                    msg="Sorry";
                
                }
                    
                    

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

}
