package domain;

import domain.model.Privilege;
import domain.model.Production;

import java.util.Map;
import java.util.TreeMap;

/**
 * Extends the AbstractController to provide functionally to work with privileges.
 * Uses the default implementation of the abstract controller.
 */
public class PrivilegeController extends AbstractController<Integer, Privilege> {

    /**
     * Reloads the data and updates the map to display privileges.
     */
    @Override
    protected void reloadData() {
        super.reloadData();

        super.map = super.getDataContext().getPrivileges();
    }
}

