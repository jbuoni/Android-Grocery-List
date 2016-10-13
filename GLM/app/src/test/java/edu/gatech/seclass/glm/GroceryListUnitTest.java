package edu.gatech.seclass.glm;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ListItem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * ListMgmtController unit tests.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * Created by jbuoni on 10/11/16.
 */
public class GroceryListUnitTest {
    private ListItem item1, item2, item3;
    private List<ListItem> listItems;
    private GroceryList groceryList;

    @Before
    public void setUp() {
        listItems = new ArrayList<ListItem>();

        item1 = new ListItem(1, new Item(1, "Test Item 1", 1), false, 1);
        item2 = new ListItem(2, new Item(2, "Test Item 2", 3), true, 4);
        item3 = new ListItem(3, new Item(2, "Test Item 3", 2), true, 5);

        listItems.add(item1);
        listItems.add(item2);
        listItems.add(item3);

        groceryList = new GroceryList("Test List", new Integer(1), listItems);
    }

    @Test
    public void setName_getName_isCorrect() throws Exception {
        groceryList.setName("Test List 1");

        assertEquals("List name should equal \"Test List 1\"", groceryList.getName(), "Test List 1");
    }

    @Test
    public void getAllListItems_isCorrect() throws Exception {
        assertEquals("List should have 3 items", groceryList.getAllListItems().size(), 3);
    }

    @Test
    public void setListItems_isCorrect() throws Exception {
        ListItem item4 = new ListItem(4, new Item(4, "Test Item 4", 1), false, 4);
        groceryList.addListItem(item4);

        assertEquals("List should have 4 items", groceryList.getAllListItems().size(), 4);
    }

    @Test
    public void removeListItemById_isCorrect() throws Exception {
        groceryList.removeListItemById(1);
        assertEquals("List should have 4 items", groceryList.getAllListItems().size(), 2);

        for (ListItem item: groceryList.getAllListItems()) {
            assertNotEquals("List item should not have an id of 1", item.getId(), new Integer(1));
        }
    }

    @Test
    public void uncheckAllListItems_isCorrect() throws Exception {
        groceryList.uncheckAllListItems();

        for (ListItem item : groceryList.getAllListItems()) {
            assertNotEquals("List item should not be checked", item.getIsChecked(), true);
        }

        assertEquals("List should have 3 items", groceryList.getAllListItems().size(), 3);
    }

    @Test
    public void checkListItemById_isCorrect() throws Exception {
        ListItem item4 = new ListItem(4, new Item(4, "Test Item 4", 1), false, 4);
        groceryList.addListItem(item4);

        groceryList.checkListItemById(1);
        groceryList.checkListItemById(4);

        assertEquals("List item 1 should be checked", groceryList.getAllListItems().get(0).getIsChecked(), true);
        assertEquals("List item 4 should be checked", groceryList.getAllListItems().get(3).getIsChecked(), true);
    }

    @Test
    public void sortListByItemType_isCorrect() throws Exception {
        ListItem item4 = new ListItem(4, new Item(4, "Test Item 4", 1), false, 4);
        groceryList.addListItem(item4);
        groceryList.sortListByItemType();

        assertEquals("Item 1 type should be 1", groceryList.getAllListItems().get(0).getItem().getItemType(), new Integer(1));
        assertEquals("Item 4 type should be 1", groceryList.getAllListItems().get(1).getItem().getItemType(), new Integer(1));
        assertEquals("Item 3 type should be 2", groceryList.getAllListItems().get(2).getItem().getItemType(), new Integer(2));
        assertEquals("Item 2 type should be 3", groceryList.getAllListItems().get(3).getItem().getItemType(), new Integer(3));
    }


}

