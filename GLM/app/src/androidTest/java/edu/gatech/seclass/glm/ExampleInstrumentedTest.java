package edu.gatech.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.mock.MockContext;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.glm.DAO.DAO;
import edu.gatech.seclass.glm.DAO.DAOI;
import edu.gatech.seclass.glm.DAO.DatabaseContract;
import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ItemType;
import edu.gatech.seclass.glm.Model.ListItem;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExampleInstrumentedTest {

    private static int testListId = 0;

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
    public void test1CreateGroceryList() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        String listName = "Test List";
        GroceryList testList = daoi.createList(listName);
        testListId = testList.getId();
        GroceryList testListFromID = daoi.loadList(testListId);
        assertEquals(listName, testListFromID.getName());
    }

    @Test
    /**
     * Test the daoi.addItemToList function works by adding two items
     * to the list created in test1CreateGroceryList and loading the list
     * to check if the added items are in the list.
     */
    public void test2AddItemToList() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        List<ItemType> testItemTypeList = daoi.getAllItemTypes();
        List<Item> testItemList1 = daoi.getItemsByItemType(testItemTypeList.get(0));
        Item testItem1 = testItemList1.get(0);
        List<Item> testItemList2 = daoi.getItemsByItemType(testItemTypeList.get(1));
        Item testItem2 = testItemList2.get(0);
        daoi.addItemToList(testListId, testItem1.getId(), 3);
        daoi.addItemToList(testListId, testItem2.getId(), 4);
        GroceryList testListFromID = daoi.loadList(testListId);
        List<ListItem> listItems = testListFromID.getAllListItems();
        ListItem dbListItem1 = listItems.get(0);
        ListItem dbListItem2 = listItems.get(1);
        if (dbListItem1.getItem().getId() == testItem1.getId())
        {
            assertTrue(dbListItem1.getQuantity() == 3 && dbListItem2.getItem().getId() == testItem2.getId()
                    && dbListItem2.getQuantity() == 4 && !dbListItem1.getIsChecked() && !dbListItem2.getIsChecked());
            return;
        }
        else if (dbListItem2.getItem().getId() == testItem1.getId())
        {
            assertTrue(dbListItem1.getQuantity() == 4 && dbListItem1.getItem().getId() == testItem2.getId()
                    && dbListItem2.getQuantity() == 3 && !dbListItem1.getIsChecked() && !dbListItem2.getIsChecked());
            return;
        }

        assertTrue(false);
    }

    @Test
    /**
     * Test that dao.deleteList deletes the list from the database.
     */
    public void test3DeleteList() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        daoi.deleteList(testListId);
        GroceryList testListFromID = daoi.loadList(testListId);
        assertNull(testListFromID);
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

    @Test
    /**
     * Test findItemsLike by searching for "12 Pack" and check
     * that the three Items with names starting with 12 Pack are returned.
     */
    public void testFindItemsLike() {

        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        List<Item> testList = daoi.findItemsLike("12 Pack");
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
     * Test that creating a new item stores it in the database.
     */
    public void testDeleteItem() {

        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        String itemName = "Test Item " + System.currentTimeMillis();
        List<ItemType> testItemTypeList = daoi.getAllItemTypes();
        Item item = daoi.createNewItem(itemName, testItemTypeList.get(0).getId());
        List<Item> itemsByType = daoi.getItemsByItemType(testItemTypeList.get(0));

        boolean itemFound = false;
        for (Item i:itemsByType)
        {
            if (i.getName().equals(itemName))
            {
                itemFound = true;
            }

        }
        assertTrue(itemFound);

    }


    //@Test
    /**
     * Test that DAO.getAllLists works by calling it and making sure
     * all the lists in the original database are there and have the
     * expected data.
     */



}
