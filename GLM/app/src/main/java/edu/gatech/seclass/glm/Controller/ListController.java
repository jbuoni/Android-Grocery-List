package edu.gatech.seclass.glm.Controller;

import android.content.Context;

import edu.gatech.seclass.glm.DAO.DAO;

/**
 * Created by jbuoni on 10/12/16.
 */

public class ListController {

    private DAO dao;

    public ListController(Context context) {
        dao = new DAO(context);
    }

    public void createList(String name) {
        dao.createList(name);
    }


}
