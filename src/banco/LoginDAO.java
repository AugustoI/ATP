package banco;


import banco.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author GUSTAVO
 */
public class LoginDAO extends Banco{
    private String Login;
    private String Senha;

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        if ((!"".equals(Login)) && (!" ".equals(Login))){
            this.Login = Login.trim().toUpperCase();
        }else{
            throw new RuntimeException("Login Inválido!");
        }
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        if ((!"".equals(Senha)) && (!" ".equals(Senha))){
            this.Senha = Senha;
        }else{            
            throw new RuntimeException("Senha Inválida!");
        }
    }
    public boolean Logar() throws SQLException{
        Statement Log = conectar().createStatement();
        ResultSet dados;
        dados = Log.executeQuery("SELECT * FROM Usuarios WHERE Login = '" + this.getLogin()
                + "' AND Senha = PASSWORD('" + this.getSenha() + "') ");
        return (dados.next());
    }
    
    public void InserirLogin() throws SQLException{
        String SQLInsere = "INSERT INTO Usuarios VALUES (0,?,PASSWORD(?))";
        PreparedStatement Inserir = conectar().prepareStatement(SQLInsere);
        Inserir.setString(1, this.getLogin());
        Inserir.setString(2, this.getSenha());        
        Inserir.executeUpdate();
    }
    
     public void EditarLogin() throws SQLException{
        String SQLInsere = " UPDATE Usuarios SET Login = ?, Senha = ? WHERE Login = ? ";
        PreparedStatement Editar = conectar().prepareStatement(SQLInsere);
        Editar.setString(1, this.getLogin());
        Editar.setString(2, this.getSenha());        
        Editar.setString(3, this.getLogin());        
        Editar.executeUpdate();
    }
     
    public void ExcluirLogin() throws SQLException{
        String SQLInsere = "DELETE FROM Usuarios WHERE Login = ? ";
        PreparedStatement Editar = conectar().prepareStatement(SQLInsere);
        Editar.setString(1, this.getLogin());        
        Editar.executeUpdate();
    } 
    
    public ResultSet BuscaUsuario() throws SQLException{
        Statement Log = conectar().createStatement();
        ResultSet dados;
        dados = Log.executeQuery("SELECT Login, Senha FROM Usuarios WHERE Login LIKE '%" + this.getLogin() + "%'");        
        if (dados.next()){
            return (dados);
        }else {
            throw new RuntimeException("Usuário não encontrado!");
        }
        
    }
    
    public void inserirUsuario(String us, String ps) throws SQLException {
        String inserir = "insert into usuarios values(?,?,password(?))";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(inserir);
        p.setInt(1, 0);
        p.setString(2, us);
        p.setString(3, ps);
        p.executeUpdate();
    }
    
    public boolean login(String us, String ps) throws SQLException {
        String pesquisar = "select * from usuarios where Login=? and Senha=password(?)";
        Connection con = new ConexaoDAO().conectar();
        PreparedStatement p = con.prepareStatement(pesquisar);
        p.setString(1, us);
        p.setString(2, ps);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }  
    }
}
