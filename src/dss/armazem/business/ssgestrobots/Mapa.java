package dss.armazem.business.ssgestrobots;
import java.util.*;

public class Mapa {
    private final List<MyEntry<String, Collection<Node>>> grafo;

    public Mapa() {
        this.grafo = new ArrayList<>();
    }

    public Mapa(List<MyEntry<String, Collection<Node>>> grafo) {
        this.grafo = grafo;
    }

    public List<MyEntry<String, Collection<Node>>> getGrafo() {
        return grafo;
    }

    public void put(MyEntry<String, Collection<Node>> entry) {
        this.grafo.add(entry);
    }

    public void addNodo(int origem, Node node) {
        Collection<Node> nodes = this.grafo.get(origem-1).getValue(); //add(node);
        nodes.add(node);
    }

    public int pesoCaminho(Collection<MyEntry<String, Integer>> caminho) {
        int peso = 0;
        for (MyEntry<String, Integer> entry : caminho)
            peso += entry.getValue();
        return peso;
    }

    public Collection<MyEntry<String, Integer>> caminhoMaisRapido(String origem, String destino) {
        Collection<Node> nodosAdj = this.grafo.get(Integer.getInteger(origem)).getValue();
        Collection<MyEntry<String, Integer>> caminhoAux, caminhoFinal = new ArrayList<>();
        caminhoFinal.add(new MyEntry<>(origem, 0));
        try {
            for (Node nodo : nodosAdj)
                if (nodo.getDestino().equals(destino)) {
                    caminhoFinal.add(new MyEntry<>(nodo.getDestino(), nodo.getPeso()));
                    return caminhoFinal;
                }

            for (Node nodo : nodosAdj) {
                caminhoAux = caminhoMaisRapido(nodo.getDestino(), destino);

                if (caminhoFinal != null)
                    caminhoFinal = caminhoAux;
                else if (pesoCaminho(caminhoAux) < pesoCaminho(caminhoFinal))
                    caminhoFinal.add(new MyEntry<>(nodo.getDestino(), nodo.getPeso()));
            }

            return caminhoFinal;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

}