package persistencia;

import java.sql.*;

/**
 *
 * @author William
 */
public class PGSql {

    private Connection connection;
    private String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private String login = "postgres";
    private String passw = "123456";
    private static PGSql instancia;
    private static boolean setPath = false;

    public Connection getConnection() {
        return this.connection;
    }

    private PGSql() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(this.url, this.login, this.passw);
        } catch (ClassNotFoundException cnfex) {
            System.out.println(cnfex.getMessage());
            System.exit(1);
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        }
    }

    public static PGSql getInstance() {
        try {
            if (PGSql.instancia == null) {
                PGSql.instancia = new PGSql();
            }
            return PGSql.instancia;
        } catch (Exception e) {
            return null;
        }
    }

    public ResultSet queryError(String query) throws SQLException {
        if (!setPath) {
            exec("SET SEARCH_PATH TO aeroporto;");
            setPath = true;
        }
        System.out.println("Query: " + query);
        Statement statement;
        ResultSet rs;
        statement = this.connection.createStatement();
        rs = statement.executeQuery(query);
        //statement.close();
        return rs;
    }

    public ResultSet query(String query) {
        if (!setPath) {
            exec("SET SEARCH_PATH TO aeroporto;");
            setPath = true;
        }
        System.out.println("Query: " + query);
        Statement statement;
        ResultSet rs;
        try {
            statement = this.connection.createStatement();
            rs = statement.executeQuery(query);
            //statement.close();
            return rs;
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
            return null;
        }
    }

    public boolean exec(String query) {
        System.out.println("Exec Query: " + query);
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.execute(query);
            return true;
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
            return false;
        }
    }

    public boolean execError(String query) throws SQLException {
        System.out.println("Exec Query: " + query);
        Statement statement;
        statement = this.connection.createStatement();
        statement.execute(query);
        return true;
    }
    
}
