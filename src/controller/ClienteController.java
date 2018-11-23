package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.MascarasFX;

/**
 * FXML Controller class
 *
 * @author Idelfonso
 */
public class ClienteController implements Initializable {

    @FXML
    private Label lblSairCliente;
    @FXML
    private JFXTextField txtCpfCliente;
    @FXML
    private JFXTextField txtNomeCliente;
    @FXML
    private JFXTextField txtRgCliente;
    @FXML
    private JFXTextField txtTelCliente;
    @FXML
    private DatePicker dtNascimento;
    @FXML
    private JFXTextField txtEndRua;
    @FXML
    private JFXTextField txtEndNum;
    @FXML
    private JFXTextField txtEndBairro;
    @FXML
    private JFXTextField txtEndCidade;
    @FXML
    private JFXComboBox<String> cbEndEstado;
    @FXML
    private JFXTextField txtEndCep;
    @FXML
    private JFXButton btnSalvarCliente;
    @FXML
    private JFXButton btnEditarCliente;
    @FXML
    private JFXButton btnApagarCliente;
    @FXML
    private JFXButton btnSairCliente;
    
   

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MascarasFX.mascaraCPF(txtCpfCliente);
        MascarasFX.mascaraTelefone(txtTelCliente);
        MascarasFX.mascaraData(dtNascimento);  
        MascarasFX.mascaraRG(txtRgCliente);
    }

    @FXML
    private void onMouseClickedSair(MouseEvent event) {
        // Recuperando o stage atual
        Stage stageAtual = (Stage) lblSairCliente.getScene().getWindow();
        // Fecha o stage atual
        stageAtual.close();
    }
    @FXML
    private void onActionSair(ActionEvent event) {
        Stage stageAtual = (Stage) btnSairCliente.getScene().getWindow();
        stageAtual.close();
    }

    @FXML
    private void sairComEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Stage stageAtual = (Stage) btnSairCliente.getScene().getWindow();
            stageAtual.close();
        }
    }

    @FXML
    private void onActionSalvar(ActionEvent event) {
    }

    @FXML
    private void onActionEditar(ActionEvent event) {
    }

    @FXML
    private void onActionApagar(ActionEvent event) {
    }

}
