package dss.armazem.business;

import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.business.ssgestpaletes.SSGestPaletes;
import dss.armazem.business.ssgestrobots.SSGestRobots;

import java.util.Collection;

/**
 * Classe principal que visa gerir o Armazém.
 * Nela, existem como variáveis os seus subsistemas:
 * O subsistema de gestão de paletes e o de gestão de robots.
 */
public class ArmazemLN implements IArmazemLN {
    private final SSGestPaletes gestPaletes;
    private final SSGestRobots gestRobots;

    /**
     * Construtor vazio
     */
    public ArmazemLN() {
        this.gestPaletes = new SSGestPaletes();
        this.gestRobots = new SSGestRobots();
    }

    /**
     * Adiciona o identificador e a descrição de uma Palete às variáveis de instância
     * necessárias do subsistema das Paletes
     * @param id identificador da Palete
     * @param descricao Descrição da Palete
     */
    public void validaCodigo(String id, String descricao) {
        this.gestPaletes.validaCodigo(id, descricao);
    }

    public Collection<Palete> getListaPaletes() {
        return this.gestPaletes.getListaPaletes();
    }


    /**
     * A partir de um determinado identificador, um robot irá notificar o Sistema
     * que já entregou a Palete e alterará o seu estado para "Livre". Este método será
     * realizado pelo
     * @param idRobot identificador
     * @param locAtual localização atual do Robot em questão
     */
    public void notificaEntrega(String idRobot, int locAtual) {
        this.gestRobots.notificaEntrega(idRobot, locAtual);
    }

    /**
     * Procura, através do seu identificador, uma Palete e remove-a da lista da Paletes da secção
     * que pertencia anteriormente. De seguida, altera o seu estado para "Transporte"
     * @param idPalete identificador da Palete
     */
    public void notificaRecolha(String idPalete) {
        this.gestPaletes.notificaRecolha(idPalete);
    }

    /**
     * Escolhe o Robot mais próximo (primeiro elemento da lista) e este, de seguida,
     * transporta uma Palete
     * @param idRobot id do Robot
     * @throws Exception null
     */
    public void robotLivre(String idRobot) throws Exception {
        String p = this.gestPaletes.queue();
        if(p == null) throw new Exception();
        this.gestRobots.trasportaRobot(idRobot, p);
    }
}
