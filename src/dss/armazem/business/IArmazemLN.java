package dss.armazem.business;

import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.business.ssgestrobots.MyEntry;
import java.util.Collection;

public interface IArmazemLN {
    Collection<Palete> getListaPaletes();
    Collection<MyEntry<String, Integer>> validaCodigo(String id, String descricao) throws Exception;
    Collection<MyEntry<String, Integer>> notificaEntrega(String idRobot) throws Exception;
    Collection<MyEntry<String, Integer>> transporte() throws Exception;
    Collection<MyEntry<String, Integer>> notificaRecolha(String idPalete, String idRobot);
}
