package dss.armazem.business.ssgestrobots;

import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.data.RobotDAO;

import java.util.Collection;

/**
 * Classe que visa gerir o subsistema dos Robots.
 * Nela, irão ser tratados todos os Robots existentes no Armazém.
 */
public class SSGestRobots {
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
     * A partir de um determinado identificador, um robot irá notificar o Sistema
     * que já entregou a Palete e alterará o seu estado para "Livre".
     * @param id identificador
     * @param locAtual localização atual do Robot em questão
     */
    public String notificaEntrega(String id, int locAtual) {
        Robot r = this.robotDAO.get(id);
        this.robotDAO.updateEntrega(id, locAtual);
        return r.getPalete();
    }

    public Robot  robotLivre() {
        return this.robotDAO.getRobotLivre();
    }

    public Collection<MyEntry<String, Integer>> transporte(Robot r, Palete p) {
        r.setEstado("Transporte");
        r.setPalete(p.getID());
        this.robotDAO.put(r);
        int[] v = new int[14];
        for(int i = 0; i<14;i++) v[i] = 0;
        return this.map.caminhoMaisRapido(""+r.getLoc()+"", ""+p.getLoc()+"", 14, v);
    }
}
