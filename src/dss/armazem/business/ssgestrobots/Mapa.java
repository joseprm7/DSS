package dss.armazem.business.ssgestrobots;
import java.util.*;

/**
 * Classe Mapa.
 * Esta classe corresponde, de certa forma, a um grafo que contém uma lista de vértices que,
 * por sua vez, estão associados a uma lista de vértices que os sucedem. Caso exista algum vértice
 * "X" que não tenha vértices sucessores, a lista que estará associada a "X" estará vazia.
 */
public class Mapa {
    private final List<MyEntry<String, Collection<Node>>> mapa;

    /**
     * Construtor vazio. Aqui já é construido o mapa/grafo do Armazem
     */
    public Mapa() {
        this.mapa = new ArrayList<>();
        for (int i = 1; i <= 14; i++) put(new MyEntry<>(Integer.toString(i), new ArrayList<>()));

        addNodo(1, new Node("2", 1));

        addNodo(2, new Node("1", 1));
        addNodo(2, new Node("13", 1));
        addNodo(2, new Node("3", 1));

        addNodo(3, new Node("4", 1));
        addNodo(3, new Node("2", 1));

        addNodo(4, new Node("5", 1));
        addNodo(4, new Node("3", 1));

        addNodo(5, new Node("6", 1));
        addNodo(5, new Node("4", 1));

        addNodo(6, new Node("5", 1));
        addNodo(6, new Node("7", 1));

        addNodo(7, new Node("6", 1));
        addNodo(7, new Node("14", 1));
        addNodo(7, new Node("8", 1));

        addNodo(8, new Node("9", 1));
        addNodo(8, new Node("7", 1));

        addNodo(9, new Node("10", 1));
        addNodo(9, new Node("8", 1));

        addNodo(10, new Node("11", 1));
        addNodo(10, new Node("9", 1));

        addNodo(11, new Node("10", 1));
        addNodo(11, new Node("12", 1));

        addNodo(12, new Node("13", 1));
        addNodo(12, new Node("11", 1));

        addNodo(13, new Node("2", 1));
        addNodo(13, new Node("12", 1));

        addNodo(14, new Node("7", 1));
    }

    /**
     * Construtor parametrizado
     * @param grafo grafo/mapa
     */
    public Mapa(List<MyEntry<String, Collection<Node>>> grafo) {
        this.mapa = grafo;
    }

    /**
     * Getter que obtém o mapa
     * @return mapa
     */
    public List<MyEntry<String, Collection<Node>>> getMapa() {
        return this.mapa;
    }

    /**
     * Adiciona uma entrada à lista
     * @param entry entrada
     */
    public void put(MyEntry<String, Collection<Node>> entry) {
        this.mapa.add(entry);
    }

    /**
     * Adiciona um nodo sucessor a um determinado vértice que lhe antecede
     * @param origem vértice origem
     * @param node nodo destino
     */
    public void addNodo(int origem, Node node) {
        this.mapa.get(origem-1).getValue().add(node);
    }

    /**
     * Verifica se há caminho de um vértice origem até um determinado destino
     * @param origem identificador do vértice origem
     * @param destino identificador do vértice destino
     * @param n_vertices número de vértices total do mapa/grafo
     * @return true caso exista caminho, falso em caso contrário
     */
    public boolean haCaminho(String origem, String destino, int n_vertices) {
        int[] visitados = new int[n_vertices];
        for (int i = 0; i < n_vertices; i++)
            visitados[i] = 0;
        return haCaminhoAux(origem, destino, visitados);
    }

    /**
     * Função auxiliar da haCaminho
     * @param origem identificador do vértice origem
     * @param destino identificador do vértice destino
     * @param visitados array com número de posições igual ao número total de vértices.
     *                  Caso o valor de uma posição seja 1, significa que a função já passou
     *                  por esse vértice, e por isso não irá voltar mais. Caso seja 0,
     *                  significa que a função ainda não passou nesse vértice
     * @return true caso continue a existir caminho, false em caso contrário
     */
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

    /**
     * Calcula o caminho de um vértice origem até um vértice destino
     * @param origem identificador do vértice origem
     * @param destino identificador do vértice destino
     * @param n_vertices número total de vértices do mapa/grafo
     * @param visitados array com número de posições igual ao número total de vértices.
     *                  Caso o valor de uma posição seja 1, significa que a função já passou
     *                  por esse vértice, e por isso não irá voltar mais. Caso seja 0,
     *                  significa que a função ainda não passou nesse vértice
     * @return lista de entradas
     */
    public Collection<MyEntry<String, Integer>> caminhoMaisRapido(String origem, String destino, int n_vertices, int[] visitados) {
        try {
            if (origem.equals(destino)) {
                Collection<MyEntry<String, Integer>> list = new ArrayList<>();
                list.add(new MyEntry<>(origem, 0));
                return list;
            }
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

            Node node = new Node();
            for (Node nodo : nodosSucessores) {
                if (visitados[Integer.parseInt(nodo.getDestino()) - 1] == 0
                        && haCaminho(nodo.getDestino(), destino, n_vertices)) {
                    node = nodo;
                }
            }

            caminho.addAll(caminhoMaisRapido(node.getDestino(), destino, n_vertices, visitados));
            return caminho;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }
}