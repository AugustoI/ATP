
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GUSTAVO
 */
public class Banco {    
    public Connection conectar() throws SQLException{        
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1/Sisham","root","061099");
    }     
}
