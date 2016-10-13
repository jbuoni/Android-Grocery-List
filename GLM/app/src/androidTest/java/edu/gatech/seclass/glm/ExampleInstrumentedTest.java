package edu.gatech.seclass.glm;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.mock.MockContext;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.seclass.glm.DAO.DAO;
import edu.gatech.seclass.glm.DAO.DAOI;
import edu.gatech.seclass.glm.Model.GroceryList;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("edu.gatech.seclass.glm", appContext.getPackageName());
    }

    @Test
    /**
     * Test that creating a list stores it in the database.
     */
    public void testCreateGroceryList() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DAOI daoi = new DAO(appContext);
        String listName = "Test List";
        GroceryList testList = daoi.createList(listName);
        GroceryList testListFromID = daoi.loadList(testList.getId());
        assertEquals(listName, testListFromID.getName());
    }
}
