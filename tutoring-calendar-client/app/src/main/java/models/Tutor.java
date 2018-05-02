package models;

import android.graphics.Bitmap;

/**
 * Created by bart on 22/04/18.
 */

public class Tutor {

    private int ra;
    private String name;
    private String description;
    private Bitmap image;

    public Tutor(int ra, String name, String description, Bitmap image) {
        this.setRa(ra);
        this.setName(name);
        this.setDescription(description);
        this.setImage(image);
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

    public Bitmap getImage() { return this.image; }

    public void setImage(Bitmap image) { this.image = image; }
}
