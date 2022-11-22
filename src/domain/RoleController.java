package domain;

import domain.model.Role;

/**
 * Extends the AbstractController to provide functionally to work with roles.
 * Uses the default implementation of the abstract controller.
 */
public class RoleController extends AbstractController<Integer, Role> {

    /**
     * Reloads the data and updates the map to display roles.
     */
    @Override
    protected void reloadData() {
        super.reloadData();

        super.map = super.getDataContext().getRoles();
    }
}
