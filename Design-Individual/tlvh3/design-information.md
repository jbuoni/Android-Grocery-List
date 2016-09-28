# Design Information

1. We have a class "user" that can store a hash table of grocery list objects. Within each grocerylist we can add items, delete items, and change the quantity of the items (item is a class that makes up the grocery list).   
2. The database is not considered too closely here, but each object of class "item" contains a feild to hold "item name" and "item type" which correspond to how the database should work.   
3. You will be able to add items within the groceryList object.This could be done by first specifying type, and retrieving all items of that type in the database, then checking to see if an item of that type exists. once here you are asked how much of that item or how many of that item you would like to add. This would be contained in the addItem() method.
4. You can search for items in a database by name using the searchByName(). This will then return possible answers, and if none are the correct ones, ask you to add an atime to database using an addToDataBase() method.
5. All lists will be saved after being modified.
6. You can set the boolean "CheckOff" to true by using the checkoff() function in the item class.
7. You can clear all the checked off items in a list by using the clearCheckedOff() method contained in the grocery list class.
8. There is no way to "uncheck" an item.
9. getItemsofType() will be used on a grocery list to get items of a certain time within th egrocery list to be displayed. This will solve this requirement (will most likely be implemented through buttons).
10. User consists of multiple gorcer lists. You can add lists to the user. You can change the name within a list within the groceryList object. You can delete lists within the user object. You can also set the current list using the selectList() method in the user class.