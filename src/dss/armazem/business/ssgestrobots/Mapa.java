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
        return this.grafo;
    }

    public void put(MyEntry<String, Collection<Node>> entry) {
        this.grafo.add(entry);
    }

    public void addNodo(int origem, Node node) {
        Collection<Node> nodos = this.grafo.get(origem-1).getValue();
        //for (Node n : nodos)
           // System.out.println("Origem: " + origem + ", " + "Succesor: " + n.getDestino());
        this.grafo.get(origem-1).getValue().add(node);
    }

    public int pesoCaminho(Collection<MyEntry<String, Integer>> caminho) {
        int peso = 0;
        for (MyEntry<String, Integer> entry : caminho)
            peso += entry.getValue();
        return peso;
    }

    public boolean haCaminho(String origem, String destino, int n_vertices) {
        int[] visitados = new int[n_vertices];
        for (int i = 0; i < n_vertices; i++)
            visitados[i] = 0;
        return haCaminhoAux(origem, destino, visitados);
    }

    public boolean haCaminhoAux(String origem, String destino, int[] visitados) {
        visitados[Integer.parseInt(origem)-1] = 1;
        if (origem.equals(destino)) return true;
        for (Node nodo: this.grafo.get(Integer.parseInt(origem)-1).getValue()) {
                if (visitados[Integer.parseInt(nodo.getDestino())-1] == 0
                        && haCaminhoAux(nodo.getDestino(), destino, visitados))
                    return true;
        }
        return false;
    }

    public Collection<MyEntry<String, Integer>> caminhoMaisRapido(String origem, String destino, int n_vertices) {
        Collection<Node> nodosSucessores = this.grafo.get(Integer.parseInt(origem)-1).getValue();
        Collection<MyEntry<String, Integer>> caminho = new ArrayList<>();
        caminho.add(new MyEntry<>(origem, 0));
        try {
            if (nodosSucessores.size() == 0) return caminho;
            for (Node nodo : nodosSucessores)
                if (nodo.getDestino().equals(destino)) {
                    caminho.add(new MyEntry<>(nodo.getDestino(), nodo.getPeso()));
                    return caminho;
                }

            for (Node nodo : nodosSucessores) {
                if (haCaminho(nodo.getDestino(), destino, n_vertices)) {
                    for (Node sucNodo : this.grafo.get(Integer.parseInt(nodo.getDestino())-1).getValue()) {
                        if (haCaminho(sucNodo.getDestino(), destino, n_vertices))
                            caminho.add(new MyEntry<>(sucNodo.getDestino(), sucNodo.getPeso()));
                    }
                }
            }

            return caminho;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public void toString(int vertice) {
        for (Node nodo : this.grafo.get(vertice-1).getValue())
            System.out.println(nodo.getDestino() + ", " + nodo.getPeso());
    }

}