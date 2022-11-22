package presentation.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Serves as a bare minimum for views.
 */
public abstract class AbstractView implements Initializable {
    @FXML protected Button btnCancel;
    @FXML protected Button btnDelete;
    @FXML protected Button btnSubmit;
    @FXML protected Label lblConfirmation;
    @FXML protected Label lblError;

    /**
     * Provides a means to reset the views.
     */
    public abstract void initializeView();
}
