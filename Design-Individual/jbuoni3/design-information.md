# Grocery List Application Design Information

1. The application contains a database corresponding with the User, List, and Item types. To realize this requirement I made the ItemDataTableWrapper, UserItemDataTableWrapper, ListDataTableWrapper, and UserDataTable wrapper, all of which are children of DataTableWrapper
	* To make the objects easier to work with, I added Item with attributes, name, id, type, quantity (and quantityUnit) and checked. This handles items associated with a list
	* To make the objects easier to work with, I added ItemList with attributes, listId, listName, and items
	* Items that are used for searching are accessed through ItemDataTableWrapper. Item class has defaults, which will not be returned through this row
2. Database utility is responsible for reading the data from the database.
	* Is a singleton
	* Satisfys requirements 1 - 8 and 10 as it stores information in different ways
		* Requirment 2 is satisfied with ItemDataTableWrapper, and the DataBaseUtility class that will access it. 
		* Other requirements to update list, items, save are satisfied, because changes will be stored in the database through their respective accessors and model classes
3. Changes in item or list data will be saved using User.save() function after every function
	* Allows for creating multiple lists, updating quantities, and saving checked values
4. The requirement that the UI must be intuitive and responsive, was not considered because it does not affect the design directly.
5. DataBaseUtility.addItemToItemsDatabase() will store new items (searched for by user) and their type to the Items database (requirement 4)
6. All ItemList and Item updates are done though the updateUserInfo funtion, which stores updates all changes to the User object (checking all boxes, updating quantity, adding list, etc.)
7. The requirment that the application must present the items in a list grouped by type was not considered because it does not affect the design correctly.