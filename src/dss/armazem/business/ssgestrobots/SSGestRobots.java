package dss.armazem.business.ssgestrobots;
import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.data.RobotDAO;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Classe que visa gerir o subsistema dos Robots.
 * Nela, irão ser tratados todos os Robots existentes no Armazém.
 */
public class SSGestRobots {
    private RobotDAO robotDAO;

    /**
     * Construtor vazio
     */
    public SSGestRobots() {
        this.robotDAO = new RobotDAO();
    }

    /**
     * A partir de um determinado identificador, um robot irá notificar o Sistema
     * que já entregou a Palete e alterará o seu estado para "Livre".
     * @param id identificador
     * @param locAtual localização atual do Robot em questão
     */
    public void notificaEntrega(String id, int locAtual) {
        Robot r = this.robotDAO.get(id);
        r.notificaEntrega(locAtual);
        //this.robotDAO.put(id);
    }

    /*
     * A partir de um determinado identificador, um robot irá transportar uma
     * determinada Palete e alterará o seu estado para "Transporte"
     * @param idRobot
     * @param p
     *
    public void trasportaRobot(String idRobot, Palete p) {
        Robot r = this.robots.get(idRobot);
        r.setEstado("Transporte");
        r.setPalete(p);
    }*/
}
