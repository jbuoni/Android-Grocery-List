package edu.gatech.seclass.glm;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ItemType;
import edu.gatech.seclass.glm.Model.ListItem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * ListItems unit tests
 * Created by jbuoni on 10/12/16.
 */

public class ListItemUnitTest {

    private ListItem item;

    @Before
    public void setUp() {
        item = new ListItem(1, new Item(1, "Test Item 1", new ItemType(2, "Type 2")), false, 1);
    }


    @Test
    public void setId_getId_isCorrect() throws Exception {
        item.setId(2);
        assertEquals("ListItem should have an id of 2", item.getId(), new Integer(2));
    }

    @Test
    public void setIsChecked_getIsChecked_isCorrect() throws Exception {
        item.setIsChecked(true);
        assertEquals("ListItem should be checked", item.getIsChecked(), true);
        item.setIsChecked(false);
        assertNotEquals("ListItem should not be checked", item.getIsChecked(), true);
    }

    @Test
    public void setQuantity_getQuantity_isCorrect() throws Exception {
        item.setQuantity(10);
        assertEquals("ListItem should have a quantity of 10", item.getQuantity(), new Integer(10));
    }


    @Test
    public void compareTo_isCorrect() throws Exception {
        ListItem item2 = new ListItem(1, new Item(1, "Test Item 1", new ItemType(1, "Type 1")), false, 1);
        ListItem item3 = new ListItem(1, new Item(1, "Test Item 1", new ItemType(3, "Type 3")), false, 1);

        assertEquals("Should return 1 when comparing Items with lower Type value", item.compareTo(item2), 1);
        assertEquals("Should return 0 when comparing Items with same Type value", item.compareTo(item), 0);
        assertEquals("Should return -1 when comparing Items with higher Type value", item.compareTo(item3), -1);
    }

    @Test
    public void toString_isCorrect() throws Exception {
        assertEquals("Validate toString", item.toString(), "Name: Test Item 1 Type: Type 2 Quantity: 1");
    }

}
