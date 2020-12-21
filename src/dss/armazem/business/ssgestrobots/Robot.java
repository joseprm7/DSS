package dss.armazem.business.ssgestrobots;

import dss.armazem.business.ssgestpaletes.Palete;
import java.util.Objects;

/**
 * Classe que visa gerir todos os dados relativos a um Robot.
 * Um Robot contém um identificador que visa distingui-lo dos demais,
 * Um Estado que permite ver se ele se encontra em transporte de uma Palete ou livre
 * para transportar,
 * Uma Palete que, no caso de estar a transportar alguma, lhe está associada e,
 * caso não esteja, a Palete será nula,
 * Uma Localização que permite ao Sistema saber onde se encontra no Mapa.
 */
public class Robot {
    private String id;
    private String estado;
    private Palete palete;
    private int loc;

    /**
     * Construtor vazio
     */
    public Robot() {
        this.id = "";
        this.estado = "";
        this.palete = null;
        this.loc = -1;
    }

    /**
     * Construtor parametrizado
     * @param id identificador
     * @param estado estado
     * @param palete Palete associada
     * @param loc Localização
     */
    public Robot(String id, String estado, Palete palete, int loc) {
        this.id = id;
        this.estado = estado;
        this.setPalete(palete);
        this.loc = loc;
    }

    /**
     * Construtor por cópia
     * @param r Robot
     */
    public Robot(Robot r) {
        this.id = r.getId();
        this.estado = r.getEstado();
        this.palete = r.getPalete();
        this.loc = r.getLoc();
    }

    /**
     * Getters e Setters
     */

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Palete getPalete() {
        return this.palete;
    }

    public void setPalete(Palete palete) {
        this.palete = palete/*.clone()*/;
    }

    public int getLoc() {
        return this.loc;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

    /**
     * Métodos equals, hashCode e clone
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return id.equals(robot.id) &&
                estado.equals(robot.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estado);
    }

    public Robot clone() {
        return new Robot(this);
    }

    /**
     * O Robot entrega a Palete e altera o seu estado para "Livre".
     * A partir deste momento, a Palete deixa de estar associada ao Robot e, por isso,
     * a variável palete passa a tomar o valor de null.
     * @param locAtual localização atual do Robot e, consequentemente, da Palete
     */
    public void notificaEntrega(int locAtual) {
        this.setEstado("Livre");
        this.setLoc(locAtual);
        Palete p = this.getPalete();
        p.paleteEntregue(locAtual);
        this.setPalete(null);
    }
}
