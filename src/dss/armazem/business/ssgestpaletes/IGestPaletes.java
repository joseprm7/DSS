package dss.armazem.business.ssgestpaletes;

import java.util.Collection;

public interface IGestPaletes {
    void validaCodigo(String id, String descricao) throws Exception;
    Collection<Palete> getListaPaletes();
    Palete transporte() throws Exception;
    void notificaRecolha(String idPalete);
    void notificaEntrega(String idPalete, int loc);

}
