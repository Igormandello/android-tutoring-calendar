package models;

/**
 * Created by bart on 22/04/18.
 */

public class Tutor {

    private int ra;
    private String name;
    private String description;
    private String imgPath;

    public Tutor(int ra, String name, String description, String imgPath) {
        this.setRa(ra);
        this.setName(name);
        this.setDescription(description);
        this.setImgPath(imgPath);
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

}
