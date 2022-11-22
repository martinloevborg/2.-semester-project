package domain;

import domain.model.Production;

import java.util.Map;
import java.util.TreeMap;

/**
 * Extends the AbstractController to provide functionally to work with productions.
 */
public class ProductionController extends AbstractController<Integer, Production> {
    /**
     * Reloads the data and updates the map to display productions.
     */
    @Override
    protected void reloadData() {
        super.reloadData();
        // Specifies the data to be used by this instance.
        super.map = super.getDataContext().getProductions();
    }

    /**
     * Gets the entries of productions filtered by a specific account id.
     * Used to find all productions created by a specific account.
     * @param key the id of the account to to filter the data.
     * @return a filtered map of productions.
     */
    @Override
    public Map<Integer, Production> getEntries(Object key) {
        reloadData();
//        System.out.println(key.toString());
        Map<Integer, Production> result = new TreeMap<>();
        if (key instanceof Integer) {
            int id = (int) key;
            for (Map.Entry<Integer, Production> e : super.map.entrySet()) {
                if (e.getValue().getUserId() == id) {
                    result.put(e.getKey(), e.getValue());
                }
            }
        }
        return result;
    }

    /**
     * Deletes the specified production by using a soft delete approach.
     * @param production the production to delete.
     * @return a boolean with the status of the delete procedure. Returns true if successful.
     */
    @Override
    public boolean delete(Production production) {
        Production p = new Production(production.getId(), production.getProductionId(), production.getName(), production.getUserId(), production.isApprovedCredits(), true);

        return super.getDataConnection().update(p);
    }
}
