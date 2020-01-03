/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.pharmacy.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author icom
 */
public class Connectdb {
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pahrmacy_pos";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    private static final String CREATEDB_QUERY = "CREATE DATABASE IF NOT EXISTS pahrmacy_pos";
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS ";
    private static final String SELECT_QUERY = "SELECT";
    
    Connection conn;
    Statement s;
    public Connectdb(){
        try {
            this.conn = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);
            this.s = conn.createStatement();
            s.executeUpdate(CREATEDB_QUERY);
            //System.out.println("db connnection done !!");
            
        } catch (SQLException ex) {
            Logger.getLogger(Connectdb.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    
    public void createTable(String table_name, String query) throws SQLException{
        String result = CREATE_TABLE_QUERY+table_name+"("
                +query
                +")"; 
        s.executeUpdate(result);
    }
  
    public ResultSet  selectFrom(String table_name, String values,String where) throws SQLException{
        ResultSet result_set = null;
        if( where.isEmpty()){
            String query = SELECT_QUERY+values+"from"+table_name;
            result_set = s.executeQuery(query);
        }
        else{
            
            String query = SELECT_QUERY+values+"\n from "+table_name
                            +"\n where "+where;
            result_set = s.executeQuery(query);
        }
        
        return result_set;
    }
    
    
}
