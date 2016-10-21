package edu.gatech.seclass.glm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import edu.gatech.seclass.glm.Controller.ListMgmtController;
import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.Model.ListItem;
import edu.gatech.seclass.glm.R;
import edu.gatech.seclass.glm.Utils.ItemArrayAdapter;

/**
 * GroceryListActivity generates the grocery list page.
 * Created by jbuoni on 10/10/16.
 */

public class GroceryListActivity extends AppCompatActivity {

    private ItemArrayAdapter listAdapter;
    private List<ListItem> listItems;
    private ListMgmtController controller;

    /**
     * Create Activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_list);

        //Load grocery lists
        controller = new ListMgmtController(this.getApplicationContext());
        controller.updateCurrentList(((GroceryList)getIntent().getSerializableExtra("GroceryList")).getId());
        listItems = controller.getCurrentListItems();

        //Auto update the list view
        ListView lv = (ListView)findViewById(R.id.listItemContainer);
        listAdapter = new ItemArrayAdapter(this, listItems, controller);
        lv.setAdapter(listAdapter);

        generateListItemViews();
    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     *
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     *
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     *
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     *
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     *
     * @return You must return true for the menu to be displayed;
     *         if you return false it will not be shown.
     *
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_grocery_list, menu);
        return true;
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     *
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     *
     * @return boolean Return false to allow normal menu processing to
     *         proceed, true to consume it here.
     *
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_item:
                addItem();
                return(true);
            case R.id.search:
                searchItem();
                return(true);
            case R.id.uncheck_all:
                uncheckAllItems();
                return(true);
            case R.id.select_list:
                startActivity(new Intent(this, MainActivity.class));
                return(true);

        }
        return(super.onOptionsItemSelected(item));
    }

    /**
     * Loads SearchItemActivity.
     */
    private void searchItem(){
        Intent intent = new Intent(this, SearchItemActivity.class);
        intent.putExtra("groceryListID", controller.getCurrentList().getId());
        startActivity(intent);
    }

    /**
     * Loads AddItemActivity.
     */
    private void addItem(){
        Intent intentAddAct = new Intent(this, AddItemActivity.class);
        intentAddAct.putExtra("groceryListID", controller.getCurrentList().getId());
        startActivity(intentAddAct);
    }

    /**
     * Unchecks all items in the list.
     */
    private void uncheckAllItems(){
        controller.uncheckAllListItems();
        generateListItemViews();
    }

    /**
     * Updates the Item list view.
     */
    private void generateListItemViews() {
        //Better way when using ArrayAdapters. Keeps object reference the same.
        listItems.clear();
        listItems.addAll(controller.getCurrentListItems());
        listAdapter.notifyDataSetChanged();
    }

}
