/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.InstanciaTO;
import persistencia.PGSql;

/**
 *
 * @author William
 */
public class InstanciaDAL {

    private static InstanciaDAL instancia;

    private InstanciaDAL() {
        PGSql.getInstance().exec("SET search_path TO aeroporto;");
    }

    public void delete(InstanciaTO i) throws SQLException {
        String SQL = "DELETE FROM instancia WHERE data_viagem = '" + i.getDate() + "' AND id_trecho = " + i.getTrecho().getIdTrecho() + ";";
        PGSql.getInstance().execError(SQL);
    }

    public InstanciaTO create(int id_trecho, String data) throws Exception {
        String SQL = "INSERT INTO instancia (id_trecho, data_viagem) VALUES (" + id_trecho + ", '" + data + "');";
        try {
            PGSql.getInstance().execError(SQL);
            InstanciaTO i = new InstanciaTO();
            i.setDate(data);
            i.setTrecho(TrechoDAL.findById(id_trecho));
            return i;
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            throw new Exception(ex.toString());
        }
    }

    public static InstanciaDAL getInstance() {
        if (InstanciaDAL.instancia == null) {
            InstanciaDAL.instancia = new InstanciaDAL();
        }
        return InstanciaDAL.instancia;
    }

    public ArrayList<InstanciaTO> findByIdTrecho(int id_trecho) {
        String SQL = "SELECT * FROM instancia WHERE id_trecho = " + id_trecho + ";";
        ArrayList<InstanciaTO> i = new ArrayList<>();
        InstanciaTO in;
        try {
            ResultSet rs = PGSql.getInstance().query(SQL);
            if (rs != null) {
                while (rs.next()) {
                    in = new InstanciaTO();
                    in.setTrecho(TrechoDAL.findById(id_trecho));
                    in.setDate(rs.getString("data_viagem"));
                    i.add(in);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return i;

    }

    public InstanciaTO find(String data_viagem, int id_trecho) {
        InstanciaTO i = new InstanciaTO();
        String SQL_FIND = "SELECT * FROM instancia WHERE data_viagem = '" + data_viagem + "' AND id_trecho = " + id_trecho + ";";
        ResultSet rs = PGSql.getInstance().query(SQL_FIND);
        try {
            if (rs.next()) {
                i.setDate(rs.getString("data_viagem"));
                i.setTrecho(TrechoDAL.findById(id_trecho));
                return i;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
}
