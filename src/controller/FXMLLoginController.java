package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DAOUsuario;
import model.Usuario;


/**
 * FXML Controller class
 *
 * @author Idelfonso
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXTextField txtSenha;
    @FXML
    private JFXButton btnSair;

    private String user;
    private String pass;
    @FXML
    private JFXButton btnEntrar;
    @FXML
    private BorderPane borderPane;
    @FXML
    private VBox vb;
   

    /**
     *
     */
    public FXMLLoginController() {
 
    }
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
    }
//==============================================================================
    
    /**
     *
     * @param e
     */
    @FXML
    private void btnEntrarAction(ActionEvent e) {
        logar();
    }

    /**
     * 
     * @param event 
     */
    @FXML
    private void entrarComEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            logar();
        }
    }

    /**
     *
     * @param e
     */
    @FXML
    private void btnSairAction(ActionEvent e) {
        System.exit(0);
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void sairComEnter(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            System.exit(0);
        }
    }

    /**
     *
     */
    private void limparCampos() {
        txtLogin.setText("");
        txtSenha.setText("");
    }

    /**
     *
     * @throws IOException
     */
    private void chamarTelaPrincipal() throws IOException {
        Parent novaTela = FXMLLoader.load(getClass().getResource("FXMLTelaPrincipal.fxml"));
        Scene sc = new Scene(novaTela);
        Stage st = new Stage(StageStyle.DECORATED);
        st.setTitle("Tela Principal");
        st.setScene(sc);
        st.initModality(Modality.APPLICATION_MODAL);
        st.show();
    }
    

    //--------------------------------------------------------------------------
    private void logar() {
        this.user = txtLogin.getText();
        this.pass = txtSenha.getText();
        
        txtSenha.setText(pass.replaceAll("\\w", "*"));
        System.out.println(user +" | "+ pass +"\n"+ txtLogin.getText() +" | "+txtSenha.getText());
        
        if(DAOUsuario.logarUsuario(user, pass) != null){
            Usuario u = DAOUsuario.logarUsuario(user, user);
            LoginMain.changeScreen("principal", u );

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Acesso negado");
            String s = "Login / Senha inválidos.";
            alert.setContentText(s);
            alert.show();

            limparCampos();
            txtLogin.requestFocus();
        }
    }
    //--------------------------------------------------------------------------

    
}
