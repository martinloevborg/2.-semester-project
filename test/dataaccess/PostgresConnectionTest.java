package dataaccess;

import domain.model.Production;

import static org.junit.Assert.*;

public class PostgresConnectionTest {
    PostgresConnection con;

    @org.junit.Before
    public void setUp() throws Exception {
        con = new PostgresConnection();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }


    @org.junit.Test
    public void loadData() {
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

    @org.junit.Test
    public void update() {
        Production p = null;
        for (Production prod : con.loadData().getProductions().values()) {
            p = prod;
        }

        System.out.printf("id: %d%n", p.getId());
        System.out.printf("productionid: %d%n", p.getProductionId());
        System.out.printf("name: %s%n", p.getName());
        System.out.printf("userId: %d%n", p.getUserId());
        System.out.printf("disabled: %b%n", p.isDisabled());
        System.out.printf("approvedCredits: %b%n", p.isApprovedCredits());
        System.out.println();

        Production newProd = new Production(
                p.getId(),
                p.getUserId(),
                "Test update name",
                p.getUserId(),
                p.isDisabled(),
                p.isApprovedCredits());

        assertTrue(con.update(newProd));
    }

    @org.junit.Test
    public void delete() {
        Production p = null;
        for (Production prod : con.loadData().getProductions().values()) {
            p = prod;
        }
        System.out.println("id " + p.getId());
        assertTrue(con.delete(p));
    }

    @org.junit.Test
    public void create() {
        Production p = new Production(
                0,
                112,
                "PostgressConnectionTest",
                2,
                false,
                false);

        assertTrue(con.create(p));
    }
}