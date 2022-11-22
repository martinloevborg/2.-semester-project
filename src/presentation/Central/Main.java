package presentation.Central;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presentation.Visuals.*;

public class Main extends Application
{
    public static Settings STN = new Settings();
    public static dataSector DTS = new dataSector();
    public static BorderPane root;
    public static Stage stage;

    @Override
    public void start(Stage stage) {
        this.root = new MForside();
        stage.setTitle("ViewR");
        Scene scene = new Scene(this.root, 1000, 500);
        stage.getIcons().add(new Image("presentation\\images\\logoStage.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setCenterLogin(){
        root.setCenter(STN.setVBoxDesignDark(new MloginPage(), Pos.CENTER, false, Main.STN.colorLight));
    }
    public static void setCenterHome(){
        root.setCenter(new MCenter(Main.DTS.getObj()));
    }
    public static void setAdminTools(){
        if (DTS.getAccessLevel() <= 1){
            root.setLeft(Main.STN.setVBoxDesignDark(new MleftPane(), Pos.TOP_CENTER, false, Main.STN.colorDark));
        }
        else{
            root.setLeft(null);
        }
    }
    public void load(String[] args) {
        launch(args);
    }

    public void initialize(){
        stage = new Stage();
        this.root = new MForside();
        stage.setTitle("ViewR");
        Scene scene = new Scene(this.root, 1000, 500);
        stage.getIcons().add(new Image("presentation\\images\\logoStage.png"));
        stage.setScene(scene);
    }

    public static void show(){
        stage.show();
    }
}
