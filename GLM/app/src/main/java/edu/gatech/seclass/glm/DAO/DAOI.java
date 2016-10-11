package edu.gatech.seclass.glm.DAO;

import java.util.List;

import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ItemType;

/**
 * Created by danielbansch on 10/8/16.
 */

public interface DAOI {
    public GroceryList saveList(GroceryList groceryList);
    public GroceryList loadList(Integer id);
    public void deleteList(Integer id);
    public void addNewItem(Item item);
    public List<Item> findItemsLike(String searchString);
    public List<Item> getItemsByItemType(ItemType itemType);
    public List<ItemType> getAllItemTypes();
    public List<GroceryList> getAllLists();
}
