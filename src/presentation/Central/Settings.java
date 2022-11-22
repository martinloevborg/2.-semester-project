package presentation.Central;

import domain.model.Account;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.Visuals.MCenter;

public class Settings {

    //List of general settings - creates symmetry and equality
    public final String
            colorLight = "-fx-background-color: rgb(60, 60, 60);",
            colorSLight = "-fx-background-color: rgb(120, 120, 120);",
            colorDark = "-fx-background-color: rgb(35, 35, 35);",
            colorButton = "-fx-background-color: rgb(180, 180, 180);",
            colorButtonEntered = "-fx-background-color: rgb(180, 200, 180);";
    public final Insets
            insetsNormal = new Insets(3),
            insetsLarge = new Insets(10);
    public final int
            setTextW_L = 400,
            setTextW_S = 140,
            setSpacing = 5;


    public Settings(){
    }

    public VBox productCreator(Object obj, String name, boolean addAction){
        Main.DTS.setObj(obj);

        VBox box = new VBox();
        Text txt = new Text("?");
        txt.setStyle("-fx-font-size: 80");
        box.getChildren().addAll(txt, Main.STN.setNormalTextWidth(name, true, 0));

        //Fancy green glow enter/exit
        box.setOnMouseEntered(e1 ->{
            box.setOpacity(0.5);
            box.setStyle("-fx-background-color: rgba(180, 200, 180, 0.8); -fx-background-radius: 2;");
        });
        box.setOnMouseExited(e2 ->{
            box.setOpacity(1);
            box.setStyle("-fx-background-color: rgba(120, 120, 120, 0.3); -fx-background-radius: 2;");
        });

        //Add action when clicked yes/no
        if (addAction == true){
            box.setOnMouseClicked( e -> {
                Main.root.setCenter(new MCenter((Account) Main.DTS.getObj()));
            });
        }

        //Custom Settings
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(100, 160);
        box.setStyle("-fx-background-color: rgba(120, 120, 120, 0.3); -fx-background-radius: 2;");

        return box;
    }
    public VBox setVBoxDesignDark(VBox box, Pos position, boolean pWidth, String color) {
        box.setSpacing(this.setSpacing);
        box.setPadding(this.insetsNormal);
        box.setAlignment(position);
        box.setFillWidth(true);
        if(pWidth == true){
            box.setPrefWidth(120);
        }
        box.setStyle(color);
        return box;
    }
    public HBox setHBoxDesign(HBox box){
        box.setSpacing(5);
        box.setPadding(this.insetsLarge);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setStyle(this.colorDark);
        return box;
    }
    public Button setButtonStyle(String name, double w, double h){
        Button btn = new Button(name);
        btn.setStyle("-fx-background-radius: 0;"+this.colorButton+"-fx-font-weight: normal");
        btn.setMinSize(w, h);
        btn.setMaxSize(w, h);

        btn.setOnMouseEntered(e -> {
            btn.setStyle("-fx-background-radius: 0;"+this.colorButtonEntered+"-fx-font-weight: bold");
        });
        btn.setOnMouseExited(e -> {
            btn.setStyle("-fx-background-radius: 0;"+this.colorButton+"-fx-font-weight: normal");
        });

        return btn;
    }
    public TextField setTextField(String name){
        TextField txtf = new TextField();
        txtf.setText(name);
        txtf.setOnMouseClicked(e -> {
            txtf.selectAll();
        });
        txtf.setOnKeyReleased(e2 -> {
            if (txtf.getText().isEmpty()){
                txtf.setText(name);
            }
        });
        txtf.setOnKeyPressed(e3->{
            if (txtf.getText().equals(name)){
                txtf.setText("");
            }
        });
        return txtf;
    }
    public Text setNormalTextWidth(String name, boolean tick, int width){
        Text txt = new Text(name);
        if (width != 0) {
            txt.setWrappingWidth(width);
        }
        txt.setFill(Color.WHITE);
        if(tick == true){
            txt.setStyle("-fx-font-weight: BOLD");
        }
        return txt;
    }

    //Set Logo size - ratio preservation
    public ImageView logoCreator(Boolean bs){
        ImageView imgw = new ImageView();
        imgw.setImage(new Image("presentation\\images\\logo2.png"));
        imgw.setPreserveRatio(true);

        if (bs == true){
            imgw.setFitHeight(25);
        }
        else if (bs == false){
            imgw.setFitHeight(250);
        }
        return imgw;
    }

    //Error Message window
    public void setError(){
        Stage stg = new Stage();
        VBox box = new VBox();
        box.setStyle(this.colorSLight);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        stg.initStyle(StageStyle.UNDECORATED);
        Button btn = new Button("Close");
        box.getChildren().addAll(Main.STN.setNormalTextWidth("Write a valid Username!", false, this.setTextW_S), btn);
        btn.setOnAction(e -> {
            stg.close();
            Main.root.setDisable(false);
        });
        Main.root.setDisable(true);
        stg.setScene(new Scene(box, 200, 120));
        stg.setAlwaysOnTop(true);
        stg.show();
    }

    //Seperation column/pillars
    public HBox setFilling(){
        HBox box = new HBox();
        int maxHeight = 2;
        box.setFillHeight(true);
        box.setStyle(Main.STN.colorSLight);
        box.setMaxWidth(maxHeight);
        box.setMinWidth(maxHeight);
        return box;
    }
    public VBox setFillingH(){
        VBox box = new VBox();
        int maxWidth = 2;
        box.setFillWidth(true);
        box.setStyle(Main.STN.colorSLight);
        box.setMaxHeight(maxWidth);
        box.setMinHeight(maxWidth);
        return box;
    }

    //Admin hidden login Panel info
    public void getShowMe(){
        Stage stg = new Stage();
        VBox box = new VBox();
        Button btn = new Button("Close");

        for(Account user : Main.DTS.getUsers()){
            box.getChildren().add(Main.STN.setNormalTextWidth("U: " + user.getUsername() + "\nP: " + user.getPassword(), false, 0));
        }
        btn.setOnAction(e -> {
            stg.close();
        });

        box.setStyle(this.colorSLight);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(5);
        box.getChildren().add(btn);
        stg.setScene(new Scene(box, 240, 140));
        stg.setAlwaysOnTop(true);
        stg.setResizable(false);
        stg.show();
    }
}
