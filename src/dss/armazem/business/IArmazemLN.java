package dss.armazem.business;

import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.business.ssgestrobots.MyEntry;

import java.util.Collection;

public interface IArmazemLN {
    Collection<Palete> getListaPaletes();
    Collection<MyEntry<String, Integer>> validaCodigo(String id, String descricao);
    void notificaEntrega(String idRobot, int locAtual);
    Collection<MyEntry<String, Integer>> transporte();
    void notificaRecolha(String idPalete);
}
