package domain;

import dataaccess.PostgresConnection;
import interfaces.IController;
import interfaces.IDataConnection;
import interfaces.IDataContext;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides the necessary implementation of the IController which is used to manipulate and retrieve maps of data of a given type.
 * @param <K> the key of the data.
 * @param <T> the data.
 */
public abstract class AbstractController<K, T> implements IController<K, T> {
    /**
     * Stores all records of the database.
     */
    private IDataContext db;
    /**
     * The connection to the database.
     */
    private IDataConnection connection;
    /**
     * The map of the given type of data.
     */
    protected Map<K, T> map;

    /**
     * Sole constructor. Initializes the connection to the database and loads the data.
     */
    public AbstractController() {
        map = new TreeMap<K, T>();
        connection = new PostgresConnection();
        reloadData();
    }

    /**
     * Reloads the data from the database.
     */
    protected void reloadData() {
        db = connection.loadData();
    }

    /**
     * Gets the data context storing all the data from the database.
     * @return a data context holding all the data from the database.
     */
    protected IDataContext getDataContext() {
        return db;
    }

    /**
     * Gets the connection to the database.
     * @return the connection to the database.
     */
    protected IDataConnection getDataConnection() {
        return connection;
    }

    /**
     * Reloads the data and returns the specific map of data.
     * @return the specific map of data.
     */
    @Override
    public Map<K, T> getEntries() {
        reloadData();
        return map;
    }

    /**
     * Reloads and returns the specific map of data based on an id.
     * @param key the id to to filter the data.
     * @return the specific map of data which has been filtered.
     */
    @Override
    public Map<K, T> getEntries(Object key) {
        reloadData();

        // Get entries for the specified key.
        TreeMap<K, T> result = new TreeMap<>();

        for (Map.Entry<K, T> e : map.entrySet()) {
            if (e.getKey() == key) {
                result.put(e.getKey(), e.getValue());
            }
        }

        return result;
    }

    /**
     * Gets a single record from the map based on a key. Returns null if it doesn't exist.
     * @param key the key of the data to find.
     * @return the object associated to the key or null if it doesnt exist.
     */
    @Override
    public T getEntry(K key) {
        reloadData();

        // Return null or the specified element corresponding to the key.
        return map.getOrDefault(key, null);
    }

    /**
     * Creates a record of the specified type. Reloads the data upon successful creation.
     * @param t the record to create. The ID must be 0.
     * @return a boolean with the status of the procedure. Returns true if the creation went well.
     */
    @Override
    public boolean create(T t) {
        if (connection.create(t)) {
            reloadData();
            return true;
        }

        return false;
    }

    /**
     * Edits a record of the specified type. Reloads the data upon successful update.
     * @param t the record to update containing the same ID with updated fields. ID cannot be 0.
     * @return a boolean with the status of the procedure. Returns true if the update went well.
     */
    @Override
    public boolean edit(T t) {
        if (connection.update(t)) {
            reloadData();
            return true;
        }

        return false;
    }

    /**
     * Not supported by default.
     */
    @Override
    public boolean edit(T oldT, T newT){
        throw new UnsupportedOperationException("Not supported by default.");
    }

    /**
     * Deletes a record of the specified type. Reloads the data upon successful deletion.
     * @param t the object to delete. The ID cannot be 0.
     * @return a boolean with the status of the procedure. Returns true if the update went well.
     */
    @Override
    public boolean delete(T t) {
        if (connection.delete(t)) {
            reloadData();
            return true;
        }

        return false;
    }
}