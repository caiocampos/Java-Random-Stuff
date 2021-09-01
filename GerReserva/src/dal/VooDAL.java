/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import model.CompanhiaTO;
import model.VooTO;
import persistencia.PGSql;

/**
 *
 * @author Caio
 */
public class VooDAL {

    final String SQL_SALVAR = "INSERT INTO voo (sigla_comp, num_voo) VALUES ('%s', '%s');";
    final String SQL_DELETE = "DELETE FROM voo WHERE sigla_comp='%s' and num_voo='%s';";
    final String SQL_FIND_ONE = "SELECT * FROM voo WHERE sigla_comp='%s' and num_voo='%s';";
    final String SQL_FIND_ALL = "SELECT DISTINCT * FROM voo WHERE sigla_comp like '%%%s%%';";
    final String SQL_GET_ALL = "SELECT DISTINCT * FROM voo;";
    private static VooDAL instancia;

    private VooDAL() {
    }

    public static VooDAL getInstance() {
	if (VooDAL.instancia == null) {
	    VooDAL.instancia = new VooDAL();
	}
	return VooDAL.instancia;
    }
    
    public ArrayList<VooTO> getAll() throws SQLException{
        ArrayList<VooTO> voos = new ArrayList<>();
        HashMap<String, CompanhiaTO> cache = new HashMap<>();
        VooTO v;
        ResultSet rs = PGSql.getInstance().query(SQL_GET_ALL);
        if(rs != null){
            while(rs.next()){
                v = new VooTO();
                if(cache.get(rs.getString("sigla_comp")) == null){
                    cache.put(rs.getString("sigla_comp"), CompanhiaDAL.getInstance().findOne(rs.getString("sigla_comp")));
                }
                v.setCompanhia(cache.get(rs.getString("sigla_comp")));
                v.setNumVoo(rs.getString("num_voo"));
                voos.add(v);
            }
        }
        return voos;
    }
    
    public VooTO findOne(String siglaComp, String numVoo) {
	VooTO vooTO = new VooTO();
	CompanhiaTO companhiaTO = new CompanhiaTO();
	java.sql.ResultSet rs = PGSql.getInstance().query(String.format(SQL_FIND_ONE, siglaComp, numVoo));
	try {
	    rs.next();
	    companhiaTO.setSigla(rs.getString("sigla_comp"));
	    vooTO.setCompanhia(companhiaTO);
	    vooTO.setNumVoo(rs.getString("num_voo"));
	    return vooTO;
	} catch (Exception ex) {
	    return null;
	}
    }

    public ArrayList<VooTO> findAll(String siglaComp) {
	ArrayList<VooTO> res = new ArrayList();
	java.sql.ResultSet rs = PGSql.getInstance().query(String.format(SQL_FIND_ALL, siglaComp));
	VooTO vooTO;
	CompanhiaTO companhiaTO;
	try {
	    while (rs.next()) {
		vooTO = new VooTO();
		companhiaTO = new CompanhiaTO();
		companhiaTO.setSigla(rs.getString("sigla_comp"));
		vooTO.setCompanhia(companhiaTO);
		vooTO.setNumVoo(rs.getString("num_voo"));
		res.add(vooTO);
	    }
	    return res;
	} catch (Exception ex) {
	    return null;
	}
    }

    public boolean salvar(VooTO vooObj) {
	return PGSql.getInstance().exec(String.format(SQL_SALVAR, vooObj.getCompanhia().getSigla(), vooObj.getNumVoo()));
    }

    public boolean deletar(VooTO vooObj) {
	return PGSql.getInstance().exec(String.format(SQL_DELETE, vooObj.getCompanhia().getSigla(), vooObj.getNumVoo()));
    }
}
