package dss.armazem.business;

import java.util.Objects;

public class Gestor {
    private String username;
    private String pw;
    private boolean online;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gestor gestor = (Gestor) o;
        return online == gestor.online &&
                Objects.equals(username, gestor.username) &&
                Objects.equals(pw, gestor.pw);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, pw, online);
    }
}
