package edu.gatech.seclass.glm.Model;

import edu.gatech.seclass.glm.Model.Item;

/**
 * ListItem object implements comparable so we can sort by type
 * Created by danielbansch on 10/8/16.
 */

public class ListItem implements Comparable<ListItem> {

    private Integer id;
    private Item item;
    private boolean isChecked;
    private Integer quantity;

    /**
     * Constructor
     * @param id - Integer
     * @param item - Item
     * @param isChecked - boolean
     * @param quantity - Integer
     */
    public ListItem(Integer id, Item item, boolean isChecked, Integer quantity)
    {
        this.id = id;
        this.item = item;
        this.isChecked = isChecked;
        this.quantity = quantity;

    }

    /**
     * Returns ID
     * @return - Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns if the item is checked
     * @return - boolean
     */
    public boolean getIsChecked() {
        return isChecked;
    }

    /**
     * Sets checked value
     * @param isChecked - boolean
     */
    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    /**
     * Sets ID value
     * @param id - Integer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Return item
     * @return - Item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Update ListItem quantity
     * @return - Integer
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Update ListItem quantity
     * @param quantity - Integer
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Compares ListItems by type
     * @param item - Item
     * @return - 1 if the item type is lower, 0 if it is the same, -1 if it is higher
     */
    @Override
    public int compareTo(ListItem item) {

        if (item.getItem().getItemType().getId() < this.getItem().getItemType().getId()) {
            return 1;
        }
        else if (item.getItem().getItemType().getId() > this.getItem().getItemType().getId()) {
            return -1;
        }
        else {
            return 0;
        }

    }

    /**
     * Custom toString
     * @return - String
     */
    @Override
    public String toString(){
        return String.format("Name: %1$1s Type: %2$1s Quantity: %3$1s", this.item.getName(), this.item.getItemType().getName(), this.getQuantity());
    }

}
