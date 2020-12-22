package dss.armazem.business.ssgestpaletes;

import java.util.Objects;

/**
 * Classe que visa gerir todos os dados relativos a uma Palete.
 * Contém um estado que pode ser "Transporte" caso esteja a ser transportada por um robot,
 * "Pronta" caso esteja na queue de entrada à espera ed um robot ou
 * "Armazenada" caso já esteja guardada na sua prateleira
 * Contém um identificador para ser distinguida, uma descrição do material que a Palete contém
 * e uma localização
 */
public class Palete {
    private String estado;
    private String id;
    private String descricao;
    private int loc;

    /**
     * Construtor vazio
     */
    public Palete() {
        this.estado = "";
        this.id = "";
        this.descricao = "";
        this.loc = -1;
    }

    /**
     * Construtor parametrizado
     * @param estado estado da Palete
     * @param id identificador da Palete
     * @param descricao descrição do material contido
     * @param loc localização da Palete
     */
    public Palete(String estado, String id, String descricao, int loc) {
        this.estado = estado;
        this.id = id;
        this.descricao = descricao;
        this.loc = loc;
    }

    /**
     * Construtor por cópia
     * @param p Palete
     */
    public Palete(Palete p) {
        this.estado = p.getEstado();
        this.id = p.getID();
        this.descricao = p.getDescricao();
        this.loc = p.getLoc();
    }

    /**
     * Getters e setters
     */

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getID() {
        return this.id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getLoc() {
        return this.loc;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

    /**
     * Métodos equals, hashCode e clone
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Palete palete = (Palete) o;
        return this.id.equals(palete.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estado, id, descricao);
    }

    public Palete clone() {
        return new Palete(this);
    }

    @Override
    public String toString() {
        return "Palete{" +
                "estado='" + estado + '\'' +
                ", id='" + id + '\'' +
                ", descricao='" + descricao + '\'' +
                ", loc=" + loc +
                '}';
    }

    /**
     * Dependendo da localização anterior da palete, o método
     * altera o estado da Palete para "Armazenada" ou "Pronta"
     * @param locAtual localização que a Palete vai passar a ter, depois de ser transportada
     */
    public void paleteEntregue(int locAtual) {
        int loc = this.getLoc();
        if(loc == -1) {
            this.setEstado("Armazenada");
        } else {
            this.setEstado("Pronta");
        }
        this.setLoc(locAtual);
    }
}
