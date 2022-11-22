//package dataaccess;
//
//import domain.model.Credit;
//import domain.model.DataContext;
//import domain.model.Person;
//import domain.model.Privilege;
//import domain.model.Production;
//import domain.model.Role;
//import domain.model.Account;
//import interfaces.IDataContext;
//import interfaces.IDataConnection;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.PrintWriter;
//import java.util.Map;
//import java.util.Scanner;
//
//public class DataConnection implements IDataConnection
//{
//    private final String root = ".\\appdata\\";
//    private final String creditPath = root + "Credit.csv";
//    private final String personPath = root + "Person.csv";
//    private final String privilegePath = root + "Privilege.csv";
//    private final String productionPath = root + "Production.csv";
//    private final String rolePath = root + "Role.csv";
//    private final String userPath = root + "User.csv";
//    private IDataContext db;
//
//    public DataConnection()
//    {
//        db = new DataContext();
//        initialize();
//    }
//
//    @Override
//    public IDataContext loadData()
//    {
//        db = new DataContext();
//        initialize();
//        return db;
//    }
//
//    @Override
//    public boolean saveProductions()
//    {
//        try (PrintWriter writer = new PrintWriter(new File(productionPath)))
//        {
//            String result = "ID;ProductionId;Name;UserId;ApprovedCredits";
//            for (Production p : db.getProductions().values())
//            {
//                String temp = "0";
//                if (p.isApprovedCredits())
//                    temp = "1";
//                result += "\n" + p.getId() + ";" + p.getProductionId() + ";" + p.getName() + ";" + p.getUserId() + ";" + temp;
//            }
//
//            writer.write(result);
//            System.out.println("Productions successfully saved.");
//
//
//        }
//        catch (FileNotFoundException e)
//        {
//            System.out.println(e.getMessage());
//            return false;
//        }
//
//
//        return true;
//    }
//
//    @Override
//    public boolean saveRoles()
//    {
//        return false;
//    }
//
//    @Override
//    public boolean saveUsers()
//    {
//        return false;
//    }
//
//    @Override
//    public boolean savePersons()
//    {
//        try (PrintWriter writer = new PrintWriter(new File(personPath)))
//        {
//            String result = "ID;Name";
//            for (Person p : db.getPersons().values())
//            {
//                result += "\n" + p.getId() + ";" + p.getName();
//            }
//
//            writer.write(result);
//            System.out.println("Persons successfully saved.");
//
//
//        }
//        catch (FileNotFoundException e)
//        {
//            System.out.println(e.getMessage());
//            return false;
//        }
//
//        return true;
//    }
//
//    @Override
//    public boolean saveCredits()
//    {
//        try (PrintWriter writer = new PrintWriter(new File(creditPath)))
//        {
//            String result = "ID;PERSON_ID;RoleId";
//            for (Map.Entry<Credit.Key, Credit> k : db.getCredits().entrySet())
//            {
//                result += "\n" + k.getKey().getProductionId() + ";" + k.getKey().getPersonId() + ";" + k.getValue().getRoleId();
//            }
//
//            writer.write(result);
//            System.out.println("Credits successfully saved.");
//        }
//        catch (FileNotFoundException e)
//        {
//            System.out.println(e.getMessage());
//            return false;
//        }
//
//        return true;
//    }
//
//    //region loaders
//    private void initialize()
//    {
//        // The order does matter. The data that require the other sets must be loaded last.
//        loadPrivilege();
//        loadPerson();
//        loadRole();
//        loadProduction();
//        loadUser();
//        loadCredit();
//    }
//
//    private void loadCredit()
//    {
//        Scanner reader = initializeReader(creditPath);
//        if (reader == null)
//        {
//            System.out.println("Error loading the credits");
//            System.exit(1);
//        }
//
//        while (reader.hasNextLine())
//        {
//            int PRODUCTION_ID  = -1;
//            int PERSON_ID = -1;
//            int roleId = -1;
//
//            // Try loading the data.
//            String[] field = reader.nextLine().split(";");
//            try
//            {
//                PRODUCTION_ID = Integer.parseInt(field[0]);
//                PERSON_ID = Integer.parseInt(field[1]);
//                roleId = Integer.parseInt(field[2]);
//            }
//            catch (NumberFormatException e)
//            {
//                System.out.println(e.getMessage());
//            }
//
//            // Add the object upon successful retrieval.
//            if (PRODUCTION_ID > -1 && PERSON_ID > -1 && roleId > -1)
//            {
//                Production production = null;
//                Person person = null;
//                Role role = null;
//                for (Production p : db.getProductions().values()) {
//                    if (p.getId() == PRODUCTION_ID) {
//                        production = p;
//                        break;
//                    }
//                }
//                for (Person p : db.getPersons().values()) {
//                    if (p.getId() == PERSON_ID) {
//                        person = p;
//                        break;
//                    }
//                }
//                for (Role r : db.getRoles().values()) {
//                    if (r.getId() == roleId) {
//                        role = r;
//                        break;
//                    }
//                }
//
//                if (production != null && person != null && role != null)
//                {
//                    Credit c = new Credit(PRODUCTION_ID, PERSON_ID, roleId, person.getName(), role.getName());
//                    db.getCredits().put(c.getId(), c);
//                }
//            }
//        }
//    }
//
//    private void loadPrivilege()
//    {
//        Scanner reader = initializeReader(privilegePath);
//        if (reader == null)
//        {
//            System.out.println("Error loading the persons");
//            System.exit(1);
//        }
//
//        while (reader.hasNextLine())
//        {
//            int privilegeId = -1;
//
//            // Try loading the data.
//            String[] field = reader.nextLine().split(";");
//            try
//            {
//                privilegeId = Integer.parseInt(field[0]);
//            }
//            catch (NumberFormatException e)
//            {
//                System.out.println(e.getMessage());
//            }
//
//            // Add the object upon successful retrieval.
//            if (privilegeId > -1)
//            {
//                db.getPrivileges().put(privilegeId ,new Privilege(privilegeId, field[1]));
//            }
//        }
//    }
//
//    private void loadPerson()
//    {
//        Scanner reader = initializeReader(personPath);
//        if (reader == null)
//        {
//            System.out.println("Error loading the persons");
//            System.exit(1);
//        }
//
//        while (reader.hasNextLine())
//        {
//            int personId = -1;
//
//            // Try loading the data.
//            String[] field = reader.nextLine().split(";");
//            try
//            {
//                personId = Integer.parseInt(field[0]);
//            }
//            catch (NumberFormatException e)
//            {
//                System.out.println(e.getMessage());
//            }
//
//            // Add the object upon successful retrieval.
//            if (personId > -1)
//            {
//                db.getPersons().put(personId, new Person(personId, field[1]));
//            }
//        }
//    }
//
//    private void loadRole()
//    {
//        Scanner reader = initializeReader(rolePath);
//        if (reader == null)
//        {
//            System.out.println("Error loading the roles");
//            System.exit(1);
//        }
//
//        while (reader.hasNextLine())
//        {
//            int roleId = -1;
//
//            // Try loading the data.
//            String[] field = reader.nextLine().split(";");
//            try
//            {
//                roleId = Integer.parseInt(field[0]);
//            }
//            catch (NumberFormatException e)
//            {
//                System.out.println(e.getMessage());
//            }
//
//            // Add the object upon successful retrieval.
//            if (roleId > -1)
//            {
//                db.getRoles().put(roleId, new Role(roleId, field[1]));
//            }
//        }
//    }
//
//    private void loadProduction()
//    {
//        Scanner reader = initializeReader(productionPath);
//        if (reader == null)
//        {
//            System.out.println("Error loading the productions");
//            System.exit(1);
//        }
//
//        while (reader.hasNextLine())
//        {
//            int internalId = -1;
//            int productionId = -1;
//            int userId = -1;
//            int approved = -1;
//
//            // Try loading the data.
//            String[] field = reader.nextLine().split(";");
//            try
//            {
//                internalId = Integer.parseInt(field[0]);
//                productionId = Integer.parseInt(field[1]);
//                userId = Integer.parseInt(field[3]);
//                approved = Integer.parseInt(field[4]);
//            }
//            catch (NumberFormatException e)
//            {
//                System.out.println(e.getMessage());
//            }
//
//            // Add the object upon successful retrieval.
//            if (internalId > -1 && productionId > -1 && userId > -1 && approved > -1)
//            {
//                db.getProductions().put(internalId, new Production(internalId, productionId, field[2], userId, convertIntToBoolean(approved)));
//            }
//        }
//    }
//
//    private void loadUser()
//    {
//        Scanner reader = initializeReader(userPath);
//        if (reader == null)
//        {
//            System.out.println("Error loading the users");
//            System.exit(1);
//        }
//
//        while (reader.hasNextLine())
//        {
//            int userId = -1;
//            int privilege = -1;
//            int disabled = -1;
//
//            // Try loading the data.
//            String[] field = reader.nextLine().split(";");
//            try
//            {
//                userId = Integer.parseInt(field[0]);
//                privilege = Integer.parseInt(field[3]);
//                disabled = Integer.parseInt(field[4]);
//            }
//            catch (NumberFormatException e)
//            {
//                System.out.println(e.getMessage());
//            }
//
//            // Add the object upon successful retrieval.
//            if (userId > -1 && privilege > -1 && disabled > -1)
//            {
//                db.getUsers().put(userId, new Account(userId, field[1], field[2], privilege, convertIntToBoolean(disabled)));
//            }
//        }
//    }
//
//    private Scanner initializeReader(String filePath)
//    {
//        try
//        {
//            Scanner scanner = new Scanner(new File(filePath));
//            if (scanner.hasNextLine())
//            {
//                scanner.nextLine();
//            }
//            return scanner;
//        }
//        catch (FileNotFoundException e)
//        {
//            return null;
//        }
//    }
//
//    private boolean convertIntToBoolean(int i)
//    {
//        return i != 0;
//    }
//    //endregion
//}