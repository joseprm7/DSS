package dss.armazem.business;

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