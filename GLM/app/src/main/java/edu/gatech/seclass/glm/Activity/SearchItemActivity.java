package edu.gatech.seclass.glm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.gatech.seclass.glm.Controller.ListMgmtController;
import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.R;

/**
 * Created by jbuoni on 10/11/16.
 */
public class SearchItemActivity extends AppCompatActivity {

    private ListMgmtController controller;
    private ArrayAdapter<String> listAdapter;
    private List<Item> items;
    private ArrayList<String> arrayItems;

    /**
     * Create Activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_item);

        final ListView listView = (ListView) findViewById(R.id.searchResultsListView);
        final EditText searchText = (EditText) findViewById(R.id.searchBar);

        items = new LinkedList<Item>();
        arrayItems = new ArrayList<String>();
        controller = new ListMgmtController(getApplicationContext());
        controller.updateCurrentList(this.getIntent().getIntExtra("groceryListID", -1));
        listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayItems);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (Item item: items) {
                    if (item.getName().equals(arrayItems.get(position))){

                        controller.addListItem(controller.getCurrentList().getId(), item.getId(), Integer.valueOf(1));

                        Intent intent = new Intent(listView.getContext(), GroceryListActivity.class);
                        intent.putExtra("GroceryList", controller.getCurrentList());

                        listView.getContext().startActivity(intent);
                    }
                }
            }
        });

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateListView(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateListView(s.toString());
            }
        });
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
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
                addNewItem();
                return(true);
            case R.id.back:
                Intent intent = new Intent(this, GroceryListActivity.class);
                intent.putExtra("GroceryList", controller.getCurrentList());
                startActivity(intent);
                return(true);
            case R.id.select_list:
                startActivity(new Intent(this, MainActivity.class));
                return(true);

        }
        return(super.onOptionsItemSelected(item));
    }

    /**
     * Adds new item to database and loads grocery list.
     */
    private void addNewItem() {
        Intent intentAddNewItemAct = new Intent(this, AddNewItemActivity.class);
        intentAddNewItemAct.putExtra("groceryListID", controller.getCurrentList().getId());
        startActivity(intentAddNewItemAct);
    }

    /**
     * Updates the items returned from the search.
     *
     * @param searchString Text to search
     */
    private void updateListView(String searchString) {
        arrayItems.clear();
        items = controller.searchForItem(searchString);

        for (Item item: items) {
            arrayItems.add(item.getName());
        }

        listAdapter.notifyDataSetChanged();
    }
}
