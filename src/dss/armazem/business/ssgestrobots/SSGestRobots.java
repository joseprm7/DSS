package dss.armazem.business.ssgestrobots;

import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.data.RobotDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que visa gerir o subsistema dos Robots.
 * Nela, irão ser tratados todos os Robots existentes no Armazém.
 */
public class SSGestRobots {
    private final RobotDAO robotDAO;

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
        this.robotDAO.put(r);
    }

    public Robot  robotLivre() {
        return this.robotDAO.getRobotLivre();
    }

    public /*List<String>*/void transporte(Robot r, Palete p) {
        r.setEstado("Trasnporte");
        r.setPalete(p.getID());
        this.robotDAO.put(r);
        //List<String> res = this.caminho();
        //return res;
        //Só falta retornar o caminho mais rapido
    }
}
