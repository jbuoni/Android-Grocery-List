package edu.gatech.seclass.glm.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * GroceryList object. Serializable so it can be passed between activities
 * Created by danielbansch on 10/8/16.
 */
@SuppressWarnings("serial")
public class GroceryList implements Serializable {

    private String name;
    private Integer id;
    private List<ListItem> listItems;

    /**
     * Constructor
     * @param name - String
     * @param id - Integer
     */
    public GroceryList(String name, Integer id) {
        this.name = name;
        this.id = id;
        this.listItems = null;
    }

    /**
     * Constructor
     * @param name - String
     * @param id - Integer
     * @param listItems - List<ListItem>
     */
    public GroceryList(String name, Integer id, List<ListItem> listItems)
    {
        this.name = name;
        this.id = id;
        this.listItems = listItems;
    }

    /**
     * Grocerylist name
     * @return - String representation of GroceryList name
     */
    public String getName() {
        return name;
    }

    /**
     * Update GroceryList name
     * @param name - String representation for the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns GroceryList id
     * @return - - Integer id for the grocery list
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns all list items
     * @return List<ListItem> List items for the grocery list
     */
    public List<ListItem> getAllListItems() {
        return listItems;
    }

    /**
     * Sets list to new ListItem list
     * @param listItems - List<ListItem> list of new items
     */
    public void setListItems(List<ListItem> listItems) {
        this.listItems = listItems;
    }

    /**
     * Add listItem to grocery list
     * @param listItem - ListItem to add
     */
    public void addListItem(ListItem listItem)
    {
        if (listItems == null)
        {
            listItems = new ArrayList<ListItem>();
        }
        listItems.add(listItem);
        
    }

    /**
     * Removes list item by id. Uses iterator for safe removal
     * @param id - - Integer id for the item
     */
    public void removeListItemById(Integer id)
    {
        //Use an iterator to ensure safe removal.
        Iterator<ListItem> i = listItems.iterator();
        while(i.hasNext()) {
            ListItem item = i.next();
            if(item.getId() == id) {
                i.remove();
            }
        }
    }

    /**
     * Unchecks all listItems in the grocery list
     */
    public void uncheckAllListItems()
    {
        for (int j = 0; j < listItems.size();j++) {
            listItems.get(j).setIsChecked(false);
        }
    }

    /**
     * Checks specified item in list
     * @param id - Integer id for the item
     */
    public void checkListItemById(Integer id)
    {
        for (ListItem item : listItems) {
            if(item.getId() == id) {
                item.setIsChecked(true);
            }
        }
    }

    /**
     * Sorts all listItems by type using the ListItem compare to function.
     */
    public void sortListByItemType()
    {
        Collections.sort(this.listItems);
    }

}
