package edu.gatech.seclass.glm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ItemType;
import edu.gatech.seclass.glm.Model.ListItem;

/**
 * Created by danielbansch on 10/8/16.
 */

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
    public void updateListName(GroceryList groceryList, String name) {
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        // Set the value and where clause
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.GroceryListEntry.NAME_COLUMN, name);
        String selection = DatabaseContract.GroceryListEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(groceryList.getId())};
        //update the database
        db.update(
                DatabaseContract.GroceryListEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

    }

    @Override
    public GroceryList createList(String name) {
        // Gets the data repository in write mode, add the list and return the GroceryList object
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.GroceryListEntry.NAME_COLUMN, name);
        long groceryListID = db.insert(DatabaseContract.GroceryListEntry.TABLE_NAME, null, values);
        GroceryList groceryList = new GroceryList(name, (int) groceryListID, new ArrayList<ListItem>());
        return groceryList;
    }

    @Override
    public GroceryList loadList(Integer id) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT GL." + DatabaseContract.GroceryListEntry.NAME_COLUMN +
                ", LI." + DatabaseContract.ListItemEntry._ID +
                ", LI." + DatabaseContract.ListItemEntry.QUANTITY_COLUMN +
                ", LI." + DatabaseContract.ListItemEntry.IS_CHECKED_COLUMN +
                ", I." + DatabaseContract.ItemEntry._ID +
                ", I." + DatabaseContract.ItemEntry.NAME_COLUMN +
                ", IT." + DatabaseContract.ItemTypeEntry._ID +
                ", IT." + DatabaseContract.ItemTypeEntry.NAME_COLUMN +
                " FROM " +
                DatabaseContract.GroceryListEntry.TABLE_NAME +
                " as GL LEFT OUTER JOIN " + DatabaseContract.ListItemEntry.TABLE_NAME + " as LI ON " +
                "GL." + DatabaseContract.GroceryListEntry._ID +
                "=LI." + DatabaseContract.ListItemEntry.GROCERY_LIST_COLUMN +
                " LEFT OUTER JOIN " + DatabaseContract.ItemEntry.TABLE_NAME + " as I ON " +
                "I." + DatabaseContract.ItemEntry._ID +
                "=LI." + DatabaseContract.ListItemEntry.ITEM_COLUMN +
                " LEFT OUTER JOIN " + DatabaseContract.ItemTypeEntry.TABLE_NAME + " as IT ON " +
                "IT." + DatabaseContract.ItemTypeEntry._ID +
                "=I." + DatabaseContract.ItemEntry._ID +
                " WHERE GL." + DatabaseContract.GroceryListEntry._ID + "=" + String.valueOf(id), null);

        //get the GroceryList
        GroceryList groceryList = null;
        String glName = null;
        List<ListItem> liList = new ArrayList<ListItem>();

        if (c.moveToFirst()) {

            glName = c.getString(0);
            do
            {
                try {
                    long liID = c.getLong(1);
                    if (liID == 0)
                    {
                        break;
                    }
                    long liQty = c.getLong(2);
                    long liIsChecked = c.getLong(3);
                    long itemId = c.getLong(4);
                    String itemName = c.getString(5);
                    long itemTypeId = c.getLong(6);
                    String itemTypeName = c.getString(7);
                    ItemType it = new ItemType((int) itemTypeId, itemTypeName);
                    Item i = new Item((int) itemId, itemName, it);
                    ListItem li = new ListItem((int) liID, i, (liIsChecked == 1), (int) liQty);
                    liList.add(li);
                }
                catch (Exception e)
                {

                }
            } while (c.moveToNext());

            groceryList = new GroceryList(glName, id, liList);
        }
        return groceryList;
    }

    @Override
    public ListItem addItemToList(Integer groceryListID, Integer itemID, Integer quantity) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //create the new list item, initally set isChecked to 0 (false)
        values.put(DatabaseContract.ListItemEntry.GROCERY_LIST_COLUMN, groceryListID);
        values.put(DatabaseContract.ListItemEntry.ITEM_COLUMN, itemID);
        values.put(DatabaseContract.ListItemEntry.QUANTITY_COLUMN, quantity);
        values.put(DatabaseContract.ListItemEntry.IS_CHECKED_COLUMN, 0);
        long listItemID = db.insert(DatabaseContract.ListItemEntry.TABLE_NAME, null, values);
        return new ListItem((int) listItemID, getItemByID(itemID), false, quantity);

    }

    @Override
    public void deleteList(Integer id) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Delete the ListItems for the GroceryList
        // Set the value and where clause
        ContentValues values = new ContentValues();
        String selection = DatabaseContract.ListItemEntry.GROCERY_LIST_COLUMN + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        //delete the row from the database
        db.delete(
                DatabaseContract.ListItemEntry.TABLE_NAME,
                selection,
                selectionArgs);

        //Delete the GroceryList row
        // Set the where clause
        selection = DatabaseContract.GroceryListEntry._ID + " = ?";
        String[] selectionArgsGL = {String.valueOf(id)};
        //delete the row from the database
        db.delete(
                DatabaseContract.GroceryListEntry.TABLE_NAME,
                selection,
                selectionArgsGL);
    }

    @Override
    public Item createNewItem(String itemName, Integer itemTypeID) {
        // Gets the data repository in write mode, add the list and return the GroceryList object
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ItemEntry.NAME_COLUMN, itemName);
        values.put(DatabaseContract.ItemEntry.ITEM_TYPE_COLUMN, itemTypeID);
        long itemID = db.insert(DatabaseContract.ItemEntry.TABLE_NAME, null, values);
        Item item = new Item((int) itemID, itemName, getItemTypeByID(itemTypeID));
        return item;
    }

    @Override
    public List<Item> findItemsLike(String searchString) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT" +
                " I." + DatabaseContract.ItemEntry._ID +
                ", I." + DatabaseContract.ItemEntry.NAME_COLUMN +
                ", IT." + DatabaseContract.ItemTypeEntry._ID +
                ", IT." + DatabaseContract.ItemTypeEntry.NAME_COLUMN +
                " FROM " +
                DatabaseContract.ItemEntry.TABLE_NAME +
                " AS I JOIN " + DatabaseContract.ItemTypeEntry.TABLE_NAME + " AS IT ON " +
                " IT." + DatabaseContract.ItemTypeEntry._ID +
                "= I." + DatabaseContract.ItemEntry._ID +
                " WHERE I." + DatabaseContract.ItemEntry.NAME_COLUMN + " LIKE \"%" + searchString + "%\"", null);

        //put the results in a List of Items
        List<Item> searchResults = new ArrayList<Item>();

        if (c.moveToFirst()) {
            do
            {
                long itemId = c.getLong(0);

                String itemName = c.getString(1);

                long itemTypeId = c.getLong(2);

                String itemTypeName = c.getString(3);

                searchResults.add(new Item((int) itemId, itemName, new ItemType((int) itemTypeId, itemTypeName)));

            } while (c.moveToNext());
        }

        return searchResults;

    }

    @Override
    public List<Item> getItemsByItemType(ItemType itemType) {

        SQLiteDatabase db = this.getReadableDatabase();

        //columns selected
        String[] projection = {
                DatabaseContract.ItemEntry._ID,
                DatabaseContract.ItemEntry.NAME_COLUMN
        };

        //where filter
        String selection = DatabaseContract.ItemEntry.ITEM_TYPE_COLUMN + " =?";
        String[] selectionArgs = {String.valueOf(itemType.getId())};

        Cursor c = db.query(
                DatabaseContract.ItemEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        //put the results in a List of Items
        List<Item> filterResults = new ArrayList<Item>();

        if (c.moveToFirst()) {
            do
            {
                long itemId = c.getLong(
                        c.getColumnIndexOrThrow(DatabaseContract.ItemEntry._ID)
                );

                String itemName = c.getString(
                        c.getColumnIndexOrThrow(DatabaseContract.ItemEntry.NAME_COLUMN)
                );

                filterResults.add(new Item((int) itemId, itemName, itemType));

            } while (c.moveToNext());
        }

        return filterResults;

    }

    private Item getItemByID(Integer id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT" +
                " I." + DatabaseContract.ItemEntry._ID +
                ", I." + DatabaseContract.ItemEntry.NAME_COLUMN +
                ", IT." + DatabaseContract.ItemTypeEntry._ID +
                ", IT." + DatabaseContract.ItemTypeEntry.NAME_COLUMN +
                " FROM " +
                DatabaseContract.ItemEntry.TABLE_NAME +
                " AS I JOIN " + DatabaseContract.ItemTypeEntry.TABLE_NAME + " AS IT ON " +
                " IT." + DatabaseContract.ItemTypeEntry._ID +
                "= I." + DatabaseContract.ItemEntry._ID +
                " WHERE I." + DatabaseContract.ItemEntry._ID + "=" + id, null);

        //get the Item
        Item item = null;
        if (c.moveToFirst()) {

            long itemId = c.getLong(0);

            String itemName = c.getString(1);

            long itemTypeId = c.getLong(2);

            String itemTypeName = c.getString(3);

            item = new Item((int) itemId, itemName, new ItemType((int) itemTypeId, itemTypeName));

        }

        return item;

    }

    private ItemType getItemTypeByID(Integer id) {

        SQLiteDatabase db = this.getReadableDatabase();

        //columns selected
        String[] projection = {
                DatabaseContract.ItemTypeEntry._ID,
                DatabaseContract.ItemTypeEntry.NAME_COLUMN
        };

        //where filter
        String selection = DatabaseContract.ItemTypeEntry._ID + " =?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor c = db.query(
                DatabaseContract.ItemTypeEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        //get the ItemType
        ItemType itemType = null;
        if (c.moveToFirst()) {

            long itemTypeId = c.getLong(
                    c.getColumnIndexOrThrow(DatabaseContract.ItemTypeEntry._ID)
            );

            String itemTypeName = c.getString(
                    c.getColumnIndexOrThrow(DatabaseContract.ItemTypeEntry.NAME_COLUMN)
            );

            itemType = new ItemType((int) itemTypeId, itemTypeName);

        }

        return itemType;

    }

    @Override
    public List<ItemType> getAllItemTypes() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + DatabaseContract.ItemTypeEntry.TABLE_NAME, null);

        //put the results in a List of Items
        List<ItemType> itemTypes = new ArrayList<ItemType>();

        if (c.moveToFirst()) {
            do
            {
                long itemTypeId = c.getLong(
                        c.getColumnIndexOrThrow(DatabaseContract.ItemTypeEntry._ID)
                );

                String itemTypeName = c.getString(
                        c.getColumnIndexOrThrow(DatabaseContract.ItemTypeEntry.NAME_COLUMN)
                );

                itemTypes.add(new ItemType((int) itemTypeId, itemTypeName));

            } while (c.moveToNext());
        }

        return itemTypes;

    }

    @Override
    public List<GroceryList> getAllLists() {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT GL." + DatabaseContract.GroceryListEntry.NAME_COLUMN +
                ", LI." + DatabaseContract.ListItemEntry._ID +
                ", LI." + DatabaseContract.ListItemEntry.QUANTITY_COLUMN +
                ", LI." + DatabaseContract.ListItemEntry.IS_CHECKED_COLUMN +
                ", I." + DatabaseContract.ItemEntry._ID +
                ", I." + DatabaseContract.ItemEntry.NAME_COLUMN +
                ", IT." + DatabaseContract.ItemTypeEntry._ID +
                ", IT." + DatabaseContract.ItemTypeEntry.NAME_COLUMN +
                ", GL." + DatabaseContract.ItemTypeEntry._ID +
                " FROM " +
                DatabaseContract.GroceryListEntry.TABLE_NAME +
                " as GL LEFT OUTER JOIN " + DatabaseContract.ListItemEntry.TABLE_NAME + " as LI ON " +
                "GL." + DatabaseContract.GroceryListEntry._ID +
                "=LI." + DatabaseContract.ListItemEntry.GROCERY_LIST_COLUMN +
                " LEFT OUTER JOIN " + DatabaseContract.ItemEntry.TABLE_NAME + " as I ON " +
                "I." + DatabaseContract.ItemEntry._ID +
                "=LI." + DatabaseContract.ListItemEntry.ITEM_COLUMN +
                " LEFT OUTER JOIN " + DatabaseContract.ItemTypeEntry.TABLE_NAME + " as IT ON " +
                "IT." + DatabaseContract.ItemTypeEntry._ID +
                "=I." + DatabaseContract.ItemEntry._ID + " ORDER BY GL." +
                DatabaseContract.ItemTypeEntry._ID, null);

        //get the GroceryList
        List<GroceryList> groceryLists = new ArrayList<GroceryList>();
        String glName = null;
        long glID = 0;
        List<ListItem> liList = new ArrayList<ListItem>();

        if (c.moveToFirst()) {

            glID = c.getLong(8);
            glName = c.getString(0);
            do
            {
                //if this is a new GroceryList, add the old GroceryList and start a new one
                if (glID != c.getLong(8))
                {
                    groceryLists.add(new GroceryList(glName, (int) glID, liList));
                    liList = new ArrayList<ListItem>();
                }

                glID = c.getLong(8);
                glName = c.getString(0);

                try {
                    long liID = c.getLong(1);
                    if (liID == 0)
                    {
                        continue;
                    }
                    long liQty = c.getLong(2);
                    long liIsChecked = c.getLong(3);
                    long itemId = c.getLong(4);
                    String itemName = c.getString(5);
                    long itemTypeId = c.getLong(6);
                    String itemTypeName = c.getString(7);
                    ItemType it = new ItemType((int) itemTypeId, itemTypeName);
                    Item i = new Item((int) itemId, itemName, it);
                    ListItem li = new ListItem((int) liID, i, (liIsChecked == 1), (int) liQty);
                    liList.add(li);
                }
                catch (Exception e)
                {

                }
            } while (c.moveToNext());
            //add the final grocery list
            groceryLists.add(new GroceryList(glName, (int) glID, liList));
        }
        return groceryLists;
    }

    @Override
    public void toggleListItemIsChecked(Integer listItemID, boolean checked) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        // Set the value and where clause
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ListItemEntry.IS_CHECKED_COLUMN, (checked ? 1 : 0));
        String selection = DatabaseContract.ListItemEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(listItemID)};
        //update the database
        db.update(
                DatabaseContract.ListItemEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

    }

    @Override
    public void deleteItemFromList(Integer listItemID) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete the ListItem from the GroceryList
        // Set the value and where clause
        ContentValues values = new ContentValues();
        String selection = DatabaseContract.ListItemEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(listItemID)};
        //delete the row from the database
        db.delete(
                DatabaseContract.ListItemEntry.TABLE_NAME,
                selection,
                selectionArgs);

    }
}