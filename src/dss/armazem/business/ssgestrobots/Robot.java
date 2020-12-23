package dss.armazem.business.ssgestrobots;

import dss.armazem.business.ssgestpaletes.Palete;
import dss.armazem.data.PaleteDAO;

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
    private String palete;
    private int loc;
    private final PaleteDAO paleteDAO;

    /**
     * Construtor vazio
     */
    public Robot() {
        this.id = "";
        this.estado = "";
        this.palete = "";
        this.loc = -1;
        this.paleteDAO = new PaleteDAO();
    }

    /**
     * Construtor parametrizado
     * @param id identificador
     * @param estado estado
     * @param palete Palete associada
     * @param loc Localização
     */
    public Robot(String id, String estado, String palete, int loc) {
        this.id = id;
        this.estado = estado;
        this.palete = palete;
        this.loc = loc;
        this.paleteDAO = new PaleteDAO();
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
        this.paleteDAO = new PaleteDAO();
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

    public String getPalete() {
        return this.palete;
    }

    public void setPalete(String palete) {
        this.palete = palete;
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
}
