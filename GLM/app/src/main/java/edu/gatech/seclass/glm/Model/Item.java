package edu.gatech.seclass.glm.Model;

import java.io.Serializable;

/**
 * Created by danielbansch on 10/8/16.
 */

public class Item  implements Serializable {

    private Integer id;
    private String name;
    private ItemType itemType;

    /**
     * Constructor
     * @param id - Integer
     * @param name - String
     * @param itemType - ItemType
     */
    public Item(Integer id, String name, ItemType itemType)
    {
        this.id = id;
        this.name = name;
        this.itemType = itemType;
    }

    /**
     * Returns item id
     * @return - Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns Item name
     * @return - String
     */
    public String getName() {
        return name;
    }

    /**
     * Updates Item name
     * @param name - String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns Items ItemType
     * @return - ItemType
     */
    public ItemType getItemType() {
        return itemType;
    }

    /**
     * Update ItemType
     * @param itemType - ItemType
     */
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
