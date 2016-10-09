package main.java.edu.gatech.seclass.glm.glm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by danielbansch on 10/8/16.
 */

//// TODO: 10/8/16  
public class DAO extends SQLiteOpenHelper implements DAOI {

    public DAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public GroceryList saveList(GroceryList groceryList) {
        return null;
    }

    @Override
    public GroceryList loadList(Integer id) {
        return null;
    }

    @Override
    public void deleteList(Integer id) {

    }

    @Override
    public void addNewItem(Item item) {

    }

    @Override
    public List<Item> findItemsLike(String searchString) {
        return null;
    }

    @Override
    public List<Item> getItemsByItemType(ItemType itemType) {
        return null;
    }

    @Override
    public List<ItemType> getAllItemTypes() {
        return null;
    }

    @Override
    public List<GroceryList> getAllLists() {
        return null;
    }
}
