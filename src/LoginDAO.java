
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
        this.Login = Login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }
    public boolean Logar() throws SQLException{
        Statement Log = conectar().createStatement();
        ResultSet dados;
        dados = Log.executeQuery("SELECT * FROM Usuarios WHERE Login = '" + this.getLogin()
                + "' AND Senha = PASSWORD('" + this.getSenha() + "') ");
        return (dados.next());
    }
    
    public void InserirLogin() throws SQLException{
        String SQLInsere = "INSERT INTO Usuarios(Login, Senha) VALUES (?,?)";
        PreparedStatement Inserir = conectar().prepareStatement(SQLInsere);
        Inserir.setString(1, this.getLogin());
        Inserir.setString(2, this.getSenha());        
    }
}