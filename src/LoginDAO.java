
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
    public boolean Logar(String Login, String Senha) throws SQLException{
        Statement Log = conectar().createStatement();
        ResultSet dados;
        dados = Log.executeQuery("SELECT * FROM Usuarios WHERE Login = '" + this.getLogin()
                + "' AND Senha = PASSWORD('" + this.getSenha() + "') ");
        return false;        
    }
}
