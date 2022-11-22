package presentation.Visuals;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import presentation.Central.Main;

public class MForside extends BorderPane{

    public MForside(){
        setTop(Main.STN.setVBoxDesignDark(new MtopPane(), Pos.TOP_LEFT, false, Main.STN.colorDark));
        if (Main.DTS.getAccessLevel() == 0){
            setLeft(Main.STN.setVBoxDesignDark(new MleftPane(), Pos.TOP_CENTER, false, Main.STN.colorDark));
        }
        setCenter(Main.STN.setVBoxDesignDark(new MloginPage(), Pos.CENTER, false, Main.STN.colorLight));
    }
    }
