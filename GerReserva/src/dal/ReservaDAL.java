/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.ReservaTO;
import persistencia.PGSql;

/**
 *
 * @author William
 */
public abstract class ReservaDAL {

    static final String SQL_INSERT_RESERVA = "INSERT INTO reserva (cod_reserva, nome) VALUES (nextval('cod_reserva_reserva_seq'), '%s');";

    public static ReservaTO createReserva(String nome) throws SQLException {
        Statement stm;
        stm = PGSql.getInstance().getConnection().createStatement();
        stm.executeUpdate(String.format(SQL_INSERT_RESERVA, nome), Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stm.getGeneratedKeys();
        ReservaTO r = new ReservaTO();
        if (rs.next()) {
            r.setCod_reserva(rs.getInt(1));
            r.setNome(nome);
            return r;
        }
        return null;
    }

    public static ReservaTO findByCod(int cod) {
        String SQL_FIND_BY_COD = "SELECT * FROM reserva WHERE cod_reserva = " + cod + ";";
        ReservaTO r = new ReservaTO();
        try {
            ResultSet rs = PGSql.getInstance().query(SQL_FIND_BY_COD);
            if (rs.next()) {
                r.setCod_reserva(rs.getInt("cod_reserva"));
                r.setNome(rs.getString("nome"));
                return r;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static boolean delete(ReservaTO reservaTO) {
        try {
            String SQL_DELETE = "DELETE FROM reserva WHERE cod_reserva = " + reservaTO.getCod_reserva() + ";";
            PGSql.getInstance().exec(SQL_DELETE);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
