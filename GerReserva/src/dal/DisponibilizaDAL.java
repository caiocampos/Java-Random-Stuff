/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.AeronaveTO;
import model.AssentoTO;
import model.DisponibilizaTO;
import model.InstanciaTO;
import model.TrechoTO;
import persistencia.PGSql;

/**
 *
 * @author William
 */
public abstract class DisponibilizaDAL {
    
    static final String FIND_BY_ORIG_DEST_DISPO = "SELECT *, d.cod_reserva AS codigo_reserva "
            + "FROM trecho t "
            + "INNER JOIN instancia i ON i.id_trecho = t.id_trecho "
            + "INNER JOIN disponibiliza d ON d.id_trecho = i.id_trecho AND d.data_viagem = i.data_viagem "
            + "INNER JOIN assento a ON a.fila = d.fila AND a.numero = d.numero AND a.cod_aeronave = d.cod_aeronave "
            + "WHERE origem = '%s' AND destino = '%s';";
    static final String SQL_SAVE = "INSERT INTO di";
    
    public static ArrayList<DisponibilizaTO> findArray(String[] campo, String[] dado, String[] operador, String orderBy){
        ArrayList<DisponibilizaTO> ald = new ArrayList<>();
        String where = "";
        for(int i = 0; i < campo.length; i++){
            where += String.format("{0} {1} {2}{4}", campo[i], operador[i], dado[i], (i+1 < campo.length ? " AND " : ""));
        }
        String SQL = String.format("SELECT * FROM disponibiliza WHERE {1}", where);
        
        return ald;
    }
    
    public static boolean delete(DisponibilizaTO d) throws SQLException {
        String SQL = "DELETE FROM disponibiliza WHERE "
                + "fila = '" + d.getAssento().getFila() + "' "
                + "AND numero = " + d.getAssento().getNumero() + " "
                + "AND cod_aeronave = '" + d.getAssento().getAeronave().getCodAeronave() + "' "
                + "AND data_viagem = '" + d.getInstancia().getDate() + "' "
                + "AND id_trecho = " + d.getInstancia().getTrecho().getIdTrecho() + ";";
        PGSql.getInstance().execError(SQL);
        return true;
    }
    
    public static ArrayList<DisponibilizaTO> findDisponibilizados(String data_viagem, int id_trecho) throws SQLException {
        ArrayList<DisponibilizaTO> ald = new ArrayList<>();
        String SQL = "SELECT * FROM disponibiliza WHERE id_trecho = " + id_trecho + " AND data_viagem = '" + data_viagem + "';";
        DisponibilizaTO d;
        ResultSet rs = PGSql.getInstance().query(SQL);
        if (rs != null) {
            while (rs.next()) {
                d = DisponibilizaDAL.find(rs.getString("fila").toCharArray()[0], rs.getInt("numero"), rs.getString("cod_aeronave"), rs.getString("data_viagem"), rs.getInt("id_trecho"));
                if (d != null) {
                    ald.add(d);
                }
            }
        }
        return ald;
    }
    
    public static int getDisponibilizados(int id_trecho, String data_viagem) throws SQLException {
        String SQL = "SELECT COUNT(*) AS total FROM disponibiliza WHERE data_viagem = '" + data_viagem + "' AND id_trecho = " + id_trecho + ";";
        ResultSet rs = PGSql.getInstance().queryError(SQL);
        if (rs != null && rs.next()) {
            return rs.getInt("total");
        } else {
            return -1;
        }
    }
    
    public static int getDisponibilizadosReservados(int id_trecho, String data_viagem) throws SQLException {
        String SQL = "SELECT COUNT(*) AS total FROM disponibiliza WHERE data_viagem = '" + data_viagem + "' AND id_trecho = " + id_trecho + " AND cod_reserva IS NOT NULL;";
        ResultSet rs = PGSql.getInstance().queryError(SQL);
        if (rs != null && rs.next()) {
            return rs.getInt("total");
        } else {
            return -1;
        }
    }
    
