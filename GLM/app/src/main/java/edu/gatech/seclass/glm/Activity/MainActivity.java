package edu.gatech.seclass.glm.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.List;

import edu.gatech.seclass.glm.Controller.ListController;
import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.R;
import edu.gatech.seclass.glm.Utils.GroceryListArrayAdapter;

public class MainActivity extends AppCompatActivity {

    private GroceryListArrayAdapter groceryListArrayAdapter;
    private ListController controller;
    private List<GroceryList> groceryLists;
    private int listCount = 0;

    /**
     * Create Activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new ListController(this.getApplicationContext());
        groceryLists = controller.getAllLists();

        groceryListArrayAdapter = new GroceryListArrayAdapter(this, groceryLists, controller);

        ListView groceryListView = (ListView) findViewById(R.id.groceryListContainer);
        groceryListView.setAdapter(groceryListArrayAdapter);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case R.id.exit:
                finish();
                return(true);
            case R.id.add_list:
                addList();
        }
        return(super.onOptionsItemSelected(item));
    }

    /**
     * Creates dialog to add new grocery list. When closed, saves
     * grocery list to database.
     */
    private void addList() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.create_list_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText groceryListEditText = (EditText) dialogView.findViewById(R.id.groceryListName);

        dialogBuilder.setTitle(R.string.create_grocery_list);
        dialogBuilder.setMessage(R.string.enter_list_name);
        dialogBuilder.setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int buttonVal) {
                if(groceryListEditText.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.list_error_1, Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    controller.createList(groceryListEditText.getText().toString());
                    //Safer method when using listAdapters so that the object remains the same
                    groceryLists.clear();
                    groceryLists.addAll(controller.getAllLists());
                    groceryListArrayAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                    listCount++;
                }
            }
        });
        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int buttonVal) {
                dialog.dismiss();
            }
        });
        AlertDialog createListDialog = dialogBuilder.create();
        createListDialog.show();
    }
}
