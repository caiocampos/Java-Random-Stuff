package dal;

import model.LoginTO;
import persistencia.PGSql;

public class LoginDAL {

    final String SQL_SALVAR = "INSERT INTO login (username, senha) VALUES ('%s', '%s');";
    final String SQL_UPDATE = "UPDATE login SET senha='%s' WHERE username='%s';";
    final String SQL_DELETE = "DELETE FROM login WHERE username='%s';";
    final String SQL_FIND_ONE = "SELECT * FROM login WHERE username = '%s';";
    private static LoginDAL instancia;

    private LoginDAL() {
    }

    public static LoginDAL getInstance() {
        if (LoginDAL.instancia == null) {
            LoginDAL.instancia = new LoginDAL();
        }
        return LoginDAL.instancia;
    }
    
    public LoginTO findOneByUsername(String username){
        LoginTO loginTO = new LoginTO();
        java.sql.ResultSet rs = PGSql.getInstance().query(String.format(SQL_FIND_ONE, username));
        try {
            rs.next();
            loginTO.setUsername(rs.getString("username"));
            loginTO.setSenha(rs.getString("senha"));
            loginTO.setTipo(rs.getInt("tipo"));
            return loginTO;
        }catch(Exception ex){
            return null;
        }
    }
    
    public boolean salvar(LoginTO loginObj) {
        return PGSql.getInstance().exec(String.format(SQL_SALVAR, loginObj.getUsername(), loginObj.getSenha()));
    }

    public boolean atualizar(LoginTO loginObj) {
        return PGSql.getInstance().exec(String.format(SQL_UPDATE, loginObj.getSenha(), loginObj.getUsername()));
    }

    public boolean deletar(LoginTO loginObj) {
        return PGSql.getInstance().exec(String.format(SQL_DELETE, loginObj.getUsername()));
    }
}
