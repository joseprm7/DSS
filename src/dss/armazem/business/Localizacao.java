package dss.armazem.business;

import java.util.Objects;

public class Localizacao {
    private int seccao;

    public Localizacao() {
        this.seccao = -1;
    }

    public Localizacao(int seccao) {
        this.seccao = seccao;
    }

    public Localizacao(Localizacao l) {
        this.seccao = l.getSeccao();
    }

    public int getSeccao() {
        return this.seccao;
    }

    public void setSeccao(int seccao) {
        this.seccao = seccao;
    }

    public Localizacao clone() {
        return new Localizacao(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localizacao that = (Localizacao) o;
        return this.seccao == that.seccao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seccao);
    }

    /**
     * Este passa um inteiro para uma localização, para que seja legívele e identificavel pelo Utilizador
     */
    public String toString() {
        String r = " ";
        int s = this.seccao;

        if(s == -1) r = "Zona de Entrada";
        else if (s > -1 && s < 5) r = "Corredor Norte -> Prateleira " + (s+1);
        else if (s == 5) r = "Corredor Oeste";
        else if (s > 5 && s < 11) r = "Corredor Sul -> Prateleira " + (10 - s + 1);
        else if (s == 11) r = "Corredor Este";
        else if (s == 12) r = "Zona de Saída";
        else r = "Zona Desconhecida";

        return r;
    }
}
