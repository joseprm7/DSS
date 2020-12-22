package dss.armazem.business;

import dss.armazem.business.ssgestpaletes.Palete;

import java.util.Collection;

public interface IArmazemLN {
    Collection<Palete> getListaPaletes();
    void validaCodigo(String id, String descricao);
}
