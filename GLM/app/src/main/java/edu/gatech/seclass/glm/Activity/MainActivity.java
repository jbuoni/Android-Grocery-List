package edu.gatech.seclass.glm.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private ListController listController;
    private List<GroceryList> groceryLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listController = new ListController(this.getApplicationContext());
        groceryLists = listController.getAllLists();

        groceryListArrayAdapter = new GroceryListArrayAdapter(this, groceryLists);

        ListView groceryListView = (ListView) findViewById(R.id.groceryListContainer);
        groceryListView.setAdapter(groceryListArrayAdapter);
    }

    public void addList(View view) {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.create_list_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText groceryListEditText = (EditText) dialogView.findViewById(R.id.groceryListName);

        dialogBuilder.setTitle("Custom dialog");
        dialogBuilder.setMessage("Enter text below");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int buttonVal) {
                if(!groceryListEditText.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a grocery list name", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    listController.createList(groceryListEditText.getText().toString());
                    dialog.dismiss();
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

    public void deleteSelectedLists(View view) {
        //TODO implement
    }
}
