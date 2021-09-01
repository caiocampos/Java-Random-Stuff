/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.TrechoTO;
import persistencia.PGSql;

/**
 *
 * @author William
 */
public class TrechoDAL {

    final String SQL_SALVAR = "";
    final String FIND_BY_ORIG_DEST_DISPO = "SELECT t.* "
            + "FROM trecho t "
            + "INNER JOIN instancia i ON i.id_trecho = t.id_trecho "
            + "INNER JOIN disponibiliza d ON d.id_trecho = i.id_trecho AND d.data_viagem = i.data_viagem "
            + "INNER JOIN assento a ON a.fila = d.fila AND a.numero = d.numero AND a.cod_aeronave = d.cod_aeronave "
            + "WHERE origem = '%s' AND destino = '%s' AND cod_reserva IS NULL;";
    private static TrechoDAL instancia;

    private TrechoDAL() {
        PGSql.getInstance().exec("SET search_path TO aeroporto;");
    }

    public static TrechoTO salvar(TrechoTO t) throws SQLException {
        String SQL = "INSERT INTO trecho (sigla_comp, num_voo, origem, destino, "
                + "horario, seg, ter, qua, qui, sex, sab, dom) VALUES ("
                + "'" + t.getSiglaComp() + "', "
                + "'" + t.getNumVoo() + "', "
                + "'" + t.getOrigem() + "', "
                + "'" + t.getDestino() + "', "
                + "'" + t.getHorario() + "', "
                + "'" + (t.isSeg() ? "t" : "f") + "', "
                + "'" + (t.isTer() ? "t" : "f") + "', "
                + "'" + (t.isQua() ? "t" : "f") + "', "
                + "'" + (t.isQui() ? "t" : "f") + "', "
                + "'" + (t.isSex() ? "t" : "f") + "', "
                + "'" + (t.isSab() ? "t" : "f") + "', "
                + "'" + (t.isDom() ? "t" : "f") + "')";
        System.out.println("Query: " + SQL);
        Statement stm = PGSql.getInstance().getConnection().createStatement();
        stm.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stm.getGeneratedKeys();
        if (rs != null && rs.next()) {
            int id_trecho = rs.getInt("id_trecho");
            t.setIdTrecho(id_trecho);
        }

        return t;
    }

    public static TrechoTO update(TrechoTO t) {
        String SQL_UPDATE = "UPDATE trecho SET "
                + "sigla_comp = '" + t.getSiglaComp() + "', "
                + "num_voo = '" + t.getNumVoo() + "', "
                + "origem = '" + t.getOrigem() + "', "
                + "destino = '" + t.getDestino() + "', "
                + "horario = '" + t.getHorario() + "', "
                + "seg = '" + (t.isSeg() ? "t" : "f") + "', "
                + "ter = '" + (t.isTer() ? "t" : "f") + "', "
                + "qua = '" + (t.isQua() ? "t" : "f") + "', "
                + "qui = '" + (t.isQui() ? "t" : "f") + "', "
                + "sex = '" + (t.isSex() ? "t" : "f") + "', "
                + "sab = '" + (t.isSab() ? "t" : "f") + "', "
                + "dom = '" + (t.isDom() ? "t" : "f") + "' WHERE id_trecho = " + t.getIdTrecho() + ";";
        PGSql.getInstance().exec(SQL_UPDATE);
        return t;
    }

