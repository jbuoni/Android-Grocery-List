package edu.gatech.seclass.glm.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.gatech.seclass.glm.Activity.GroceryListActivity;
import edu.gatech.seclass.glm.Activity.MainActivity;
import edu.gatech.seclass.glm.Controller.ListController;
import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.R;

/**
 * ArrayAdapter override for Main activity
 * https://www.caveofprogramming.com/guest-posts/custom-listview-with-imageview-and-textview-in-android.html
 * Created by jbuoni on 10/13/16.
 */

public class GroceryListArrayAdapter extends BaseAdapter {

    private Context context;
    private static LayoutInflater inflater;
    private ListController listController;
    private List<GroceryList> groceryLists;

    /**
     * Constructor
     * @param mainActivity - MainActivity
     * @param listController - listController
     */
    public GroceryListArrayAdapter(MainActivity mainActivity, List<GroceryList> groceryLists, ListController listController) {
        this.listController = listController;
        this.groceryLists = groceryLists;
        context = mainActivity;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * ListItem list size
     * @return int representing size
     */
    @Override
    public int getCount() {
        return groceryLists.size();
    }

    /**
     * Returns grocery list at position
     * @param position - int of item position to return
     * @return - GroceryList
     */
    @Override
    public Object getItem(int position) {
        return groceryLists.get(position);
    }

    /**
     * Return grocery list with matching ID
     * @param position - int of item position to return
     * @return - GroceryList
     */
    @Override
    public long getItemId(int position) {
        return groceryLists.get(position).getId();
    }

    /**
     * Creates view object for each item in the GroceryList
     * @param position - int of item position to return
     * @param convertView - View object
     * @param parent - Parent activity
     * @return - GroceryList item
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.grocery_list_listview, null);

        Button viewListButton = (Button)rowView.findViewById(R.id.editGroceryListButton);

        final TextView groceryListName = (TextView)rowView.findViewById(R.id.groceryListName);
        groceryListName.setText(groceryLists.get(position).getName());


        viewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GroceryListActivity.class);
                intent.putExtra("GroceryList", groceryLists.get(position));

                context.startActivity(intent);
            }
        });

        groceryListName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View dialogView = inflater.inflate(R.layout.edit_list_dialog, null);
                    dialogBuilder.setView(dialogView);

                    final EditText groceryListNameEditText = (EditText) dialogView.findViewById(R.id.groceryListName);
                    //Set as to previous name
                    groceryListNameEditText.setText(groceryListNameEditText.getText().toString());

                    dialogBuilder.setTitle("Edit Grocery List");
                    dialogBuilder.setMessage("Update grocery list name below");
                    dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int buttonVal) {
                            if(groceryListNameEditText.getText().toString().isEmpty()) {
                                Toast toast = Toast.makeText(context.getApplicationContext(), "Please enter a grocery list name", Toast.LENGTH_LONG);
                                toast.show();
                            } else {
                                listController.updateListName(groceryLists.get(position), groceryListNameEditText.getText().toString());
                                groceryLists = listController.getAllLists();
                                notifyDataSetChanged();
                            }
                        }
                    });
                    dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int buttonVal) {
                            dialog.dismiss();
                        }
                    });
                    final AlertDialog createListDialog = dialogBuilder.create();

                    Button deleteListButton = (Button) dialogView.findViewById(R.id.deleteListButton);

                    deleteListButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listController.deleteList(groceryLists.get(position).getId());
                            groceryLists.remove(position);
                            notifyDataSetChanged();
                            createListDialog.dismiss();
                        }
                    });

                    createListDialog.show();

                    return true;
                }
                return false;
            }
        });

        return rowView;
    }
}
