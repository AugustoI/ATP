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
public class DisciConteudos {
    public void inserirDisciplina(String disciplina) throws SQLException{
        String inserir = "insert into disciplinas values(?,?)";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(inserir);
        p.setInt(1, 0);
        p.setString(2, disciplina);
        p.executeUpdate();
    }
    
    public ResultSet pegarDisciplinas() throws SQLException { 
        String pesquisar = "select * from disciplinas";
        Connection con = new ConexaoDAO().conectar();        
        PreparedStatement p = con.prepareStatement(pesquisar);
        ResultSet rs = p.executeQuery();        
        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
    }
    
    public ResultSet pegarNomeDisciplina() throws SQLException { 
        String pesquisar = "select NomeDisciplinas from disciplinas inner join conteudos on Disciplinas_ID = ID_Disciplinas";
        Connection con = new ConexaoDAO().conectar();        
        PreparedStatement p = con.prepareStatement(pesquisar);
        ResultSet rs = p.executeQuery();        
        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
    }
    
    public ResultSet pegarIdDisciplina(String disciplina) throws SQLException { 
        String pesquisar = "select Disciplinas_ID from disciplinas where NomeDisciplinas = ?";
        Connection con = new ConexaoDAO().conectar();        
        PreparedStatement p = con.prepareStatement(pesquisar);
        p.setString(1, disciplina);
        ResultSet rs = p.executeQuery();        
        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
    }
    
    public ResultSet pesquisarDisciplina(String enunciado) throws SQLException { 
        String pesquisar = "select * from disciplinas where NomeDisciplinas like ?";        
        Connection con = new ConexaoDAO().conectar();        
        PreparedStatement p = con.prepareStatement(pesquisar);
        p.setString(1, enunciado+"%");
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
    }
    
    public void excluirDisciplinaPeloId(int id) throws SQLException {
        String excluir = "delete from disciplinas where Disciplinas_ID=?";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(excluir);
        p.setInt(1, id);            
        p.executeUpdate();
    }
    
    
    
    public void inserirConteudo(String conteudo, String serie, int id) throws SQLException{
        String inserir = "insert into conteudos values(?,?,?,?)";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(inserir);
        p.setInt(1, 0);
        p.setString(2, conteudo);
        p.setString(3, serie);
        p.setInt(4, id);
        p.executeUpdate();
    }
    
    public ResultSet pegarConteudos() throws SQLException { 
        String pesquisar = "select * from conteudos";
        Connection con = new ConexaoDAO().conectar();        
        PreparedStatement p = con.prepareStatement(pesquisar);
        ResultSet rs = p.executeQuery();        
        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
    }
    
    public ResultSet pegarConteudos(String disciplina) throws SQLException { 
        String pesquisar = "select * from conteudos where ID_Disciplinas = "
                + "(select Disciplinas_ID from disciplinas where NomeDisciplinas=?)";
        Connection con = new ConexaoDAO().conectar();        
        PreparedStatement p = con.prepareStatement(pesquisar);
        p.setString(1, disciplina);
        ResultSet rs = p.executeQuery();        
        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
    }
    
    public ResultSet pesquisarConteudos(int id) throws SQLException { 
        String pesquisar = "select * from conteudos where ID_Disciplinas = ?";        
        Connection con = new ConexaoDAO().conectar();        
        PreparedStatement p = con.prepareStatement(pesquisar);
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
    }
    
    public ResultSet pesquisarConteudos(String conteudo) throws SQLException { 
        String pesquisar = "select * from disciplinas inner join conteudos on Disciplinas_ID = ID_Disciplinas where NomeConteudos like ?";        
        Connection con = new ConexaoDAO().conectar();        
        PreparedStatement p = con.prepareStatement(pesquisar);
        p.setString(1, conteudo+"%");
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
    }
    
    //TESTE
    public ResultSet pegarTUDO() throws SQLException { 
        String pesquisar = "select * from disciplinas inner join conteudos on Disciplinas_ID = ID_Disciplinas;";        
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
