package edu.gatech.seclass.glm.Activity;
import edu.gatech.seclass.glm.DAO.DAO;
import edu.gatech.seclass.glm.DAO.DAOI;
import edu.gatech.seclass.glm.DAO.DatabaseContract;
import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ItemType;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.glm.Model.ItemType;
import edu.gatech.seclass.glm.R;

/**
 * Created by jbuoni on 10/11/16.
 */

public class AddItemActivity extends AppCompatActivity implements OnItemSelectedListener {
    private Spinner sp_type, sp_item;
    DAOI daoi = new DAO(this);
    List<ItemType> curItemTypes = new ArrayList<>();
    List<Item> listItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        sp_type = (Spinner)findViewById(R.id.aspnType);
        sp_item = (Spinner)findViewById(R.id.aspnItem);
        populate_spntype();
        populate_spnitem(curItemTypes.get(0));
        sp_type.setOnItemSelectedListener(this);
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;

        //if type
        if(spinner.getId() == R.id.aspnType){
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

            //Get all items and populate the item spinner
            populate_spnitem(curItemTypes.get(position));

        }
        else if(spinner.getId() == R.id.aspnItem){

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
        Toast.makeText(this, listItems.get(1).getName(), Toast.LENGTH_LONG).show();
        for (int i = 0; i<listItems.size(); i++){
            listStrItems.add(listItems.get(i).getName());
        }

        ArrayAdapter<String> adapterItem = new ArrayAdapter<>(AddItemActivity.this, android.R.layout.simple_spinner_item, listStrItems);
        sp_item.setAdapter(adapterItem);

    }
}
