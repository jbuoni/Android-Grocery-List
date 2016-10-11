package edu.gatech.seclass.glm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ItemType;

/**
 * Created by danielbansch on 10/8/16.
 */

//// TODO: 10/8/16
public class DAO extends SQLiteOpenHelper implements DAOI {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GroceryListManager.db";
    public static final String TEXT_TYPE = " TEXT";

    private static final String SQL_CREATE_GROCERYLIST =
            "CREATE TABLE " + DatabaseContract.GroceryListEntry.TABLE_NAME + " (" +
                    DatabaseContract.GroceryListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DatabaseContract.GroceryListEntry.NAME_COLUMN + TEXT_TYPE + ")";

    private static final String SQL_DELETE_GROCERYLIST =
            "DROP TABLE IF EXISTS " + DatabaseContract.GroceryListEntry.TABLE_NAME;

    private static final String SQL_CREATE_ITEMTYPE =
            "CREATE TABLE " + DatabaseContract.ItemTypeEntry.TABLE_NAME + " (" +
                    DatabaseContract.ItemTypeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DatabaseContract.ItemTypeEntry.NAME_COLUMN + TEXT_TYPE + ")";

    private static final String SQL_DELETE_ITEMTYPE =
            "DROP TABLE IF EXISTS " + DatabaseContract.ItemTypeEntry.TABLE_NAME;


    private static final String SQL_CREATE_ITEM =
            "CREATE TABLE " + DatabaseContract.ItemEntry.TABLE_NAME + " (" +
                    DatabaseContract.ItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DatabaseContract.ItemEntry.NAME_COLUMN + TEXT_TYPE + ", " +
                    DatabaseContract.ItemEntry.ITEM_TYPE_COLUMN + " INTEGER, " +
                    "FOREIGN KEY(" + DatabaseContract.ItemEntry.ITEM_TYPE_COLUMN +
                    ") REFERENCES " + DatabaseContract.ItemTypeEntry.TABLE_NAME + "(" +
                    DatabaseContract.ItemTypeEntry._ID + "))";

    private static final String SQL_DELETE_ITEM =
            "DROP TABLE IF EXISTS " + DatabaseContract.ItemEntry.TABLE_NAME;

    private static final String SQL_CREATE_LISTITEM =
            "CREATE TABLE " + DatabaseContract.ListItemEntry.TABLE_NAME + " (" +
                    DatabaseContract.ListItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DatabaseContract.ListItemEntry.QUANTITY_COLUMN + " INTEGER, " +
                    DatabaseContract.ListItemEntry.IS_CHECKED_COLUMN + " INTEGER, " +
                    DatabaseContract.ListItemEntry.ITEM_COLUMN + " INTEGER, " +
                    DatabaseContract.ListItemEntry.GROCERY_LIST_COLUMN + " INTEGER, " +
                    "FOREIGN KEY(" + DatabaseContract.ListItemEntry.ITEM_COLUMN +
                    ") REFERENCES " + DatabaseContract.ItemEntry.TABLE_NAME + "(" +
                    DatabaseContract.ItemTypeEntry._ID + ") " +
                    "FOREIGN KEY(" + DatabaseContract.ListItemEntry.GROCERY_LIST_COLUMN +
                            ") REFERENCES " + DatabaseContract.GroceryListEntry.TABLE_NAME + "(" +
                    DatabaseContract.ItemTypeEntry._ID + "))";

    private static final String SQL_DELETE_LISTITEM =
            "DROP TABLE IF EXISTS " + DatabaseContract.ListItemEntry.TABLE_NAME;


    public DAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create the tables
        db.execSQL(SQL_CREATE_GROCERYLIST);
        db.execSQL(SQL_CREATE_ITEMTYPE);
        db.execSQL(SQL_CREATE_ITEM);
        db.execSQL(SQL_CREATE_LISTITEM);

