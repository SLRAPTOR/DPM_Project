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
public class inptModel {

    Connection conn;

    public inptModel() {
        conn = Module.dbConnection.getConnection();
    }

    public void loadTable(String keyword, int categoryId, String string, int i, DefaultTableModel inptTableModel) {
        String LOadDataQuery1 = "SELECT * FROM inwardpatients WHERE nicNo like ? ";

        //String LOadDataQuery12 = "SELECT * FROM inwardpatients WHERE bthNo like ? ";
        String runningQuery = null;
        if (categoryId == 0) {
            runningQuery = LOadDataQuery1;

        }

        try {
            PreparedStatement ps = conn.prepareStatement(runningQuery);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            Object[] rowData;

            inptTableModel.setRowCount(0);

            while (rs.next()) {

                String nicNo = rs.getString("nicNo");
                String name = rs.getString("name");

                Date Dob = rs.getDate("dob");
                String conNo = rs.getString("contactNo");

                String ward = rs.getString("ward");

                String address = rs.getString("address");

                String guard = rs.getString("guardian");

                rowData = new Object[]{nicNo, name, Dob, conNo, ward, address, guard};

                inptTableModel.addRow(rowData);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public String insertpt(String nic, String name, String connNo, Date day, String ward, String addres, String gurd) {
        String msg = "";

        String query = "INSERT INTO inwardpatients ( nicNo, name, dob, contactNo,ward,address,guardian) VALUES (?,?,?,?,?,?,?) ";

        try {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, nic);
            ps.setString(2, name);

            ps.setDate(3, new java.sql.Date(day.getTime()));

            ps.setString(4, connNo);

            ps.setString(5, ward);

            ps.setString(6, addres);
            ps.setString(7, gurd);

            ps.execute();

            msg = "Success";

        } catch (Exception e) {

            e.printStackTrace();

            msg = "Error" + e.getMessage();

        }

        return msg;
    }

    public Object[] getSelectedUserDatabyId(String userid) {

        String runningQuery = "SELECT * FROM inwardpatients WHERE nicNo like ? ";

        try {
            PreparedStatement ps = conn.prepareStatement(runningQuery);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            Object[] rowData = null;

            while (rs.next()) {

                String nicNo = rs.getString("nicNo");

                String name = rs.getString("name");
                Date Dob = rs.getDate("dob");

                String conNo = rs.getString("contactNo");

                String ward = rs.getString("ward");

                String address = rs.getString("address");

                String guard = rs.getString("guardian");

                rowData = new Object[]{nicNo, name, Dob, conNo, ward, address, guard};

            }
            return rowData;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;

    }

    public void loadTable(String keyword, int categoryId, DefaultTableModel inptTableModel) {

        String LOadDataQuery13 = "SELECT * FROM inwardpatients WHERE nicNo like ? ";

        String LOadDataQuery14 = "SELECT * FROM inwardpatients WHERE name like ? ";

        String runningQuery = "SELECT * FROM inwardpatients WHERE nicNo like ? ";

        try {
            PreparedStatement ps = conn.prepareStatement(runningQuery);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            Object[] rowData;

            inptTableModel.setRowCount(0);

            while (rs.next()) {

                String nicNo = rs.getString("nicNo");
                String name = rs.getString("name");

                String dob = rs.getString("dob");

                String contactNo = rs.getString("contactNo");

                String ward = rs.getString("ward");

                String address = rs.getString("address");

                String guard = rs.getString("guardian");

                rowData = new Object[]{nicNo, name, dob, contactNo, ward, address, guard};

                inptTableModel.addRow(rowData);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public String deleteStudent(String Id) {
        String msg = null;
        String Query = "DELETE FROM inwardpatients WHERE nicNo =? ";
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

    public String upPt(String nic, String name, String connNo, Date day, String ward,String address, String gurd) {
        String msg = "";

        String query = "UPDATE dpmdb.inwardpatients SET    name=?, dob =?, contactNo=?, ward=?,address=?,guardian=? WHERE (nicNo=?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setDate(2, new java.sql.Date(day.getTime()));
            ps.setString(3, connNo);
            ps.setString(4, ward);
            ps.setString(5, address);
            ps.setString(6, gurd);
            ps.setString(7, nic);

            ps.execute();

            msg = "Success";

        } catch (Exception e) {

            e.printStackTrace();

            msg = "Error" + e.getMessage();

        }

        return msg;
    }

}
