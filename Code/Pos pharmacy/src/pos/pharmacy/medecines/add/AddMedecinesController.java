package pos.pharmacy.medecines.add;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;

import javafx.collections.FXCollections;
import javafx.collections.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import pos.pharmacy.dbConnection.Connectdb;

public class AddMedecinesController {
    Connectdb cn;
    public  AddMedecinesController() throws SQLException{
        cn = new Connectdb();
       
    }

   @FXML
    private TextField name_txt;

    @FXML
    private TextField price_txt;

    @FXML
    private TextField qt_txt;

    @FXML
    private TextField notify_txt;

    @FXML
    private TextField company;

    @FXML
    private TextArea description_txt;

    @FXML
    private DatePicker expiration_txt;

    @FXML
    private DatePicker fabrication_txt;

    @FXML
    private TextField tax_txt;
    
    @FXML
    private TextField key_txt;

    @FXML
    private ComboBox<String> type_txt;

    @FXML
    private Button type;

    @FXML
    private Button save;
    
    @FXML
    void handleNumeric(KeyEvent event) {
       String price = price_txt.getText();
       String tax = tax_txt.getText();
       String qt = qt_txt.getText();
       String notify = notify_txt.getText();
       
      String text = ((TextField)event.getSource()).getText();   
      String result = "";
      result = text.replaceAll("[^0-9]","");
      System.out.println(result);
      ((TextField)event.getSource()).setText(result);               
       
    }

    @FXML
    void handleSave(ActionEvent event) throws SQLException {
        saveItem();
        saveMedecine();
    }
    
    @FXML
    void handleType(ActionEvent event) {

    }
     @FXML
    public void initialize() throws SQLException {
       
         addTypeToCombo();
    }
    
    
    void addTypeToCombo() throws SQLException{
        String values = "*";
        ResultSet rs = cn.selectFrom("type", values, "");
        //List<String> types = new ArrayList<String>();
        ObservableList<String> types = FXCollections.observableArrayList();
        while (rs.next()) {
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            types.addAll(rs.getString(2)); 
            
        }
        
        type_txt.setItems(types);
         
    }
    
    Integer findType() throws SQLException{
        String values  = " type_id";
        String where = "type_name = "+type_txt.getValue();
        Integer result = 0 ;
        ResultSet rs = cn.selectFrom("type", values, "");
        
        if(rs.next()){
            result = rs.getInt(1);
        }
        
        return result;
    }
    
    void saveItem() throws SQLException{
        // get current date
        LocalDateTime date = LocalDateTime.now(); 
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedDate = date.format(myFormatObj);
       
       Integer type =  findType() ;
       //add values
        String val = ""+type+""
                + ",\""+key_txt.getText()+ "\""
                +",\""+name_txt.getText()+ "\""                
                + ","+price_txt.getText()+""
                + ","+tax_txt.getText()+""
                + ","+qt_txt.getText()+""
                + ","+notify_txt.getText()+""
                + ",\""+formattedDate+"\""
                + ",\""+formattedDate+"\"";
            
        cn.insertInto("item",""
                + " type_id,item_key,item_name,"
                + "item_price,tax,"
                + "item_qt,notify_qt,"
                + "create_at,modify_at",
                val);
        
    }
    
    void saveMedecine() throws SQLException{
        
       // get last inserted item
        ResultSet rs = cn.selectFrom(" item", " max(item_id)", "");
        int item_id = 0 ;
        if(rs.next())
             item_id = rs.getInt(1);
        
        
        String val = ""+item_id+""
                   +",\""+description_txt.getText()+"\""
                   + ",\""+company.getText()+"\""
                   +",\""+expiration_txt.getValue()+"\""
                   + ",\""+fabrication_txt.getValue()+"\"";
        cn.insertInto("medecine","item_id,description, company, expiration, fabrication", val);
        
    }
}
