package dss.armazem.business;

import dss.armazem.business.ssgestpaletes.Palete;
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

    public void robotLivre(String idRobot) throws Exception {
        String l = this.gestPaletes.queue();
        if(l == null) throw new Exception();
        Palete p = this.gestPaletes.transportaPalete(l);
        this.gestRobots.trasportaRobot(idRobot, p);
    }

    public void notificaRecolha(String idPalete) {
        this.gestPaletes.notificaRecolha(idPalete);
    }
}
