package edu.gatech.seclass.glm.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

import edu.gatech.seclass.glm.Activity.GroceryListActivity;
import edu.gatech.seclass.glm.Model.ListItem;
import edu.gatech.seclass.glm.R;

/**
 * ArrayAdapter override for GroceryList activity
 * https://www.caveofprogramming.com/guest-posts/custom-listview-with-imageview-and-textview-in-android.html
 * Created by jbuoni on 10/11/16.
 */
public class ItemArrayAdapter extends BaseAdapter{

    private List<ListItem> items;
    private Context context;
    private static LayoutInflater inflater;

    /**
     * Constructor
     * @param groceryListActivity - GroceryListActivity
     * @param listItems - List<ListItem>
     */
    public ItemArrayAdapter(GroceryListActivity groceryListActivity, List<ListItem> listItems) {
        items = listItems;
        context = groceryListActivity;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * ListItem list size
     * @return int representing size
     */
    @Override
    public int getCount() {
        return items.size();
    }

    /**
     * Returns list item at position
     * @param position - int of item position to return
     * @return - ListItem
     */
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    /**
     * Return ListItem with matching ID
     * @param position - int of item position to return
     * @return - ListItem
     */
    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    /**
     * Creates view object for each item in the ListView
     * @param position - int of item position to return
     * @param convertView - View object
     * @param parent - Parent activity
     * @return - ListView item
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.list_item_listview, null);

        CheckBox checkBox = (CheckBox)rowView.findViewById(R.id.itemCheckBox);
        final EditText itemName = (EditText)rowView.findViewById(R.id.itemName);

        checkBox.setChecked(items.get(position).getIsChecked());
        itemName.setText(items.get(position).getItem().getName());

        checkBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                items.get(position).setIsChecked(!items.get(position).getIsChecked());
            }
        });

        itemName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(position);
            }
        });

        return rowView;
    }

    /**
     * Returns all items
     * @return - List<ListItem>
     */
    public List<ListItem> getItems() {
        return items;
    }
}
