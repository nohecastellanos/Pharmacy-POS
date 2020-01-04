/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.pharmacy.medecines;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author icom
 */
public class MedecinesController {
    @FXML
    private Button add_btn;
    
    @FXML
    void goToAddMedecine(ActionEvent event) throws MalformedURLException, IOException {
       Stage stage = new Stage();
       Pane myPane = null;
       URL url = new File("src/pos/pharmacy/medecines/add/addMedecines.fxml").toURI().toURL();
       myPane = FXMLLoader.load(url);
       Scene scene = new Scene(myPane);
       stage.setScene(scene);    
       stage.show();
    }
    
     @FXML
    void searchMedecine(ActionEvent event) throws MalformedURLException, IOException {
      
    }
}
