/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.pharmacy.dashboard;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.xml.transform.OutputKeys;
import pos.pharmacy.dbConnection.Connectdb;


/**
 *
 * @author icom
 */
public class DashboardController {
    @FXML
    Pane pane;    
    @FXML
    BorderPane borderpane;
    Connectdb cn;
    TreateDashboard  tds;
    public DashboardController() throws SQLException{
       cn =  new Connectdb();
       tds = new TreateDashboard(cn);
       tds.createItemTable(cn);
       tds.createMedecinesTable(cn);
       tds.createOthersTable(cn);
    }
    
    @FXML
    private void gotoDashboard(ActionEvent e){
        
    }
    
    @FXML
    private void gotoMedecines(ActionEvent event) throws IOException{
       URL url = new File("src/pos/pharmacy/medecines/medecines.fxml").toURI().toURL();
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(url);
       AnchorPane myPane =  FXMLLoader.load(url);
    
       borderpane.setCenter(myPane);
               

    }
    
     @FXML
    private void gotoOtherItems(ActionEvent e){
        
    }
     @FXML
    private void gotoSoonExpiring(ActionEvent e){
        
    }
     @FXML
    private void gotoSoonEnd(ActionEvent e){
        
    }
  
   
      @FXML
    private void gotoAccount(ActionEvent e){
        
    }
    
     @FXML
    private void gotoCashier(ActionEvent e){
        
    }
    
   
    
    
    private void loadFXML(URL url) {
        FXMLLoader loader = new FXMLLoader(url);
        //mainBorderPane.setCenter(loader.load());
    }
}
