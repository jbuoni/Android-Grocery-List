###[Design 1](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team47/blob/master/Design-Individual/dbansch3/design.pdf)
![alt text](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team47/blob/master/Images/daniel.png "Design 1")

####Discussion

####Pros
- The diagram was very detailed and well thought out. 
- The use of MCV clearly seperates the data, controller, and view classes
- Good object implementation. Seems to cover all requirements, but is not too overly complicated

####Cons 
- Could split out the DAO class into an interface, which seperate classes to the User, Item, List, and ItemType tables
- Though the design as a whole is good, the document itself is hard to follow. Could be cleaned up
- Could investigate splitting the controller class
- Could investigate some of the relationships, though at the moment, they seem to be sufficient

###[Design 2](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team47/blob/master/Design-Individual/jbuoni3/design.pdf)
![alt text](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team47/blob/master/Images/jason.png "Design 2")
 
####Discussion
In this design, we will use the MVC design pattern to split the application view, the controllers, and the data models apart. 
The DataTableWrapper will be the interface in which all other data table wrappers are based off of. The DataBaseUtility will
be responsible for reading and writing to the database through these wrappers. Each portion of the shopping list (Lists, Items,
and the User doing the shopping) will be represented as an individual class. 

The goal of this design was to make the approach as simple as possible. We sacrifie performance capabilities for simplicity.
The user will handle all changes to the lists, items in the list, and the item status. The DataBaseUtility will handle all 
communication with the database.

####Pros
- The Item class is simplistic. There is not much logic needed to implement it and it does not handle too much responsiblity
- The use of MVC makes clear the seperation of data objects, the classes responsible for manipulation of data, and the view
- The use of the interface / child classes seems like a good idea, and helps break out the data items while creating a template

####Cons 
- Some aspects seem too simplistic. They may cause more issues when attempting to implement. For example, navigating through
all items and saving all items at each change can be unideal
- There is no ItemType class, and instead the type is combined with the item, we may want to seperate the concerns
- The idea that the User class has the control over the Item and ItemLists may become more of an annoyance and cause issues

###[Design 3](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team47/blob/master/Design-Individual/tlvh3/design.pdf)
![alt text](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team47/blob/master/Images/thomas.png "Design 3")
 
####Discussion

####Pros
- Simplistic and too the point. Does not overly complicate things
- The data appears to be saved on every change. This is a good idea

####Cons 
- The UML may not be correct
- We may want to include some of the database access classes

###[Design 4](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team47/blob/master/Design-Individual/zmyrick3/design.pdf)
![alt text](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team47/blob/master/Images/zach.png "Design 4")

####Discussion

####Pros
- Simplistic database design. Like the use of the dynamic updates. Removes some excess functions
- Simplistic design. Not overly complicated

####Cons 
- The Item, GroceryList, and User classes are also views. May want to split them apart
- There is a risk that the application class can turn into a monolith
- May want to review some of the relationships

###[Team Design](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team47/blob/master/Design-Individual/dbansch3/design.pdf)
![alt text](https://github.gatech.edu/gt-omscs-se-2016fall/6300Fall16Team47/blob/master/Images/daniel.png "Team Design")

####Discussion

