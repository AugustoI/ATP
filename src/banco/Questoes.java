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
    
    public ResultSet pegarUltimaQuestao() throws SQLException { 
        String pesquisar = "select max(Questoes_ID) as ult from questoes";        
        Connection con = new ConexaoDAO().conectar();        
        PreparedStatement p = con.prepareStatement(pesquisar);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
    }
    
    public ResultSet pegarQuestao() throws SQLException { 
        String pesquisar = "select Questoes_ID, Enunciado, Dificuldade, MultiplaEscolha from questoes";        
        Connection con = new ConexaoDAO().conectar();        
        PreparedStatement p = con.prepareStatement(pesquisar);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
    }
    
    public ResultSet pesquisarQuestao(String enunciado) throws SQLException { 
        String pesquisar = "select Questoes_ID, Enunciado, Dificuldade, MultiplaEscolha from questoes where Enunciado like ?";        
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
    
    public ResultSet pegarQuestaoPeloId(int id) throws SQLException { 
        String pesquisar = "select * from questoes where Questoes_ID=?";        
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
    
    public ResultSet pegarNomeDisciplinasPeloConteudosID(int id) throws SQLException { 
        String pesquisar = "select NomeDisciplinas from disciplinas where Disciplinas_ID="
                + "(select ID_Disciplinas from conteudos where Conteudos_ID=?);";        
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
    
    public ResultSet pegarNomeConteudosPeloConteudosID(int id) throws SQLException { 
        String pesquisar = "select NomeConteudos from conteudos where Conteudos_ID=?";        
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
    
    public void inserirQuestaoAbertaEditada(String e, int d, int idConteudo, int idQuestao) throws SQLException{
        String atualizar = "update questoes set Enunciado=?, Dificuldade=?, ID_Conteudos=? where Questoes_ID=?";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(atualizar);
        p.setString(1, e);
        p.setInt(2, d);
        p.setInt(3, idConteudo);
        p.setInt(4, idQuestao);
        p.executeUpdate();
    }
    
    public void inserirQuestaoFechadaEditada(String e, int d, String letraA, String letraB, String letraC,
                                      String letraD, String letraE, String letraF, int idConteudo, int idQuestao) throws SQLException{
        String atualizar = "update questoes set Enunciado=?, Dificuldade=?, ID_Conteudos=?, AlternativaA=?, AlternativaB=?, "
                + "AlternativaC=?, AlternativaD=?, AlternativaE=?, AlternativaF=? where Questoes_ID=?";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(atualizar);
        p.setString(1, e);
        p.setInt(2, d);      
        p.setInt(3, idConteudo);
        p.setString(4, letraA);
        p.setString(5, letraB);
        p.setString(6, letraC);
        p.setString(7, letraD);
        p.setString(8, letraE);
        p.setString(9, letraF);
        p.setInt(10, idQuestao);
        p.executeUpdate();
    }
    
    public void inserirImagemEditada(InputStream img, int pos, int idQuestao) throws SQLException{
        String atualizar = "update imagemquest set Imagem=?, POSICAO=? where ID_Questao=?";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(atualizar);
        p.setBinaryStream(1, img);
        p.setInt(2, pos);
        p.setInt(3, idQuestao);                
        p.executeUpdate();
    }
    
    public ResultSet confereSeTemImagem(int id) throws SQLException {
        String pesquisar = "select ID_Questao from imagemquest where ID_Questao=?";        
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
    
    public void excluirQuestaoPeloId(int id) throws SQLException {
        String excluir = "delete from imagemquest where ID_Questao=?";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(excluir);
        p.setInt(1, id);            
        p.executeUpdate();
        
        excluir = "delete from questoes where Questoes_ID=?";
        p = con.prepareStatement(excluir);
        p.setInt(1, id);            
        p.executeUpdate();
    }
}