        //insert some data
        //insert some grocery lists
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.GroceryListEntry.NAME_COLUMN, "Grocery List");
        long groceryListID = db.insert(DatabaseContract.GroceryListEntry.TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(DatabaseContract.GroceryListEntry.NAME_COLUMN, "Party List");
        long partyListID = db.insert(DatabaseContract.GroceryListEntry.TABLE_NAME, null, values);

        //insert some ItemTypes
        values = new ContentValues();
        values.put(DatabaseContract.ItemTypeEntry.NAME_COLUMN, "Beer");
        long beerItemTypeID = db.insert(DatabaseContract.ItemTypeEntry.TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(DatabaseContract.ItemTypeEntry.NAME_COLUMN, "Milk");
        long milkItemTypeID = db.insert(DatabaseContract.ItemTypeEntry.TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(DatabaseContract.ItemTypeEntry.NAME_COLUMN, "Cereal");
        long cerealItemTypeID = db.insert(DatabaseContract.ItemTypeEntry.TABLE_NAME, null, values);

        //insert some Items
        values = new ContentValues();
        values.put(DatabaseContract.ItemEntry.NAME_COLUMN, "12 Pack of Coors");
        values.put(DatabaseContract.ItemEntry.ITEM_TYPE_COLUMN, beerItemTypeID);
        long coorsItemID = db.insert(DatabaseContract.ItemEntry.TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(DatabaseContract.ItemEntry.NAME_COLUMN, "12 Pack of Budweiser");
        values.put(DatabaseContract.ItemEntry.ITEM_TYPE_COLUMN, beerItemTypeID);
        long budItemID = db.insert(DatabaseContract.ItemEntry.TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(DatabaseContract.ItemEntry.NAME_COLUMN, "12 Pack of Bud Light");
        values.put(DatabaseContract.ItemEntry.ITEM_TYPE_COLUMN, beerItemTypeID);
        long budLightItemID = db.insert(DatabaseContract.ItemEntry.TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(DatabaseContract.ItemEntry.NAME_COLUMN, "Gallon Whole Milk");
        values.put(DatabaseContract.ItemEntry.ITEM_TYPE_COLUMN, milkItemTypeID);
        long wholeMilkItemID = db.insert(DatabaseContract.ItemEntry.TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(DatabaseContract.ItemEntry.NAME_COLUMN, "Gallon Skim Milk");
        values.put(DatabaseContract.ItemEntry.ITEM_TYPE_COLUMN, milkItemTypeID);
        long skimMilkItemID = db.insert(DatabaseContract.ItemEntry.TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(DatabaseContract.ItemEntry.NAME_COLUMN, "Lucky Charms");
        values.put(DatabaseContract.ItemEntry.ITEM_TYPE_COLUMN, cerealItemTypeID);
        long luckyCharmsItemID = db.insert(DatabaseContract.ItemEntry.TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(DatabaseContract.ItemEntry.NAME_COLUMN, "Frosted Flakes");
        values.put(DatabaseContract.ItemEntry.ITEM_TYPE_COLUMN, cerealItemTypeID);
        long frostedFlakesItemID = db.insert(DatabaseContract.ItemEntry.TABLE_NAME, null, values);

        //create some list items
        values = new ContentValues();
        values.put(DatabaseContract.ListItemEntry.GROCERY_LIST_COLUMN, groceryListID);
        values.put(DatabaseContract.ListItemEntry.ITEM_COLUMN, wholeMilkItemID);
        values.put(DatabaseContract.ListItemEntry.QUANTITY_COLUMN, 1);
        values.put(DatabaseContract.ListItemEntry.IS_CHECKED_COLUMN, 0);
        long listItemID1 = db.insert(DatabaseContract.ListItemEntry.TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(DatabaseContract.ListItemEntry.GROCERY_LIST_COLUMN, groceryListID);
        values.put(DatabaseContract.ListItemEntry.ITEM_COLUMN, luckyCharmsItemID);
        values.put(DatabaseContract.ListItemEntry.QUANTITY_COLUMN, 1);
        values.put(DatabaseContract.ListItemEntry.IS_CHECKED_COLUMN, 1);
        long listItemID2 = db.insert(DatabaseContract.ListItemEntry.TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(DatabaseContract.ListItemEntry.GROCERY_LIST_COLUMN, partyListID);
        values.put(DatabaseContract.ListItemEntry.ITEM_COLUMN, budItemID);
        values.put(DatabaseContract.ListItemEntry.QUANTITY_COLUMN, 1);
        values.put(DatabaseContract.ListItemEntry.IS_CHECKED_COLUMN, 0);
        long listItemID3 = db.insert(DatabaseContract.ListItemEntry.TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(DatabaseContract.ListItemEntry.GROCERY_LIST_COLUMN, partyListID);
        values.put(DatabaseContract.ListItemEntry.ITEM_COLUMN, coorsItemID);
        values.put(DatabaseContract.ListItemEntry.QUANTITY_COLUMN, 1);
        values.put(DatabaseContract.ListItemEntry.IS_CHECKED_COLUMN, 1);
        long listItemID4 = db.insert(DatabaseContract.ListItemEntry.TABLE_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop the tables and call onCreate to recreate the database
        db.execSQL(SQL_DELETE_LISTITEM);
        db.execSQL(SQL_DELETE_ITEM);
        db.execSQL(SQL_DELETE_ITEMTYPE);
        db.execSQL(SQL_DELETE_GROCERYLIST);
        onCreate(db);
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
