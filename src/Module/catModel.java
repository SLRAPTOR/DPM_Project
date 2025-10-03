
package Module;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author chamuditha
 */
public class catModel {
   Connection conn;
   
   public catModel(){
       conn=Module.dbConnection.getConnection();
   }

    public String addCat(String cat) {
        String msg = null;
        String query = "INSERT INTO user_cat (catname) VALUES (?)";
        
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, cat);
            psm.execute();
            
            msg="Success";
        } catch (Exception e) {
            e.printStackTrace();
            msg=e.getMessage()+"error";
        }
       return msg;
    }

    public String addCat1(String cat) {
        String msg = null;
        String query = "INSERT INTO specialist_types (special_for) VALUES (?)";
        
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, cat);
            psm.execute();
            
            msg="Success";
        } catch (Exception e) {
            e.printStackTrace();
            msg=e.getMessage()+"error";
        }
       return msg;    }
}
