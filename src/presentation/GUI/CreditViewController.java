package presentation.GUI;

import domain.CreditController;
import domain.PersonController;
import domain.PrivilegeController;
import domain.RoleController;
import domain.model.*;
import interfaces.IController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This view exposes an interface used to manage credits.
 */
public class CreditViewController extends AbstractView {

    @FXML
    ComboBox<Person> cbxPerson;
    @FXML
    ComboBox<Role> cbxRole;
    @FXML
    protected Button btnCancel;
    @FXML
    protected Button btnDelete;
    @FXML
    protected Button btnSubmit;
    @FXML
    protected Label lblError;
    @FXML
    protected Label lblConfirmation;

    /**
     * Used to manipulate and retrieve data related to credits.
     */
    IController<Credit.Key, Credit> creditController;
    /**
     * Used to manipulate and retrieve data related to roles.
     */
    IController<Integer, Role> roleController;
    /**
     * Used to manipulate and retrieve data related to persons.
     */
    IController<Integer, Person> personController;
    /**
     * Current selected credit.
     */
    Credit credit = null;

    /**
     * Prepares the interface to create a new credit.
     * @param p the production that the credits are assigned to.
     */
    public void create(Production p) {
        initializeView();

        btnDelete.setVisible(false);

        btnSubmit.setText("Opret");
        btnSubmit.setOnAction(actionEvent -> {
            Credit toCreate = new Credit(p.getId(), cbxPerson.getValue().getId(), cbxRole.getValue().getId(), null, null);

            if (creditController.create(toCreate)) {
                // update went well.
                App.goBack();
            } else {
                // error.
                lblError.setText("Fejl under oprettelse.");
            }
        });

    }

    /**
     * Prepares the interface to edit a credit.
     * @param c the credit to edit.
     */
    public void edit(Credit c) {
        // clear data from last use.
        initializeView();
        credit = c;
        btnDelete.setVisible(true);
        cbxPerson.setValue(personController.getEntry(c.getId().getPersonId()));
        cbxRole.setValue(roleController.getEntry(c.getId().getRoleId()));

        // adjust button to handle edit.
        btnSubmit.setText("Rediger");
        cbxPerson.setDisable(true);
        btnSubmit.setOnAction(event -> {
            Credit toUpdate = new Credit(c.getId().getProductionId(), c.getId().getPersonId(), cbxRole.getValue().getId(), c.getPersonName(), c.getRoleName());

            if (creditController.edit(c, toUpdate)) {
                // update went well.
                App.goBack();
            } else {
                // error.
                lblError.setText("Fejl under opdatering.");
            }
        });
    }

    /**
     * Prepares the interface to delete the currently selected credit.
     */
    public void delete() {
        // adjust button to handle edit.
        btnSubmit.setText("Slet");
        btnDelete.setVisible(false);
        cbxRole.setDisable(true);
        btnSubmit.setOnAction(event -> {

            if (creditController.delete(credit)) {
                // update went well.
                App.goBack();
            } else {
                // error.
                lblError.setText("Fejl under opdatering.");
            }
        });
    }

    /**
     * Resets the view to its default state.
     */
    @Override
    public void initializeView() {
        IController<Integer, Privilege> pc = new PrivilegeController();

        if(App.getLoggedInAccount() != null && App.getLoggedInAccountPrivilege() != null){
            if (App.getLoggedInAccountPrivilege().getName().toLowerCase().equals("administrator")){
                btnDelete.setVisible(true);
            } else {
                btnDelete.setVisible(false);
            }
        }

        lblConfirmation.setText("");
        lblError.setText("");
        cbxPerson.getItems().clear();
        cbxPerson.getItems().addAll(personController.getEntries().values());

        cbxPerson.setDisable(false);
        cbxRole.setDisable(false);
    }

    /**
     * Called once when the view loads to set up the initial state. Sets up event handlers.
     * @see <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/Initializable.html">https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/Initializable.html</a>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        creditController = new CreditController();
        roleController = new RoleController();
        personController = new PersonController();

        initializeView();
        cbxRole.getItems().addAll(roleController.getEntries().values());
        btnDelete.setOnAction(e -> delete());
        btnCancel.setOnAction(e -> App.goBack());
    }
}
