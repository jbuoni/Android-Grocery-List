# Test Plan
**Author**: Team 47

## 1 Testing Strategy

### 1.1 Overall strategy

The overall strategy we will take will be to perform unit, integration and system testing. We will test each completed module completely as a unit, will test and see it's integration results when joining connecting to other units, and will test the overall system as well in the end to ensure nothing has been broken. We will build a finite state machine to model our system, and allow tests to be created in a less confusing way. We will then use the category partition test method, followed by alpha and beta tests to help eliminate as many bugs as possible.

For regression testing we will do a integration test for all units directly connected to the unit that underwent a change.

Some activites that will be performed as part of are testing process and who will preform them are as follows:
-Finite State Machine Model - Thomas Von Heill

-Category-Partition test method - Thomas Von Heill

-White box testing (branch coverage) - All Team Members

-Alpha and Beta testing - All Team Members

### 1.2 Test Selection

Unit testing will be done with white-box testing techniques.  Unit tests will be done by the designer of the unit, so knowledge of how the code is designed will be used when creating the tests.

System tests will be done using black-box testing techniques.  These tests will be functional and acceptance tests to verify that the system meets the requirements for the project.

### 1.3 Adequacy Criterion

Assessing the quality of our test cases will come mainly from ensuring we go all paths in our program, aiming for 90% branch coverage. Alpha and beta tests will later confirm or unfound our original assesment of our test case quality. 

### 1.4 Bug Tracking

Bugs and enhancements will be handled through an e-mail address, and tracked in an excel spreadsheet. We will have different levels of priority for different bugs, and all will be handled accordingly.

### 1.5 Technology

Most unit testing will be done with JUnit.  DAO testing will be done with AndroidJUnit4, because a database will have to be created on an emulated device.

Espresso and Sikuli tests were created to automate GUI testing, but this technology was abandoned in favor of manual GUI testing.  Espresso and Sikuli tests frequently crashed for reasons not related to the GLM program.

## 2 Test Cases

