package presentation.Visuals;

import domain.AbstractController;
import domain.AccountController;
import domain.model.Account;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import presentation.Central.Main;
import presentation.GUI.App;

public class MEdit extends VBox {

    private HBox box1, box2, box3, box4, box5, boxBtn;
    private TextField tf1, tf2, tf3, tf4, tf5, txtf;
    private Account tempAcc;
    private final String searchName = "Search user ID";
    private Text txt;
    private Button saveBtn, homeBtn, delBtn;
    private boolean isDelDis, isApproved;

    private AbstractController con;

    public MEdit(){
        this.boxBtn = new HBox();

        this.txt = Main.STN.setNormalTextWidth("Edit Account", true, 0);
        this.saveBtn = Main.STN.setButtonStyle("Save Changes", 120, 25);
        this.homeBtn = Main.STN.setButtonStyle("Back Home", 120 , 25);
        this.delBtn = Main.STN.setButtonStyle("Delete User", 120 , 25);

        this.tf1 = new TextField();
        this.tf2 = new TextField();
        this.tf3 = new TextField();
        this.tf4 = new TextField();
        this.tf5 = new TextField();
        this.txtf = new TextField();


        //Functionality
        this.txtf.setOnMouseClicked(e1 -> {
            this.txtf.selectAll();
        });
        this.txtf.setOnKeyReleased(e2 -> {
            if (this.txtf.getText().isEmpty()){
                this.txtf.setText(this.searchName);
            }
        });
        this.txtf.setOnKeyPressed(e3->{
            if (this.txtf.getText().equals(this.searchName) && e3.getCode() != KeyCode.ENTER){
                this.txtf.setText("");
            }
            else if(e3.getCode() == KeyCode.ENTER){
                AccountController con = new AccountController();
                resetTF();

                for (Account acc : con.getEntries().values()){
                    try{
                        if (acc.getId() == Integer.parseInt(txtf.getText())){
                            getChildren().clear();

                            this.txtf.setText(this.searchName);
                            this.tempAcc = new Account(acc.getId(), acc.getUsername(), acc.getPassword(), acc.getPrivilegeId(), acc.isDeleted());
                            this.box1 = setTFBox("Username: ", Main.STN.setTextField(acc.getUsername()+""), this.tf1, false);
                            this.box2 = setTFBox("Password: ", Main.STN.setTextField(acc.getPassword()+""), this.tf2, true);
                            this.box3 = setTFBox("ID:", Main.STN.setTextField(acc.getId()+""), this.tf3, false);
                            this.box4 = setTFBox("Access Level: ", Main.STN.setTextField(acc.getPrivilegeId()+""), this.tf4, true);
                            this.box5 = setTFBox("Deleted: ", Main.STN.setTextField(acc.isDeleted()+""), this.tf5, true);
                            this.isDelDis = acc.isDeleted();

                            getChildren().addAll(this.txt, this.txtf, this.box1, this.box2, this.box3, this.box4, this.box5, this.boxBtn);
                            break;
                        }
                    }
                    catch(NumberFormatException e2){
                        System.out.println("Unable to parse query:" + " | " + e2.getMessage() + " | "+e2.getCause() + " | ");
                    }
                }
            }
        });
        this.saveBtn.setOnAction(e1 -> {
            AccountController con = new AccountController();

            if(this.tf1.getText().isBlank() && this.tf2.getText().isBlank() && this.tf3.getText().isBlank() && this.tf4.getText().isBlank() && this.tf5.getText().isBlank()){
                System.out.println("No changes Detected");
            }
            else{
                if (this.tf5.getText().equals("true") || this.tf5.getText().equals("t") || this.tf5.getText().equals("yes")){
                    this.isDelDis = true;
                }
                try{
                    int privelege = this.tempAcc.getPrivilegeId();
                    if (this.tf3.getText().isBlank()){
                        privelege = this.tempAcc.getPrivilegeId();
                    }
                    con.edit(new Account(this.tempAcc.getId(), this.tf1.getText(), this.tf2.getText(), privelege, this.isDelDis));
                    Main.root.setCenter(new MEdit());
                }
                catch(NumberFormatException e5){
                    System.out.println("Unable to query request.");
                }
            }
        });
        this.homeBtn.setOnAction(e2 ->{
            App.goBack();
        });
        this.delBtn.setOnAction(e3 ->{
            AccountController con = new AccountController();
            if (this.tempAcc != null){
                for (Account acc : con.getEntries().values()){
                    if(acc.getId() == this.tempAcc.getId()){
                        con.delete(this.tempAcc);
                        break;
                    }
                }
            }
        });

        //Settings
        this.txt.setStyle("-fx-font-size: 60");
        this.txtf.setText(this.searchName);
        this.txtf.setAlignment(Pos.CENTER);
        this.boxBtn.setSpacing(10);
        this.boxBtn.setAlignment(Pos.CENTER);
        this.boxBtn.getChildren().addAll(this.saveBtn, this.homeBtn, this.delBtn);

        setSpacing(5);
        setStyle(Main.STN.colorLight);
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(this.txt, this.txtf);
    }

    public void resetTF(){
        this.tf1.setText("");
        this.tf2.setText("");
        this.tf3.setText("");
        this.tf4.setText("");
        this.tf5.setText("");
    }

    public HBox setTFBox(String name, TextField txtfd, TextField tf, boolean addLastF){
        HBox box = new HBox();
        Text txt = Main.STN.setNormalTextWidth(name, true, 80);
        txtfd.setDisable(true);
        txtfd.setOpacity(1);
        txtfd.setStyle("-fx-border-radius: 1; -fx-background-radius: 1;");
        tf.setStyle("-fx-border-radius: 1; -fx-background-radius: 1;");
        box.getChildren().addAll(txt, txtfd, tf);
        tf.setDisable(!addLastF);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(5);
        return box;
    }
}
