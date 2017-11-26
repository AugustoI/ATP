/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.sql.Blob;
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
    
    public void inserirImagem(int id, Blob img, int pos) throws SQLException{
        String inserir = "insert into imagemquest values(?,?,?,?)";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(inserir);
        p.setInt(1, 0);
        p.setInt(2, id);
        p.setBlob(3, img);
        p.setInt(4, pos);
        p.executeUpdate();
    }
    
    public ResultSet pegarConteudosID(String nome) throws SQLException{
        String conteudo = "select Conteudos_ID from conteudos where ID_Disciplinas="
                + "(select Disciplinas_ID from disciplinas where NomeDisciplinas=?)";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(conteudo);
        p.setString(1, nome);        
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
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
