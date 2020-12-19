package dss.armazem.business.ssgestpaletes;
import dss.armazem.business.ssgestrobots.Localizacao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SSGestPaletes {
    private List<String> queue;
    private Map<String,Seccao> seccoes;

    public SSGestPaletes() {
        this.queue = new ArrayList<>();
        this.seccoes = new TreeMap<>();
        this.seccoes.put("Entrada", new Seccao("Entrada",new ArrayList<>()));
    }

    public void validaCodigo(String id, String descricao) {
        Palete p = new Palete("Em Queue", id, descricao, new Localizacao(-1));
        this.seccoes.get("Entrada").addPalete(p);
        this.queue.add(p.getID());
    }

    public String queue() {
        return this.queue.get(0);
    }
}
