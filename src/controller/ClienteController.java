package controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.MascarasFX;
import model.MaskedTextField;

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
    private JFXDatePicker dtNascCliente;
    @FXML
    private JFXTextField txtRgCliente;
    @FXML
    private JFXTextField txtTelCliente;
    @FXML
    private DatePicker data;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        MascarasFX.mascaraCPF(txtCpfCliente);
        MascarasFX.mascaraTelefone(txtTelCliente);    
        
    }    

    @FXML
    private void onMouseClickedSair(MouseEvent event) {
        // Recuperando o stage atual
        Stage stageAtual = (Stage) lblSairCliente.getScene().getWindow();
        // Fecha o stage atual
        stageAtual.close();
    }
    
}
