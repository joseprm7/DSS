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
    private PaleteDAO paleteDAO;
    private SeccaoDAO seccaoDAO;

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
        this.paleteDAO.put(id, "Queue", descricao, "Entrda", -1);
    }

    public Collection<Palete> getListaPaletes() {
        return this.paleteDAO.get();
    }








    /**
     * Retorna o primeiro identificador da Palete da lista queue
     * @return primeiro identificador da Palete da lista queue
     *
    public String queue() {
        List<String> p = (ArrayList<String>) this.queue;
        return p.get(0);
    }*/

    /**
     * Procura, através do seu identificador, uma Palete que futuramente irá ser transportada
     * @param idPalete identificador da Palete
     * @return Palete que irá ser transportada
     *
    public Palete transportaPalete(String idPalete) {
        int r = 0;
        Palete p = this.entrada.stream().filter(x -> x.getID().equals(idPalete)).findFirst().orElse(null);
        if(p == null) {
            List<Seccao> ss = new ArrayList<>(this.seccoes.values());
            for(int i = 0; i < ss.size() && r==0; i++) {
                Collection<Palete> ps = ss.get(i).getPaletes();
                p = ps.stream().filter(x -> x.getID().equals(idPalete)).findFirst().orElse(null);
                if(p != null) {
                    r = 1;
                }
            }
        }
        return p;
    }*/

    /**
     * Procura, através do seu identificador, uma Palete e remove-a da lista da Paletes da secção
     * que pertencia anteriormente. De seguida, altera o seu estado para "Transporte"
     * @param idPalete identificador da Palete
     *
    public void notificaRecolha(String idPalete) {
        int r = 0;
        Palete p = this.entrada.stream().filter(x -> x.getID().equals(idPalete)).findFirst().orElse(null);
        if(p == null) {
            List<Seccao> ss = new ArrayList<>(this.seccoes.values());
            for(int i = 0; i < ss.size() && r==0; i++) {
                Collection<Palete> ps = ss.get(i).getPaletes();
                p = ps.stream().filter(x -> x.getID().equals(idPalete)).findFirst().orElse(null);
                if(p != null) {
                    ps.remove(p);
                    r = 1;
                }
            }
        }
        if(p != null) p.setEstado("Transporte");
    }*/
}