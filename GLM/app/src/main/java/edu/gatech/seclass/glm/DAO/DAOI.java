package main.java.edu.gatech.seclass.glm.DAO;

import java.util.List;

import main.java.edu.gatech.seclass.glm.Model.GroceryList;
import main.java.edu.gatech.seclass.glm.Model.Item;
import main.java.edu.gatech.seclass.glm.Model.ItemType;
import main.java.edu.gatech.seclass.glm.Model.ListItem;

/**
 * Created by danielbansch on 10/8/16.
 */

public interface DAOI {
    public void updateList(GroceryList groceryList);
    public GroceryList createList(String name);
    public GroceryList loadList(Integer id);
    public ListItem addItemToList(Integer groceryListID, Integer itemID, Integer quantity);
    public void deleteList(Integer id);
    public Item createNewItem(String itemName, Integer itemTypeID);
    public List<Item> findItemsLike(String searchString);
    public List<Item> getItemsByItemType(ItemType itemType);
    public List<ItemType> getAllItemTypes();
    public List<GroceryList> getAllLists();
}
