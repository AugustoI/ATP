/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Couth
 */
public class Questoes {
    public void inserirQuestaoAberta(String e, int d, String m, int idConteudo) throws SQLException{
        String inserir = "insert into questoes values(?,?,?,?,?,?,?,?,?,?,?)";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(inserir);
        p.setInt(1, 0);
        p.setString(2, e);
        p.setInt(3, d);
        p.setInt(4, idConteudo);
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
                                      String letraD, String letraE, String letraF, int idConteudo) throws SQLException{
        String inserir = "insert into questoes values(?,?,?,?,?,?,?,?,?,?,?)";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(inserir);
        p.setInt(1, 0);
        p.setString(2, e);
        p.setInt(3, d);
        p.setInt(4, idConteudo);
        p.setString(5, m);
        p.setString(6, letraA);
        p.setString(7, letraB);
        p.setString(8, letraC);
        p.setString(9, letraD);
        p.setString(10, letraE);
        p.setString(11, letraF);
        p.executeUpdate();
    }
    
    public void inserirImagem(int id, InputStream img, int pos) throws SQLException{
        String inserir = "insert into imagemquest values(?,?,?,?)";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(inserir);
        p.setInt(1, 0);
        p.setInt(2, id);
        p.setBinaryStream(3, img);
        p.setInt(4, pos);
        p.executeUpdate();
    }
    
    public ResultSet pegarDisciplinasID(String nome) throws SQLException{
        String conteudo = "select Disciplinas_ID from disciplinas where NomeDisciplinas=?";
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
    
    public ResultSet pegarConteudosID(String nome) throws SQLException{
        String conteudo = "select Conteudos_ID from conteudos where NomeConteudos=?";
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
    
    public ResultSet pegarConteudos(String nome) throws SQLException { 
        String pesquisar = "select NomeConteudos from conteudos where ID_Disciplinas="
                + "(select Disciplinas_ID from disciplinas where NomeDisciplinas=?)";        
        Connection con = new ConexaoDAO().conectar();        
        PreparedStatement p = con.prepareStatement(pesquisar);
        p.setString(1, nome);        
        ResultSet rs = p.executeQuery();        
        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
    }
}
