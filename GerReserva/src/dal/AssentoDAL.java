/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import model.AeronaveTO;
import model.AssentoTO;
import persistencia.PGSql;

/**
 *
 * @author Caio
 */
public class AssentoDAL {

    final String SQL_SALVAR = "INSERT INTO assento (fila, numero, cod_aeronave, classe) VALUES ('%s', '%s', '%s', '%s');";
    final String SQL_UPDATE = "UPDATE assento SET classe='%s' WHERE fila='%s' and numero='%s' and cod_aeronave='%s';";
    final String SQL_DELETE = "DELETE FROM assento WHERE fila='%s' and numero='%s' and cod_aeronave='%s';";
    final String SQL_FIND_ONE = "SELECT * FROM assento WHERE fila='%s' and numero='%s' and cod_aeronave='%s';";
    final String SQL_FIND_ALL = "SELECT DISTINCT * FROM assento WHERE cod_aeronave like '%%%s%%';";
    private static AssentoDAL instancia;

    private AssentoDAL() {
    }

    public static AssentoDAL getInstance() {
	if (AssentoDAL.instancia == null) {
	    AssentoDAL.instancia = new AssentoDAL();
	}
	return AssentoDAL.instancia;
    }

    public AssentoTO findOne(Character fila, Integer numero, String codAeronave) {
	AssentoTO assentoTO = new AssentoTO();
	AeronaveTO aeronaveTO = new AeronaveTO();
	java.sql.ResultSet rs = PGSql.getInstance().query(String.format(SQL_FIND_ONE, fila, numero, codAeronave));
	try {
	    rs.next();
	    assentoTO.setFila(rs.getString("fila").charAt(0));
	    assentoTO.setNumero(rs.getInt("numero"));
	    aeronaveTO.setCodAeronave(rs.getString("cod_aeronave"));
	    assentoTO.setAeronave(aeronaveTO);
	    assentoTO.setClasse(rs.getString("classe"));
	    return assentoTO;
	} catch (Exception ex) {
	    return null;
	}
    }

    public ArrayList<AssentoTO> findAll(String codAeronave) {
	ArrayList<AssentoTO> res = new ArrayList();
	java.sql.ResultSet rs = PGSql.getInstance().query(String.format(SQL_FIND_ALL, codAeronave));
	AssentoTO assentoTO;
	AeronaveTO aeronaveTO;
	try {
	    while (rs.next()) {
		assentoTO = new AssentoTO();
		aeronaveTO = new AeronaveTO();
		assentoTO.setFila(rs.getString("fila").charAt(0));
		assentoTO.setNumero(rs.getInt("numero"));
		aeronaveTO.setCodAeronave(rs.getString("cod_aeronave"));
		assentoTO.setAeronave(aeronaveTO);
		assentoTO.setClasse(rs.getString("classe"));
		res.add(assentoTO);
	    }
	    return res;
	} catch (Exception ex) {
	    return null;
	}
    }

    public boolean salvar(AssentoTO assentoObj) {
	return PGSql.getInstance().exec(String.format(SQL_SALVAR, assentoObj.getFila(), assentoObj.getNumero(), assentoObj.getAeronave().getCodAeronave(), assentoObj.getClasse()));
    }

    public boolean atualizar(AssentoTO assentoObj) {
	return PGSql.getInstance().exec(String.format(SQL_UPDATE, assentoObj.getClasse(), assentoObj.getFila(), assentoObj.getNumero(), assentoObj.getAeronave().getCodAeronave()));
    }

    public boolean deletar(AssentoTO assentoObj) {
	return PGSql.getInstance().exec(String.format(SQL_DELETE, assentoObj.getFila(), assentoObj.getNumero(), assentoObj.getAeronave().getCodAeronave()));
    }
}
