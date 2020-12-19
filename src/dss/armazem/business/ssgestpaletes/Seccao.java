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
}