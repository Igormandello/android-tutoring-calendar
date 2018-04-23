package models;

/**
 * Created by bart on 22/04/18.
 */

public class Tutor {

    private int ra;
    private String name;
    private String description;

    public Tutor(int ra, String name, String description) {
        this.setRa(ra);
        this.setName(name);
        this.setDescription(description);
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
