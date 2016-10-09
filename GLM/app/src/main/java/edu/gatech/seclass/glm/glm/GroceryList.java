package main.java.edu.gatech.seclass.glm.glm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielbansch on 10/8/16.
 */

public class GroceryList {

    private String name;
    private Integer id;
    private List<ListItem> listItems;

    public GroceryList(String name, Integer id, List<ListItem> listItems)
    {
        this.name = name;
        this.id = id;
        this.listItems = listItems;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public List<ListItem> getAllListItems() {
        return listItems;
    }

    public void setListItems(List<ListItem> listItems) {
        this.listItems = listItems;
    }

    public void addListItem(ListItem listItem)
    {
        if (listItems == null)
        {
            listItems = new ArrayList<ListItem>();
        }
        listItems.add(listItem);
        
    }

    public void removeListItemById(Integer id)
    {
        //// TODO: 10/8/16
    }

    public void uncheckAllListItems()
    {
        //// TODO: 10/8/16
    }

    public void checkListItemById(Integer id)
    {
        //// TODO: 10/8/16
    }

    public void sortListByItemType()
    {
        //// TODO: 10/8/16
    }

}
