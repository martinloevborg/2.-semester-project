package domain;

import domain.model.Credit;

import java.util.Map;
import java.util.TreeMap;

/**
 * Extends the AbstractController to provide functionally to work with credits.
 */
public class CreditController extends AbstractController<Credit.Key, Credit> {
    /**
     * Reloads the data and updates the map to display credits.
     */
    @Override
    protected void reloadData() {
        super.reloadData();
        // Specifies the data to be used by this instance.
        super.map = super.getDataContext().getCredits();
    }

    /**
     * Gets the entries of credits based upon the specified production id. Used to find all credits of a given production.
     * @param key the id of the production to to filter the data.
     * @return a filtered map of credits.
     */
    @Override
    public Map<Credit.Key, Credit> getEntries(Object key) {
        reloadData();
        Map<Credit.Key, Credit> result = new TreeMap<>();
        if (key instanceof Integer) {
            int id = (int) key;

            for (Map.Entry<Credit.Key, Credit> e : super.map.entrySet()) {
                if (e.getKey().getProductionId() == id) {
                    result.put(e.getKey(), e.getValue());
                }
            }
        }
        return result;
    }

    /**
     * Updates a specified record. Credits are implemented as only keys, which is the reason why it uses a different approach.
     * @param oldT the old key to delete
     * @param newT the new key to insert
     * @return a boolean with the status of the update procedure. Returns true if successful.
     */
    @Override
    public boolean edit(Credit oldT, Credit newT) {
        if (super.delete(oldT)) {
            if (super.create(newT)) {
                return true;
            } else {
                super.create(oldT);
            }
        }
        return false;
    }
}