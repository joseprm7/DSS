package dss.armazem.business.ssgestrobots;

import java.util.Objects;

public class Localizacao {
    private int zona;

    public Localizacao() {
        this.zona = -1;
    }

    public Localizacao(int seccao) {
        this.zona = seccao;
    }

    public Localizacao(Localizacao l) {
        this.zona = l.getZona();
    }

    public int getZona() {
        return this.zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

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
