package edu.gatech.seclass.glm;

import android.content.Context;
import android.test.mock.MockContext;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.glm.Controller.ListController;
import edu.gatech.seclass.glm.Controller.ListMgmtController;
import edu.gatech.seclass.glm.DAO.DAO;
import edu.gatech.seclass.glm.DAO.DAOI;
import edu.gatech.seclass.glm.Model.GroceryList;
import edu.gatech.seclass.glm.Model.Item;
import edu.gatech.seclass.glm.Model.ItemType;
import edu.gatech.seclass.glm.Model.ListItem;

import static org.junit.Assert.assertEquals;


/**
 * ListMgmtController unit tests.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * Created by jbuoni on 10/11/16.
 */
public class GroceryListControllerUnitTest {

    private ListController controller;
    private ListMgmtController lController;
    private ListItem item1, item2, item3;
    private List<ListItem> list;

    @Before
    public void init() {
        Context mContext = new MockContext();
        controller = new ListController(mContext);
        lController = new ListMgmtController(mContext);

        controller.createList("Test List");

        //TODO this may not always be 1
        lController.updateCurrentList(1);

        list = new ArrayList<ListItem>();

        item1 = new ListItem(1, new Item(1, "Test Item 1", new ItemType(1, "Test Type 1")), false, 1);
        item2 = new ListItem(2, new Item(2, "Test Item 2", new ItemType(2, "Test Type 2")), false, 1);
        item3 = new ListItem(3, new Item(2, "Test Item 3", new ItemType(3, "Test Type 3")), false, 1);


    }

}

