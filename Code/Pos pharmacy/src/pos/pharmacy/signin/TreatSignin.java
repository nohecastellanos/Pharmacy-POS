/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.pharmacy.signin;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pos.pharmacy.dbConnection.Connectdb;
/**
 *
 * @author icom
 */
public class TreatSignin  {
    public String password;
    public String username;
 
    public TreatSignin(String password, String username ){
        
        this.password = password;
        this.username = username;
        
        
    }
    
    public int signIn(Connectdb cn) throws SQLException, IOException{
        int done = 0;
        if(username!=null && password!=null && !password.isEmpty() && !username.isEmpty()){
            String query = " user_name, password, role_id ";
            String where = " user_name = "+"'"+username+"'"
                          + "and password = "+"'"+password+"'";
            ResultSet result = cn.selectFrom("user",query,where);
            if(result.next()){
                done = 1;
                goToDashboard();
            }
            else{
                System.out.println("wrong username or password");
             }
        }
        else{
           System.out.println("empty username or password"); 
        }   
       return done;
    }
    
     public void goToDashboard() throws IOException{
       Stage stage = new Stage();
       stage.setTitle("Shop Management");
       Pane myPane = null;
       URL url = new File("src/pos/pharmacy/dashboard/dashboard.fxml").toURI().toURL();
       myPane = FXMLLoader.load(url);
       Scene scene = new Scene(myPane);
       stage.setScene(scene);    
       stage.show();
    }
    
   
}
