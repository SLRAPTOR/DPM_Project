/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chamuditha
 */
public class ouptModel {

    Connection conn;

    public ouptModel() {
        conn = Module.dbConnection.getConnection();
    }

    public void loadTable(String keyword, int categoryId, DefaultTableModel ouptTableModel) {
        String LOadDataQuery1 = "SELECT * FROM outpatients WHERE nicNo like ? ";

        String LOadDataQuery12 = "SELECT * FROM outpatients WHERE bthNo like ? ";

        String runningQuery = null;
        if (categoryId == 0) {
            runningQuery = LOadDataQuery1;

        } else if (categoryId == 1) {
            runningQuery = LOadDataQuery12;
        }

        try {
            PreparedStatement ps = conn.prepareStatement(runningQuery);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            Object[] rowData;

            ouptTableModel.setRowCount(0);

            while (rs.next()) {

                String nicNo = rs.getString("nicNo");
                String name = rs.getString("name");
                String conNo = rs.getString("contactNo");
                Date Dob = rs.getDate("dob");

              

                rowData = new Object[]{nicNo, name, Dob, conNo, };

                ouptTableModel.addRow(rowData);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public Object[] getSelectedUserDatabyId(String userid) {
        String runningQuery = "SELECT * FROM outpatients WHERE dpmNo like ? ";

        try {
            PreparedStatement ps = conn.prepareStatement(runningQuery);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            Object[] rowData = null;

            while (rs.next()) {
                String nicNo = rs.getString("nicNo");
                String name = rs.getString("name");
                String conNo = rs.getString("contactNo");
                Date Dob = rs.getDate("dob");



                rowData = new Object[]{nicNo, name, Dob, conNo};

            }
            return rowData;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public String insertpt( String nic, String name, String connNo, Date day,String address) {

        String msg = "";

        String query = "INSERT INTO outpatients (nicNo, name,dob,contactNo,address) VALUES (?,?,?,?,?) ";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
       
            ps.setString(1, nic);
            ps.setString(2, name);
            
            ps.setDate(3, new java.sql.Date(day.getTime()));

            ps.setString(4, connNo);
            
            ps.setString(5, address);
            

      

            ps.execute();

            msg = "Success";

        } catch (Exception e) {

            e.printStackTrace();

            msg = "Error" + e.getMessage();

        }

        return msg;

    }

    public String uppt( String nic, String name, String connNo, Date day,String address) {
        String msg = "";

        String query = "UPDATE dpmdb.outpatients SET  name=?, dob =?, contactNo=? ,address=? WHERE (AND nicNo=?)";

        try { 
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, name);
            
            ps.setDate(2, new java.sql.Date(day.getTime()));


            ps.setString(3, connNo);
            
            ps.setString(4, address);

            ps.setString(5, nic);

            ps.execute();

            msg = "Success";

        } catch (Exception e) {

            e.printStackTrace();

            msg = "Error" + e.getMessage();

        }

        return msg;
    }

}
