package edu.gatech.seclass.glm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.glm.Controller.ListMgmtController;
import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ItemType;
import edu.gatech.seclass.glm.R;

/**
 * View that allows the user to add a new item to the Grocery List.
 *
 * Created by jbuoni on 10/18/16.
 */

public class AddNewItemActivity extends AppCompatActivity {
    private ListMgmtController controller;
    private Spinner sp_type;
    private List<ItemType> curItemTypes = new ArrayList<>();
    private EditText itemNameEditText, itemQuantityEditText;

    /**
     * Create Activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_item);

        controller = new ListMgmtController(getApplicationContext());
        controller.updateCurrentList(this.getIntent().getIntExtra("groceryListID", -1));

        itemNameEditText = (EditText)findViewById(R.id.itemName);
        itemQuantityEditText = (EditText)findViewById(R.id.aQuant);
        sp_type = (Spinner)findViewById(R.id.aspnType);

        populate_spntype();
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
        getMenuInflater().inflate(R.menu.menu_new_item, menu);
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
     * Calls controller to add a new item to the database.
     */
    private void addNewItem() {
        String itemName = itemNameEditText.getText().toString();
        String itemQuantity = itemQuantityEditText.getText().toString();
        if (itemName.isEmpty()) {
            makeToast("No item name specified.");
        } else if(itemQuantity.isEmpty()) {
            makeToast("No item quantity specified.");
        } else {
            Item newItem = controller.addNewItemToDB(itemName, curItemTypes.get(sp_type.getSelectedItemPosition()).getId());

            try {
                Integer quantity = Integer.parseInt(itemQuantity);
                controller.addListItem(controller.getCurrentList().getId(), newItem.getId(), quantity);

                Intent intent = new Intent(this, GroceryListActivity.class);
                intent.putExtra("GroceryList", controller.getCurrentList());
                startActivity(intent);
            } catch (NumberFormatException e) {
                makeToast("You must enter a valid quantity");
            }

        }
    }

    /**
     * Populate item type spinner.
     */
    private void populate_spntype(){
        //get list of types to populate
        curItemTypes = controller.getAllItemTypes();
        List<String> listStrTypes = new ArrayList<>();

        for (int i = 0; i < curItemTypes.size(); i++){
            listStrTypes.add(curItemTypes.get(i).getName());
        }

        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listStrTypes);
        sp_type.setAdapter(adapterType);
    }

    /**
     * Make error toast.
     *
     * @param text Toast message.
     */
    private void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
