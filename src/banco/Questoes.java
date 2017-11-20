/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Couth
 */
public class Questoes {
    public void inserirQuestaoAberta(String e, int d, String m) throws SQLException{
        String inserir = "insert into questoes values(?,?,?,?,?,?,?,?,?,?,?)";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(inserir);
        p.setInt(1, 0);
        p.setString(2, e);
        p.setInt(3, d);
        p.setInt(4, 0); //NÃO ESTÁ CERTO
        p.setString(5, m);
        p.setString(6, "");
        p.setString(7, "");
        p.setString(8, "");
        p.setString(9, "");
        p.setString(10, "");
        p.setString(11, "");
        p.executeUpdate();
    }
    
    public void inserirQuestaoFechada(String e, int d, String m, String letraA, String letraB, String letraC,
                                      String letraD, String letraE, String letraF) throws SQLException{
        String inserir = "insert into questoes values(?,?,?,?,?,?,?,?,?,?,?)";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(inserir);
        p.setInt(1, 0);
        p.setString(2, e);
        p.setInt(3, d);
        p.setInt(4, 0); //NÃO ESTÁ CERTO
        p.setString(5, m);
        p.setString(6, letraA);
        p.setString(7, letraB);
        p.setString(8, letraC);
        p.setString(9, letraD);
        p.setString(10, letraE);
        p.setString(11, letraF);
        p.executeUpdate();
    }
    
    public ResultSet pegarDisciplinas() throws SQLException { 
        String pesquisar = "select NomeDisciplinas from disciplinas";
        Connection con = new ConexaoDAO().conectar();        
        PreparedStatement p = con.prepareStatement(pesquisar);
        ResultSet rs = p.executeQuery();        
        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
    }
}
