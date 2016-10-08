# Project Plan
**Author**: Jason Buoni and Daniel Bansch, Team 47

## 1 Introduction

The Grocery List Manager application allows users to create and track grocery items they need to purchase during their next shopping trip, right on their phone. 

## 2 Process Description

### Create Grocery List
**Description**
The application allows a user to create one, or many grocery lists. The user can create a list with the name of their choice, and add items or remove items to that list.
Upon editing the list, the list itself will be saved. 

**Enterance Criteria**
None

**Exit Criteria**
The output of this activity is a saved grocery list.

### Switch Grocery List
**Description**
Users can switch between grocery lists. 

**Enterance Criteria**
Create Grocery List

**Exit Criteria**
The output of this is a the grocery list displayed on the screen is the list the user switched to. 

### Add Item To List
**Description**
Users can add an item to a grocery list. Items will be searchable by name, or type. Every item will have an associated type, and all items are stored in a database. 
If the item is not in the database, then the user can add the item, linked to a type, which will then be added to the database. User can specify the quantity of the item they 
need to purchase. 

**Enterance Criteria**
Create Grocery List

**Exit Criteria**
The output of this is an updated grocery list containing the item.

### Remove Item From List
**Description**
Users can remove items from grocery lists. Items that are removed will not be removed from the database. 

**Enterance Criteria**
Create Grocery List
Add Item To List

**Exit Criteria**
The output of this is a grocery list that no longer contains the item. 

### Check Item Off List
**Description**
The user checks off an item on their grocery list after they have added it to their cart. 

**Enterance Criteria**
Create Grocery List
Add Item To List

**Exit Criteria**
The output of this is an item that is checked off of the grocery list. 

## 3 Team 47

**Jason Buoni**
**Daniel Bansch**
**Zack Myrick**
**Tom Von Heill**
- Developer: Works on designing and developing the Grocery List application
- Test Engineer: Works on writing automated tests, and QA
- Project Manager:  In charge of planning the project, setting the scope of the project, and submitting assignments.


| Member   | Developer     |  Test Engineer | Project Manager |
|----------|:-------------:|:--------------:|:---------------:|
| Jason    |      X        |       X        |                 |
| Thomas   |      X        |       X        |                 |
| Daniel   |      X        |       X        |       X         |
| Zack     |      X        |       X        |                 |


