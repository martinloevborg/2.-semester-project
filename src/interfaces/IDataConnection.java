package interfaces;

/**
 * Interface used between the domain and data access to connect to the database.
 */
public interface IDataConnection {
    /**
     * Loads the data from the database.
     * @return a data context containing the data.
     */
    IDataContext loadData();

    /**
     * Issues an update to the database.
     * @param obj the object to update.
     * @return a boolean with the status of the procedure.
     */
    boolean update(Object obj);

    /**
     * Issues an delete operation to the database.
     * @param obj the object to delete.
     * @return a boolean with the status of the procedure.
     */
    boolean delete(Object obj);

    /**
     * Issues an insert operation to the database.
     * @param obj the object to insert.
     * @return a boolean with the status of the procedure.
     */
    boolean create(Object obj);
}
