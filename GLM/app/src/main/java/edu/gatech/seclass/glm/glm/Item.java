package edu.gatech.seclass.glm.glm;

/**
 * Created by danielbansch on 10/8/16.
 */

public class Item {

    private Integer id;
    private String name;

    public Item(Integer id, String name)
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
