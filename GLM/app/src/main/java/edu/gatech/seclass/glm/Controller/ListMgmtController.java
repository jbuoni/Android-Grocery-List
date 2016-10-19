package edu.gatech.seclass.glm.Controller;

import android.content.Context;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ItemType;
import edu.gatech.seclass.glm.Model.ListItem;
import edu.gatech.seclass.glm.DAO.DAO;

/**
 * ListMgmtController handles all communication between the current grocery list, and the UI
 * and database.
 *
 * Created by jbuoni on 10/11/16.
 */

public class ListMgmtController {

    /** Current grocery list */
    private GroceryList currentList;
    /** Database object */
    private DAO dao;

    /**
     * Constructor
     *
     * @param context  Context required for the DAO database connection.
     * @see DAO
     *
     */
    public ListMgmtController(Context context){
        dao = new DAO(context);
    }

    /**
     * Set the controller's current list
     * @param listId Integer representation of list ID
     */
    public void updateCurrentList(int listId){
        currentList = dao.loadList(listId);
    }

    /**
     * Returns the current list's items sorted by item type.
     *
     * @return ListItems in the current list.
     */
    public List<ListItem> getCurrentListItems(){
        //Make sure it is the most up to date version
        currentList = dao.loadList(currentList.getId());

        List<ListItem> items = currentList.getAllListItems();

        Collections.sort(items);

        return items;
    }

    /**
     * Returns current list.
     *
     * @return Current GroceryList.
     */
    public GroceryList getCurrentList(){
        return currentList;
    }

    /**
     * Check / Uncheck a list item.
     *
     * @param item ListItem to be checked or unchecked.
     */
    public void toggleCheck(ListItem item) {
        dao.toggleListItemIsChecked(item.getId(), item.getIsChecked());
        currentList = dao.loadList(currentList.getId());
    }

    /**
     * Unchecks all ListItems in the current list.
     */
    public void uncheckAllListItems(){
        currentList.uncheckAllListItems();
        for (ListItem item: currentList.getAllListItems()) {
            dao.toggleListItemIsChecked(item.getId(), item.getIsChecked());
        }
        currentList = dao.loadList(currentList.getId());
    }

    /**
     * Adds ListItem to the current list.
     *
     * @param item ListItem to be added.
     * @deprecated Use addListItem that requires the item id, not the item.
     */
    public void addListItem(ListItem item){
        dao.addItemToList(currentList.getId(), item.getId(), item.getQuantity());
        currentList = dao.loadList(currentList.getId());
    }

    /**
     * Adds item to list. Does not have to be the current list.
     *
     * @param groceryListID GroceryList to add item to.
     * @param itemID Item to add.
     * @param quantity Quantity of the item.
     */
    public void addListItem(Integer groceryListID, Integer itemID, Integer quantity) {
        dao.addItemToList(groceryListID, itemID, quantity);
    }

    /**
     * Removes the item from the list.
     *
     * @param item ListItem to remove from the list.
     */
    public void removeListItem(ListItem item){
        dao.deleteItemFromList(item.getId());
        currentList = dao.loadList(currentList.getId());
    }

    /**
     * Updates item in the list.
     *
     * @param item ListItem to update.
     */
    public void updateItem(ListItem item) {
        for (ListItem i: currentList.getAllListItems()) {
            if(i.getId() == item.getId()){
                dao.deleteItemFromList(item.getId());
                dao.addItemToList(currentList.getId(), item.getId(), item.getQuantity());
            }
        }

        currentList = dao.loadList(currentList.getId());
    }

    /**
     * Search for items with text similar to parameter.
     *
     * @param searchText String to search for.
     * @return List of Items matching the search.
     */
    public List<Item> searchForItem(String searchText) {
        return dao.findItemsLike(searchText);
    }

    /**
     * Returns all possible item types from the database.
     *
     * @return List of ItemTypes
     */
    public List<ItemType> getAllItemTypes() {
        return dao.getAllItemTypes();
    }

    /**
     * Returns all items of a certain type.
     *
     * @param type ItemType to search by.
     * @return Lists of Items with ItemType type.
     */
    public List<Item> getAllItemsByType(ItemType type) {
        return dao.getItemsByItemType(type);
    }

    /**
     * Adds new item to database. Returns new item as Item object
     *
     * @param itemName Name of the item.
     * @param itemTypeID ItemType to map to.
     * @return Item object.
     */
    public Item addNewItemToDB(String itemName, Integer itemTypeID) {
        return dao.createNewItem(itemName, itemTypeID);
    }
}
