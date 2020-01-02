/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.pharmacy.signin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pos.pharmacy.dbConnection.Connectdb;

/**
 *
 * @author icom
 */
public class SigninCotroller implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    
    @FXML
    private void handleButtonAction(ActionEvent event)throws SQLException {       
        Connectdb cn = new Connectdb();
        createRoleTable(cn);
        createUserTable(cn);
        
    }
    
    public void createRoleTable(Connectdb  cn) throws SQLException{
       String  query = "role_id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                      +"role_name varchar(25) NOT NULL UNIQUE,"
                      +"role_description varchar(100)";
        cn.createTable("role", query);
    }
    
    public void createUserTable(Connectdb cn) throws SQLException{
        String  query = "user_id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                      +"user_name varchar(25) NOT NULL UNIQUE,"
                      +"password varchar(25) NOT NULL,"
                      +"role_id int,"
                      +"email varchar(255) unique,"
                      +"adress varchar(255),"
                      +"phone varchar(25),"
                      +"foreign key(role_id) references role(role_id) ON DELETE CASCADE";
                      
        cn.createTable("user", query);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
