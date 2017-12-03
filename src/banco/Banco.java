package banco;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author GUSTAVO
 */
public class Banco {    
    public Connection conectar() throws SQLException{        
        return DriverManager.getConnection("jdbc:mysql://localhost/quest","root","061099");        
    }     
    public ResultSet executaSQL(String SQL) throws SQLException{
        Statement con = conectar().createStatement();
        ResultSet dados;
        dados = con.executeQuery(SQL);        
        return (dados);        
    }
}
