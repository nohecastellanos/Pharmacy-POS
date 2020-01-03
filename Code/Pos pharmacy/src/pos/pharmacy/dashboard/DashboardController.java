/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.pharmacy.dashboard;

import java.sql.SQLException;
import pos.pharmacy.dbConnection.Connectdb;
import sun.security.pkcs11.Secmod;

/**
 *
 * @author icom
 */
public class DashboardController {
    Connectdb cn;
    TreateDashboard  tds;
    public DashboardController() throws SQLException{
       cn =  new Connectdb();
       tds = new TreateDashboard(cn);
       tds.createItemTable(cn);
       tds.createMedecinesTable(cn);
       tds.createOthersTable(cn);
    }
}
