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

        Parent cadUsuario = FXMLLoader.load(getClass().getResource("/view/FXMLCadastroUsuario.fxml"));

        Scene cenaCadUsu = new Scene(cadUsuario);
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setTitle("Usuários");
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

        Parent cadCliente = FXMLLoader.load(getClass().getResource("/view/Cliente.fxml"));

        Scene cenaCadCli = new Scene(cadCliente);
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setTitle("Clientes");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(cenaCadCli);
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

        Parent cadOS = FXMLLoader.load(getClass().getResource("/view/FXMLCadastroOS.fxml"));

        Scene cenaCadOs = new Scene(cadOS);
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setTitle("Ordens de Serviços");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(cenaCadOs);
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

        Scene cenaTelaSobre = new Scene(telaSobre);
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setTitle("Sobre");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(cenaTelaSobre);
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
