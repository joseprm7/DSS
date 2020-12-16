package dss.armazem.business;

import java.util.Objects;

public class Robot {
    private String id;
    private String estado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
}
