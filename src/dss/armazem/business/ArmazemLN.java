package dss.armazem.business;

import dss.armazem.business.ssgestpaletes.SSGestPaletes;
import dss.armazem.business.ssgestrobots.Localizacao;
import dss.armazem.business.ssgestrobots.SSGestRobots;

public class ArmazemLN {
    private SSGestPaletes gestPaletes;
    private SSGestRobots gestRobots;

    public ArmazemLN() {
        this.gestPaletes = new SSGestPaletes();
        this.gestRobots = new SSGestRobots();
    }

    public void validaCodigo(String id, String descricao) {
        this.gestPaletes.validaCodigo(id, descricao);
    }

    public void notificaEntrega(String idRobot, Localizacao locAtual) {
        this.gestRobots.notificaEntrega(idRobot, locAtual);
    }
}
