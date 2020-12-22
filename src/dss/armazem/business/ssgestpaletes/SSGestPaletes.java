package dss.armazem.business.ssgestpaletes;

import dss.armazem.data.PaleteDAO;
import dss.armazem.data.SeccaoDAO;
import java.util.*;

/**
 * Classe que visa gerir o subsistema das Paletes.
 * Contém uma lista de todos os identificadores das Paletes existentes no Armazém,
 * Uma lista das Paletes que se encontram na queue de entrada à espera de serem transportadas,
 * Uma lista de Seccões com todas as secções do Armazém.
 * É de notar que todas estas listas estão armazenadas numa base de dados.
 */
public class SSGestPaletes {
    private final PaleteDAO paleteDAO;
    private final SeccaoDAO seccaoDAO;

    /**
     * Construtor vazio
     */
    public SSGestPaletes() {
        this.paleteDAO = new PaleteDAO();
        this.seccaoDAO = new SeccaoDAO();
    }

    /**
     * Adiciona o código de uma Palete à variável queue e cria uma classe Palete que
     * irá ser incluída na queue de entrada para mais tarde ser transportada
     * @param id identificador da Palete
     * @param descricao descricao da Palete
     */
    public void validaCodigo(String id, String descricao) {
        Palete p = new Palete("Queue", id, descricao, -1);
        this.paleteDAO.put(p);
    }

    public Collection<Palete> getListaPaletes() {
        return this.paleteDAO.get();
    }

    /**
     * Procura, através do seu identificador, uma Palete e remove-a da lista da Paletes da secção
     * que pertencia anteriormente. De seguida, altera o seu estado para "Transporte"
     * @param idPalete identificador da Palete
     */
    public void notificaRecolha(String idPalete) {
        Palete p = this.paleteDAO.get(idPalete);
        p.setEstado("Transporte");
        this.paleteDAO.put(p);
    }

    /**
     * Retorna o primeiro identificador da Palete da lista queue
     * @return primeiro identificador da Palete da lista queue
     */
    public String queue() {
        Palete r = this.paleteDAO.getFirstPaleteInQueue();
        r.setEstado("Transporte");
        this.paleteDAO.put(r);
        return r.getID();
    }
}