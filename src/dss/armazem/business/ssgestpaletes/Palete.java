package dss.armazem.business.ssgestpaletes;

import java.util.Objects;

public class Palete {
    private String estado;
    private String id;
    private String descricao;

    public Palete() {
        this.estado = "";
        this.id = "";
        this.descricao = "";
    }

    public Palete(String estado, String id, String descricao) {
        this.estado = estado;
        this.id = id;
        this.descricao = descricao;
    }

    public Palete(Palete p) {
        this.estado = p.getEstado();
        this.id = p.getID();
        this.descricao = p.getDescricao();
    }

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
}
