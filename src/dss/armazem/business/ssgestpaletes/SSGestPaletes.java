package dss.armazem.business.ssgestpaletes;
import dss.armazem.business.ssgestrobots.Localizacao;

import java.util.*;
import java.util.stream.Collectors;

public class SSGestPaletes {
    private Collection<String> queue;
    private Collection<Palete> entrada;
    private Map<String,Seccao> seccoes;

    public SSGestPaletes() {
        this.queue = new ArrayList<>();
        this.seccoes = new TreeMap<>();
        this.entrada =  new ArrayList<>();
    }

    /**
     *
     */
    public void validaCodigo(String id, String descricao) {
        Palete p = new Palete("Queue", id, descricao, new Localizacao(-1));
        this.entrada.add(p);
        this.queue.add(p.getID());
    }

    /**
     *
     */
    public String queue() {
        List<String> p = (ArrayList<String>) this.queue;
        return p.get(0);
    }

    public Palete transportaPalete(String idPalete) {
        int r = 0;
        Palete p = this.entrada.stream().filter(x -> x.getID().equals(idPalete)).findFirst().orElse(null);
        if(p == null) {
            List<Seccao> ss = new ArrayList<>(this.seccoes.values());
            for(int i = 0; i < ss.size() && r==0; i++) {
                Collection<Palete> ps = ss.get(i).getPaletes();
                p = ps.stream().filter(x -> x.getID().equals(idPalete)).findFirst().orElse(null);
                if(p != null) {
                    r = 1;
                }
            }
        }
        return p;
    }

    public void notificaRecolha(String idPalete) {
        int r = 0;
        Palete p = this.entrada.stream().filter(x -> x.getID().equals(idPalete)).findFirst().orElse(null);
        if(p == null) {
            List<Seccao> ss = new ArrayList<>(this.seccoes.values());
            for(int i = 0; i < ss.size() && r==0; i++) {
                Collection<Palete> ps = ss.get(i).getPaletes();
                p = ps.stream().filter(x -> x.getID().equals(idPalete)).findFirst().orElse(null);
                if(p != null) {
                    ps.remove(p);
                    r = 1;
                }
            }
        }
        if(p != null) p.setEstado("Transporte");
    }
}