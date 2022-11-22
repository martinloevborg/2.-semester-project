package presentation.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This view exposes an interface used to sign in.
 */
public class LoginViewController extends AbstractView implements Initializable {
    @FXML
    private Label lbl;
    @FXML
    private Label lbl1;
    @FXML
    private TextField txt;
    @FXML
    private Label lbl2;
    @FXML
    private PasswordField txt1;
    @FXML
    private Button btn;
    @FXML
    private Button btn1;

    /**
     * Called once when the view loads to set up the initial state. Sets up event handlers.
     * @see <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/Initializable.html">https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/Initializable.html</a>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn.setOnAction(e -> {
            if(App.login(txt.getText(), txt1.getText())){
                App.goBack();
            } else {
                txt.setStyle("-fx-text-fill: red");
                txt1.setStyle("-fx-text-fill: red");
            }
        });

        btn1.setOnAction(e -> App.goBack());
    }

    /**
     * Resets the view to its default state.
     */
    @Override
    public void initializeView() {
        txt.setText("");
        txt1.setText("");
        txt.setStyle("-fx-text-fill: black");
        txt1.setStyle("-fx-text-fill: black");
    }
}
