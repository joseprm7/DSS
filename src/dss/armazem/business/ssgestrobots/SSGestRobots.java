package dss.armazem.business.ssgestrobots;
import dss.armazem.business.ssgestpaletes.Palete;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SSGestRobots {
    private Map<String, Robot> robots;

    public SSGestRobots() {
        this.robots = new TreeMap<>();
        this.robots.put("0", new Robot("0","Ocupado",new Palete("","1","",new Localizacao()), new Localizacao(-1)));
    }

    public Set<Robot> getRobots() {
        return (Set<Robot>) this.robots.values();
    }

    public void notificaEntrega(String id, Localizacao locAtual) {
        Robot r = this.robots.get(id);
        System.out.println(r.getEstado()+"  " + r.getPalete().getID());
        r.notificaEntrega(locAtual);
        System.out.println(r.getEstado() + " " + (r.getPalete() == null));
    }
}
