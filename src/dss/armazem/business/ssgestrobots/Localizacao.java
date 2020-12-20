package dss.armazem.business.ssgestrobots;

import java.util.Objects;

/**
 * Classe que visa gerir todos os dados relativos a uma Localização de um objeto.
 * Como o Mapa do Armazém é dividido por "zonas", p.e zona de entrada, zona de saída,
 * secção 3 - prateleira 1, etc., esta classe irá ter apenas uma variável de instância
 * correspondente à zona do objeto no Mapa.
 *
 */
public class Localizacao {
    private int zona;

    /**
     * Construtor vazio
     */
    public Localizacao() {
        this.zona = -1;
    }

    /**
     * Construtor parametrizado
     * @param zona zona do objeto
     */
    public Localizacao(int zona) {
        this.zona = zona;
    }

    /**
     * Construtor por cópia
     * @param l Localização
     */
    public Localizacao(Localizacao l) {
        this.zona = l.getZona();
    }

    /**
     * Getters e Setters
     */

    public int getZona() {
        return this.zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    /**
     * Métodos clone, equals, hashCode e toString
     */

    public Localizacao clone() {
        return new Localizacao(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localizacao that = (Localizacao) o;
        return this.zona == that.zona;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zona);
    }

    /**
     * Este passa um inteiro para uma localização, para que seja legívele e identificavel pelo Utilizador
     */
    public String toString() {
        String r;
        int s = this.zona;

        if(s == -1) r = "Entrada";
        else if (s > -1 && s < 5) r = "Corredor Norte";
        else if (s == 5) r = "Corredor Oeste";
        else if (s > 5 && s < 11) r = "Corredor Sul";
        else if (s == 11) r = "Corredor Este";
        else if (s == 12) r = "Saída";
        else r = "Zona Desconhecida";

        return r;
    }
}
