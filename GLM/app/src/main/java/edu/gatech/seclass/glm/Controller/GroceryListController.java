package edu.gatech.seclass.glm.Controller;

import android.content.Context;

import java.util.List;

import edu.gatech.seclass.glm.DAO.DAO;
import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.Model.ListItem;

/**
 * Created by jbuoni on 10/11/16.
 */

public class GroceryListController {

    private GroceryList currentList;
    private DAO dao;

    public GroceryListController(Context context){
        dao = new DAO(context);
    }

    public void setCurrentList(int listId){
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
        dao.saveList(currentList);
    }
}
