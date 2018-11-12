package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Idelfonso
 */
public class FXMLTelaPrincipalController implements Initializable {

    @FXML
    private MenuBar mnInicio;
    @FXML
    private MenuItem mnCadUsu;
    @FXML
    private MenuItem mnCadCli;
    @FXML
    private MenuItem mnCadOS;
    @FXML
    private Menu mnConsultar;
    @FXML
    private MenuItem mnConsCli;
    @FXML
    private MenuItem mnConsOS;
    @FXML
    private Menu mnSobre;
    @FXML
    private BorderPane bdPrincipal;
    @FXML
    private Menu menuIniciar;
    @FXML
    private Menu mnSair;
    @FXML
    private MenuItem fecharAplicacao;
    @FXML
    private MenuItem info;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void chamarTelaCadUsu(ActionEvent event) throws IOException {

        Parent cadUsu = FXMLLoader.load(getClass().getResource("/view/FXMLCadastroUsuario.fxml"));

        Scene cenaCadUsu = new Scene(cadUsu);
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setTitle("Cadastro de Usuário");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(cenaCadUsu);
        stage.show();

        //bdPrincipal.getChildren().setAll(cadUsu);
    }

    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void chamarTelaCadCli(ActionEvent event) throws IOException {

        Parent cadUsu = FXMLLoader.load(getClass().getResource("/view/FXMLCadastroCliente.fxml"));

        Scene cenaCadUsu = new Scene(cadUsu);
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setTitle("Cadastro de Usuário");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(cenaCadUsu);
        stage.getWidth();
        stage.show();
    }

    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void chamarTelaCadOS(ActionEvent event) throws IOException {

        Parent cadUsu = FXMLLoader.load(getClass().getResource("/view/FXMLCadastroOS.fxml"));

        Scene cenaCadUsu = new Scene(cadUsu);
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setTitle("Cadastro de Usuário");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(cenaCadUsu);
        stage.getWidth();
        stage.show();
    }

    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void infoSobreAction(ActionEvent event) throws IOException {

        Parent telaSobre = FXMLLoader.load(getClass().getResource("/view/FXMLSobre.fxml"));

        Scene cenaCadUsu = new Scene(telaSobre);
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setTitle("Cadastro de Usuário");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(cenaCadUsu);
        stage.show();

    }

    /**
     * 
     * @param event 
     */
    @FXML
    private void fecharAplicacaoAction(ActionEvent event) {
        System.exit(0);
    }

}
