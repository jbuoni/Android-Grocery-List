package edu.gatech.seclass.glm.Utils;

import android.content.Context;
import android.support.v4.content.res.TypedArrayUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

import edu.gatech.seclass.glm.Activity.GroceryListActivity;
import edu.gatech.seclass.glm.Activity.MainActivity;
import edu.gatech.seclass.glm.Model.ListItem;
import edu.gatech.seclass.glm.R;

/**
 * ArrayAdapter override for GroceryList activity
 * Created by jbuoni on 10/11/16.
 */

public class ItemArrayAdapter extends BaseAdapter{

    private List<ListItem> items;
    private Context context;
    private static LayoutInflater inflater;

    public ItemArrayAdapter(GroceryListActivity groceryListActivity, List<ListItem> listItems) {
        items = listItems;
        context = groceryListActivity;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.grocery_list_item, null);

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

    public List<ListItem> getItems() {
        return items;
    }
}
