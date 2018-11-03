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
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    private final String user;
    private final String pass;
    @FXML
    private JFXButton btnEntrar;
    @FXML
    private BorderPane borderPane;

    /**
     * 
     */
    public FXMLLoginController() {
        this.user = "admin";
        this.pass = "admin";
    }

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
     * @param e 
     */
    @FXML
    private void btnEntrarAction(ActionEvent e) {
        if ((txtLogin.getText().equals(user)) && (txtSenha.getText().equals(pass))) {

        LoginMain.changeScreen("principal");

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Acesso negado");
            String s = "Dados nao foram informados corretamente.";
            alert.setContentText(s);
            alert.show();
            
            limparCampos();
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

}
