/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Couth
 */
public class ConexaoDAO {
    public Connection conectar() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/Quest","root","joaoh123");
    }
}
