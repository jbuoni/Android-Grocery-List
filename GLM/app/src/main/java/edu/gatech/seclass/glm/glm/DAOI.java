package main.java.edu.gatech.seclass.glm.glm;

import java.util.List;

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
