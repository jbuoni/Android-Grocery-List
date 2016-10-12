package main.java.edu.gatech.seclass.glm.Model;

/**
 * Created by danielbansch on 10/8/16.
 */

public class ItemType {

    private Integer id;
    private String name;

    public ItemType(Integer id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
