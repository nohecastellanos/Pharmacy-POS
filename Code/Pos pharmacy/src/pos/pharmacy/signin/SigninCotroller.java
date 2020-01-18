/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.pharmacy.signin;


import java.io.IOException;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import pos.pharmacy.dbConnection.Connectdb;
/**
 *
 * @author icom
 */
public class SigninCotroller {
    
    @FXML
    private Label labellogin;
    @FXML
    private Button btnlogin;
    @FXML
    private TextField  txtusername;
    @FXML
    private PasswordField  txtpassword;
    @FXML
    private TextField txtpasswordvisible;
    @FXML
    private CheckBox showpasswordbox;
    
    private String password;
    private String username;
    Connectdb cn = new Connectdb() ;
    
    public SigninCotroller() throws SQLException{
        cn.createDb();
        
        createRoleTable(cn);
        createUserTable(cn);
        createPermissionsTable(cn);
    }
    
     @FXML
    void handleHidePassword(ActionEvent event) {
        if (showpasswordbox.isSelected()) {
            txtpasswordvisible.setText(txtpassword.getText());
            txtpasswordvisible.setVisible(true);
            txtpassword.setVisible(false);
           
            return;
        }
            txtpassword.setText(txtpasswordvisible.getText());
            txtpassword.setVisible(true);
            txtpasswordvisible.setVisible(false);
}
    
   
    @FXML
    private void handleLoginAction(ActionEvent event)throws SQLException, IOException {       
        username = txtusername.getText();
        password = txtpassword.getText().length() > txtpasswordvisible.getText().length()  ?  txtpassword.getText() :txtpasswordvisible.getText() ;
       
        TreatSignin tr = new TreatSignin(password, username);
        int done = tr.signIn(cn);
         
        if (done==1)
        close();

    }
    
    @FXML
    public void initialize() throws SQLException {
       handleHidePassword(null);
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
    
      public void createPermissionsTable(Connectdb cn) throws SQLException{
        String  query = "permission_id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                        +"role_id int ,"
                        +"permission_name varchar(25) NOT NULL,"
                        +"permission_description varchar(25),"
                        +"foreign key(role_id) references role(role_id) ON DELETE CASCADE";
                      
        cn.createTable("permission", query);
    }
    
      public void close(){
        Stage stage = (Stage) btnlogin.getScene().getWindow();
        // do what you have to do
        stage.close();
      
      }
  
    
}
