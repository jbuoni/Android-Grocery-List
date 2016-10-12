package edu.gatech.seclass.glm.Controller;

import android.content.Context;

import java.util.List;

import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.Model.ListItem;
import edu.gatech.seclass.glm.DAO.DAO;

/**
 * Created by jbuoni on 10/11/16.
 */

public class GroceryListController {

    private GroceryList currentList;
    private DAO dao;

    public GroceryListController(Context context){
        dao = new DAO(context);
    }

    public void updateCurrentList(int listId){
        currentList = dao.loadList(listId);
    }

    public List<ListItem> getCurrentListItems(){
        return currentList.getAllListItems();
    }

    public GroceryList getCurrentList(){
        return currentList;
    }

    public void uncheckAllListItems(){
        currentList.uncheckAllListItems();
        dao.updateList(currentList);
    }

    public void addListItem(ListItem item){
        currentList.addListItem(item);
        dao.updateList(currentList);
    }

    public void removeListItem(ListItem item){
        currentList.removeListItemById(item.getId());
        dao.updateList(currentList);
    }

    public void updateItem(ListItem item) {
        for (ListItem i: currentList.getAllListItems()) {
            if(i.getId() == item.getId()){
                currentList.removeListItemById(i.getId());
                currentList.addListItem(item);
            }
        }

        dao.updateList(currentList);
    }
}
