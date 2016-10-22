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
| Create Grocery List   | 1. Open Application                         | 1. Application Opens                                         |                |              |
|                       | 2. Press "New List"                         | 2. New Window Opened                                         |                |              |
|                       | 3. Name list and save                       | 3. New list is saved                                         |                |              |
| Switch Grocery List   | 1. Open Application                         | 1. Application Opens                                         |                |              |
|                       | 2. Open a previously made list              | 2. Window with list chosen opens.                            |                |              |
|                       | 3. Switch from current list to another list | 3. Window with the new chosen list opens.                    |                |              |
| Add Item To List      | 1. Open Application                         | 1. Application Opens                                         |                |              |
|                       | 2. Open a previously made list              | 2. Window with list chosen opens.                            |                |              |
|                       | 3. Press "add item"                         | 3. window with search method opens.                          |                |              |
|                       | 4. Search by type or name                   | 4. items of that type or name open up                        |                |              |
|                       | 5. Press on item of interest                | 5. Window asking quantity is pulled up                       |                |              |
|                       | 6. Enter Quantity of item needed            | 6. New item with correct quantity is now saved in your list. |                |              |
| Remove Item From List | 1. Open Application                         | 1. Application Opens                                         |                |              |
|                       | 2. Open a previously made list              | 2. Window with list chosen opens.                            |                |              |
|                       | 3. Press "X" next to item of interest       | 3. Item of interest is removed from list.                    |                |              |
| Check Item Off List   | 1. Open Application                         | 1. Application Opens                                         |                |              |
|                       | 2. Open a previously made list              | 2. Window with list chosen opens.                            |                |              |
|                       | 3. Swipe right over item to be checked off  | 3. Item is checked off                                       |                |              |
| test1CreateGroceryList| 1. Create two grocery lists using DAOI.createList| 1. Lists are created in the database and the id of the lists is returned.    |     As expected           |      P        |
|                       | 2. Load the two created lists.              | 2. The lists are loaded from the database.        |                |              |
|                       | 3. Check the names of the loaded lists.     | 3. The names match the names used in the createList function.                                       |                |              |
| test2AddItemToList| 1. Add two items to the two new grocery lists using DAOI.addItemToList.  | 1. Items are added to the database and associated with the grocery list.    |     As expected           |      P        |
|                       | 2. Reload the two grocery lists.              | 2. The lists are loaded from the database.        |                |              |
|                       | 3. Check the items in the grocery lists.     | 3. The items match the items added in step 1.                                       |                |              |
| test3GetAllLists| 1. Run DAOI.getAllLists.  | 1. All of the grocery lists in the database are returned.    |     As expected           |      P        |
|                       | 2. Check that the lists created in test1CreateGroceryList and test2AddItemToList are in the returned List              | 2. The added grocery lists are in the List.        |                |              |
| test4TestUpdateListName| 1. Change the name of one of the lists from test1CreateGroceryList.   | 1. The name of the grocery list is updated in the database.    |     As expected           |      P        |
|                       | 2. Reload the updated grocery list and check its name              | 2. The list has the updated name.        |                |              |
| test5TestDeleteItemFromList| 1. Delete an item from one of the lists added in test2AddItemToList with DAOI.dleteItemFromList. | 1. The list item is delete from the database.    |     As expected           |      P        |
|                       | 2. Reload the grocery lists and see if it contains the list item.    | 2. It doesn't contain the list item.        |                |              |
| test6TestToggleListItemIsChecked| 1. Use DAO.toggleListItemIsChecked to change a listItem from unchecked to checked. | 1. The list item's isChecked value is set to checked in the database.    |     As expected           |      P        |
|                       | 2. Reload the grocery lists and see if the list item is set to checked.    | 2. The list item is set to checked      |                |              |
| test7DeleteList| 1. Use DAO.deleteList to delete the lists created in test1CreateGroceryList.  | 1. The grocery lists are deleted from the database.    |     As expected           |      P        |
|                       | 2. Try to reload the grocery lists using DAOI.loadList.  | 2. The loadList function returns null.      |                |              |
| testGetItemsByItemType| 1. Call DAOI.getItemsByItemType for the ItemType Beer.| 1. All the items with the item type beer are returned.    |     As expected           |      P        |
|                       | 2. Loop through the List of items and check that all of the initial Beer items are in the list.              | 2. The items are in the List.        |                |              |
| testGetAllItemTypes| 1. Call DAOI.getAllItemTypes.| 1. All the ItemTypes in the database are returned.    |     As expected           |      P        |
|                       | 2. Loop through the List of ItemTypes and check that all of the initialized ItemTypes are in the list.              | 2. The ItemTypes are in the List.        |                |              |
| testFindItemsLike| 1. Call DAOI.findItemsLike with the search string "12 Pack".| 1. All the items containing the string "12 Pack" in their name are returned from the database.    |     As expected           |      P        |
|                       | 2. Loop through the List of Items and check that all of the initialized Items that contain "12 Pack" in their name are in the list.  | 2. The three expected Items are in the List.        |                |              |
| testCreateNewItem| 1. Add a new item using DAOI.createNewItem. | 1. The new item is inserted into the database.    |     As expected           |      P        |
|                       | 2. Call DAOI.getItemsByItemType with the ItemType used to create the new item.    | 2. The returned List of items contains the new item.        |                |              |
