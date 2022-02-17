package hu.csepel.muzeumfrontendjavafx.classes;

public class Festmeny {
    private int id;
    private String title;
    private Boolean on_display;
    private int year;

    public Festmeny(int id, String title, Boolean on_display, int year) {
        this.id = id;
        this.title = title;
        this.on_display = on_display;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getOn_display() {
        return on_display;
    }

    public void setOn_display(Boolean on_display) {
        this.on_display = on_display;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
