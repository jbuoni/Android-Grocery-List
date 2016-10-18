package edu.gatech.seclass.glm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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

                        controller.addListItemNoId(controller.getCurrentList().getId(), item.getId(), Integer.valueOf(1));

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

    public void addNewItemToDatabase(View v) {
        Intent intentAddNewItemAct = new Intent(this, AddNewItemActivity.class);
        intentAddNewItemAct.putExtra("groceryListID", controller.getCurrentList().getId());
        startActivity(intentAddNewItemAct);
    }

    private void updateListView(String searchString) {
        arrayItems.clear();
        items = controller.searchForItem(searchString);

        for (Item item: items) {
            arrayItems.add(item.getName());
        }

        listAdapter.notifyDataSetChanged();
    }
}
