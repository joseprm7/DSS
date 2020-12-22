package dss.armazem.business;

import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.business.ssgestpaletes.SSGestPaletes;
import dss.armazem.business.ssgestrobots.Robot;
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
        this.transporte();
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

    public void transporte() {
        Robot r = this.gestRobots.robotLivre();
        if(r != null) {
            Palete p = this.gestPaletes.transporte();
            if(p != null) this.gestRobots.transporte(r, p);
        }
    }
}
