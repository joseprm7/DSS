package dss.armazem.business.ssgestpaletes;
import dss.armazem.business.ssgestrobots.Localizacao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SSGestPaletes {
    private List<String> queue;
    private List<Palete> entrada;
    private Map<String,Seccao> seccoes;

    public SSGestPaletes() {
        this.queue = new ArrayList<>();
        this.seccoes = new TreeMap<>();
    }

    /**
     *
     * @param id
     * @param descricao
     */
    public void validaCodigo(String id, String descricao) {
        Palete p = new Palete("Queue", id, descricao, new Localizacao(-1));
        this.entrada.add(p);
        this.queue.add(p.getID());
    }

    public String queue() {
        return this.queue.get(0);
    }
}