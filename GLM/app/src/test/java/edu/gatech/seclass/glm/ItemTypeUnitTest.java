package edu.gatech.seclass.glm;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.seclass.glm.Model.ItemType;

import static org.junit.Assert.assertEquals;

/**
 * Created by jbuoni on 10/13/16.
 */

public class ItemTypeUnitTest {
    private ItemType itemType;

    @Before
    public void setUp() {
        itemType = new ItemType(2, "Type 1");
    }


    @Test
    public void getId_isCorrect() throws Exception {
        assertEquals("ItemType should have an id of 2", itemType.getId(), new Integer(2));
    }

    @Test
    public void setName_getName_isCorrect() throws Exception {
        itemType.setName("Test Name");
        assertEquals("ItemType should have an name of \"Test Name\"", itemType.getName(), "Test Name");
    }

    @Test
    public void compareTo_isCorrect() throws Exception {
        ItemType itemType2 = new ItemType(1, "Type 2");
        ItemType itemType3 = new ItemType(3, "Type 3");

        assertEquals("Should return 1 when comparing Items with lower Type value", itemType.compareTo(itemType2), 1);
        assertEquals("Should return 0 when comparing Items with same Type value", itemType.compareTo(itemType), 0);
        assertEquals("Should return -1 when comparing Items with higher Type value", itemType.compareTo(itemType3), -1);
    }
}
