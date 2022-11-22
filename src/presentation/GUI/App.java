package presentation.GUI;

import domain.AccountController;
import domain.model.Account;
import domain.model.Privilege;
import interfaces.IAuthenticator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.Central.Main;
import presentation.Visuals.MEdit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Shell of the application. Provides functionally to load views and to switch between views.
 * Makes available the functionally to sign in and retrieve information about the signed in user.
 */
public class App extends Application {
    /**
     * Stores references to the views of the application.
     */
    private static Map<View, Parent> views = new HashMap<>();
    /**
     * Stores references to the controllers of the views of the application.
     */
    private static Map<View, AbstractView> controllers = new HashMap<>();
    /**
     * The only scene used to provide functionally between views.
     */
    private static Scene scene;
    /**
     * Holds a references to the previous view when changing to the a new one.
     */
    private static Parent oldRoot;
    /**
     * Used to sign in users and holds information about the signed in user.
     */
    private static IAuthenticator iau= new AccountController();

    /**
     * Starts the application by loading the FXML and sets up the stage.
     * @param stage the primary stage used by the application
     * @throws Exception throws exception if it fails to load the FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {
        loadViews();
        new Main().initialize();
        oldRoot = views.get(View.master);
        scene = new Scene(oldRoot);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes the root of the scene in order to switch views.
     * @param view the view to switch to.
     */
    public static void changeScene(View view) {
        oldRoot = scene.getRoot();
        scene.setRoot(views.get(view));
    }

    /**
     * Changes the root of the scene to edit person.
     */
    public static void changeScene() {
        oldRoot = scene.getRoot();
        scene.setRoot(new MEdit());
    }
    /**
     * Returns to the previous view.
     */
    public static void goBack() {
        scene.setRoot(oldRoot);
        controllers.get(View.master).initializeView();
    }

    /**
     * Gets the controller of a specified view.
     * @param view the view of the controller.
     * @return the controller associated with the specified view.
     */
    public static AbstractView getController(View view) {
        return controllers.get(view);
    }

    /**
     * Loads the FXML of all the views
     * @throws IOException throws IOException if it fails to load the views.
     */
    private void loadViews() throws IOException {
        for (View v : View.values()) {
            FXMLLoader fxml = new FXMLLoader(App.class.getResource("FXML/" + v.get() + ".fxml"));
            views.put(v, fxml.load());
            controllers.put(v, fxml.getController());
        }
    }

    /**
     * Gets the signed in user account.
     * @return the signed in user account.
     */
    public static Account getLoggedInAccount() {
        return iau.getLoggedInAccount();
    }

    /**
     * Helper method to used to start the application.
     * @param load the arguments from the command line.
     */
    public void load (String[] load){
        launch(load);
    }

    /**
     * Enum containing the views. Each view references an FXML file.
     */
    public enum View {
        master("MasterView"),
        production("ProductionView"),
        credit("CreditView"),
        login("LoginView"),
        person("PersonView");

        private String view;

        View(String view) {
            this.view = view;
        }

        public String get() {
            return view;
        }
    }

    /**
     * Attempts to sign in the user view the specified username and password.
     * @param username the username to sign in as.
     * @param password the password that corresponds to the username.
     * @return true if the sign in is successful. Otherwise returns false.
     */
    public static boolean login(String username, String password){
        boolean result = iau.login(username, password);
        if(result){
            ((MasterView) controllers.get(View.master)).enableAdmin();
        }
        return result;
    }

    /**
     * Gets the privilege of the signed in user.
     * @return the privilege of the signed in user.
     */
    public static Privilege getLoggedInAccountPrivilege(){
        return iau.getLoggedInAccountPrivilege();
    }
}
