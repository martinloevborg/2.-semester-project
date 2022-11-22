package presentation.GUI;

import domain.PersonController;
import domain.model.Person;
import interfaces.IController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This view exposes an interface used to manage persons.
 */
public class PersonViewController extends AbstractView {

    @FXML
    Button btnOK;
    @FXML
    Button btnDelete1;
    @FXML
    Button btnCancel1;
    @FXML
    Button btnNew;
    @FXML
    Button btnDelete1conf;
    @FXML
    TextField txtName;
    @FXML
    ListView<Person> list;

    /**
     * Used to manipulate and retrieve data related to persons.
     */
    private IController<Integer, Person> personController;

    /**
     * Resets the view to its initial state.
     */
    @Override
    public void initializeView() {
        toggleDeleteButton();
        btnDelete1conf.setVisible(false);
        btnCancel1.setOnAction(e -> App.goBack());

        reloadList();

        btnNew.setOnAction(e -> {
            txtName.setText("");
            list.getSelectionModel().clearSelection();
            btnDelete1.setVisible(false);
            btnDelete1conf.setVisible(false);

        });

        btnOK.setOnAction(e -> {
            Person selection = list.getSelectionModel().getSelectedItem();
            if (selection == null) {
                // Create new.
                selection = new Person(0, txtName.getText());
                if (personController.create(selection)) {
                    reloadList();
                } else {
                    System.out.println("create Error");
                }
            } else {
                // Update.
                selection = new Person(selection.getId(), txtName.getText());
                if (personController.edit(selection)) {
                    reloadList();
                } else {
                    System.out.println("update Error");
                }
            }
        });
    }

    /**
     * Called once when the view loads to set up the initial state. Sets up event handlers.
     *
     * @see <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/Initializable.html">https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/Initializable.html</a>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personController = new PersonController();
        btnDelete1.setOnAction(e -> {
            btnDelete1conf.setVisible(true);
            btnDelete1.setVisible(false);
            btnDelete1conf.setOnAction(e1 -> {
                txtName.setText("");
                if (personController.delete(list.getSelectionModel().getSelectedItem())) {
                    reloadList();
                    btnDelete1conf.setVisible(false);
                    toggleDeleteButton();
                }
            });
        });

        list.getSelectionModel().selectedItemProperty().addListener((observableValue, old, selection) -> {
            if (selection != null) {
                txtName.setText(selection.getName());
            }
            if (!btnDelete1.isVisible()) {
                toggleDeleteButton();
                btnDelete1conf.setVisible(false);
            }
        });
    }

    /**
     * Reloads the list of persons when data has changed.
     */
    private void reloadList() {
        list.getItems().clear();
        list.getItems().addAll(personController.getEntries().values());
    }

    /**
     * Toggles the delete button based on the privilege of the logged in user.
     */
    private void toggleDeleteButton() {
        String name = App.getLoggedInAccountPrivilege().getName().toLowerCase();
        if (App.getLoggedInAccountPrivilege().getName().toLowerCase().equals("administrator")) {
            btnDelete1.setVisible(true);
        } else {
            btnDelete1.setVisible(false);
        }
    }
}