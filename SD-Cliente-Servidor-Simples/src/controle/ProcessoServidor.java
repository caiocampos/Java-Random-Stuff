/*
    Classe que verifica qual o serviço requisitado pelo cliente e encaminha
    para o DAO o pedido de acesso ao banco. Quando o DAO responde a requisição
    esta classe gera uma resposta para entregar para o cliente.
*/
package controle;

import modelo.ProtocolData;
import dao.ContatoDAO;
import modelo.Contato;

public class ProcessoServidor {
    public static ProtocolData run(ProtocolData pd) {
        ProtocolData pdr = new ProtocolData();
        switch(pd.getCodigo()) {
            case "arm":
                if(ContatoDAO.getInstance().salvar(pd.getContato())) {
                    pdr.setCodigo("suc");
                    pdr.setContato(new Contato());
                    return pdr;
                } else {
                    pdr.setCodigo("fal");
                    return pdr;
                }
            case "sub":
                if(ContatoDAO.getInstance().atualizar(pd.getContato())) {
                    pdr.setCodigo("suc");
                    pdr.setContato(new Contato());
                    return pdr;
                } else {
                    pdr.setCodigo("fal");
                    return pdr;
                }
            case "rem":
                if(ContatoDAO.getInstance().deletar(pd.getContato().getNome())) {
                    pdr.setCodigo("suc");
                    pdr.setContato(new Contato());
                    return pdr;
                } else {
                    pdr.setCodigo("fal");
                    return pdr;
                }
            case "rec":
                Contato c = ContatoDAO.getInstance().buscar(pd.getContato().getNome());
                if(c != null) {
                    pdr.setCodigo("suc");
                    pdr.setContato(c);
                    return pdr;
                } else {
                    pdr.setCodigo("fal");
                    pdr.setContato(new Contato());
                    return pdr;
                }
            case "fin":
                pdr.setCodigo("suc");
                pdr.setContato(new Contato());
                return pdr;
        }
        pdr.setCodigo("fal");
        return pdr;
    }
}
