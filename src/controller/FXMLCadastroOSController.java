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
public class FXMLCadastroOSController implements Initializable {

    @FXML
    private Label lblSairOS;

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
    private void onMouseClickedSairOS(MouseEvent event) {
        // Recuperando o stage atual
        Stage stageAtual = (Stage) lblSairOS.getScene().getWindow();
        // Fecha o stage atual
        stageAtual.close();
    }
    
}
