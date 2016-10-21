package edu.gatech.seclass.glm.DAO;

import android.provider.BaseColumns;

/**
 * Models used for the SQLite database
 */
public final class DatabaseContract {

    private DatabaseContract() {}

    /**
     * Define the ItemType db table
     */
    public static class ItemTypeEntry implements BaseColumns {
        public static final String TABLE_NAME = "ItemType";
        public static final String NAME_COLUMN = "name";
    }

    /**
     * Define the Item db table
     */
    public static class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "Item";
        public static final String NAME_COLUMN = "name";
        public static final String ITEM_TYPE_COLUMN = "item_type";
    }

    /**
     * Define the ListItem db table
     */
    public static class ListItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "ListItem";
        public static final String IS_CHECKED_COLUMN = "is_checked";
        public static final String QUANTITY_COLUMN = "quantity";
        public static final String ITEM_COLUMN = "item";
        public static final String GROCERY_LIST_COLUMN = "grocery_list";
    }

    /**
     *  Define the GroceryList db table
     */
    public static class GroceryListEntry implements BaseColumns {
        public static final String TABLE_NAME = "GroceryList";
        public static final String NAME_COLUMN = "name";
    }


}