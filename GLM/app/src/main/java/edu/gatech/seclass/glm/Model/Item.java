package edu.gatech.seclass.glm.Model;

/**
 * Created by danielbansch on 10/8/16.
 */

public class Item {

    private Integer id;
    private String name;
    private Integer itemType;

    public Item(Integer id, String name, Integer itemType)
    {
        this.id = id;
        this.name = name;
        this.itemType = itemType;
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

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }
}
