package pos.pharmacy.medecines.add;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.Date;  
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pos.pharmacy.dbConnection.Connectdb;

public class AddMedecinesController {
    Connectdb cn;
    public  AddMedecinesController(){
        cn = new Connectdb();
    }

    @FXML
    private TextField name_txt;

    @FXML
    private TextField price_txt;

    @FXML
    private TextField qt_txt;

     @FXML
    private DatePicker expiration_txt;

    @FXML
    private DatePicker fabrication_txt;

    @FXML
    private TextField notify_txt;

    @FXML
    private TextField company;

    @FXML
    private TextArea description_txt;

    @FXML
    private Button save;


    @FXML
    void handleSave(ActionEvent event) throws SQLException {
        saveItem();
        saveMedecine();
    }
    
    void saveItem() throws SQLException{
        // get current date
        LocalDateTime date = LocalDateTime.now(); 
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedDate = date.format(myFormatObj);
       System.out.println(date);
       //add values
        String val = "\""+name_txt.getText()+ "\""
                + ","+price_txt.getText()+""
                + ","+qt_txt.getText()+""
                + ","+notify_txt.getText()+""
                + ",\""+formattedDate+"\""
                + ",\""+formattedDate+"\"";
            
        cn.insertInto("item", "item_name,item_price,"
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
