package dss.armazem.business.ssgestrobots;
import java.util.*;

public class Mapa {
    private final Map<String, Collection<Node>> grafo;

    public Mapa(Map<String, Collection<Node>> grafo) {
        this.grafo = grafo;
    }

    public void addNodo(String origem, Collection<Node> adjacentes) {
        this.grafo.put(origem, adjacentes);
    }

    public int pesoCaminho(Map<String, Integer> caminho) {
        int peso = 0;
        for (Map.Entry<String, Integer> entry : caminho.entrySet())
            peso += entry.getValue();
        return peso;
    }

    public Map<String, Integer> caminhoMaisRapido(String origem, String destino) {
        Collection<Node> nodosAdj = this.grafo.get(origem);
        Map<String, Integer> caminhoAux, caminhoFinal = new TreeMap<>();
        caminhoFinal.put(origem, 0);
        try {
            for (Node nodo : nodosAdj) {
                if (nodo.getDestino().equals(destino)) {
                    caminhoFinal.put(nodo.getDestino(), nodo.getPeso());
                    return caminhoFinal;
                } else caminhoAux = caminhoMaisRapido(nodo.getDestino(), destino);

                if (caminhoFinal != null)
                    caminhoFinal = caminhoAux;
                else if (pesoCaminho(caminhoAux) < pesoCaminho(caminhoFinal))
                    caminhoFinal.put(nodo.getDestino(), nodo.getPeso());
            }

            return caminhoFinal;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

    }
}