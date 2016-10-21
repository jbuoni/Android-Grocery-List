package edu.gatech.seclass.glm.Controller;

import android.content.Context;

import java.util.List;

import edu.gatech.seclass.glm.DAO.DAOI;
import edu.gatech.seclass.glm.DAO.DAO;
import edu.gatech.seclass.glm.Model.GroceryList;

/**
 * ListMgmtController handles all communication between the current grocery list, and the UI
 * and database.
 *
 * Created by jbuoni on 10/12/16.
 */

public class ListController {

    private DAOI dao;

    /**
     * Constructor.
     *
     * @param context Activity Context.
     */
    public ListController(Context context) {
        dao = new DAO(context);
    }

    /**
     * Creates a new Grocery list.
     *
     * @param name Name of Grocery list.
     */
    public void createList(String name) {
        dao.createList(name);
    }

    /**
     * Returns all grocery lists
     * @return List of GroceryList items
     */
    public List<GroceryList> getAllLists() {
        return dao.getAllLists();
    }

    /**
     * Deletes grocery list that matches that ID.
     *
     * @param id Id of the grocery list to delete.
     */
    public void deleteList (Integer id) {dao.deleteList(id);}

    /**
     * Updates the name of the passed in Grocery List.
     *
     * @param groceryList Grocery list to update.
     * @param name New name of the grocery list.
     */
    public void updateListName(GroceryList groceryList, String name) {
        dao.updateListName(groceryList, name);
    }


}