    public static TrechoTO findById(int id_trecho) {
        String SQL_FIND = "SELECT * FROM trecho WHERE id_trecho = " + id_trecho + ";";
        TrechoTO t = new TrechoTO();
        try {
            ResultSet rs = PGSql.getInstance().query(SQL_FIND);
            if (rs.next()) {
                t.setDestino(rs.getString("destino"));
                t.setOrigem(rs.getString("origem"));
                t.setSeg(rs.getBoolean("seg"));
                t.setTer(rs.getBoolean("ter"));
                t.setQua(rs.getBoolean("qua"));
                t.setQui(rs.getBoolean("qui"));
                t.setSex(rs.getBoolean("sex"));
                t.setSab(rs.getBoolean("sab"));
                t.setDom(rs.getBoolean("dom"));
                t.setIdTrecho(rs.getInt("id_trecho"));
                t.setSiglaComp(rs.getString("sigla_comp"));
                t.setNumVoo(rs.getString("num_voo"));
                t.setHorario(rs.getString("horario").substring(0, 8));
                return t;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public static ArrayList<TrechoTO> findAllQuery(String busca) {
        String SQL = "SELECT * FROM aeroporto.trecho "
                + "WHERE origem LIKE '%" + busca + "%' "
                + "OR destino LIKE '%" + busca + "%'"
                + "OR sigla_comp LIKE '%" + busca + "%' "
                + "ORDER BY id_trecho DESC;";
        ArrayList<TrechoTO> trechos = new ArrayList<>();
        TrechoTO t;
        try {
            ResultSet rs = PGSql.getInstance().query(SQL);
            while (rs.next()) {
                t = new TrechoTO();
                t.setSiglaComp(rs.getString("sigla_comp"));
                t.setNumVoo(rs.getString("num_voo"));
                t.setOrigem(rs.getString("origem"));
                t.setDestino(rs.getString("destino"));
                t.setHorario(rs.getString("horario").substring(0, 8));
                t.setSeg(rs.getBoolean("seg"));
                t.setTer(rs.getBoolean("ter"));
                t.setQua(rs.getBoolean("qua"));
                t.setQui(rs.getBoolean("qui"));
                t.setSex(rs.getBoolean("sex"));
                t.setSab(rs.getBoolean("sab"));
                t.setDom(rs.getBoolean("dom"));
                t.setIdTrecho(rs.getInt("id_trecho"));
                trechos.add(t);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return trechos;
    }

    public static ArrayList<TrechoTO> findAll() {
        String SQL = "SELECT * FROM trecho ORDER BY id_trecho DESC;";
        ArrayList<TrechoTO> trechos = new ArrayList<>();
        TrechoTO t;
        try {
            ResultSet rs = PGSql.getInstance().query(SQL);
            while (rs.next()) {
                t = new TrechoTO();
                t.setSiglaComp(rs.getString("sigla_comp"));
                t.setNumVoo(rs.getString("num_voo"));
                t.setOrigem(rs.getString("origem"));
                t.setDestino(rs.getString("destino"));
                t.setHorario(rs.getString("horario").substring(0, 8));
                t.setSeg(rs.getBoolean("seg"));
                t.setTer(rs.getBoolean("ter"));
                t.setQua(rs.getBoolean("qua"));
                t.setQui(rs.getBoolean("qui"));
                t.setSex(rs.getBoolean("sex"));
                t.setSab(rs.getBoolean("sab"));
                t.setDom(rs.getBoolean("dom"));
                t.setIdTrecho(rs.getInt("id_trecho"));
                trechos.add(t);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return trechos;
    }

    public static TrechoDAL getInstance() {
        if (TrechoDAL.instancia == null) {
            TrechoDAL.instancia = new TrechoDAL();
        }
        return TrechoDAL.instancia;
    }

    public ArrayList<TrechoTO> findTrecho(String origem, String destino) {
        java.sql.ResultSet rs = PGSql.getInstance().query(String.format(FIND_BY_ORIG_DEST_DISPO, origem, destino));
        TrechoTO trechoTO;
        ArrayList<TrechoTO> trechos = new ArrayList<>();
        try {
            while (rs.next()) {
                trechoTO = new TrechoTO();
                trechoTO.setIdTrecho(rs.getInt("id_trecho"));
                trechoTO.setSiglaComp(rs.getString("sigla_comp"));
                trechoTO.setNumVoo(rs.getString("num_voo"));
                trechoTO.setOrigem(rs.getString("origem"));
                trechoTO.setDestino(rs.getString("destino"));
                trechoTO.setHorario(rs.getString("horario"));
                trechoTO.setSeg(rs.getBoolean("seg"));
                trechoTO.setTer(rs.getBoolean("ter"));
                trechoTO.setQua(rs.getBoolean("qua"));
                trechoTO.setQui(rs.getBoolean("qui"));
                trechoTO.setSex(rs.getBoolean("sex"));
                trechoTO.setSab(rs.getBoolean("sab"));
                trechoTO.setDom(rs.getBoolean("dom"));
                trechos.add(trechoTO);
            }
            return trechos;
        } catch (Exception ex) {
            return null;
        }
    }
}
