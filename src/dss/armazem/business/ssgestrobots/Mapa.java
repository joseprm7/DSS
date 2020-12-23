package dss.armazem.business.ssgestrobots;
import java.util.*;

public class Mapa {
    private final List<MyEntry<String, Collection<Node>>> mapa;

    public Mapa() {
        this.mapa = new ArrayList<>();
    }

    public Mapa(List<MyEntry<String, Collection<Node>>> grafo) {
        this.mapa = grafo;
    }

    public List<MyEntry<String, Collection<Node>>> getMapa() {
        return this.mapa;
    }

    public void put(MyEntry<String, Collection<Node>> entry) {
        this.mapa.add(entry);
    }

    public void addNodo(int origem, Node node) {
        this.mapa.get(origem-1).getValue().add(node);
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

    public int caminhoPeso(String origem, String destino, int n_vertices) {
        int[] visitados = new int[n_vertices];
        int peso = 0;
        for (int i = 0; i < n_vertices; i++)
            visitados[i] = 0;
        return caminhoAuxPeso(origem, destino, visitados, peso);
    }

    public int caminhoAuxPeso(String origem, String destino, int[] visitados, int peso) {
        visitados[Integer.parseInt(origem)-1] = 1;
        if (origem.equals(destino)) return peso;
        for (Node nodo: this.mapa.get(Integer.parseInt(origem)-1).getValue()) {
            if (visitados[Integer.parseInt(nodo.getDestino())-1] == 0
                    && haCaminhoAux(nodo.getDestino(), destino, visitados)) {
                peso += nodo.getPeso();
                return caminhoAuxPeso(nodo.getDestino(), destino, visitados, peso);
            }
        }
        return 0;
    }

    public boolean haCaminhoAux(String origem, String destino, int[] visitados) {
        visitados[Integer.parseInt(origem)-1] = 1;
        if (origem.equals(destino)) return true;
        for (Node nodo: this.mapa.get(Integer.parseInt(origem)-1).getValue()) {
                if (visitados[Integer.parseInt(nodo.getDestino())-1] == 0
                        && haCaminhoAux(nodo.getDestino(), destino, visitados))
                    return true;
        }
        return false;
    }

    public Collection<MyEntry<String, Integer>> caminhoMaisRapido(String origem, String destino, int n_vertices, int[] visitados) {
        try {
            Collection<Node> nodosSucessores = this.mapa.get(Integer.parseInt(origem)-1).getValue();
            Collection<MyEntry<String, Integer>> caminho = new ArrayList<>();
            visitados[Integer.parseInt(origem)-1] = 1;
            caminho.add(new MyEntry<>(origem, 0));
            if (nodosSucessores.size() == 0) return caminho;
            for (Node nodo : nodosSucessores)
                if (nodo.getDestino().equals(destino)) {
                    caminho.add(new MyEntry<>(nodo.getDestino(), nodo.getPeso()));
                    return caminho;
                }

            List<Integer> listPesos = new ArrayList<>();
            for (Node nodo : nodosSucessores) {
                int pesoAtual = caminhoPeso(nodo.getDestino(), destino, n_vertices);
                listPesos.add(pesoAtual);
            }

            Node node = new Node();
            for (Node nodo : nodosSucessores) {
                int pesoAtual = caminhoPeso(nodo.getDestino(), destino, n_vertices);
                System.out.println(pesoAtual);
                System.out.println(minLista(listPesos));
                if (visitados[Integer.parseInt(nodo.getDestino()) - 1] == 0
                        && haCaminho(nodo.getDestino(), destino, n_vertices)
                        && pesoAtual == minLista(listPesos)) {
                    node = nodo;
                }
            }
            //caminho.add(new MyEntry<>(node.getDestino(), node.getPeso()));
            caminho.addAll(caminhoMaisRapido(node.getDestino(), destino, n_vertices, visitados));
            return caminho;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public int minLista(List<Integer> lista) {
        int min = lista.get(0);
        for (Integer i : lista)
            if (i < min) min = i;
        return min;
    }

    public void toString(int vertice) {
        for (Node nodo : this.mapa.get(vertice-1).getValue())
            System.out.println(nodo.getDestino() + ", " + nodo.getPeso());
    }

}