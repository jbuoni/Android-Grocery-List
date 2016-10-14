package edu.gatech.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.mock.MockContext;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.glm.DAO.DAO;
import edu.gatech.seclass.glm.DAO.DAOI;
import edu.gatech.seclass.glm.DAO.DatabaseContract;
import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ItemType;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("edu.gatech.seclass.glm", appContext.getPackageName());
    }

    @Test
    /**
     * Test that creating a list stores it in the database.
     */
    public void testCreateGroceryList() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        String listName = "Test List";
        GroceryList testList = daoi.createList(listName);
        GroceryList testListFromID = daoi.loadList(testList.getId());
        assertEquals(listName, testListFromID.getName());
    }

    @Test
    /**
     * Test getItemsByItemType by sending the Beer ItemType
     * that is inserted into the database onCreate and assert
     * that returns all of the items of type beer that were
     * inserted in the database onCreate.
     */
    public void testGetItemsByItemType() {

        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        ItemType it = new ItemType(1, "Beer");
        List<Item> testList = daoi.getItemsByItemType(it);
        List<String> beerNames = new ArrayList<String>();
        beerNames.add("12 Pack of Coors");
        beerNames.add("12 Pack of Budweiser");
        beerNames.add("12 Pack of Bud Light");

        int foundCount = 0;
        for (Item i:testList)
        {
            String itemName = i.getName();
            for (String beerName:beerNames)
            {
                if (beerName.equals(itemName))
                {
                    foundCount++;
                }
            }
        }

        assertEquals(beerNames.size(), foundCount);

    }

    @Test
    /**
     * Test DAO.getAllItemTypes by checking that the ItemTypes
     * that are inserted into the database onCreate are all
     * in the list that is returned by the method.
     */
    public void testGetAllItemTypes() {

        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        List<ItemType> testItemTypeList = daoi.getAllItemTypes();
        List<String> itemTypeNames = new ArrayList<String>();
        itemTypeNames.add("Beer");
        itemTypeNames.add("Milk");
        itemTypeNames.add("Cereal");

        int foundCount = 0;
        for (ItemType it:testItemTypeList)
        {
            String foundItemTypeName = it.getName();
            for (String itName:itemTypeNames)
            {
                if (foundItemTypeName.equals(itName))
                {
                    foundCount++;
                }
            }
        }

        assertEquals(itemTypeNames.size(), foundCount);

    }
}
