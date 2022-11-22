package presentation.GUI;

import domain.CreditController;
import domain.ProductionController;
import domain.model.Credit;
import domain.model.Production;
import interfaces.IController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentation.Central.Main;
import presentation.Visuals.MEdit;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * The main interface of the application.
 * Provides functionally to switch between the different views.
 * Displays productions and its credits.
 */
public class MasterView extends AbstractView {
    @FXML private ListView<Production> productionList;
    @FXML private ListView<Credit> creditList;
    @FXML private Button search;
    @FXML private Button login;
    @FXML private TextField txt;
    @FXML private MenuButton btnMenu;
    @FXML private MenuItem btnProduction;
    @FXML private MenuItem createCredit;
    @FXML private MenuItem person;
    @FXML private MenuItem brugeradmin;
    @FXML private CheckBox cbxShowOwn;

    /**
     * Used to manipulate and retrieve data related to productions.
     */
    private IController<Integer, Production> productionController;
    /**
     * Used to manipulate and retrieve data related to credits.
     */
    private IController<Credit.Key, Credit> creditController;
    /**
     * Currently selected production.
     */
    private Production selection;

    /**
     * Called once when the view loads to set up the initial state. Sets up event handlers.
     * @see <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/Initializable.html">https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/Initializable.html</a>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productionController = new ProductionController();
        search();

        person.setOnAction(e -> {
            App.getController(App.View.person).initializeView();
            App.changeScene(App.View.person);
        });

        brugeradmin.setOnAction(e -> {
          // Stage root = new Stage();
          // root.setScene(new Scene(new MEdit(), 1200, 800));
          // root.show();
            App.changeScene();
        });
        
        login.setOnAction(e -> {
            App.getController(App.View.login).initializeView();
            App.changeScene(App.View.login);
        });

        btnMenu.setVisible(false);
        cbxShowOwn.setVisible(false);

        creditController = new CreditController();
        productionList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && App.getLoggedInAccount() != null) {
                    ((ProductionViewController) App.getController(App.View.production)).edit(productionList.getSelectionModel().getSelectedItem());
                    Production p = productionList.getSelectionModel().getSelectedItem();
                    if (p.getUserId() == App.getLoggedInAccount().getId() || App.getLoggedInAccountPrivilege().getName().toLowerCase().equals("administrator")) {
                        ((ProductionViewController) App.getController(App.View.production)).edit(p);
                        App.changeScene(App.View.production);
                    }
                }
            }
        });
        creditList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && App.getLoggedInAccount() != null) {
                    ((CreditViewController) App.getController(App.View.credit)).edit(creditList.getSelectionModel().getSelectedItem());
                    Credit c = creditList.getSelectionModel().getSelectedItem();
                    Production p = productionController.getEntry(c.getId().getProductionId());
                    if (p != null) {
                        if (p.getUserId() == App.getLoggedInAccount().getId() || App.getLoggedInAccountPrivilege().getName().toLowerCase().equals("administrator")) {
                            ((CreditViewController) App.getController(App.View.credit)).edit(c);
                            App.changeScene(App.View.credit);
                        }
                    }
                }
            }
        });

        productionList.getSelectionModel().selectedItemProperty().addListener((observableValue, production, t1) -> {
            selection = productionList.getSelectionModel().getSelectedItem();
            if (selection != null) {
                int id = productionList.getSelectionModel().getSelectedItem().getId();
                creditList.getItems().clear();
                Map<Credit.Key, Credit> credit = creditController.getEntries(id);
                creditList.getItems().addAll(credit.values());
            }
        });

        btnProduction.setOnAction(e -> {
            ((ProductionViewController) App.getController(App.View.production)).create();
            App.changeScene(App.View.production);
        });

        createCredit.setOnAction(e -> {
            if (selection.getUserId() == App.getLoggedInAccount().getId()) {
                ((CreditViewController) App.getController(App.View.credit)).create(selection);
                App.changeScene(App.View.credit);
            }
        });

        // Update the view when to either display own productions or all.
        cbxShowOwn.setOnAction(e-> {
            search();
        });

        productionList.layoutXProperty().addListener((observableValue, number, t1) -> {
            btnMenu.setLayoutX(productionList.getLayoutX());
            cbxShowOwn.setLayoutX(productionList.getLayoutX() + 306);
        });
    }

    /**
     * Event which queries the data and updates the list of productions.
     */
    public void search() {
        Map<Integer, Production> data;
        if (App.getLoggedInAccount() != null && App.getLoggedInAccountPrivilege() != null) {
            if (App.getLoggedInAccountPrivilege().getName().toLowerCase().equals("producer") && cbxShowOwn.isSelected()) {
                data = productionController.getEntries(App.getLoggedInAccount().getId());
            } else {
                data = productionController.getEntries();
            }
        } else {
            data = productionController.getEntries();
        }
        productionList.getItems().clear();
        if (txt.getText().isEmpty()) {
            for (Production p : data.values()){
                if (!p.isDisabled()) {
                    productionList.getItems().add(p);
                }
            }
        } else {
            for (Production p : data.values()) {
                if (p.getName().toLowerCase().contains(txt.getText().toLowerCase())) {
                    if (!p.isDisabled()) {
                        productionList.getItems().add(p);
                    }
                }
            }
        }
    }

    /**
     * Resets the view to its initial state.
     */
    @Override
    public void initializeView() {
        search();
        boolean exists = false;
        if (selection != null) {
            if (productionController.getEntry(selection.getId()) != null) {
                if (productionList.getItems().contains(selection)) {
                    productionList.getSelectionModel().select(selection);
                    creditList.getItems().clear();
                    creditList.getItems().addAll(creditController.getEntries(
                            productionList.getSelectionModel().getSelectedItem().getId()).values());
                    exists = true;
                }
            }
        }

        if (!exists) {
            creditList.getItems().clear();
        }
    }

    /**
     * Enables the admin tools to manage various entities.
     */
    public void enableAdmin(){
        btnMenu.setVisible(true);
        cbxShowOwn.setVisible(false);
        brugeradmin.setVisible(true);

        if (App.getLoggedInAccount() != null && App.getLoggedInAccountPrivilege() != null) {
            if (App.getLoggedInAccountPrivilege().getName().toLowerCase().equals("producer")) {
                cbxShowOwn.setVisible(true);
                brugeradmin.setVisible(false);
            }
        }
    }
}