package edu.gatech.seclass.glm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import edu.gatech.seclass.glm.Controller.GroceryListController;
import edu.gatech.seclass.glm.Model.ListItem;
import edu.gatech.seclass.glm.R;

/**
 * GroceryListActivity generates the grocery list page.
 * Created by jbuoni on 10/10/16.
 */

public class GroceryListActivity extends AppCompatActivity {

    private ArrayAdapter<ListItem> listAdapter;
    private List<ListItem> listItems;
    private GroceryListController groceryListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        groceryListController = new GroceryListController(this.getApplicationContext());

        listItems = groceryListController.getCurrentListItems();

        //Auto update the list view
        ListView lv = (ListView)findViewById(R.id.listItemContainer);
        listAdapter = new ArrayAdapter<ListItem>(this, R.layout.grocery_list_item, listItems);
        lv.setAdapter(listAdapter);

        generateListItemViews();
    }

    public void searchItem(View view){
        startActivity(new Intent(this, SearchItemActivity.class));
    }

    public void addItem(View view){
        startActivity(new Intent(this, AddItemActivity.class));
    }

    public void uncheckAllItems(View view){
        groceryListController.uncheckAllListItems();
    }

    private void generateListItemViews() {
        listItems = groceryListController.getCurrentListItems();
        listAdapter.notifyDataSetChanged();
    }
}