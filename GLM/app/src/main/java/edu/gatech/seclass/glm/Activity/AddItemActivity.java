package edu.gatech.seclass.glm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.glm.DAO.DAO;
import edu.gatech.seclass.glm.DAO.DAOI;
import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ItemType;
import edu.gatech.seclass.glm.R;

/**
 * Created by jbuoni on 10/11/16.
 */

public class AddItemActivity extends AppCompatActivity implements OnItemSelectedListener {
    private Spinner sp_type, sp_item;
    private EditText quantity;
    Button clickButton;
    DAOI daoi = new DAO(this);
    List<ItemType> curItemTypes = new ArrayList<>();
    List<Item> listItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        //grab grocerlistid save to private variable
        //add decimal number box
        quantity = (EditText)findViewById(R.id.aQuant);

        //add button
        clickButton = (Button) findViewById(R.id.addButton);

        //get spinners
        sp_type = (Spinner)findViewById(R.id.aspnType);
        sp_item = (Spinner)findViewById(R.id.aspnItem);

        //populate spinners
        populate_spntype();
        populate_spnitem(curItemTypes.get(0));

        //set listeners
        sp_type.setOnItemSelectedListener(this);
    }

    public void buttonOnClick(View view){
        if(quantity.getText().toString().length()>0){
            //
            int listID = this.getIntent().getIntExtra("groceryListID", -1);
            daoi.addItemToList(listID,listItems.get(sp_item.getSelectedItemPosition()).getId(),Integer.parseInt(quantity.getText().toString()));
            Intent intent = new Intent(AddItemActivity.this, GroceryListActivity.class);
            intent.putExtra("GroceryList", daoi.loadList(listID));
            startActivity(intent);

        }else{
            Toast.makeText(this, "You must enter a quantity", Toast.LENGTH_LONG).show();
        }

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
        curItemTypes = daoi.getAllItemTypes();
        List<String> listStrTypes = new ArrayList<>();

        for (int i = 0; i<curItemTypes.size(); i++){
            listStrTypes.add(curItemTypes.get(i).getName());
        }

        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(AddItemActivity.this, android.R.layout.simple_spinner_item, listStrTypes);
        sp_type.setAdapter(adapterType);
    }

    //populates item spinner
    private void populate_spnitem(ItemType curType){
        //Get all items and populate the item spinner
        listItems = daoi.getItemsByItemType(curType);
        List<String> listStrItems = new ArrayList<>();
        //Get names of all the items
        for (int i = 0; i<listItems.size(); i++){
            listStrItems.add(listItems.get(i).getName());
        }

        ArrayAdapter<String> adapterItem = new ArrayAdapter<>(AddItemActivity.this, android.R.layout.simple_spinner_item, listStrItems);
        sp_item.setAdapter(adapterItem);

    }
}
