package presentation.Central;

import domain.model.Account;
import domain.model.Production;
import java.util.HashSet;

public class dataSector {

    private byte accesslevel = 8;
    private int curID;
    private String descriptionP = "", descriptionA = "";
    private HashSet<Account> users = new HashSet<>(); //temporary
    private HashSet<Production> mapProd = new HashSet<>(), set2 = new HashSet<>();
    private Object obj;

    public dataSector(){
        this.obj = new Account(8, "Default", "password", (byte)1, false);

        //this.obj = new Production(0, 1,"Default", 1, false, false);
        this.users.add(new Account(0, "admin", "123", (byte)0, false));
        this.users.add(new Account(2, "SmackTrack66", "password", (byte)1, false));
        this.users.add(new Account(3, "PupPepper87", "password", (byte)3, false));
    }

    public void setAccessLevel(byte al){
        this.accesslevel = al;
    }
    public byte getAccessLevel(){
        return this.accesslevel;
    }
    public boolean userCheck(String username, String password) {
        for(Account a : users){
            if(a.getUsername().equals(username) && a.getPassword().equals(password)){
                Main.DTS.setAccessLevel((byte)a.getPrivilegeId());
                this.curID = a.getId();
                return true;
            }
            System.out.println("User Invalid: " + a.getUsername() + " | " + a.getPassword());
        }
        return false;
    }
    public String getDescriptionA() {
        return descriptionA;
    }
    public int getCurID() {
        return curID;
    }
    public HashSet<Account> getUsers(){
        return this.users;
    }
    public HashSet<Production> getMapProd() {
        return this.mapProd;
    }
    public Object getObj() {
        return obj;
    }
    public void setObj(Object obj) {
        this.obj = obj;
    }

}
