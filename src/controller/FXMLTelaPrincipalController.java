package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Usuario;

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

    private Usuario usuario;
    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblPerfil;
    @FXML
    private Label lblCpf;
    @FXML
    private HBox hbBottom;
    @FXML
    private Label lblData;
    @FXML
    private Label lblRelogio;

//==============================================================================    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /**
         * Método para receber o usuario vindo da tela de login e poder
         * trabalhar com os dados dele na tela principal.
         */
        LoginMain.addOnChangeScreenListener(new LoginMain.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                usuario = (Usuario) userData;
                verificarPerfil();
                //System.out.println(usuario);

                lblUsuario.setText(usuario.getNome());
                lblPerfil.setText(usuario.getPerfil());
                lblCpf.setText(usuario.getCpf());
                lblData.setText(dataHora());
            }
        });
        lblRelogio.setEffect(new DropShadow(10, Color.RED));

    }
//==============================================================================
    
    private String dataHora() {
        Locale locale = new Locale("pt", "BR");
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'", locale);
        return formatador.format(calendar.getTime());

    }

    private void verificarPerfil() {
        if (!usuario.getPerfil().equals("Admin")) {
            mnCadUsu.setVisible(false);
        }
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
        //Stage stage = new Stage();
        stage.setTitle("Clientes");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(cenaCadCli);
        stage.show();
        
        // Passando o usuario logado para a tela de cadastro de Ordem de Serviço
        LoginMain.changeScreen("usuario", usuario);
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
        stage.show();
        
        // Passando o usuario logado para a tela de cadastro de Ordem de Serviço
        LoginMain.changeScreen("usuario", usuario);
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

    @FXML
    private void consultarCliente(ActionEvent event) throws IOException {
        Parent telaSobre = FXMLLoader.load(getClass().getResource("/view/ConsultarCliente.fxml"));

        Scene cenaTelaSobre = new Scene(telaSobre);
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setTitle("Cliente");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(cenaTelaSobre);
        stage.show();
    }

    @FXML
    private void consultarOS(ActionEvent event) throws IOException {
        Parent telaSobre = FXMLLoader.load(getClass().getResource("/view/ConsultarOS.fxml"));

        Scene cenaTelaSobre = new Scene(telaSobre);
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setTitle("Ordem de Serviço");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(cenaTelaSobre);
        stage.show();
    }

}
