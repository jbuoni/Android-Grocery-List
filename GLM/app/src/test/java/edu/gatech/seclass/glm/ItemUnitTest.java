package edu.gatech.seclass.glm;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ItemType;

import static org.junit.Assert.assertEquals;

/**
 * Created by jbuoni on 10/13/16.
 */

public class ItemUnitTest {

    private Item item;

    @Before
    public void setUp() {
        item = new Item(1, "Test Item 1", new ItemType(2, "Type 2"));
    }

    @Test
    public void getId_isCorrect() throws Exception {
        assertEquals("Item should have an id of 1", item.getId(), new Integer(1));
    }

    @Test
    public void setName_getName_isCorrect() throws Exception {
        item.setName("Test Name");
        assertEquals("ListItem should have an name of \"Test Name\"", item.getName(), "Test Name");
    }

    @Test
    public void setItemType_getItemType_isCorrect() throws Exception {
        ItemType itemType = new ItemType(2, "Type 2");

        item.setItemType(itemType);
        assertEquals("ItemType should equal ItemType(2, \"Type 2\")", item.getItemType(), itemType);
    }

}
