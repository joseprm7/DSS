package dss.armazem.business.ssgestrobots;

import dss.armazem.business.ssgestpaletes.Palete;
import java.util.Collection;

public interface IGestRobots {
    Collection<MyEntry<String, Integer>> transporte(Robot r, Palete p);
    String notificaEntrega(String id, int locAtual);
    Robot  robotLivre();
    Robot getRobot(String id);
}