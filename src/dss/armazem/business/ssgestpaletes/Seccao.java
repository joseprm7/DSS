package dss.armazem.business.ssgestpaletes;

import dss.armazem.business.ssgestpaletes.Palete;

import java.util.ArrayList;
import java.util.List;

public class Seccao {
    private int id;
    private List<Palete> paletes;

    public Seccao() {
        this.id = -1;
        this.paletes = new ArrayList<>();
    }
}