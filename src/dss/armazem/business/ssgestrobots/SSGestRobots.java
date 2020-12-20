package dss.armazem.business.ssgestrobots;
import dss.armazem.business.ssgestpaletes.Palete;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Classe que visa gerir o subsistema dos Robots.
 * Nela, irão ser tratados todos os Robots existentes no Armazém.
 */
public class SSGestRobots {
    private Map<String, Robot> robots;

    /**
     * Construtor vazio
     */
    public SSGestRobots() {
        this.robots = new TreeMap<>();
        this.robots.put("0", new Robot("0","Ocupado",new Palete("","1","",new Localizacao()), new Localizacao(-1)));
    }

    /**
     * Getters e Setters
     */

    public Set<Robot> getRobots() {
        return (Set<Robot>) this.robots.values();
    }

    /**
     * A partir de um determinado identificador, um robot irá notificar o Sistema
     * que já entregou a Palete e alterará o seu estado para "Livre".
     * @param id identificador
     * @param locAtual localização atual do Robot em questão
     */
    public void notificaEntrega(String id, Localizacao locAtual) {
        Robot r = this.robots.get(id);
        r.notificaEntrega(locAtual);
    }

    /**
     * A partir de um determinado identificador, um robot irá transportar uma
     * determinada Palete e alterará o seu estado para "Transporte"
     * @param idRobot
     * @param p
     */
    public void trasportaRobot(String idRobot, Palete p) {
        Robot r = this.robots.get(idRobot);
        r.setEstado("Transporte");
        r.setPalete(p);
    }
}
