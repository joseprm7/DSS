package dss.armazem.business.ssgestrobots;

/**
 * Classe Nodo.
 * Dado uma lista de Map.Entry, ou seja, uma lista de entradas de um Map, em que a chave da Entry
 * corresponde a um identificador do vértice (por exemplo, "1") e o valor a uma lista dos seus vértices
 * sucessores. Ora, esses vértices correspondem a Nodos, que contêm um identificador (com o nome
 * destino porque é o sucessor do vértice antecessor que está na chave) e o valor do peso
 * da aresta que liga o vértice sucessor ao antecessor. Neste caso, o peso pode corresponder à distância
 * entre os vértices antecessor e sucessor.
 * É importante referir que poderão existir casos em que o vértice "1", por exemplo, se liga ao vértice "2",
 * mas o contrário não acontece.
 */
public class Node {
    private String destino;
    private int peso;

    /**
     * Construtor vazio
     */
    public Node() {
        this.destino = "";
        this.peso = 1;
    }

    /**
     * Construtor parametrizado
     * @param destino destino do nodo antecessor
     * @param peso peso da ligação do vértice antecessor ao vértice sucessor
     */
    public Node(String destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    /**
     * Construtor por cópia
     * @param n Node
     */
    public Node(Node n) {
        this.destino = n.getDestino();
        this.peso = n.getPeso();
    }

    /**
     * Getters e Setters
     */

    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
