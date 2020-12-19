package dss.armazem.business.ssgestpaletes;

import dss.armazem.business.ssgestrobots.Localizacao;
import java.util.Objects;

public class Palete {
    private String estado;
    private String id;
    private String descricao;
    private Localizacao loc;

    public Palete() {
        this.estado = "";
        this.id = "";
        this.descricao = "";
        this.loc = new Localizacao();
    }

    public Palete(String estado, String id, String descricao, Localizacao loc) {
        this.estado = estado;
        this.id = id;
        this.descricao = descricao;
        this.loc = loc;
    }

    public Palete(Palete p) {
        this.estado = p.getEstado();
        this.id = p.getID();
        this.descricao = p.getDescricao();
        this.loc = p.getLoc();
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

    public Localizacao getLoc() {
        return this.loc.clone();
    }

    public void setLoc(Localizacao loc) {
        this.loc = new Localizacao(loc);
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
