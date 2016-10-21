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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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

    private void addList() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.create_list_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText groceryListEditText = (EditText) dialogView.findViewById(R.id.groceryListName);

        dialogBuilder.setTitle("Create Grocery List");
        dialogBuilder.setMessage("Enter grocery list name below");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int buttonVal) {
                if(groceryListEditText.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a grocery list name", Toast.LENGTH_LONG);
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
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int buttonVal) {
                dialog.dismiss();
            }
        });
        AlertDialog createListDialog = dialogBuilder.create();
        createListDialog.show();
    }
}
