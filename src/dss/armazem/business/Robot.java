package dss.armazem.business;

import java.util.Objects;

public class Robot {
    private String id;
    private String estado;
    private Palete palete;
    private Localizacao loc;

    public Robot(){
        this.id = "";
        this.estado = "";
        this.palete = null;
        this.loc = new Localizacao();
    }

    public Robot(String id, String estado, Palete palete, Localizacao loc) {
        this.id = id;
        this.estado = estado;
        setPalete(palete);
        setLoc(loc);
    }

    public Robot(Robot r) {
        this.id = r.getId();
        this.estado = r.getEstado();
        this.palete = r.getPalete();
        this.loc = r.getLoc();
    }

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

    public Localizacao getLoc() {
        return this.loc.clone();
    }

    public void setLoc(Localizacao loc) {
        this.loc = loc.clone();
    }

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
