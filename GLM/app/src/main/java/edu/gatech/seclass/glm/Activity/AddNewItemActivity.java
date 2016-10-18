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
 * Created by jbuoni on 10/18/16.
 */

public class AddNewItemActivity extends AppCompatActivity {
    private ListMgmtController controller;
    private Spinner sp_type;
    private List<ItemType> curItemTypes = new ArrayList<>();
    private EditText itemNameEditText, itemQuantityEditText;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_new_item, menu);
        return true;
    }

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
                controller.addListItemNoId(controller.getCurrentList().getId(), newItem.getId(), quantity);

                Intent intent = new Intent(this, GroceryListActivity.class);
                intent.putExtra("GroceryList", controller.getCurrentList());
                startActivity(intent);
            } catch (NumberFormatException e) {
                makeToast("You must enter a valid quantity");
            }

        }
    }

    public void addItemButtonOnClick(View v) {
        addNewItem();
    }

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

    private void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
