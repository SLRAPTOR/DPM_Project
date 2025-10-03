/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author chamuditha
 */
public class backupRestoreCode {
    
    public String backupDB(String path){
    
        String msg=null;
        
        try {
            Runtime runtime=Runtime.getRuntime();
            Process p=runtime.exec("C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe -P3307 -uroot -ppasindu12236 --add-drop-database -B dpmdb -r"+path );
            int processComplete=p.waitFor();
            System.out.println("process done"+processComplete);
            msg="Success";
        } catch (Exception e) {
            e.printStackTrace();
            msg="Error"+e.getMessage();
         }
        
    return msg;
    }    
    
     public String restoreDB(String path){
    
        String msg=null;
        
        try {
            Runtime runtime=Runtime.getRuntime();
            Process p=runtime.exec("C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe -user=root --password=pasindu12236 -e "+path );
            int processComplete=p.waitFor();
            System.out.println("Restore process done"+processComplete);
            msg="Success";
        } catch (Exception e) {
            e.printStackTrace();
            msg="Error"+e.getMessage();
         }
        
    return msg;
    }    
}