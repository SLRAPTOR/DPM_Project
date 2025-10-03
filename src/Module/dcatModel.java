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
public class dcatModel {
    
 
      Connection conn;

    public dcatModel() {
        conn = Module.dbConnection.getConnection();
    }
    
    
     public void loadTable(String keyword, int categoryId, DefaultTableModel wardTableModel) {

        String runningQuery = "SELECT * FROM specialist_types WHERE id like ? ";

        try {
            PreparedStatement ps = conn.prepareStatement(runningQuery);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            Object[] rowData;

            wardTableModel.setRowCount(0);

            while (rs.next()) {

                String id_User = rs.getString("id");
                String name = rs.getString("special_for");
                
                rowData = new Object[]{id_User, name};

                wardTableModel.addRow(rowData);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public String addwm(String cat) {
        String msg = null;
        String query = "INSERT INTO specialist_types (special_for) VALUES (?)";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, cat);
            psm.execute();

            msg = "Success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage() + "error";
        }
        return msg;
    }

    public Object[] getSelectedUserDatabyId(String userid) {

        String runningQuery = "SELECT * FROM specialist_types WHERE id like ? ";

        try {
            PreparedStatement ps = conn.prepareStatement(runningQuery);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            Object[] rowData = null;

            while (rs.next()) {
                String id_User = rs.getString("id");
                String name = rs.getString("special_for");

                rowData = new Object[]{id_User, name};
            }
            return rowData;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;

    }

    public String upwm(String nic,String name) {
    
        String msg = "";

        String query = "UPDATE dpmdb.specialist_types SET     special_for=? WHERE (id=?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(2, nic);

            ps.setString(1, name);
          

           

            ps.execute();

            msg = "Success";

        } catch (Exception e) {

            e.printStackTrace();

            msg = "Error" + e.getMessage();

        }

        return msg;
    
    }
}

