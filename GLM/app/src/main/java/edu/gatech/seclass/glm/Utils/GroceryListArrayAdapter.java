package edu.gatech.seclass.glm.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import edu.gatech.seclass.glm.Activity.GroceryListActivity;
import edu.gatech.seclass.glm.Activity.MainActivity;
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
    private List<GroceryList> groceryLists;

    /**
     * Constructor
     * @param mainActivity - MainActivity
     * @param lists - List<GroceryList>
     */
    public GroceryListArrayAdapter(MainActivity mainActivity, List<GroceryList> lists) {
        groceryLists = lists;
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

        CheckBox checkBox = (CheckBox)rowView.findViewById(R.id.groceryListCheckbox);
        Button viewListButton = (Button)rowView.findViewById(R.id.editGroceryListButton);

        TextView groceryListName = (TextView)rowView.findViewById(R.id.groceryListName);
        groceryListName.setText(groceryLists.get(position).getName());


        //TODO checkbox set checked

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GroceryListActivity.class);
                intent.putExtra("GroceryList", groceryLists.get(position));

                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
