package dss.armazem.business.ssgestpaletes;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que visa gerir todos os dados relativos a uma Secção.
 * Uma seccão contém várias prateleiras, que por sua vez cada uma contém uma Palete.
 * Esta classe tem como variáveis de instância um identificador que permite distinguir
 * as diversas secções do mapa e uma lista com as Paletes que lá estão armazenadas.
 */
public class Seccao {
    private String id;
    private List<Palete> paletes;
    private boolean ocupado;
    private int loc;
    private int prateleira;

    /**
     * Construtor vazio
     */
    public Seccao() {
        this.id = "";
        this.paletes = new ArrayList<>();
        this.ocupado = false;
        this.loc = -2;
        this.prateleira = 0;
    }

    /**
     * Construtor parametrizado
     * @param id identificador da Seccão
     * @param paletes lista de paletes Seccção
     */
    public Seccao(String id, List<Palete> paletes, boolean ocupado, int loc, int prateleira) {
        this.id = id;
        this.setPaletes(paletes);
        this.ocupado = ocupado;
        this.loc = loc;
        this.prateleira = prateleira;
    }

    /**
     * Adiciona uma Palete à lista de Paletes
     * @param p palete a ser inserida
     */
    public void addPalete(Palete p) {
        this.paletes.add(p);
    }

    /**
     * Getters e setters
     */

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

    public boolean isOcupado() {
        return this.ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public int getLoc() {
        return this.loc;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

    public int getPrateleira() {
        return this.prateleira;
    }

    public void setPrateleira(int prateleira) {
        this.prateleira = prateleira;
    }
}