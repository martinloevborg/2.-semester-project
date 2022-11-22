package domain;

import domain.model.Person;

/**
 * Extends the AbstractController to provide functionally to work with persons.
 * Uses the default implementation of the abstract controller.
 */
public class PersonController extends AbstractController<Integer, Person> {
    /**
     * Reloads the data and updates the map to display persons.
     */
    @Override
    protected void reloadData() {
        super.reloadData();
        // Specifies the data to be used by this instance.
        super.map = super.getDataContext().getPersons();
    }
}


