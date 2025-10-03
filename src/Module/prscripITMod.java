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
public class prscripITMod {

    Connection conn;

    public prscripITMod() {
        conn = Module.dbConnection.getConnection();
    }

    public String insertpt(String id, String patient, String medicine, Date day, String doseage) {

        String msg = "";

        String query = "INSERT INTO prescription_item ( idpharmacy, patient, medicines, dosage, date) VALUES (?,?,?,?,?) ";

        try {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, id);
            ps.setString(2, patient);

            ps.setString(3, medicine);

            ps.setString(4, doseage);
            ps.setDate(5, new java.sql.Date(day.getTime()));

            ps.execute();

            msg = "Success";

        } catch (Exception e) {

            e.printStackTrace();

            msg = "Error" + e.getMessage();

        }

        return msg;

    }

    public String upPt(String id, String patient, String medicine, Date day, String doseage) {

        String msg = "";

        String query = "UPDATE dpmdb.prescription_item SET     patient=?, medicines =?, dosage=?, date=? WHERE (idpharmacy=?) ";

        try {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, patient);

            ps.setString(2, medicine);

            ps.setString(3, doseage);
            ps.setDate(4, new java.sql.Date(day.getTime()));

            ps.setString(5, id);

            ps.execute();

            msg = "Success";

        } catch (Exception e) {

            e.printStackTrace();

            msg = "Error" + e.getMessage();

        }

        return msg;
    }

    public void loadTable(String keyword, int categoryId, DefaultTableModel prscripITtableMod) {
        String LOadDataQuery1 = "SELECT * FROM prescription_item WHERE idpharmacy like ? ";

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

            prscripITtableMod.setRowCount(0);

            while (rs.next()) {

                String id = rs.getString("idpharmacy");
                String pt = rs.getString("patient");
                String med = rs.getString("medicines");
                String dose = rs.getString("dosage");
                Date day = rs.getDate("date");

                rowData = new Object[]{id,pt,med,dose,day};

                prscripITtableMod.addRow(rowData);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public Object[] getSelectedUserDatabyId(String userid) {
String runningQuery = "SELECT * FROM prescription_item WHERE idpharmacy like ? ";

        try {
            PreparedStatement ps = conn.prepareStatement(runningQuery);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            Object[] rowData = null;

            while (rs.next()) {

            
                String id = rs.getString("idpharmacy");
                String pt = rs.getString("patient");
                String med = rs.getString("medicines");
                String dose = rs.getString("dosage");
                Date day = rs.getDate("date");

                rowData = new Object[]{id,pt,med,dose,day};


            }
            return rowData;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public String deleteStudent(String Id) {
                String msg = null;
        String Query = "DELETE FROM prescription_item WHERE idpharmacy =? ";
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
}
