package presentation.Visuals;

import domain.model.Account;
import domain.model.Production;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import presentation.Central.Main;

public class MCenter extends VBox {

    private Text box1Txt1, box1Txt2, box2Txt1, box2Txt2, box3Txt1, box3Txt2, box3Txt3;
    private VBox box1, box2, boxMaster;
    private HBox box3, mainBox;

    public MCenter(Object obj){
        this.boxMaster = Main.STN.setVBoxDesignDark(new VBox(), Pos.TOP_LEFT, false, Main.STN.colorLight);
        this.mainBox = new HBox();
        this.box1 = new VBox();
        this.box2 = new VBox();
        this.box3 = new HBox();

        this.box1Txt1 = new Text("");
        this.box1Txt2 = new Text("");
        this.box2Txt1 = new Text("");
        this.box2Txt2 = new Text("");
        this.box3Txt1 = new Text("");
        this.box3Txt2 = new Text("");
        this.box3Txt3 = new Text("");

        if(obj instanceof Account){
            this.box1Txt1 = Main.STN.setNormalTextWidth(((Account) obj).getUsername(), true, Main.STN.setTextW_S);
            this.box1Txt2 = Main.STN.setNormalTextWidth(Main.DTS.getDescriptionA(), false, Main.STN.setTextW_S);
            this.box2Txt1 = Main.STN.setNormalTextWidth("Personal description", true, Main.STN.setTextW_L);
            this.box2Txt2 = Main.STN.setNormalTextWidth(Main.DTS.getDescriptionA(), false, Main.STN.setTextW_L);
            this.mainBox.getChildren().addAll(Main.STN.productCreator(Main.DTS.getObj(), ((Account) obj).getUsername(), false), Main.STN.setFilling(), box1, Main.STN.setFilling(), box2);
            if(Main.DTS.getAccessLevel() == 0){
                this.box3Txt1 = Main.STN.setNormalTextWidth("User ID: "+ ((Account) obj).getId(), true, 0);
                this.box3Txt2 = Main.STN.setNormalTextWidth(" Username: "+ ((Account) obj).getUsername(), true, 0);
                this.box3Txt3 = Main.STN.setNormalTextWidth(" Deleted: "+ ((Account) obj).isDeleted(), true, 0);
            }

        }
        else if(obj instanceof Production){
            this.box1Txt1 = Main.STN.setNormalTextWidth(((Production) obj).getName(), true, Main.STN.setTextW_S);
            this.box1Txt2 = Main.STN.setNormalTextWidth(Main.DTS.getDescriptionA(), false, Main.STN.setTextW_S);
            this.box2Txt1 = Main.STN.setNormalTextWidth("Production description", true, Main.STN.setTextW_L);
            this.box2Txt2 = Main.STN.setNormalTextWidth(Main.DTS.getDescriptionA(), false, Main.STN.setTextW_L);
            mainBox.getChildren().addAll(Main.STN.productCreator(Main.DTS.getObj(), ((Production) obj).getName(), false), Main.STN.setFilling(), box1, Main.STN.setFilling(), box2);
            if(Main.DTS.getAccessLevel() == 0){
                this.box3Txt1 = Main.STN.setNormalTextWidth("User ID: "+ ((Production) obj).getId(), true, 0);
                this.box3Txt2 = Main.STN.setNormalTextWidth(" Production ID: "+ ((Production) obj).getProductionId(), true, 0);
                this.box3Txt3 = Main.STN.setNormalTextWidth(" Approved: "+ ((Production) obj).isApprovedCredits(), true, 0);
            }
        }
        this.box1.setPadding(new Insets(0, 5, 5, 5));
        this.box1.getChildren().addAll(this.box1Txt1, this.box1Txt2);
        this.box1.setAlignment(Pos.TOP_LEFT);

        this.box2.setPadding(new Insets(0, 0, 5, 5));
        this.box2.setAlignment(Pos.TOP_LEFT);
        this.box2.getChildren().addAll(this.box2Txt1, this.box2Txt2);

        this.mainBox.setAlignment(Pos.CENTER);
        this.mainBox.setSpacing(5);

        this.boxMaster.getChildren().addAll(this.mainBox, Main.STN.setFillingH());
        this.boxMaster.getChildren().addAll(this.box3, Main.STN.setFillingH());

        this.box3.setAlignment(Pos.TOP_CENTER);
        this.box3.setSpacing(5);
        this.box3.getChildren().addAll(this.box3Txt1, this.box3Txt2, this.box3Txt3);

        setSpacing(5);
        getChildren().addAll(boxMaster);
        setStyle(Main.STN.colorLight);
        setAlignment(Pos.TOP_LEFT);
    }

}