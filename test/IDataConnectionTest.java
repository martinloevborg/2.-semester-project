import dataaccess.PostgresConnection;
import domain.model.Production;
import interfaces.IDataConnection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class IDataConnectionTest {
    IDataConnection con;

    @Before
    public void setup() {
        con = new PostgresConnection();
    }

    @Test
    public void loadDataTest() {
        var data = con.loadData();

        data.getProductions().values().forEach(p -> {
            System.out.printf("id: %d%n", p.getId());
            System.out.printf("productionid: %d%n", p.getProductionId());
            System.out.printf("name: %s%n", p.getName());
            System.out.printf("userId: %d%n", p.getUserId());
            System.out.printf("disabled: %b%n", p.isDisabled());
            System.out.printf("approvedCredits: %b%n", p.isApprovedCredits());
            System.out.println();
        });

        assertNotNull(data);
    }

    @Test
    public void createData() {
        Production p = new Production(
                0,
                888,
                "Unittest",
                7,
                false,
                false);

        assertTrue(con.create(p));
    }

    @Test
    public void updateData() {
        Production p = null;
        for (Production pr : con.loadData().getProductions().values()) {
            p = pr;
        }

        System.out.printf("id: %d%n", p.getId());
        System.out.printf("productionid: %d%n", p.getProductionId());
        System.out.printf("name: %s%n", p.getName());
        System.out.printf("userId: %d%n", p.getUserId());
        System.out.printf("disabled: %b%n", p.isDisabled());
        System.out.printf("approvedCredits: %b%n", p.isApprovedCredits());
        System.out.println();

        Production newP = new Production(
                p.getId(),
                p.getUserId(),
                "Test udpate name",
                p.getUserId(),
                p.isDisabled(),
                p.isApprovedCredits());

        assertTrue(con.update(newP));
    }

    @Test
    public void deleteData() {
        Production p = null;
        for (Production pr : con.loadData().getProductions().values()) {
            p = pr;
        }
        System.out.println("id " + p.getId());
        assertTrue(con.delete(p));
    }
}
