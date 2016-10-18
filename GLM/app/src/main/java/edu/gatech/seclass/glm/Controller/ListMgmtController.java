package edu.gatech.seclass.glm.Controller;

import android.content.Context;

import java.util.List;

import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ItemType;
import edu.gatech.seclass.glm.Model.ListItem;
import edu.gatech.seclass.glm.DAO.DAO;

/**
 * Created by jbuoni on 10/11/16.
 */

public class ListMgmtController {

    private GroceryList currentList;
    private DAO dao;

    public ListMgmtController(Context context){
        dao = new DAO(context);
    }

    public void updateCurrentList(int listId){
        currentList = dao.loadList(listId);
    }

    public List<ListItem> getCurrentListItems(){
        //Make sure it is the most up to date version
        currentList = dao.loadList(currentList.getId());
        return currentList.getAllListItems();
    }

    public GroceryList getCurrentList(){
        return currentList;
    }

    public void toggleCheck(ListItem item) {
        dao.toggleListItemIsChecked(item.getId(), item.getIsChecked());
        currentList = dao.loadList(currentList.getId());
    }

    public void uncheckAllListItems(){
        currentList.uncheckAllListItems();
        for (ListItem item: currentList.getAllListItems()) {
            dao.toggleListItemIsChecked(item.getId(), item.getIsChecked());
        }
        currentList = dao.loadList(currentList.getId());
    }

    public void addListItem(ListItem item){
        dao.addItemToList(currentList.getId(), item.getId(), item.getQuantity());
        currentList = dao.loadList(currentList.getId());
    }

    public void addListItemNoId(Integer groceryListID, Integer itemID, Integer quantity) {
        dao.addItemToList(groceryListID, itemID, quantity);
    }

    public void removeListItem(ListItem item){
        dao.deleteItemFromList(item.getId());
        currentList = dao.loadList(currentList.getId());
    }

    public void updateItem(ListItem item) {
        for (ListItem i: currentList.getAllListItems()) {
            if(i.getId() == item.getId()){
                dao.deleteItemFromList(item.getId());
                dao.addItemToList(currentList.getId(), item.getId(), item.getQuantity());
            }
        }

        currentList = dao.loadList(currentList.getId());
    }

    public List<Item> searchForItem(String searchText) {
        return dao.findItemsLike(searchText);
    }

    public List<ItemType> getAllItemTypes() {
        return dao.getAllItemTypes();
    }

    public List<Item> getAllItemsByType(ItemType type) {
        return dao.getItemsByItemType(type);
    }

    public Item addNewItemToDB(String itemName, Integer itemTypeID) {
        return dao.createNewItem(itemName, itemTypeID);
    }
}
