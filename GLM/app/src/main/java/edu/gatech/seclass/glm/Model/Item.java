package edu.gatech.seclass.glm.Model;

/**
 * Created by danielbansch on 10/8/16.
 */

public class Item {

    private Integer id;
    private String name;
    private ItemType itemType;

    public Item(Integer id, String name, ItemType itemType)
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

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
