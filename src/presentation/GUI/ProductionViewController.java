package presentation.GUI;

import domain.ProductionController;
import domain.model.Production;
import interfaces.IController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This view exposes an interface used to manage productions.
 */
public class ProductionViewController extends AbstractView {
    @FXML private TextField txtProductionId;
    @FXML private TextField txtName;
    @FXML private CheckBox chbApproved;
    @FXML private Label lblApproved;

    /**
     * Used to manipulate and retrieve data related to productions.
     */
    private IController<Integer, Production> productionController;
    /**
     * Currently selected production.
     */
    private Production selection = null;

    /**
     * Prepares the interface to create a new production.
     */
    public void create() {
        // Reset view to default.
        initializeView();
        btnDelete.setVisible(false);
        lblConfirmation.setText("Opret ny production");
        lblApproved.setVisible(false);
        chbApproved.setVisible(false);

        // Adjust submit button to handle create.
        btnSubmit.setText("Opret");
        btnSubmit.setOnAction(event -> {
            int productionId = validateProductionId();
            if (productionId == -1) {
                return;
            }
            Production toCreate = new Production(0, productionId, txtName.getText(), App.getLoggedInAccount().getId(), false, false);
            if (productionController.create(toCreate)) {
                // Update went well.
                App.goBack();
            }
            else {
                // Error.
                lblError.setText("Fejl under oprettelse af produktion.");
                lblError.setVisible(true);
            }
        });
    }

    /**
     * Prepares the interface to edit a production.
     * @param production the production to edit.
     */
    public void edit(Production production) {
        // Reset view to default.
        initializeView();
        btnDelete.setVisible(true);
        if (App.getLoggedInAccount() != null && App.getLoggedInAccountPrivilege() != null) {
            if (App.getLoggedInAccountPrivilege().getName().toLowerCase().equals("administrator")) {
                chbApproved.setVisible(true);
                lblApproved.setVisible(true);
            }
        }

        // Populate fields with selected production.
        selection = production;
        lblConfirmation.setText("Rediger produktion");
        txtProductionId.setText(production.getProductionId() + "");
        txtName.setText(production.getName());
        chbApproved.setSelected(production.isApprovedCredits());

        // Adjust submit button to handle edit.
        btnSubmit.setText("Gem");
        btnSubmit.setOnAction(event -> {
            int productionId = validateProductionId();
            if (productionId == -1) {
                return;
            }
            Production toUpdate = new Production(production.getId(), productionId, txtName.getText(),
                    production.getUserId(), chbApproved.isSelected(), production.isDisabled());

            if (productionController.edit(toUpdate)) {
                // Update went well.
                App.goBack();
            }
            else {
                // Error.
                lblError.setText("Fejl under opdatering af produktion.");
                lblError.setVisible(true);
            }
        });
    }

    /**
     * Prepares the interface to delete the currently selected production.
     */
    public void delete() {
        btnDelete.setVisible(false);

        // Disable fields to prevent edits.
        txtProductionId.setDisable(true);
        txtName.setDisable(true);
        chbApproved.setDisable(true);
        lblConfirmation.setText("Ønsker du at slette denne produktion?");

        // Adjust submit button to handle delete.
        btnSubmit.setText("Slet");
        btnSubmit.setOnAction(event -> {
            Production toDelete = new Production(selection.getId(), selection.getProductionId(), selection.getName(),
                    selection.getUserId(), selection.isApprovedCredits(), true);

            if (productionController.edit(toDelete)) {
                // Update went well.
                App.goBack();
            }
            else {
                // Error.
                lblError.setText("Fejl under slet af produktion.");
                lblError.setVisible(true);
            }
        });
    }

    /**
     * Converts the text representation of the production id to an integer.
     * @return returns the production id. Returns -1 on encountered error.
     */
    private int validateProductionId() {
        int productionId = -1;
        try {
            productionId = Integer.parseInt(txtProductionId.getText());
        }
        catch (NumberFormatException e) {
            lblError.setText("Produktionsid skal være et tal");
            lblError.setVisible(true);

        }
        return productionId;
    }

    /**
     * Called once when the view loads to set up the initial state. Sets up event handlers.
     * @see <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/Initializable.html">https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/Initializable.html</a>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productionController = new ProductionController();
        btnCancel.setOnAction(e -> App.goBack());
        btnDelete.setOnAction(e -> delete());

        initializeView();
        btnCancel.layoutXProperty().addListener((observableValue, number, t1) -> {
            btnDelete.setLayoutX(btnCancel.getLayoutX()+120);
        });

    }

    /**
     * Resets the view to its initial state.
     */
    @Override
    public void initializeView() {
        selection = null;
        lblConfirmation.setText("");
        lblError.setText("");
        txtName.setText("");
        txtProductionId.setText("");
        txtName.setDisable(false);
        txtProductionId.setDisable(false);
        chbApproved.setDisable(false);
        chbApproved.setIndeterminate(false);
        chbApproved.setSelected(false);
        chbApproved.setVisible(false);
        lblApproved.setVisible(false);
    }
}
