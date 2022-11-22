package presentation.Visuals;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import presentation.Central.Main;

public class MloginPage extends VBox {

    private VBox root;
    private Text txt;
    private TextField txtf1, txtf2;
    private Button btn1;

    public MloginPage(){
        //Nodes
        this.root = Main.STN.setVBoxDesignDark(new VBox(), Pos.CENTER, false, Main.STN.colorLight);
        this.txt = Main.STN.setNormalTextWidth("Login Page", true, 0);
        this.txtf1 = Main.STN.setTextField("Username");
        this.txtf2 = Main.STN.setTextField("Password");
        this.btn1 = Main.STN.setButtonStyle("Sign in", 250, 25);

        //Functionality
        this.btn1.setOnAction( e-> {
            if (!this.txtf1.getText().equals("Username") && !this.txtf1.getText().equals("a")){
                if(Main.DTS.userCheck(this.txtf1.getText(), this.txtf2.getText())){
                    System.out.println("Logged in!");
                    Main.setCenterHome();
                    Main.setAdminTools();
                }
                else{
                    System.out.println("Unable to log in with given user.");
                }
            }
            else if(this.txtf1.getText().equals("a")){
                Main.STN.getShowMe();
            }
            else{
                Main.STN.setError();
            }
        });

        //Pane settings
        setAlignment(Pos.CENTER);
        this.root.getChildren().addAll(this.txt, this.txtf1, this.txtf2, this.btn1);
        this.root.setMaxSize(250, 500);
        this.root.setSpacing(5);
        getChildren().add(this.root);
    }

}
