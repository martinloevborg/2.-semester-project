package interfaces;

import java.util.Map;

/**
 * Interface used between presentation and domain to manipulate data.
 * @param <K> the key of the data.
 * @param <T> the data.
 */
public interface IController<K, T> {
    /**
     * Gets the entries of the map
     * @return map containing the entries.
     */
    Map<K, T> getEntries();

    /**
     * Gets a filtered map of entries.
     * @param key the key used to filter the map.
     * @return a filtered map of entries.
     */
    Map<K, T> getEntries(Object key);

    /**
     * Gets a specific record in the map.
     * @param key the key of the record.
     * @return a specific record.
     */
    T getEntry(K key);

    /**
     * Creates the specified object.
     * @param t the object to create.
     * @return boolean to determine the status of the procedure.
     */
    boolean create(T t);
    /**
     * Edits the specified object.
     * @param t the object to edit.
     * @return boolean to determine the status of the procedure.
     */
    boolean edit(T t);
    /**
     * Deletes the specified object.
     * @param t the object to delete.
     * @return boolean to determine the status of the procedure.
     */
    boolean delete(T t);
    /**
     * Edits the specified object.
     * @param oldT the old object.
     * @param newT the new object to replace the old.
     * @return boolean to determine the status of the procedure.
     */
    boolean edit(T oldT, T newT);
}