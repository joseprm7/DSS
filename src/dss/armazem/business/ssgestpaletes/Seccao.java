package dss.armazem.business.ssgestpaletes;

import java.util.ArrayList;
import java.util.List;

public class Seccao {
    private int id;
    private List<Palete> paletes;

    public Seccao() {
        this.id = -1;
        this.paletes = new ArrayList<>();
    }

    public Seccao(int id, List<Palete> paletes) {
        this.id = id;
        this.setPaletes(paletes);
    }

    public void addPalete(Palete p) {
        this.paletes.add(p);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Palete> getPaletes() {
        return this.paletes;
    }

    public void setPaletes(List<Palete> paletes) {
        this.paletes = paletes;
    }
}