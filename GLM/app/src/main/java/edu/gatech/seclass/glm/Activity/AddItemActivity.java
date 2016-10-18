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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

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

    private void searchItem(){
        Intent intent = new Intent(this, SearchItemActivity.class);
        intent.putExtra("groceryListID", controller.getCurrentList().getId());
        startActivity(intent);
    }

    private void loadGroceryListActivity() {
        Intent intent = new Intent(AddItemActivity.this, GroceryListActivity.class);
        intent.putExtra("GroceryList", controller.getCurrentList());
        startActivity(intent);
    }

    private void addItem() {
        if(quantity.getText().toString().length()>0){
            try {
                Integer q = Integer.parseInt(quantity.getText().toString());
                controller.addListItemNoId(controller.getCurrentList().getId(), listItems.get(sp_item.getSelectedItemPosition()).getId(), q);
            } catch (NumberFormatException ex) {
                makeToast("You must enter a valid quantity");
            }
            loadGroceryListActivity();

        }else{
            makeToast("You must enter a valid quantity");
        }
    }

    public void addItemButtonOnClick(View view){
        addItem();
    }

    public void addNewItemButtonOnClick(View view){
        Intent intentNewAddAct = new Intent(this, AddNewItemActivity.class);
        intentNewAddAct.putExtra("groceryListID", controller.getCurrentList().getId());
        startActivity(intentNewAddAct);
    }

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

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //populates type spinner
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

    //populates item spinner
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

    private void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
