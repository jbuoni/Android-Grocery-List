package edu.gatech.seclass.glm.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import edu.gatech.seclass.glm.Activity.MainActivity;
import edu.gatech.seclass.glm.Model.ListItem;
import edu.gatech.seclass.glm.R;

/**
 * Created by jbuoni on 10/11/16.
 */

public class ItemArrayAdapter extends BaseAdapter{

    private ListItem[] items;
    private Context context;
    private static LayoutInflater inflater;

    public ItemArrayAdapter(MainActivity mainActivity, ListItem[] listItems) {
        items = listItems;
        context = mainActivity;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return items[position].getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View rowView;
        rowView = inflater.inflate(R.layout.grocery_list_item, null);

        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });
        return rowView;
    }
}
