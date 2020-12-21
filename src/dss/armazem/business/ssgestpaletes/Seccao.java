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

    /**
     * Construtor vazio
     */
    public Seccao() {
        this.id = "";
        this.paletes = new ArrayList<>();
    }

    /**
     * Construtor parametrizado
     * @param id identificador da Seccão
     * @param paletes lista de paletes Seccção
     */
    public Seccao(String id, List<Palete> paletes) {
        this.id = id;
        this.setPaletes(paletes);
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
}