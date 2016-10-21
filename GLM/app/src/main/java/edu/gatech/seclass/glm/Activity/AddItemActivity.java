package edu.gatech.seclass.glm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
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
 * Created by jbuoni on 10/11/16.
 */

public class AddItemActivity extends AppCompatActivity implements OnItemSelectedListener {
    private Spinner sp_type, sp_item;
    private EditText quantity;
    private List<ItemType> curItemTypes = new ArrayList<>();
    private List<Item> listItems = new ArrayList<>();
    private ListMgmtController controller;

    /**
     * Create Activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        controller = new ListMgmtController(getApplicationContext());
        //grab grocerlistid save to private variable
        //add decimal number box
        quantity = (EditText)findViewById(R.id.aQuant);

        //get spinners
        sp_type = (Spinner)findViewById(R.id.aspnType);
        sp_item = (Spinner)findViewById(R.id.aspnItem);

        //populate spinners
        populate_spntype();
        populate_spnitem(curItemTypes.get(0));

        //set listeners
        sp_type.setOnItemSelectedListener(this);

        int listID = this.getIntent().getIntExtra("groceryListID", -1);
        controller.updateCurrentList(listID);
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
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
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
            case R.id.search:
                searchItem();
                return(true);
            case R.id.add_item:
                addItem();
                return(true);
            case R.id.back:
                loadGroceryListActivity();
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
     * Loads GroceryListActivity.
     */
    private void loadGroceryListActivity() {
        Intent intent = new Intent(AddItemActivity.this, GroceryListActivity.class);
        intent.putExtra("GroceryList", controller.getCurrentList());
        startActivity(intent);
    }

    /**
     * Adds new item to database and loads GroceryListActivity.
     */
    private void addItem() {
        if(quantity.getText().toString().length()>0){
            try {
                Integer q = Integer.parseInt(quantity.getText().toString());
                controller.addListItem(controller.getCurrentList().getId(), listItems.get(sp_item.getSelectedItemPosition()).getId(), q);
            } catch (NumberFormatException ex) {
                makeToast("You must enter a valid quantity");
            }
            loadGroceryListActivity();

        }else{
            makeToast("You must enter a valid quantity");
        }
    }

    /**
     * Loads AddNewItemActivity.
     *
     * @param view
     */
    public void addNewItemButtonOnClick(View view){
        Intent intentNewAddAct = new Intent(this, AddNewItemActivity.class);
        intentNewAddAct.putExtra("groceryListID", controller.getCurrentList().getId());
        startActivity(intentNewAddAct);
    }

    /**
     * <p>Callback method to be invoked when an item in this view has been
     * selected. This callback is invoked only when the newly selected
     * position is different from the previously selected position or if
     * there was no selected item.</p>
     *
     * Impelmenters can call getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param parent The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //see which spinner was chosen
        Toast.makeText(this, Integer.toString(parent.getSelectedItemPosition()), Toast.LENGTH_LONG).show();
        switch (parent.getId()){
            case R.id.aspnItem:
                break;
            case R.id.aspnType:
                //if type was chose, update the item's list
                populate_spnitem(curItemTypes.get(position));
                break;
        }
    }

    /**
     * Callback method to be invoked when the selection disappears from this
     * view. The selection can disappear for instance when touch is activated
     * or when the adapter becomes empty.
     *
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Populates type spinner.
     */
    private void populate_spntype(){
        //get list of types to populate
        curItemTypes = controller.getAllItemTypes();
        List<String> listStrTypes = new ArrayList<>();

        for (int i = 0; i < curItemTypes.size(); i++){
            listStrTypes.add(curItemTypes.get(i).getName());
        }

        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(AddItemActivity.this, android.R.layout.simple_spinner_item, listStrTypes);
        sp_type.setAdapter(adapterType);
    }

    /**
     * Populates item spinner.
     */
    private void populate_spnitem(ItemType curType){
        //Get all items and populate the item spinner
        listItems = controller.getAllItemsByType(curType);
        List<String> listStrItems = new ArrayList<>();
        //Get names of all the items
        for (int i = 0; i<listItems.size(); i++){
            listStrItems.add(listItems.get(i).getName());
        }

        ArrayAdapter<String> adapterItem = new ArrayAdapter<>(AddItemActivity.this, android.R.layout.simple_spinner_item, listStrItems);
        sp_item.setAdapter(adapterItem);

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
