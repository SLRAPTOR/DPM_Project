/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author chamuditha
 */
public class ptdioModel {

    Connection conn;

    public ptdioModel() {
        conn = Module.dbConnection.getConnection();
    }

    public String insertpt(int cat, String index, String patient, String bydiagnosed, String recomedatios, String conclutions, String comments) {
        String msg = "";
        String runningquery = null;
        String query1 = "INSERT INTO `dpmdb`.`ptdignos` (index, patient_in, patient_out, diagnosist, recomended_tests, doc_comments, conclution) VALUES (?,?,'null',?,?,?,?) ";
        String query2 = "INSERT INTO `dpmdb`.`ptdignos` (index, patient_in, patient_out, diagnosist, recomended_tests, doc_comments, conclution) VALUES (?,'null',?,?,?,?,?) ";

        if (cat == 0) {
            runningquery = query1;
        } else if (cat == 1) {
            runningquery = query2;
        }

            try {
                PreparedStatement ps = conn.prepareStatement(runningquery);
                ps.setString(1, index);

                ps.setString(2, patient);
                ps.setString(3, bydiagnosed);
                ps.setString(4, recomedatios);

                ps.setString(5, conclutions);

                ps.setString(6, comments);

                ps.execute();

                msg = "Success";

            } catch (Exception e) {

                e.printStackTrace();

                msg = "Error" + e.getMessage();

            }

            return msg;
        }
}

