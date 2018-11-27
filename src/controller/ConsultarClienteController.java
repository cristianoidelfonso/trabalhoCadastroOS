/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Idelfonso
 */
public class ConsultarClienteController implements Initializable {

    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblSair;

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
    private void lblSairOnMouseClicked(MouseEvent event) {
        // Recuperando o stage atual
        Stage stageAtual = (Stage) lblSair.getScene().getWindow();
        // Fecha o stage atual
        stageAtual.close();
    }
    
}
