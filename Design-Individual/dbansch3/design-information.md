# Assignment 5: Design Information
### Requirements

1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).
  * This is requirement is realized through the Controller and DAO class.  Any changes to the list will trigger functions in the Controller.  The Controller will extract the change from the View passed to it, and send it to the DAO to be saved.

2. The application must contain a database (DB) of items and corresponding item types.
  * This is realized with the Item and ItemType classes.  The DAO class connects to the database to get and save these entities.

3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.
  * This is requirement is handled by the Controller and DAO classes.  When the user clicks to add an item, the showAllItemTypes and showAllItemsByType functions are called in the Controller.  The Controller calls the DAO class to get the ItemTypes and Items from the database.

4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type.
  * This requirement is handled by the enterTextInSearch function of the Controller.  The Controller calls findItemsLike in the DAO class, which returns the search results.  The Controller displays them in the View.

5. Lists must be saved automatically and immediately after they are modified.
  * Changes to ItemLists trigger events in the Controller class.  The Controller class calls the DAO class to save the changes.

6. Users must be able to check off items in a list (without deleting them).
  * When an item is checked, the checkListItem function in the Controller class is triggered.  The Controller calls the DAO to save the changes.

7. Users must also be able to clear all the check-off marks in a list at once.
  * The uncheckAll function in the Controller clears all the checked list items.  This calls the DAO class to save the ItemList.

8. Check-off marks for a list are persistent and must also be saved immediately.
  * This functionality is discussed in requirements 6 and 7.

9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).
  * The ItemList function has a private function called sortList that is called whenever an item is added to the list.  The Controller initially displays the ItemList in the View in the selectList function.  The saveList function in the DAO class returns the ItemList when any changes are made, which is always sorted by ItemType.

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.
  * The Controller handles this functionality with the selectList, renameList, and deleteList functions.

11. The User Interface (UI) must be intuitive and responsive.
  * This requirement is not considered because it does not affect the design directly.

