# Test Plan
**Author**: Team 47

## 1 Testing Strategy

### 1.1 Overall strategy

The overall strategy we will take will be to preform unit, integration and system testing. We will test each completed module completely as a unit, will test and see it's integration results when joining connecting to other units, and will test the overall system as well in the end to ensure nothing has been broken. We will build a finite state machine to model our system, and allow tests to be created in a less confusing way. We will then use the category partition test method, followed by alpha and beta tests to help eliminate as many bugs as possible.

For regression testing we will do a integration test for all units directly connected to the unit that underwent a change.

Some activites that will be preformed as part of are testing process and who will preform them are as follows:
-Finite State Machine Model - Thomas Von Heill
-Category-Partition test method - Thomas Von Heill
-White box testing (branch coverage) - Thomas Von Heill
-Alpha testing - All Team Members
-Beta testing - Team Members' freinds and family
### 1.2 Test Selection

We will use category-partition and branch coverage methods on all testing levels.

### 1.3 Adequacy Criterion

Assessing the quality of our test cases will come mainly from ensuring we go all paths in our program, aiming for 90% branch coverage. Alpha and beta tests will later confirm or unfound our original assesment of our test case quality. 

### 1.4 Bug Tracking

Bugs and enhancements will be handled through an e-mail address, and tracked in an excel spreadsheet. We will have different levels of priority for different bugs, and all will be handled accordingly.

### 1.5 Technology

At the moment we hope to look into using expresso, TSLGenerator, and Grep.

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
