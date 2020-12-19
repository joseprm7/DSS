package dss.armazem.business.ssgestrobots;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SSGestRobots {
    private Map<String, Robot> robots;

    public SSGestRobots() {
        this.robots = new TreeMap<>();
    }

    public Set<Robot> getRobots() {
        return (Set<Robot>) this.robots.values();
    }
}
