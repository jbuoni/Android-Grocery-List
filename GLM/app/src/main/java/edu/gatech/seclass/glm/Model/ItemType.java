package edu.gatech.seclass.glm.Model;

/**
 * Created by danielbansch on 10/8/16.
 */

public class ItemType implements Comparable<ListItem> {

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

    /**
     * Compares ListItems by type
     * @param item - Item
     * @return - 1 if the item type is lower, 0 if it is the same, -1 if it is higher
     */
    @Override
    public int compareTo(ListItem item) {

        if (item.getId() < this.id) {
            return 1;
        }
        else if (item.getId() > this.id) {
            return -1;
        }
        else {
            return 0;
        }

    }


}
