package dss.armazem.business.ssgestrobots;
import java.util.*;

public class Mapa {
    private final Map<Integer, Collection<Integer>> verticesAdjacentes;

    public Mapa() {
        this.verticesAdjacentes = new HashMap<>();
    }

    public void addNodo(Integer nodo, Collection<Integer> adjacentes) {
        this.verticesAdjacentes.put(nodo, adjacentes);
    }

    public Collection<Integer> caminhoMaisRapido() {
        return new ArrayList<>();
    }
}