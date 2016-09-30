Zack A. Myrick
6300Falll16zmyrick3
Design Information Document

Requirements

1.	A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).
	-To illustrate this connection, I added to the design a class for grocery list, grocery store, user, and for the application. I added items as an attribute to the grocery list class; and I added the operations addItemsToList, deleteItemsFromList, and changeQuantity to the application as functionalities for the user.

2.	The application must contain a database (DB) of items and corresponding item types.
	-Since the application is referencing the database as a separate entity, I included database as a class with the attributes itemType and itemName.

3.	Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.
	-This requirement is met by referencing the itemType and itemName  from the database class and returning it to be adding it to the grocery list via the addItemToList operation in the Application class. The Application allows the User to specifiy a quantity for a specific item via the specifyQuantity operation.

4.	Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.
	- This requirement is demonstrated by the verifyIntededItem operation in the Application class. If the Boolean value of this operation returns false, the createNewItem method is then called to prompt the User to create new item and store it into the database.

5.	Lists must be saved automatically and immediately after they are modified.
	-To acknowledge this requirement, I added the dynamicItemSave functionality to the Grocery List class that would ideally be able to detect any changes made to the list and dynamically save them as changes are made.


6.	Users must be able to check off items in a list (without deleting them).
	- Since items are required to be able to be checked or unchecked, I added a checkbox and check-off mark attribute to the groceryList class. In order for users to be able to check off items in a list, I included a checkItemInList operation in the Application class that gives the User the ability to check off a select item in the designated grocery list.

7.	Users must also be able to clear all the check-off marks in a list at once.
	-This functionality is provided to the user via the clearAllCheckMarks operation in the Application class that references all checkboxes that are marked in the Grocery List class.

8.	Check-off marks for a list are persistent and must also be saved immediately.
	-This capability is reflected in the design by the dynamicCheckSave operation in the Database class, which acknowledges the status of check boxes in the grocery list.

9.	The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).
	- This functionality is represented by the showItemByType operation of the Application class that references the itemType attribute of the Database class.

10.	The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.
	- This ability is provided to the user via the createList, renameList, selectList, and deleteList operations in the Application class. These operations used by the Application reference the data stored in the Database class found in the savedLists attribute that is made by the storeList operation in the Database class.

************
Rationale and Assumptions

For each user, it can be assumed that each user has a distinct user name and password that allows them to login to the system. Because these attributes are distinct to the user, they are listed in the user class.

For a particular grocery store, it can be assumed that a store will have a distinct name and address as attributes.

Since items in a grocery list can either be checked or unchecked, I included a checkbox attribute for the grocery list class for each item in a list.

There is an association relationship between the user and grocery list since the user can make multiple lists for his/her purposes.

The application has a dependency on the User and GroceryList because if any of the attributes of the classes change then it will have an impact on the operations of the application class. The application class also has a dependency to the database because that is where it retrievies some information in order to display to the user.

A grocery list can contain multiple items, hence there is an aggregation relationship between the Item and GroceryList class.

A database can hold the information of multiple items, hence there is an aggregation relationship between the Item and Database class.

One instance of a GroceryStore object can have multiple instances of an item object, and an instance of an Item object can be found at more than one GroceryStore object. Therefore there is an association relationship between these two classes.




