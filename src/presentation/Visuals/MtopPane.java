package presentation.Visuals;

import domain.ProductionController;
import domain.model.Production;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.Central.Main;

public class MtopPane extends VBox {

    public HBox box;
    public Button btn;

    public MtopPane() {
        //Nodes
        this.box = Main.STN.setHBoxDesign(new HBox());
        this.btn = Main.STN.setButtonStyle("Login", 100, 25);

        //Functionality
        this.btn.setOnAction(ac -> {
            Main.setCenterLogin();
        });

        this.box.getChildren().addAll(Main.STN.logoCreator(true), searchBar(), this.btn);
        getChildren().addAll(this.box, Main.STN.setHBoxDesign(menuList()));
    }

    //Top Menu Bar
    public HBox menuList() {
        //Nodes
        HBox box = new HBox();
        Button homeBTN = Main.STN.setButtonStyle("Home", 100, 25);

        //Functionality
        homeBTN.setOnAction(a1 -> { Main.setCenterHome();
        });

        box.getChildren().add(homeBTN);
        return box;
    }
    public TextField searchBar() {

        TextField txtf = new TextField();
        txtf.setText("Search");
        txtf.setAlignment(Pos.CENTER);
        txtf.setPrefWidth(2200);
        txtf.setStyle("-fx-background-color: rgb(120,120,120); -fx-background-radius: 0;");

        txtf.setOnMouseClicked(e -> {
            txtf.selectAll();
        });
        txtf.setOnKeyReleased(e2 -> {
            if (txtf.getText().isEmpty()) {
                txtf.setText("Search");
            }
        });
        txtf.setOnKeyPressed(e3 -> {
            if (txtf.getText().equals("Search")) {
                txtf.setText("");
            }
        });

        txtf.setOnAction(e4 -> {
            Main.DTS.getMapProd().clear();
            ProductionController c1 = new ProductionController();

            for (Production a : c1.getEntries().values()) {
                if (a.getName().toLowerCase().contains(txtf.getText().toLowerCase())) {
                    Main.DTS.getMapProd().add(a);
                }
            }
            Main.setCenterHome();
        });
        return txtf;
    }
}
