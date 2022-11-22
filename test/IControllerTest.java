import dataaccess.PostgresConnection;
import domain.CreditController;
import domain.model.Credit;
import interfaces.IController;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class IControllerTest {
    @Test
    public void loadSpecificRecordTest(){
        IController<Credit.Key, Credit> ic = new CreditController();
        Credit.Key newKey = new Credit.Key(3,1,1);
        var kan = ic.getEntry(newKey);
        assertNotNull(kan);
    }

    @Test
    public void loadRecordsTest(){
        IController<Credit.Key, Credit> ic = new CreditController();
        assertNotNull(ic.getEntries());
    }

    @Test
    public void loadRecordsWithKeyTest(){
        IController<Credit.Key, Credit> ic = new CreditController();
        int id = 3;
        var er = ic.getEntries(id);
        boolean ai = false;
        if(er.values().size() > 0){
            ai = true;
            for (Credit c: er.values()) {
                if(c.getId().getProductionId() != id) {
                    ai = false;
                    break;
                }
            }
        }
        assertTrue(ai);
    }

    @Test
    public void CreateDataTest(){
        IController<Credit.Key, Credit> ic = new CreditController();
        Credit credit = new Credit(3,1,2, null,null);
        assertTrue(ic.create(credit));
    }

    @Test
    public void OpdateTest(){
        IController<Credit.Key, Credit> ic = new CreditController();
        Credit credit = new Credit(3,1,1, null,null);
        Credit creditNew = new Credit(3,1,2, null,null);
        assertTrue(ic.edit(credit, creditNew));
    }

    @Test
    public void DeleteTest(){
        IController<Credit.Key, Credit> ic = new CreditController();
        Credit credit = new Credit(3,1,2, null,null);
        assertTrue(ic.delete(credit));
    }
}
