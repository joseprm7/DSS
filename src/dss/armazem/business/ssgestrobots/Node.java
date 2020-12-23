package dss.armazem.business.ssgestrobots;

public class Node {
    private String destino;
    private int peso;

    public Node() {
        this.destino = "";
        this.peso = 1;
    }

    public Node(String destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public Node(Node n) {
        this.destino = n.getDestino();
        this.peso = n.getPeso();
    }

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
