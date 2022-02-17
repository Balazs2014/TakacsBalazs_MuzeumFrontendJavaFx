package hu.csepel.muzeumfrontendjavafx.classes;

public class Festmeny {
    private int id;
    private String cim;
    private Boolean kiallitva;
    private int ev;

    public Festmeny(int id, String cim, Boolean kiallitva, int ev) {
        this.id = id;
        this.cim = cim;
        this.kiallitva = kiallitva;
        this.ev = ev;
    }

    public int getId() {
        return id;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public Boolean getKiallitva() {
        return kiallitva;
    }

    public void setKiallitva(Boolean kiallitva) {
        this.kiallitva = kiallitva;
    }

    public int getEv() {
        return ev;
    }

    public void setEv(int ev) {
        this.ev = ev;
    }
}