| Name                  | Test Step                                   | Expected Results                                             | Actual Results | Result (P/F) |
|-----------------------|---------------------------------------------|--------------------------------------------------------------|----------------|--------------|
| Create Grocery List (Manual)   | 1. Open Application                         | 1. Application Opens                                         |     As expected           |      P        |
|                       | 2. Press "+" button to create a new list                         | 2. Create Grocery List Overlay Appears                                         |                |              |
|                       | 3. Type in a list name and click done.                       | 3. New list is saved and appended to the lists of grocery lists.                                        |                |              |
| Switch Grocery List (Manual)  | 1. Open Application                         | 1. Application Opens                                        |     As expected           |      P        |
|                       | 2. Open the list made in the "Create Grocery List" test by clicking the View/Edit button next to it.              | 2. List management activity opens with the new list with no items in it.                            |                |              |
| Add Item To List Using Search (Manual)      | 1. Open Application                         | 1. Application Opens                                         |     As expected           |      P        |
|                       | 2. Open a the previously made list              | 2. Window with list chosen opens.                            |                |              |
|                       | 3. Press the search button.                         | 3. window with search method opens.                          |                |              |
|                       | 4. Search for "Lucky"                  | 4. Lucky Charms is shown below.                        |                |              |
|                       | 5. Click Lucky Charms                | 5. The List Management page is opened with Lucky Charms as the only item.  |                |              |
| Add Item To List By Item Type (Manual)      | 1. Open Application                         | 1. Application Opens                                         |     As expected           |      P        |
|                       | 2. Open a the previously made list              | 2. Window with list chosen opens.                            |                |              |
|                       | 3. Press the "+" button.                         | 3. window Item Type, Item, and Quantity is shown.                          |                |              |
|                       | 4. Select "Cereal" as the Item Type.                  | 4. Lucky Charms is shown in the Item list.                        |                |              |
|                       | 5. Select Frosted Flakes as the Item.                | 5. Frosted flakes is displayed as the item  |                |              |
|                       | 6. Enter "1" in the quantity text box.                | 6. "1" is shown in the quantity text box.  |                |              |
|                       | 7. Click the plus button.                | 7. Frosted flakes is displayed in the Grocery list with a quantity of "1".  |                |              |
| Add a New Item To List (Manual)      | 1. Open Application                         | 1. Application Opens                                         |     As expected           |      P        |
|                       | 2. Open a the previously made list              | 2. Window with list chosen opens.                            |                |              |
|                       | 3. Press the "+" button.                         | 3. window Item Type, Item, and Quantity is shown.                          |                |              |
|                       | 4. Click the "Add New Item Button".                  | 4. The Add Item Activity is displayed.                        |                |              |
|                       | 5. Enter a name, item type, and quantity and click the "+" button.                | 5. The new item is added to the grocery list.  |                |              |
| Remove Item From List (Manual) | 1. Open Application                         | 1. Application Opens                                        |     As expected           |      P        |
|                       | 2. Open a previously made list              | 2. Window with list chosen opens.                            |                |              |
|                       | 3. Press Trash Icon next to item of interest       | 3. Item of interest is removed from list.                    |                |              |
| Check Item Off List (Manual)   | 1. Open Application                         | 1. Application Opens                                         |     As expected           |      P        |
|                       | 2. Open a previously made list              | 2. Window with list chosen opens.                            |                |              |
|                       | 3. Swipe right over item to be checked off  | 3. Item is checked off                                       |                |              |
| Uncheck All Items (Manual)   | 1. Open Application                         | 1. Application Opens                                         |     As expected           |      P        |
|                       | 2. Open a previously made list              | 2. Window with list chosen opens.                            |                |              |
|                       | 3. Click the button in the upper right and select "Uncheck All"  | 3. All the items are unchecked.                                       |                |              |
| Rename A Lists (Manual)   | 1. Open Application                         | 1. Application Opens                                         |     As expected           |      P        |
|                       | 2. Click the name of a previously save list.              | 2. Edit List Name Overlay Appears.                            |                |              |
|                       | 3. Enter a new name in the "List Name" field and click done.  | 3. The list name is updated on the main activity screen.                                       |                |              |
| Delete A List (Manual)   | 1. Open Application                         | 1. Application Opens                                         |     As expected           |      P        |
|                       | 2. Click the name of a previously save list.              | 2. Edit List Name Overlay Appears.                            |                |              |
|                       | 3. Click the Delete List button.  | 3. The list is removed form the main activity screen.                                       |                |              |
| DAOTest.test1CreateGroceryList| 1. Create two grocery lists using DAOI.createList| 1. Lists are created in the database and the id of the lists is returned.    |     As expected           |      P        |
|                       | 2. Load the two created lists.              | 2. The lists are loaded from the database.        |                |              |
|                       | 3. Check the names of the loaded lists.     | 3. The names match the names used in the createList function.                                       |                |              |
| DAOTest.test2AddItemToList| 1. Add two items to the two new grocery lists using DAOI.addItemToList.  | 1. Items are added to the database and associated with the grocery list.    |     As expected           |      P        |
|                       | 2. Reload the two grocery lists.              | 2. The lists are loaded from the database.        |                |              |
|                       | 3. Check the items in the grocery lists.     | 3. The items match the items added in step 1.                                       |                |              |
| DAOTest.test3GetAllLists| 1. Run DAOI.getAllLists.  | 1. All of the grocery lists in the database are returned.    |     As expected           |      P        |
|                       | 2. Check that the lists created in test1CreateGroceryList and test2AddItemToList are in the returned List              | 2. The added grocery lists are in the List.        |                |              |
| DAOTest.test4TestUpdateListName| 1. Change the name of one of the lists from test1CreateGroceryList.   | 1. The name of the grocery list is updated in the database.    |     As expected           |      P        |
|                       | 2. Reload the updated grocery list and check its name              | 2. The list has the updated name.        |                |              |
| DAOTest.test5TestDeleteItemFromList| 1. Delete an item from one of the lists added in test2AddItemToList with DAOI.deleteItemFromList. | 1. The list item is delete from the database.    |     As expected           |      P        |
|                       | 2. Reload the grocery lists and see if it contains the list item.    | 2. It doesn't contain the list item.        |                |              |
| DAOTest.test6TestToggleListItemIsChecked| 1. Use DAO.toggleListItemIsChecked to change a listItem from unchecked to checked. | 1. The list item's isChecked value is set to checked in the database.    |     As expected           |      P        |
|                       | 2. Reload the grocery lists and see if the list item is set to checked.    | 2. The list item is set to checked      |                |              |
| DAOTest.test7DeleteList| 1. Use DAO.deleteList to delete the lists created in test1CreateGroceryList.  | 1. The grocery lists are deleted from the database.    |     As expected           |      P        |
|                       | 2. Try to reload the grocery lists using DAOI.loadList.  | 2. The loadList function returns null.      |                |              |
| DAOTest.testGetItemsByItemType| 1. Call DAOI.getItemsByItemType for the ItemType Beer.| 1. All the items with the item type beer are returned.    |     As expected           |      P        |
|                       | 2. Loop through the List of items and check that all of the initial Beer items are in the list.              | 2. The items are in the List.        |                |              |
| DAOTest.testGetAllItemTypes| 1. Call DAOI.getAllItemTypes.| 1. All the ItemTypes in the database are returned.    |     As expected           |      P        |
|                       | 2. Loop through the List of ItemTypes and check that all of the initialized ItemTypes are in the list.              | 2. The ItemTypes are in the List.        |                |              |
| DAOTest.testFindItemsLike| 1. Call DAOI.findItemsLike with the search string "12 Pack".| 1. All the items containing the string "12 Pack" in their name are returned from the database.    |     As expected           |      P        |
|                       | 2. Loop through the List of Items and check that all of the initialized Items that contain "12 Pack" in their name are in the list.  | 2. The three expected Items are in the List.        |                |              |
| DAOTest.testCreateNewItem| 1. Add a new item using DAOI.createNewItem. | 1. The new item is inserted into the database.    |     As expected           |      P        |
|                       | 2. Call DAOI.getItemsByItemType with the ItemType used to create the new item.    | 2. The returned List of items contains the new item.        |                |              |
| GroceryListUnitTest.uncheckAllListItems_isCorrect| 1. Call uncheckAllListItems. | 1. All of the List Items are set to unchecked.    |     As expected           |      P        |
|                       | 2. Loop through each item and check if it is checked.    | 2. None of the items are checked.        |                |              |
| GroceryListUnitTest.sortListByItemType_isCorrect| 1. Call sortListByItemType. | 1. The list of list items is sorted by item type.    |     As expected           |      P        |
|                       | 2. Check that the list items are in the expected order.    | 2. The list items are in the expected order.        |                |              |