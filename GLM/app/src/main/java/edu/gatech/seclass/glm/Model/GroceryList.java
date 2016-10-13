package edu.gatech.seclass.glm.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by danielbansch on 10/8/16.
 */
@SuppressWarnings("serial")
public class GroceryList implements Serializable {

    private String name;
    private Integer id;
    private List<ListItem> listItems;

    public GroceryList(String name, Integer id) {
        this.name = name;
        this.id = id;
        this.listItems = null;
    }

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
        //Use an iterator to ensure safe removal.
        Iterator<ListItem> i = listItems.iterator();
        while(i.hasNext()) {
            ListItem item = i.next();
            if(item.getId() == id) {
                i.remove();
            }
        }
    }

    public void uncheckAllListItems()
    {
        for (int j = 0; j < listItems.size();j++) {
            listItems.get(j).setIsChecked(false);
        }
    }

    public void checkListItemById(Integer id)
    {
        for (ListItem item : listItems) {
            if(item.getId() == id) {
                item.setIsChecked(true);
            }
        }
    }

    public void sortListByItemType()
    {
        Collections.sort(this.listItems);
    }

}
