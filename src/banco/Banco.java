package banco;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author GUSTAVO
 */
public class Banco {    
    public Connection conectar() throws SQLException{        
        return DriverManager.getConnection("jdbc:mysql://localhost/quest","root","061099");        
    }     
}
