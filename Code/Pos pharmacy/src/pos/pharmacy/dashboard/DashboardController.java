/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.pharmacy.dashboard;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
    public DashboardController() throws SQLException, IOException{
       cn =  new Connectdb();
       tds = new TreateDashboard();
       tds.createTypeTable(cn);
       tds.createItemTable(cn);
       tds.createMedecinesTable(cn);
       tds.createOthersTable(cn);     
    }
    
    @FXML
    private void gotoDashboard(ActionEvent e) throws IOException{
         switchFXMLCenter("src/pos/pharmacy/dashboard/dashboardCenter.fxml");

    }
    
    @FXML
    private void gotoMedecines(ActionEvent event) throws IOException{
      
        switchFXMLCenter("src/pos/pharmacy/medecines/medecines.fxml");

    }
    
    @FXML
    private void gotoSale(ActionEvent event) throws IOException{
      
        switchFXMLCenter("src/pos/pharmacy/sale/sale.fxml");

    }    
    
    
     @FXML
    private void gotoOtherItems(ActionEvent e) throws IOException{
        switchFXMLCenter("src/pos/pharmacy/others/others.fxml");
    }
     @FXML
    private void gotoSoonExpiring(ActionEvent e) throws IOException{
        switchFXMLCenter("src/pos/pharmacy/soonExpiring/soonExpiring.fxml");
    }
     @FXML
    private void gotoSoonEnd(ActionEvent e) throws IOException{
        switchFXMLCenter("src/pos/pharmacy/soonEnd/soonEnd.fxml");
    }
  
   
      @FXML
    private void gotoAccount(ActionEvent e) throws IOException{
        switchFXMLCenter("src/pos/pharmacy/account/account.fxml");
    }
    
     @FXML
    private void gotoCashier(ActionEvent e) throws IOException{
        switchFXMLCenter("src/pos/pharmacy/Cashier/cashier.fxml");        
    }
    
   
    
    
   private void switchFXMLCenter(String path) throws MalformedURLException, IOException{
       URL url = new File(path).toURI().toURL();
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(url);
       AnchorPane myPane =  FXMLLoader.load(url);    
       borderpane.setCenter(myPane);               
       
   }
    
    
    
}
