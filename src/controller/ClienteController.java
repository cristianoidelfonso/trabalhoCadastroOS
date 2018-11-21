package controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.MaskFieldUtil;

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

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MaskFieldUtil.cpfField(txtCpfCliente);
        
    }    

    @FXML
    private void onMouseClickedSair(MouseEvent event) {
        // Recuperando o stage atual
        Stage stageAtual = (Stage) lblSairCliente.getScene().getWindow();
        // Fecha o stage atual
        stageAtual.close();
    }
    
}
