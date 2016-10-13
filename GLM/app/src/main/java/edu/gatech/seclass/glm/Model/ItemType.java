package edu.gatech.seclass.glm.Model;

/**
 * Created by danielbansch on 10/8/16.
 */

public class ItemType implements Comparable<ItemType> {

    private Integer id;
    private String name;

    /**
     * Constructor
     * @param id - Integer
     * @param name - String
     */
    public ItemType(Integer id, String name)
    {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns ID
     * @return - Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns Type name
     * @return - String
     */
    public String getName() {
        return name;
    }

    /**
     * Updates Type name
     * @param name - String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Compares ListItems by type
     * @param item - Item
     * @return - 1 if the item type is lower, 0 if it is the same, -1 if it is higher
     */
    @Override
    public int compareTo(ItemType item) {

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
