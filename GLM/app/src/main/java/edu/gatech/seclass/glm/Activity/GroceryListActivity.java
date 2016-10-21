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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_grocery_list, menu);
        return true;
    }

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

    private void searchItem(){
        Intent intent = new Intent(this, SearchItemActivity.class);
        intent.putExtra("groceryListID", controller.getCurrentList().getId());
        startActivity(intent);
    }

    private void addItem(){
        Intent intentAddAct = new Intent(this, AddItemActivity.class);
        intentAddAct.putExtra("groceryListID", controller.getCurrentList().getId());
        startActivity(intentAddAct);
    }

    private void uncheckAllItems(){
        controller.uncheckAllListItems();
        generateListItemViews();
    }

    private void generateListItemViews() {
        //Better way when using ArrayAdapters. Keeps object reference the same.
        listItems.clear();
        listItems.addAll(controller.getCurrentListItems());
        listAdapter.notifyDataSetChanged();
    }

}
