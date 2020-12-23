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
        Palete p = new Palete("Queue", id, descricao, 1);
        this.paleteDAO.put(p);
    }

    public Collection<Palete> getListaPaletes() {
        return this.paleteDAO.get();
    }

    public Palete transporte(){
        Palete p = null;
        Seccao s = this.seccaoDAO.getSeccaoLivre();
        if(s.getId() != null) {
             p = this.paleteDAO.getFirstPaleteInQueue();
             if(p.getID() != null) {
                 s.setOcupado(true);
                 this.seccaoDAO.put(s);
                 p.setEstado("Espera");
                 this.paleteDAO.put(p);
             }
        }
        return p;
    }

    public void notificaRecolha(String idPalete) {
        Palete p = this.paleteDAO.get(idPalete);
        int loc = p.getLoc();
        this.paleteDAO.updateEstadoLoc(idPalete, loc, "Transporte");

        Seccao s = this.seccaoDAO.get(loc);
        this.seccaoDAO.updateCheia(loc);
    }

    public void notificaEntrega(String idPalete, int loc) {
        Palete p = this.paleteDAO.get(idPalete);
        p.setLoc(loc);
        this.paleteDAO.put(p);

        Seccao s = this.seccaoDAO.get(loc);
        s.addPalete(p);
        this.seccaoDAO.put(s);
    }
}