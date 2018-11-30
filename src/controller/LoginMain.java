package controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Idelfonso
 */
public class LoginMain extends Application {

    /**
     * 
     */
    private static Stage stage;
    private static Scene cenaLogin, cenaPrincipal;

    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * 
     * @param primaryStage
     * @throws IOException 
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        stage = primaryStage;
        primaryStage.setTitle("Cadastro de OS");

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/FXMLLogin.fxml"));
        cenaLogin = new Scene(fxmlLogin, 400, 600);

        Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("/view/FXMLTelaPrincipal.fxml"));
        cenaPrincipal = new Scene(fxmlPrincipal);

        //primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.getIcons().add(new Image("/image/Document_48px.png"));
        primaryStage.setScene(cenaLogin);
        primaryStage.show();
    }
//==============================================================================
    /**
     * 
     * @param src
     * @param userData 
     */
    public static void changeScreen(String src, Object userData) {
        switch (src) {
            case "login":
                stage.setScene(cenaLogin);
                notifyAllListeners("login", userData);
                break;
            case "principal":
                stage.setScene(cenaPrincipal);
                stage.setMaximized(true);
                notifyAllListeners("principal", userData);
                break;
            case "usuario":
                notifyAllListeners("usuario", userData);
        }
    }
    
    /**
     * 
     * @param src 
     */
    public static void changeScreen(String src) {
        changeScreen(src, null);
    }
//==============================================================================
    /**
     * 
     */
    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();
    
    /**
     * 
     */
    public static interface OnChangeScreen {
        void onScreenChanged(String newScreen, Object userData);
    }
 
    /**
     * 
     * @param newListener 
     */
    public static void addOnChangeScreenListener(OnChangeScreen newListener) {
        listeners.add(newListener);
    }

    /**
     * 
     * @param newScreen
     * @param userData 
     */
    private static void notifyAllListeners(String newScreen, Object userData) {
        for(OnChangeScreen l : listeners ){
            l.onScreenChanged(newScreen, userData);
        }
    }
    //--------------------------------------------------------------------------    

}