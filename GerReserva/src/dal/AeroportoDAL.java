/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import model.AeroportoTO;
import persistencia.PGSql;

/**
 *
 * @author Caio
 */
public class AeroportoDAL {

    final String SQL_SALVAR = "INSERT INTO aeroporto (cod_iata, cod_icao, nome, cidade, pais) VALUES ('%s', '%s', '%s', '%s', '%s');";
    final String SQL_UPDATE = "UPDATE aeroporto SET cod_icao='%s', nome='%s', cidade='%s', pais='%s' WHERE cod_iata='%s';";
    final String SQL_DELETE = "DELETE FROM aeroporto WHERE cod_iata='%s';";
    final String SQL_FIND_ONE = "SELECT * FROM aeroporto WHERE cod_iata='%s';";
    final String SQL_FIND_ALL = "SELECT DISTINCT * FROM aeroporto WHERE cod_iata like '%%%s%%' ORDER BY cidade ASC;";
    private static AeroportoDAL instancia;

    private AeroportoDAL() {
    }

    public static AeroportoDAL getInstance() {
	if (AeroportoDAL.instancia == null) {
	    AeroportoDAL.instancia = new AeroportoDAL();
	}
	return AeroportoDAL.instancia;
    }

    public AeroportoTO findOne(String codiata) {
	AeroportoTO aeroportoTO = new AeroportoTO();
	java.sql.ResultSet rs = PGSql.getInstance().query(String.format(SQL_FIND_ONE, codiata));
	try {
	    rs.next();
	    aeroportoTO.setCodIATA(rs.getString("cod_iata"));
	    aeroportoTO.setCodICAO(rs.getString("cod_icao"));
	    aeroportoTO.setNome(rs.getString("nome"));
	    aeroportoTO.setCidade(rs.getString("cod_iata"));
	    aeroportoTO.setPais(rs.getString("cod_iata"));
	    return aeroportoTO;
	} catch (Exception ex) {
	    return null;
	}
    }

    public ArrayList<AeroportoTO> findAll(String codiata) {
	ArrayList<AeroportoTO> res = new ArrayList();
	java.sql.ResultSet rs = PGSql.getInstance().query(String.format(SQL_FIND_ALL, codiata));
	AeroportoTO aeroportoTO;
	try {
	    while (rs.next()) {
		aeroportoTO = new AeroportoTO();
		aeroportoTO.setCodIATA(rs.getString("cod_iata"));
		aeroportoTO.setCodICAO(rs.getString("cod_icao"));
		aeroportoTO.setNome(rs.getString("nome"));
		aeroportoTO.setCidade(rs.getString("cod_iata"));
		aeroportoTO.setPais(rs.getString("cod_iata"));
		res.add(aeroportoTO);
	    }
	    return res;
	} catch (Exception ex) {
	    return null;
	}
    }

    public boolean salvar(AeroportoTO aeroportoObj) {
	return PGSql.getInstance().exec(String.format(SQL_SALVAR, aeroportoObj.getCodIATA(), aeroportoObj.getCodICAO(), aeroportoObj.getNome(), aeroportoObj.getCidade(), aeroportoObj.getPais()));
    }

    public boolean atualizar(AeroportoTO aeroportoObj) {
	return PGSql.getInstance().exec(String.format(SQL_UPDATE, aeroportoObj.getCodICAO(), aeroportoObj.getNome(), aeroportoObj.getCidade(), aeroportoObj.getPais(), aeroportoObj.getCodIATA()));
    }

    public boolean deletar(AeroportoTO aeroportoObj) {
	return PGSql.getInstance().exec(String.format(SQL_DELETE, aeroportoObj.getCodIATA()));
    }
}
