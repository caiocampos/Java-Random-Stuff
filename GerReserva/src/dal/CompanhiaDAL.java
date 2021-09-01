/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import model.CompanhiaTO;
import persistencia.PGSql;

/**
 *
 * @author Caio
 */
public class CompanhiaDAL {

    final String SQL_SALVAR = "INSERT INTO companhia (sigla, nome, nome_fantasia, pais) VALUES ('%s', '%s', '%s', '%s');";
    final String SQL_UPDATE = "UPDATE companhia SET nome='%s', nome_fantasia='%s', pais='%s' WHERE sigla='%s';";
    final String SQL_DELETE = "DELETE FROM companhia WHERE sigla='%s';";
    final String SQL_FIND_ONE = "SELECT * FROM companhia WHERE sigla='%s';";
    final String SQL_FIND_ALL = "SELECT DISTINCT * FROM companhia WHERE sigla like '%%%s%%';";
    private static CompanhiaDAL instancia;

    private CompanhiaDAL() {
    }

    public static CompanhiaDAL getInstance() {
	if (CompanhiaDAL.instancia == null) {
	    CompanhiaDAL.instancia = new CompanhiaDAL();
	}
	return CompanhiaDAL.instancia;
    }

    public CompanhiaTO findOne(String sigla) {
	CompanhiaTO companhiaTO = new CompanhiaTO();
	java.sql.ResultSet rs = PGSql.getInstance().query(String.format(SQL_FIND_ONE, sigla));
	try {
	    rs.next();
	    companhiaTO.setSigla(rs.getString("sigla"));
	    companhiaTO.setNome(rs.getString("nome"));
	    companhiaTO.setNomeFantasia(rs.getString("nome_fantasia"));
	    companhiaTO.setPais(rs.getString("pais"));
	    return companhiaTO;
	} catch (Exception ex) {
	    return null;
	}
    }

    public ArrayList<CompanhiaTO> findAll(String sigla) {
	ArrayList<CompanhiaTO> res = new ArrayList();
	java.sql.ResultSet rs = PGSql.getInstance().query(String.format(SQL_FIND_ALL, sigla));
	CompanhiaTO companhiaTO;
	try {
	    while (rs.next()) {
		companhiaTO = new CompanhiaTO();
		companhiaTO.setSigla(rs.getString("sigla"));
		companhiaTO.setNome(rs.getString("nome"));
		companhiaTO.setNomeFantasia(rs.getString("nome_fantasia"));
		companhiaTO.setPais(rs.getString("pais"));
		res.add(companhiaTO);
	    }
	    return res;
	} catch (Exception ex) {
	    return null;
	}
    }

    public boolean salvar(CompanhiaTO companhiaObj) {
	return PGSql.getInstance().exec(String.format(SQL_SALVAR, companhiaObj.getSigla(), companhiaObj.getNome(), companhiaObj.getNomeFantasia(), companhiaObj.getPais()));
    }

    public boolean atualizar(CompanhiaTO companhiaObj) {
	return PGSql.getInstance().exec(String.format(SQL_UPDATE, companhiaObj.getNome(), companhiaObj.getNomeFantasia(), companhiaObj.getPais(), companhiaObj.getSigla()));
    }

    public boolean deletar(CompanhiaTO companhiaObj) {
	return PGSql.getInstance().exec(String.format(SQL_DELETE, companhiaObj.getSigla()));
    }
}
