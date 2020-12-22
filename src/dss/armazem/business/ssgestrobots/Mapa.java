package dss.armazem.business.ssgestrobots;
import java.util.*;

/*public class Mapa {
    private final List<Map.Entry<String, Collection<Node>>> grafo;

    public Mapa() {
        this.grafo = new ArrayList<>();
    }

    public Mapa(List<Map.Entry<String, Collection<Node>>> grafo) {
        this.grafo = grafo;
    }

    public void put(Map.Entry<String, Collection<Node>> entry) {
        this.grafo.add(entry);
    }

    public void addNodo(String origem, Node lista) {
        this.grafo.get(Integer.getInteger(origem)).getValue().add(lista);
    }

    public int pesoCaminho(Map<String, Integer> caminho) {
        int peso = 0;
        for (Map.Entry<String, Integer> entry : caminho.entrySet())
            peso += entry.getValue();
        return peso;
    }

    public Collection<Map.Entry<String, Integer>> caminhoMaisRapido(String origem, String destino) {
        Collection<Node> nodosAdj = this.grafo.get(Integer.getInteger(origem)).getValue();
        Collection<Map.Entry<String, Integer>> caminhoAux, caminhoFinal = new ArrayList<>();
        Map.Entry<String, Integer> entry =
        caminhoFinal.add(new Map.Entry<String, Integer>(origem, 0));
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

}*/