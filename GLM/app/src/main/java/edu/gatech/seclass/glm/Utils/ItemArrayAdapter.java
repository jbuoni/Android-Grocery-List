package edu.gatech.seclass.glm.Utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import edu.gatech.seclass.glm.Activity.GroceryListActivity;
import edu.gatech.seclass.glm.Controller.ListMgmtController;
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
    private ListMgmtController controller;

    /**
     * Constructor
     * @param groceryListActivity - GroceryListActivity
     * @param listItems - List<ListItem>
     */
    public ItemArrayAdapter(GroceryListActivity groceryListActivity, List<ListItem> listItems, ListMgmtController controller) {
        this.controller = controller;
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

        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.itemCheckBox);
        final EditText itemName = (EditText) rowView.findViewById(R.id.itemName);
        Button removeItem = (Button) rowView.findViewById(R.id.removeItemButton);
        final EditText quantity = (EditText) rowView.findViewById(R.id.itemQuantity);
        final ListItem currentItem = items.get(position);

        checkBox.setChecked(currentItem.getIsChecked());
        itemName.setText(currentItem.getItem().getName());
        quantity.setText(currentItem.getQuantity().toString());

        checkBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentItem.setIsChecked(!currentItem.getIsChecked());
                controller.toggleCheck(items.get(position));
            }
        });

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Do nothing
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Update quantity if integer
                if(!quantity.getText().toString().isEmpty()){
                    try {
                        currentItem.setQuantity(Integer.parseInt(quantity.getText().toString()));
                    } catch (NumberFormatException ex) {
                        Toast.makeText(context, "You must enter a valid quantity", Toast.LENGTH_LONG).show();
                    }

                    controller.updateItem(currentItem);
                    notifyDataSetChanged();
                }
            }
        });

        //http://stackoverflow.com/questions/13638321/android-notifydatasetchange-from-within-custom-arrayadapter-class
        removeItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.removeListItem(currentItem);
                items.remove(position);
                notifyDataSetChanged();
            }
        });

        return rowView;
    }
}