    public static DisponibilizaTO create(char fila, int numero, String cod_aeronave, String data_viagem, int id_trecho) {
        String SQL = "INSERT INTO disponibiliza (fila, numero, cod_aeronave, data_viagem, id_trecho) VALUES ("
                + "'" + fila + "', "
                + "" + numero + ", "
                + "'" + cod_aeronave + "', "
                + "'" + data_viagem + "', "
                + "" + id_trecho + ");";
        try {
            PGSql.getInstance().exec(SQL);
            DisponibilizaTO d = new DisponibilizaTO();
            d.setAssento(AssentoDAL.getInstance().findOne(fila, numero, cod_aeronave));
            d.setInstancia(InstanciaDAL.getInstance().find(data_viagem, id_trecho));
            return d;
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return null;
    }
    
    public static DisponibilizaTO update(DisponibilizaTO disponibilizaTO) {
        String SQL_UPDATE = "UPDATE disponibiliza SET "
                + "cod_reserva = " + disponibilizaTO.getCodReserva() + " WHERE "
                + "fila = '" + disponibilizaTO.getAssento().getFila() + "' "
                + "AND numero = " + disponibilizaTO.getAssento().getNumero() + " "
                + "AND cod_aeronave = '" + disponibilizaTO.getAssento().getAeronave().getCodAeronave() + "' "
                + "AND data_viagem = '" + disponibilizaTO.getInstancia().getDate() + "' "
                + "AND id_trecho = '" + disponibilizaTO.getInstancia().getTrecho().getIdTrecho() + "';";
        PGSql.getInstance().exec(SQL_UPDATE);
        return disponibilizaTO;
    }
    
    public static DisponibilizaTO save(DisponibilizaTO disponibilizaTO) throws SQLException {
        return null;
    }
    
    public static void removeReserva(DisponibilizaTO d) {
        String SQL = "UPDATE disponibiliza SET cod_reserva = NULL WHERE "
                + "fila = '" + d.getAssento().getFila() + "' "
                + "AND numero = " + d.getAssento().getNumero() + " AND "
                + "cod_aeronave = '" + d.getAssento().getAeronave().getCodAeronave() + "';";
        try {
            PGSql.getInstance().exec(SQL);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static DisponibilizaTO find(char fila, int numero,
            String cod_aeronave, String data_viagem, int id_trecho) {
        String SQL_FIND = "SELECT * FROM disponibiliza d "
                + "WHERE fila = '" + fila + "' AND "
                + "numero = " + numero + " AND "
                + "cod_aeronave = '" + cod_aeronave + "' AND "
                + "data_viagem = '" + data_viagem + "' AND "
                + "id_trecho =  " + id_trecho + ";";
        try {
            ResultSet rs = PGSql.getInstance().query(SQL_FIND);
            if (rs.next()) {
                DisponibilizaTO d = new DisponibilizaTO();
                d.setAssento(AssentoDAL.getInstance().findOne(fila, numero, cod_aeronave));
                d.setInstancia(InstanciaDAL.getInstance().find(data_viagem, id_trecho));
                d.setCodReserva(rs.getInt("cod_reserva") > 0 ? rs.getInt("cod_reserva") : -1);
                return d;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public static ArrayList<DisponibilizaTO> find(String origem, String destino) {
        java.sql.ResultSet rs = PGSql.getInstance().query(String.format(FIND_BY_ORIG_DEST_DISPO, origem, destino));
        TrechoTO trechoTO;
        DisponibilizaTO disponibilizaTO;
        InstanciaTO instanciaTO;
        AssentoTO assentoTO;
        AeronaveTO aeronave;
        ArrayList<DisponibilizaTO> trechos = new ArrayList<>();
        try {
            while (rs.next()) {
                trechoTO = new TrechoTO();
                disponibilizaTO = new DisponibilizaTO();
                instanciaTO = new InstanciaTO();
                assentoTO = new AssentoTO();
                
                trechoTO.setIdTrecho(rs.getInt("id_trecho"));
                trechoTO.setSiglaComp(rs.getString("sigla_comp"));
                trechoTO.setNumVoo(rs.getString("num_voo"));
                trechoTO.setOrigem(rs.getString("origem"));
                trechoTO.setDestino(rs.getString("destino"));
                trechoTO.setHorario(rs.getString("horario").substring(0, 8));
                trechoTO.setSeg(rs.getBoolean("seg"));
                trechoTO.setTer(rs.getBoolean("ter"));
                trechoTO.setQua(rs.getBoolean("qua"));
                trechoTO.setQui(rs.getBoolean("qui"));
                trechoTO.setSex(rs.getBoolean("sex"));
                trechoTO.setSab(rs.getBoolean("sab"));
                trechoTO.setDom(rs.getBoolean("dom"));
                
                aeronave = AeronaveDAL.getInstance().findOne(rs.getString("cod_aeronave"));
                assentoTO.setFila(rs.getString("fila").toCharArray()[0]);
                assentoTO.setNumero(rs.getInt("numero"));
                assentoTO.setAeronave(aeronave);
                
                instanciaTO.setDate(rs.getString("data_viagem"));
                instanciaTO.setTrecho(trechoTO);
                disponibilizaTO.setInstancia(instanciaTO);
                disponibilizaTO.setAssento(assentoTO);
                disponibilizaTO.setCodReserva(rs.getInt("codigo_reserva"));
                
                trechos.add(disponibilizaTO);
            }
            return trechos;
        } catch (Exception ex) {
            return null;
        }
    }
}
