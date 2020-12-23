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
public class SSGestPaletes implements IGestPaletes{
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
     * Cria uma classe Palete que irá ser incluída na BD no estado "Queue" para mais tarde ser transportada
     * @param id identificador da Palete
     * @param descricao descricao da Palete
     */
    public void validaCodigo(String id, String descricao) throws Exception {
        Palete p = new Palete("Queue", id, descricao, 1);
        this.paleteDAO.put(p);
    }

    /**
     * Obtém todas as paletes existentes na BD
     * @return lista de paletes da BD
     */
    public Collection<Palete> getListaPaletes() {
        return this.paleteDAO.get();
    }

    /**
     * Procura um lugar livre para guardar uma palete e caso haja procura uma palete em Queue e
     * devolve-a para que o sistema consiga escolher um robot para a transportar.
     * @return Palete
     */
    public Palete transporte(){
        Palete p = null;
        Seccao s = this.seccaoDAO.getSeccaoLivre();
        if(s.getId() != null) {
             p = this.paleteDAO.getFirstPaleteInQueue();
             if(p.getID() != null) {
                 if(p.getLoc() != -1) this.seccaoDAO.updateCheia(p.getLoc(), false);
                 this.seccaoDAO.updateCheia(s.getLoc(), true);
                 this.paleteDAO.updateEstadoLoc(p.getID(), s.getLoc(), "Espera");
             }
        }
        return p;
    }

    /**
     * Altera o estado duma palete e põe a variavel da seccao a false
     * de maneira a dizer que é possível guardar outras paletes nesta secção
     * @param idPalete identificador da Palete
     */
    public Palete notificaRecolha(String idPalete) {
        Palete p = this.paleteDAO.get(idPalete);
        int loc = p.getLoc();
        this.paleteDAO.updateEstadoLoc(idPalete, loc, "Transporte");
        return p;
    }

    /**
     * Caso a localização esteja no último vértice, altera o seu estado para "Pronta", porque
     * significa que está na zona de saída do Armazém e irá sair do Armazém.
     * Caso não esteja, significa que a Palete apenas ficará armazenada no Armazém
     * @param idPalete identificador da Palete
     * @param loc localização
     */
    public void notificaEntrega(String idPalete) {
        this.paleteDAO.updateEstado(idPalete, "Armazenada");
    }
}