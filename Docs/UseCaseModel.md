# Use Case Model

*This is the template for your use case model. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author**: Zack Myrick

## 1 Use Case Diagram

###[Use Case Diagram](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team47/blob/master/Images/CaseDiagram.png)
![alt text](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team47/blob/master/Images/CaseDiagram.png "Use Case Diagram")

## 2 Use Case Descriptions

#### Create Grocery List

	-Requirements: This must allow the user to create a new grocery list template provided by the application
	-Pre Conditions: None
	-Post Conditions: User has created a grocery list and it is stored within the application.
	-Scenario(s): The user decides that they want to have a grocery list they can access during their next trip to the grocery
	store. The opens the application and makes a new list for this purpose.
	
	- Steps: 
		1. Users clicks 'Add List' button
		2. User inputs list name in dialog
		3. User clicks 'Add List' button in dialog
		4. Application refreshes activity to display list. 

#### Delete Grocery List

	-Requirements: This use case must allow the user to delete a particular grocery list.
	-Pre Conditions: User must have already completed Create Grocery List use case.
	-Post Conditions: The designated list is deleted from the application. Main activity is updated to no longer contain list. 
	-Scenario(s): The user goes to a grocery store and opens up the application. The user opens a grocery list while grocery shopping. After checking out of the grocery store, the user deletes the list from the application once the user no longer has a use for it.


#### Select Grocery List

	-Requirements:  This use case must allow the user to view a pre-existing grocery list that is stored within the application.
	-Pre Conditions: User must have already completed Create Grocery List use case.
	-Post Conditions: The user is able to view the designated grocery list.
	-Scenario(s): A user goes into a grocery store. While shopping the user would like to know what items they should/need to buy.
	The user opens up the application, logs in, and selects a grocery list of choice.
	- Steps: 
		1. User clicks 'View/Edit' button
		2. Application navigates to GroceryList activity and displays list data for the selected list.

#### Add Item to List

	-Requirements: This use case must allow the user to make changes to his or her pre-existing list.
	-Pre Conditions: User must have already completed Create Grocery List use case. 
		- User must have already completed Select Grocery List use case.
	-Post Conditions: Additional item is added to the grocery list. 
	-Scenario(s): A user creates a grocery list and then closes the application. While watching TV, the user remembers that they
	forgot to add milk to their grocery store. The user goes back into the application, selects the particular list they need to 
	update, searches for the particular item, and adds milk to their grocery list.
	- Steps: 
		1. User clicks 'Add Item' button
		2. User inputs item information. 
			a. User selects item type.
			b. User selects item. 
			c. User selects item quantity.
		3. User clicks add item button.
		4. Application saves item in database and links it to grocery list and closes dialog.

#### Search for Item

	-Requirements: This use case must allow the user to be able to search for a particular item by it's name.
	-Pre Conditions: User must have already completed Create Grocery List use case. 
		- User must have already completed Select Grocery List use case.
	-Post Conditions: The user is able to find the item they had in mind.
	-Scenario(s): A user want's to find a particular toothpaste made by Colgate. The user opens the application and uses the
	search item by name functionality. The user types in Colgate into the search menu and a list of matching items appear. The 
	user is able to identify the particular Colgate Extreme White product that they were looking for.
	-Steps:
		1. User clicks on spyglass icon in top right of app.
		2. User begins searching for item name.
		3. User clicks on item they would like to add to list.

#### Check / Uncheck Item
	
	-Requirements: This use case must allow the user to checkoff particular items on a designated grocery list.
	-Pre Conditions: User must have already completed Create Grocery List use case. 
		- User must have already completed Select Grocery List use case.
		- User must have already completed Add Item use case.
	-Post Conditions: The particular item(s) in the selected grocery list are checked off.
	-Scenario(s): The user goes to a grocery store and pulls out the application. The user views the list of their choice and
	views the items on it. After picking up an item from the store, the application lets the user check it off the specified list.
	- Steps: 
		1. User clicks checkbox next to item they which to check / uncheck
		2. Application updates database with check value

#### Uncheck All Items
 
	-Requirements: This use case must allow the user to checkoff particular items on a designated grocery list.
	-Pre Conditions: User must have already completed Create Grocery List use case. 
		- User must have already completed Select Grocery List use case.
		- User must have already completed Add Item use case.
		- User must have already completed the Check / Uncheck item use case.
	-Post Conditions: All items are unchecked and the database is updated for those items. 
	-Scenario(s): The user goes to a grocery store and pulls out the application. The user views the list of their choice and
	views the items on it. After picking up an item from the store, the application lets the user check it off the specified list.
	- Steps: 
		1. User clicks on three dots in top right of app.
		2. User selects 'Uncheck All' from list.
		3. Application unchecks all items in list. 
		4. Application updates database with unchecked values for all items.

#### Update Item Quantity
	
	-Requirements: This use case must allow the user to checkoff particular items on a designated grocery list.
	-Pre Conditions: User must have already completed Create Grocery List use case. 
		- User must have already completed Select Grocery List use case.
		- User must have already completed Add Item use case.
		- User must have already completed the Check / Uncheck item use case.
	-Post Conditions: Quantity for specific item is updated in the database and application 
	-Scenario(s): Before going to a grocery store, the user notices they need more of an item than expected. The user updates the
	item of their choice with the new quantity, after selecting the appropriate list. After updating the quantity, the application
	displays the new updated quantity associated with the item.
	- Steps: 
		1. User clicks in space to the right of the item name.
		2. Dialog prompts user to update quantity.
		3. User updates quantity 
		4. User clicks 'next' button.
		5. Application updates quantity and closes dialog. 

#### Edit Grocery List Name:
	
	-Requirements: This use case must allow the user to change the name of a pre-exisiting grocery list.
	-Pre Conditions: User must have already completed grocery list use case.
	-Post Conditions: The name of the grocery list will be updated in the database and the application.
	-Scenario(s): Before going to the grocery store the user realizes that the name of the gorcery list no longer makes sense. The
	user updated the list name that needs to be dupdated to make sense. The application then updates the list name accordingly.
	-Steps:
		1. User clicks on the Grocery List name that needs to be updated.
		2. User types the new grocery list name in and clicks 'done'.
		3. Application updates the grocery list name accordingly.

#### Remove Item from List:

	-Requirements: This use case must allow the user to remove an item from the grocery list.
	-Pre Conditions: User must have already completed grocery list use case.
	-Post Conditions: The item chosen will be deleted from the list.
	-Scenario(s): User no longer wants item to be included from list that once was needed. The user will easily remove the item
	and update the list.
	-Steps:
		1. User clicks on the trashcan to the right of the item of interest.
		2. Item is deleted off of the grocery list.
		3. Application updates the grocery list accordingly.

