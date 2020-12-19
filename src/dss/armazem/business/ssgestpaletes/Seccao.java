package dss.armazem.business.ssgestpaletes;

import dss.armazem.business.ssgestpaletes.Palete;

import java.util.ArrayList;
import java.util.List;

public class Seccao {
    private String id;
    private List<Palete> paletes;

    public Seccao() {
        this.id = "";
        this.paletes = new ArrayList<>();
    }

    public Seccao(String id, List<Palete> paletes) {
        this.id = id;
        this.setPaletes(paletes);
    }

    public void addPalete(Palete p) {
        this.paletes.add(p);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Palete> getPaletes() {
        return this.paletes;
    }

    public void setPaletes(List<Palete> paletes) {
        this.paletes = paletes;
    }
}