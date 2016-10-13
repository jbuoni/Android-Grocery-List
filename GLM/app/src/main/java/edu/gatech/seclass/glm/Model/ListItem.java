package edu.gatech.seclass.glm.Model;

import edu.gatech.seclass.glm.Model.Item;

/**
 * Created by danielbansch on 10/8/16.
 */

public class ListItem implements Comparable<ListItem> {

    private Integer id;
    private Item item;
    private boolean isChecked;
    private Integer quantity;

    public ListItem(Integer id, Item item, boolean isChecked, Integer quantity)
    {
        this.id = id;
        this.item = item;
        this.isChecked = isChecked;
        this.quantity = quantity;

    }

    public Integer getId() {
        return id;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Override
    public int compareTo(ListItem item) {

        if (item.getItem().getItemType() < this.getItem().getItemType()) {
            return 1;
        }
        else if (item.getItem().getItemType() > this.getItem().getItemType()) {
            return -1;
        }
        else {
            return 0;
        }

    }

    @Override
    public String toString(){
        return String.format("Name: %1$2s Type: %2$2s Quantity: %3$2s", this.item.getName(), this.item.getItemType(), this.getQuantity());
    }

}
