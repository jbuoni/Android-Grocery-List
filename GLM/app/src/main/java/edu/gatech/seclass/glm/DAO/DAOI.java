package edu.gatech.seclass.glm.DAO;

import java.util.List;

import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ItemType;
import edu.gatech.seclass.glm.Model.ListItem;

/**
 * Created by danielbansch on 10/8/16.
 */

public interface DAOI {

    /**
     * Update a GroceryList's name in the database.
     * @param groceryList: The grocery list to be updated.
     */
    void updateListName(GroceryList groceryList);

    /**
     * Insert a new GroceryList into the database.
     * @param name: The name of the new GroceryList.
     * @return
     */
    GroceryList createList(String name);

    /**
     * Load a grocery list from the database.
     * @param id: The id of the grocery list to be loaded.
     * @return The loaded grocery list.
     */
    GroceryList loadList(Integer id);

    /**
     * Add a ListItem to a GroceryList.
     * @param groceryListID: The GroceryList's ID.
     * @param itemID: The ID of the new ListItem's item.
     * @param quantity: The quantity of the ListItem.
     * @return The new ListItem.
     */
    ListItem addItemToList(Integer groceryListID, Integer itemID, Integer quantity);

    /**
     * Delete a GroceryList.
     * @param id: The id of the GroceryList to delete.
     */
    void deleteList(Integer id);

    /**
     * Insert a new Item in the database.
     * @param itemName: The name of the Item.
     * @param itemTypeID: The ItemType id of the new item.
     * @return The new Item.
     */
    Item createNewItem(String itemName, Integer itemTypeID);

    /**
     * Search for Items by name.
     * @param searchString: The string to search for.
     * @return A List<Item> containing the found Items.
     */
    List<Item> findItemsLike(String searchString);

    /**
     * Return all of the Items of a given ItemType.
     * @param itemType: The ItemType of the Items to return.
     * @return A List<Item> that have the given ItemType.
     */
    List<Item> getItemsByItemType(ItemType itemType);

    /**
     * Retrieve all of the ItemTypes from the database.
     * @return A List<ItemType> containing all of the ItemTypes in the db.
     */
    List<ItemType> getAllItemTypes();

    /**
     * Retrieve all of the GroceryLists from the database.
     * @return A List<GroceryList> containing all of the GroceryLists in the db.
     */
    List<GroceryList> getAllLists();

    /**
     * Set the isChecked value of a ListItem.
     * @param listItemID: The ID of the ListItem to toggle.
     * @param checked: The isChecked value to set.
     */
    void toggleListItemIsChecked(Integer listItemID, boolean checked);

    /**
     * Delete a ListItem from a GroceryList
     * @param listItemID: The ID of the ListItem to be Deleted.
     */
    void deleteItemFromList(Integer listItemID);

}
