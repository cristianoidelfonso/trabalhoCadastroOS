package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Idelfonso
 * 
 * Os dados de cadastro do cliente serão nome, data de nascimento, RG, CPF, telefone(s), 
 * endereço (logradouro, nome, número, complemento, bairro, cidade, estado, CEP),
 * 
 */
public class FXMLCadastroClienteController implements Initializable {

    @FXML
    private JFXButton btnSalvarCli;
    @FXML
    private JFXButton btnEditarCli;
    @FXML
    private JFXButton btnApagarCli;
    @FXML
    private JFXButton btnSair;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSalvarAction(ActionEvent event) {
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
    }

    @FXML
    private void btnApagarAction(ActionEvent event) {
    }

    @FXML
    private void sairComEnter(KeyEvent event) {
    }

    @FXML
    private void btnSairAction(ActionEvent event) {
    }
    
}
