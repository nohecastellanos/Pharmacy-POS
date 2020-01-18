/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.pharmacy.dashboard;
import java.sql.SQLException;
import  pos.pharmacy.dbConnection.Connectdb;
/**
 * this class create tables in db if they doesnnt exist
 * @author toumi abderramane
 */
public class TreateDashboard {
    Connectdb cn;
    public TreateDashboard(){
          
    }
     public void createTypeTable(Connectdb  cn) throws SQLException{
     String  query  =  "type_id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                       +"type_name varchar(50) NOT NULL unique,"
                       + "create_at datetime not null,"
                       + "modify_at datetime not null";
      cn.createTable("type", query);
    }
    
    
    public void createItemTable(Connectdb  cn) throws SQLException{
     String  query  = "item_id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                        +"type_id INT NOT NULL,"                         
                        +"item_key varchar(25) NOT NULL UNIQUE,"
                        +"item_name varchar(25) NOT NULL UNIQUE,"                                   
                        +"item_price float not null,"
                        + "tax float not null,"
                        + "item_qt integer not null,"
                        + "notify_qt integer not null,"
                        + "create_at datetime not null,"
                        + "modify_at datetime not null,"
      + "foreign key(type_id) references type(type_id) ON DELETE CASCADE";
      cn.createTable("item", query);
    }
    
      public void createMedecinesTable(Connectdb  cn) throws SQLException{
     String  query  = "medecine_id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    + "item_id INT,"
                    +"description varchar(100) not null,"
                    +"company varchar(50) not null,"
                    + "expiration datetime not null,"
                    + "fabrication datetime not null,"
             + "foreign key(item_id) references item(item_id) ON DELETE CASCADE";
      cn.createTable("medecine", query);
    }
      
    public void createOthersTable(Connectdb  cn) throws SQLException{
     String  query  = "other_id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    + "item_id int,"
                    +"description varchar(100) not null,"
                    +"company varchar(50) not null,"
                    + "foreign key(item_id) references item(item_id) ON DELETE CASCADE";
                  
      cn.createTable("other", query);
    }

    
    
    
}
