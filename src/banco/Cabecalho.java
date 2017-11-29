/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package banco;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Couth
 */
public class Cabecalho {
    public void inserirCabecalho(String n, String i, String t, String subt, String serie, String valor,
            InputStream file, String fileName) throws SQLException {
        String inserir = "insert into cabecalho values(?,?,?,?,?,?,?,?,?)";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(inserir);
        p.setInt(1, 0);
        p.setString(2, n);
        p.setString(3, i);
        p.setString(4, t);
        p.setString(5, subt);
        p.setString(6, serie);
        p.setString(7, valor);
        p.setBinaryStream(8, file);
        p.setString(9, fileName);
        p.executeUpdate();
    }
}
