package presentation.Visuals;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import presentation.Central.Main;

public class MleftPane extends VBox {
    private VBox box;
    private Text txt, txt2;
    private Button btnEditProg, btnEditProd, btnAddProg, btnAddAcc;

    public MleftPane(){

        //Top text
        this.box = new VBox();
        this.txt = Main.STN.setNormalTextWidth("Adminastrive", true, 0);
        this.txt2 = Main.STN.setNormalTextWidth("Tools", true, 0);
        this.box.setAlignment(Pos.CENTER);
        this.box.getChildren().addAll(txt, txt2);

        this.btnEditProg = Main.STN.setButtonStyle("Edit Production", 120, 25);
        this.btnEditProd = Main.STN.setButtonStyle("Edit Person", 120, 25);
        this.btnAddProg = Main.STN.setButtonStyle("Add Production", 120, 25);
        this.btnAddAcc = Main.STN.setButtonStyle("Add account", 120, 25);

        //Functionality
        this.btnEditProg.setOnAction( a -> {
            Main.root.setCenter(new VBox());
        });
        this.btnEditProd.setOnAction( a -> {
            Main.root.setCenter(new MEdit());
        });
        this.btnAddProg.setOnAction(e -> {
            Main.root.setCenter(new VBox());
        });
        this.btnAddAcc.setOnAction(e -> {
            Main.root.setCenter(new VBox());
        });

        getChildren().addAll(this.box, this.btnAddProg, this.btnAddAcc, this.btnEditProd, this.btnEditProg);
    }

}
