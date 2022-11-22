package dataaccess;

import domain.model.*;
import interfaces.IDataConnection;
import interfaces.IDataContext;

import java.sql.*;

/**
 * Creates the connection to the database.
 * It is responsible for the retrieval and manipulation of data.
 */
public class PostgresConnection implements IDataConnection {
    /**
     * Stores the data from the database.
     */
    private IDataContext db;
    /**
     * The connection to the database.
     */
    static Connection connection;

    /**
     * The Sole constructor. Begins to load data upon instantiation.
     */
    public PostgresConnection() {
        loadData();
    }

    /**
     * Initializes the connection to the database through the database url.
     * Executes several select statements to load the data from the database.
     */
    private void initialize() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/SE02ViewR",
                    "SE02ViewR",
                    "SE02ViewR"
            );
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Load Privilege
        //region Privilege
        try {
            Statement st = connection.createStatement();

            ResultSet rs = null;
            rs = st.executeQuery("SELECT * FROM Privilege");
            while (rs.next()) {
                Privilege privilege = new Privilege(rs.getInt(1), rs.getString(2));
                db.getPrivileges().put(privilege.getId(), privilege);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //endregion

        //Load Person
        //region Person
        try {
            Statement st = connection.createStatement();

            ResultSet rs = null;
            rs = st.executeQuery("SELECT * FROM Person");
            while (rs.next()) {
                Person p = new Person(rs.getInt(1), rs.getString(2));
                db.getPersons().put(p.getId(), p);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //endregion

        //Load Role
        //region Role
        try {
            Statement st = connection.createStatement();

            ResultSet rs = null;
            rs = st.executeQuery("SELECT  * FROM  Role");
            while (rs.next()) {
                Role r = new Role(rs.getInt(1), rs.getString(2));
                db.getRoles().put(r.getId(), r);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //endregion

        //Load Account
        //region Account
        try {
            Statement st = connection.createStatement();

            ResultSet rs = null;
            rs = st.executeQuery("SELECT * FROM Account");
            while (rs.next()) {
                Account acc = new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getBoolean(5));
                db.getAccounts().put(acc.getId(), acc);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //endregion

        //Load Production
        //region Production
        try {
            Statement st = connection.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery("SELECT  * FROM Production");
            while (rs.next()) {
                Production pr = new Production(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getBoolean(6),
                        rs.getBoolean(5));
                db.getProductions().put(pr.getId(), pr);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //endregion

        //Load Credit
        //region Credit
        try {
            Statement st = connection.createStatement();

            ResultSet rs = null;
            rs = st.executeQuery("SELECT C.* , p.name personname, r.name rolename FROM Credit c " +
                    "LEFT OUTER JOIN Person p on c.PersonID = p.id " +
                    "LEFT OUTER JOIN Role r on c.roleId = r.id ");
            while (rs.next()) {
                Credit cr = new Credit(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5));
                db.getCredits().put(cr.getId(), cr);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //endregion


    }

    /**
     * Loads the data from the database and maps it to an IDataContext.
     * @return the data contained in an IDataContext.
     */

    @Override
    public IDataContext loadData() {
        db = new DataContext();
        initialize();
        return db;
    }

    /**
     * Updates a single record in the database determined by the type of input.
     * @param obj the object to update.
     * @return a boolean to reflect the status of the update procedure.
     */
    @Override
    public boolean update(Object obj) {
        String sql = null;

        if (obj instanceof Person) {
            Person p = (Person) obj;
            sql = String.format("update person set name = '%s' where id = %d", p.getName(), p.getId());
        }
        if (obj instanceof Role) {
            Role r = (Role) obj;
            sql = String.format("update role set name = '%s' where id = %d", r.getName(), r.getId());
        }
        if (obj instanceof Privilege) {
            Privilege priv = (Privilege) obj;
            sql = String.format("update privilege set name = '%s' where id = %d", priv.getName(), priv.getId());
        }
        if (obj instanceof Production) {
            Production prod = (Production) obj;
            sql = String.format("update production set name = '%s', productionid = %d, disabled = %b where id = %d",
                    prod.getName(), prod.getProductionId(), prod.isDisabled(), prod.getId());
        }
        if (obj instanceof Account) {
            Account acc = (Account) obj;
            sql = String.format(
                    "update account set password = '%s', privilegeid = %d, deleted = %b where id = %d",
                    acc.getPassword(), acc.getPrivilegeId(), acc.isDeleted(), acc.getId());
        }

        if (sql != null) {
            try (PreparedStatement st = connection.prepareStatement(sql)) {
                int updated = st.executeUpdate();
                System.out.println(updated == 1 ? "1 row updated" : updated + " rows updated");
                return updated > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


    /**
     * Deletes a single record in the database determined by the type of input.
     * @param obj the object to delete.
     * @return a boolean to reflect the status of the delete procedure.
     */
    @Override
    public boolean delete(Object obj) {
        String sql = null;

        if (obj instanceof Person) {
            Person p = (Person) obj;
            sql = String.format("delete from person where id = %d", p.getId());
        }
        if (obj instanceof Role) {
            Role r = (Role) obj;
            sql = String.format("delete from role where id = %d", r.getId());
        }
        if (obj instanceof Privilege) {
            Privilege priv = (Privilege) obj;
            sql = String.format("delete from privilege where id = %d", priv.getId());
        }
        if (obj instanceof Production) {
            Production prod = (Production) obj;
            sql = String.format("delete from production where id = %d", prod.getId());
        }
        if (obj instanceof Credit) {
            Credit c = (Credit) obj;
            sql = String.format("delete from credit where productionid = %d and personid = %d and roleid = %d",
                    c.getId().getProductionId(), c.getId().getPersonId(), c.getId().getRoleId());
        }
        if (obj instanceof Account) {
            Account acc = (Account) obj;
            sql = String.format("delete from account where id = %d", acc.getId());

        }

        if (sql != null) {
            try (PreparedStatement st = connection.prepareStatement(sql)) {
                int deleted = st.executeUpdate();
                System.out.println(deleted == 1 ? "1 row deleted" : deleted + " rows deleted");
                return deleted > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Creates a single record in the database determined by the type of input.
     * @param obj the object to create. The ID of the object should be set to 0.
     * @return a boolean to reflect the status of the create procedure.
     */
    @Override
    public boolean create(Object obj) {
        String sql = null;

        if (obj instanceof Person) {
            Person p = (Person) obj;
            if (p.getId() != 0) {
                // Must not already have an id.
                return false;
            }
            // Returns whether or not the create went well.
            sql = String.format("insert into person(name) values('%s') returning id", p.getName());
        }
        if (obj instanceof Role) {
            Role r = (Role) obj;
            if (r.getId() != 0) {
                return false;
            }
            sql = String.format("insert into role(name) values ('%s') returning id", r.getName());
        }
        if (obj instanceof Privilege) {
            Privilege priv = (Privilege) obj;
            if (priv.getId() != 0) {
                return false;
            }
            sql = String.format("insert into privilege(name) values ('%s') returning id", priv.getName());
        }
        if (obj instanceof Production) {
            Production prod = (Production) obj;
            if (prod.getId() != 0) {
                return false;
            }
            sql = String.format("insert into production (productionid, name, accountid, approvedcredits, disabled) values (%d, '%s', %d, %b, %b) returning id",
                    prod.getProductionId(), prod.getName(), prod.getUserId(), prod.isApprovedCredits(), prod.isDisabled());
        }
        if (obj instanceof Credit) {
            Credit c = (Credit) obj;
            sql = String.format("insert into credit (productionid, personid, roleid) values (%d, %d, %d) returning productionid",
                    c.getId().getProductionId(), c.getId().getPersonId(), c.getId().getRoleId());
        }
        if (obj instanceof Account) {
            Account acc = (Account) obj;
            if (acc.getId() != 0) {
                return false;
            }
            sql = String.format("insert into account (username, password, privilegeid, deleted) values ('%s', '%s', %d, %b) returning id",
                    acc.getUsername(), acc.getPassword(), acc.getPrivilegeId(), acc.isDeleted());
        }

        if (sql != null) {

            try (PreparedStatement st = connection.prepareStatement(sql)) {
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}