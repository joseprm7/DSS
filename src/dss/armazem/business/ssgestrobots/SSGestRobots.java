package dss.armazem.business.ssgestrobots;

import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.data.RobotDAO;
import java.util.Collection;

/**
 * Classe que visa gerir o subsistema dos Robots.
 * Nela, irão ser tratados todos os Robots existentes no Armazém.
 */
public class SSGestRobots implements IGestRobots {
    private final RobotDAO robotDAO;
    private final Mapa map;

    /**
     * Construtor vazio
     */
    public SSGestRobots() {
        this.robotDAO = new RobotDAO();
        this.map = new Mapa();
    }

    /**
     * Procura na base de dados um robot Livre
     * @return robot com o estado "Livre"
     */
    public Robot  robotLivre() {
        return this.robotDAO.getRobotLivre();
    }

    /**
     * A partir de um determinado identificador, um robot irá notificar o Sistema
     * que já entregou a Palete e alterará o seu estado para "Livre".
     * @param id identificador
     * @param locAtual localização atual do Robot em questão
     * @return id da Palete que o robot entregou
     */
    public String notificaEntrega(String id, int locAtual) {
        Robot r = this.robotDAO.get(id);
        this.robotDAO.updateEntrega(id, locAtual);
        return r.getPalete();
    }

    /**
     * Altera o estado do robot para "Transporte", altera o identificador da palete que este está a
     * transportar e de seguida calcula um percurso para este chegar até à palete.
     * @return percurso até à palete
     */
    public Collection<MyEntry<String, Integer>> transporte(Robot r, Palete p) {
        r.setEstado("Transporte");
        r.setPalete(p.getID());
        this.robotDAO.put(r);
        int[] v = new int[14];
        for(int i = 0; i<14;i++) v[i] = 0;
        return this.map.caminhoMaisRapido(""+r.getLoc()+"", ""+p.getLoc()+"", 14, v);
    }

    public Robot getRobot(String id) {
        return this.robotDAO.get(id);
    }

    public void alteraLoc(int loc, String idRobot) {
        this.robotDAO.updateLoc(idRobot, loc);
    }
}
