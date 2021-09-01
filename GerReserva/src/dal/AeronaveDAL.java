/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import model.AeronaveTO;
import persistencia.PGSql;

/**
 *
 * @author Caio
 */
public class AeronaveDAL {
    final String SQL_SALVAR = "INSERT INTO aeronave (cod_aeronave, fabricante, modelo, substituta) VALUES ('%s', '%s', '%s', '%s');";
    final String SQL_UPDATE = "UPDATE aeronave SET fabricante='%s', modelo='%s', substituta='%s' WHERE cod_aeronave='%s';";
    final String SQL_SALVAR_NOSUB = "INSERT INTO aeronave (cod_aeronave, fabricante, modelo) VALUES ('%s', '%s', '%s');";
    final String SQL_UPDATE_NOSUB = "UPDATE aeronave SET fabricante='%s', modelo='%s' WHERE cod_aeronave='%s';";
    final String SQL_DELETE = "DELETE FROM aeronave WHERE cod_aeronave='%s';";
    final String SQL_FIND_ONE = "SELECT * FROM aeronave WHERE cod_aeronave='%s';";
    final String SQL_FIND_ALL = "SELECT DISTINCT * FROM aeronave WHERE cod_aeronave like '%%%s%%';";
    final String SQL_FIND_ALL_SUBS = "SELECT DISTINCT * FROM aeronave WHERE substituta is null;";
    private static AeronaveDAL instancia;

    private AeronaveDAL() {
    }

    public static AeronaveDAL getInstance() {
	if (AeronaveDAL.instancia == null) {
	    AeronaveDAL.instancia = new AeronaveDAL();
	}
	return AeronaveDAL.instancia;
    }

    public AeronaveTO findOne(String codaero) {
	AeronaveTO aeronaveTO = new AeronaveTO();
	AeronaveTO sub = new AeronaveTO();
	java.sql.ResultSet rs = PGSql.getInstance().query(String.format(SQL_FIND_ONE, codaero));
	try {
	    rs.next();
	    aeronaveTO.setCodAeronave(rs.getString("cod_aeronave"));
	    aeronaveTO.setFabricante(rs.getString("fabricante"));
	    aeronaveTO.setModelo(rs.getString("modelo"));
	    sub.setCodAeronave(rs.getString("substituta"));
	    aeronaveTO.setSubstituta(sub);
	    return aeronaveTO;
	} catch (Exception ex) {
	    return null;
	}
    }

    public ArrayList<AeronaveTO> findAll(String codaero) {
	ArrayList<AeronaveTO> res = new ArrayList();
	java.sql.ResultSet rs = PGSql.getInstance().query(String.format(SQL_FIND_ALL, codaero));
	AeronaveTO aeronaveTO;
	AeronaveTO sub;
	try {
	    while (rs.next()) {
		aeronaveTO = new AeronaveTO();
		sub = new AeronaveTO();
		aeronaveTO.setCodAeronave(rs.getString("cod_aeronave"));
		aeronaveTO.setFabricante(rs.getString("fabricante"));
		aeronaveTO.setModelo(rs.getString("modelo"));
		sub.setCodAeronave(rs.getString("substituta"));
		aeronaveTO.setSubstituta(sub);
		res.add(aeronaveTO);
	    }
	    return res;
	} catch (Exception ex) {
	    return null;
	}
    }
    
    public ArrayList<AeronaveTO> findAllSubs() {
	ArrayList<AeronaveTO> res = new ArrayList();
	java.sql.ResultSet rs = PGSql.getInstance().query(SQL_FIND_ALL_SUBS);
	AeronaveTO aeronaveTO;
	try {
	    while (rs.next()) {
		aeronaveTO = new AeronaveTO();
		aeronaveTO.setCodAeronave(rs.getString("cod_aeronave"));
		aeronaveTO.setFabricante(rs.getString("fabricante"));
		aeronaveTO.setModelo(rs.getString("modelo"));
		res.add(aeronaveTO);
	    }
	    return res;
	} catch (Exception ex) {
	    return null;
	}
    }

    public boolean salvar(AeronaveTO aeronaveObj) {
	if (aeronaveObj.getSubstituta().getCodAeronave() == null) {
	    return PGSql.getInstance().exec(String.format(SQL_SALVAR_NOSUB, aeronaveObj.getCodAeronave(), aeronaveObj.getFabricante(), aeronaveObj.getModelo()));
	}
	return PGSql.getInstance().exec(String.format(SQL_SALVAR, aeronaveObj.getCodAeronave(), aeronaveObj.getFabricante(), aeronaveObj.getModelo(), aeronaveObj.getSubstituta().getCodAeronave()));
    }

    public boolean atualizar(AeronaveTO aeronaveObj) {
	if (aeronaveObj.getSubstituta().getCodAeronave() == null) {
	    PGSql.getInstance().exec(String.format(SQL_UPDATE_NOSUB, aeronaveObj.getFabricante(), aeronaveObj.getModelo(), aeronaveObj.getCodAeronave()));
	}
	return PGSql.getInstance().exec(String.format(SQL_UPDATE, aeronaveObj.getFabricante(), aeronaveObj.getModelo(), aeronaveObj.getSubstituta().getCodAeronave(), aeronaveObj.getCodAeronave()));
    }

    public boolean deletar(AeronaveTO aeronaveObj) {
	return PGSql.getInstance().exec(String.format(SQL_DELETE, aeronaveObj.getCodAeronave()));
    }
}
