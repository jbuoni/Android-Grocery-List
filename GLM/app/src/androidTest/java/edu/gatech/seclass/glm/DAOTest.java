package edu.gatech.seclass.glm;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.glm.DAO.DAO;
import edu.gatech.seclass.glm.DAO.DAOI;
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
public class DAOTest {

    private static int testListId1 = 0;
    private static int testListId2 = 0;

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

        String listName1 = "Test List1";
        GroceryList testList1 = daoi.createList(listName1);
        testListId1 = testList1.getId();
        GroceryList testListFromID1 = daoi.loadList(testListId1);
        boolean test1Passes = (listName1.equals(testListFromID1.getName()));

        String listName2 = "Test List2";
        GroceryList testList2 = daoi.createList(listName2);
        testListId2 = testList2.getId();
        GroceryList testListFromID2 = daoi.loadList(testListId2);
        boolean test2Passes = (listName2.equals(testListFromID2.getName()));

        assertTrue(test1Passes && test2Passes);
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

        //Test list 1
        List<ItemType> testItemTypeList = daoi.getAllItemTypes();

        List<Item> testItemList1 = daoi.getItemsByItemType(testItemTypeList.get(0));
        Item testItem1 = testItemList1.get(0);
        List<Item> testItemList2 = daoi.getItemsByItemType(testItemTypeList.get(1));
        Item testItem2 = testItemList2.get(0);
        daoi.addItemToList(testListId1, testItem1.getId(), 3);
        daoi.addItemToList(testListId1, testItem2.getId(), 4);
        GroceryList testListFromID = daoi.loadList(testListId1);
        List<ListItem> listItems = testListFromID.getAllListItems();
        ListItem dbListItem1 = listItems.get(0);
        ListItem dbListItem2 = listItems.get(1);
        if (dbListItem1.getItem().getId() == testItem1.getId())
        {
            assertTrue(dbListItem1.getQuantity() == 3 && dbListItem2.getItem().getId() == testItem2.getId()
                    && dbListItem2.getQuantity() == 4 && !dbListItem1.getIsChecked() && !dbListItem2.getIsChecked());
        }
        else if (dbListItem2.getItem().getId() == testItem1.getId())
        {
            assertTrue(dbListItem1.getQuantity() == 4 && dbListItem1.getItem().getId() == testItem2.getId()
                    && dbListItem2.getQuantity() == 3 && !dbListItem1.getIsChecked() && !dbListItem2.getIsChecked());
        }

        List<Item> testItemList3 = daoi.getItemsByItemType(testItemTypeList.get(1));
        Item testItem3 = testItemList3.get(0);
        List<Item> testItemList4 = daoi.getItemsByItemType(testItemTypeList.get(2));
        Item testItem4 = testItemList4.get(0);
        daoi.addItemToList(testListId2, testItem3.getId(), 3);
        daoi.addItemToList(testListId2, testItem4.getId(), 4);
        GroceryList testListFromID2 = daoi.loadList(testListId2);
        List<ListItem> listItems2 = testListFromID2.getAllListItems();
        ListItem dbListItem3 = listItems2.get(0);
        ListItem dbListItem4 = listItems2.get(1);
        if (dbListItem3.getItem().getId() == testItem3.getId())
        {
            assertTrue(dbListItem3.getQuantity() == 3 && dbListItem4.getItem().getId() == testItem4.getId()
                    && dbListItem4.getQuantity() == 4 && !dbListItem3.getIsChecked() && !dbListItem4.getIsChecked());
            return;
        }
        else if (dbListItem4.getItem().getId() == testItem3.getId())
        {
            assertTrue(dbListItem3.getQuantity() == 4 && dbListItem3.getItem().getId() == testItem4.getId()
                    && dbListItem4.getQuantity() == 3 && !dbListItem3.getIsChecked() && !dbListItem4.getIsChecked());
            return;
        }

