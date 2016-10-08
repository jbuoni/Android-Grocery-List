# Use Case Model

*This is the template for your use case model. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author**: Zack Myrick

## 1 Use Case Diagram

###[Use Case Diagram](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team47/blob/master/Images/UseCaseDiagram.png)
![alt text](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team47/blob/master/Images/UseCaseDiagram.png "Use Case Diagram")

## 2 Use Case Descriptions

1) Login
	-Requirements: This use case must allow the user to access the application and its functionalities.
	-Pre Conditions: User must initialize the application by tapping the appropriate widget on the device screen. 
	-Post Conditions: User is able to access the application.
	-Scenario(s): The user would like to see what items are on a particular grocery list. They login to the application and go to the designated list.

2) Logout
	-Requirements: This use case must allow the user to log out of the application once they've used it for their purpose.
	-Pre Conditions:
	-Post Conditions:
	-Scenario(s): The user has viewed the items on a particular list. They access the information they are looking for and decide to log out of the application.

3) Create Grocery List
	-Requirements: This must allow the user to create a new grocery list template provided by the application
	-Pre Conditions: The application must be logged into the application.
	-Post Conditions: User has created a grocery list and it is stored within the application.
	-Scenario(s): The user decides that they want to have a grocery list they can access during their next trip to the grocery store. The opens the application and makes a new list for this purpose.

4) Delete Grocery List
	-Requirements: This use case must allow the user to delete a particular grocery list.
	-Pre Conditions: The user must be logged into the application. The specific list must already be created and stored within the application.
	-Post Conditions: The designated list is deleted from the application.
	-Scenario(s): The user goes to a grocery store and opens up the application. The user opens a grocery list while grocery shopping. After checking out of the grocery store, the user deletes the list from the application once the user no longer has a use for it.

5) Select Grocery List
	-Requirements:  This use case must allow the user to view a pre-existing grocery list that is stored within the application.
	-Pre Conditions: The user must be logged into the application. The specific list must already be created and stored within the application.
	-Post Conditions: The user is able to view the designated grocery list.
	-Scenario(s): A user goes into a grocery store. While shopping the user would like to know what items they should/need to buy. The user opens up the application, logs in, and selects a grocery list of choice.

6) Update Grocery List
	-Requirements: This use case must allow the user to make changes to his or her pre-existing list.
	-Pre Conditions: The user must be logged into the application. The specific list must already be created and stored within the application.
	-Post Conditions: Items, the number of items in a list, item quantities, or the name of a grocery list is updated by the user. 
	-Scenario(s): A user creates a grocery list and then closes the application. While watching TV, the user remembers that they forgot to add milk to their grocery store. The user goes back into the application, selects the particular list they need to update, searches for the particular item, and adds milk to their grocery list.

7) Search Item By Type
	-Requirements: This use case must allow the user to be able to search for a particular item by it's item type/category.
	-Pre Conditions: The user must be logged into the application.
	-Post Conditions: The user is able to find the item they had in mind.
	-Scenario(s): A user wants to know what type of cheese to buy for their lasagna. The user opens the application and uses the search item by type functionality. The user is able to look at a list of categories, and taps on dairy. The user is able to locate various cheeses available and selects the appropriate one.

8) Search Item By Name
	-Requirements: This use case must allow the user to be able to search for a particular item by it's name.
	-Pre Conditions: The user must be logged into the application.
	-Post Conditions: The user is able to find the item they had in mind.
	-Scenario(s): A user want's to find a particular toothpaste made by Colgate. The user opens the application and uses the search item by name functionality. The user types in Colgate into the search menu and a list of matching items appear. The user is able to identify the particular Colgate Extreme White product that they were looking for.

9) Check Item
	-Requirements: This use case must allow the user to checkoff particular items on a designated grocery list.
	-Pre Conditions: The user must be logged into the application. The user must have selected a list from a collection of grocery lists. The user must be able to view the items associated/displayed on the selected grocery list.
	-Post Conditions: The particular item(s) in the selected grocery list are checked off.
	-Scenario(s): The user goes to a grocery store and pulls out the application. The user views the list of their choice and views the items on it. After picking up an item from the store, the application lets the user check it off the specified list.