        assertTrue(false);
    }

    @Test
    /**
     * Test that DAO.getAllLists works by calling it and making sure
     * all the lists in the original database are there and have the
     * expected data.
     */
    public void test3GetAllLists() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        List<GroceryList> groceryLists = daoi.getAllLists();
        GroceryList gl1 = null;
        GroceryList gl2 = null;
        for (GroceryList gl:groceryLists)
        {
            if (gl.getId() == testListId1)
            {
                gl1 = gl;
            }
            else if (gl.getId() == testListId2)
            {
                gl2 = gl;
            }
        }
        assertTrue(gl1.getName().equals("Test List1") && gl2.getName().equals("Test List2")
                && gl1.getAllListItems().size() == 2 && gl2.getAllListItems().size() == 2);
    }

    @Test
    /**
     * Test that the updateListName function works
     */
    public void test4TestUpdateListName() {

        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        GroceryList testListFromID1 = daoi.loadList(testListId1);
        String newListName = "Test List1 Updated";
        testListFromID1.setName(newListName);
        daoi.updateListName(testListFromID1, newListName);
        testListFromID1 = daoi.loadList(testListId1);
        assertEquals(newListName, testListFromID1.getName());
    }

    @Test
    /**
     * Test that the deleteItemFromList function works.
     */
    public void test5TestDeleteItemFromList() {

        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        GroceryList testListFromID1 = daoi.loadList(testListId1);
        Integer listToDeleteId = testListFromID1.getAllListItems().get(0).getId();
        daoi.deleteItemFromList(listToDeleteId);
        testListFromID1 = daoi.loadList(testListId1);
        List<ListItem> listItems = testListFromID1.getAllListItems();
        boolean listContainsId = false;
        for (ListItem li:listItems)
        {
            if (li.getId() == listToDeleteId)
            {
                listContainsId = true;
            }
        }

        assertFalse(listContainsId);

    }

    @Test
    /**
     * Test that the  function works.
     */
    public void test6TestToggleListItemIsChecked() {

        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        GroceryList testListFromID1 = daoi.loadList(testListId1);
        List<ListItem> listItems = testListFromID1.getAllListItems();
        ListItem toggleLI = listItems.get(0);
        Integer liId = toggleLI.getId();
        daoi.toggleListItemIsChecked(liId, true);
        testListFromID1 = daoi.loadList(testListId1);
        listItems = testListFromID1.getAllListItems();
        for (ListItem li:listItems)
        {
            if (li.getId() == liId)
            {
                toggleLI = li;
                assertTrue(toggleLI.getIsChecked());
                return;
            }
        }

    }



    @Test
    /**
     * Test that dao.deleteList deletes the list from the database.
     */
    public void test7DeleteList() {

        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        daoi.deleteList(testListId1);
        GroceryList testListFromID = daoi.loadList(testListId1);
        assertNull(testListFromID);
        daoi.deleteList(testListId2);
        GroceryList testListFromID2 = daoi.loadList(testListId2);
        assertNull(testListFromID2);

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


        testList = daoi.findItemsLike("Milk");
        List<String> milkNames = new ArrayList<String>();
        milkNames.add("Gallon Whole Milk");
        milkNames.add("Gallon Skim Milk");

        foundCount = 0;
        for (Item i:testList)
        {
            String itemName = i.getName();
            for (String milkName:milkNames)
            {
                if (milkName.equals(itemName))
                {
                    foundCount++;
                }
            }
        }

        assertEquals(milkNames.size(), foundCount);

    }


    @Test
    /**
     * Test that creating a new item stores it in the database.
     */
    public void testCreateNewItem() {

        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        String itemName = "Test Item " + System.currentTimeMillis();
        List<ItemType> testItemTypeList = daoi.getAllItemTypes();
        Item item = daoi.createNewItem(itemName, testItemTypeList.get(0).getId());

        String itemName2 = "Test Item 2 " + System.currentTimeMillis();
        Item item2 = daoi.createNewItem(itemName2, testItemTypeList.get(1).getId());

        String itemName3 = "Test Item 3 " + System.currentTimeMillis();
        Item item3 = daoi.createNewItem(itemName3, testItemTypeList.get(2).getId());

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

        itemsByType = daoi.getItemsByItemType(testItemTypeList.get(1));

        itemFound = false;
        for (Item i:itemsByType)
        {
            if (i.getName().equals(itemName2))
            {
                itemFound = true;
            }

        }

        assertTrue(itemFound);

        itemsByType = daoi.getItemsByItemType(testItemTypeList.get(2));

        itemFound = false;
        for (Item i:itemsByType)
        {
            if (i.getName().equals(itemName3))
            {
                itemFound = true;
            }

        }

        assertTrue(itemFound);

    }

}
