package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Idelfonso
 */
public class FXMLCadastroOSController implements Initializable {
    
    private Usuario usuarioLogado = null;

    @FXML
    private Label lblSairOS;
    @FXML
    private JFXButton btnSalvar;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnApagar;
    @FXML
    private JFXButton btnSair;
    @FXML
    private Label lblTitulo;
    @FXML
    private ToggleGroup ButtonGroup1;
    @FXML
    private JFXComboBox<String> cbSituacaoOS;
    @FXML
    private JFXButton btnImprimir;
    @FXML
    private JFXTextField txtNumOS;
    @FXML
    private JFXTextField txtDtaEmissao;
    @FXML
    private JFXRadioButton rbOrcamento;
    @FXML
    private JFXRadioButton rbOrdemServico;
    @FXML
    private JFXTextField txtNomeCliente;
    @FXML
    private JFXTextField txtIdCliente;
    @FXML
    private TableView<?> tableView;
    @FXML
    private JFXTextField txtProduto;
    @FXML
    private JFXTextField txtDataEntrega;
    @FXML
    private JFXTextArea txtDescricao;
    @FXML
    private JFXTextField txtIdUsuario;
    @FXML
    private JFXTextField txtNomeUsuario;
    @FXML
    private JFXTextField txtValor;

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
        LoginMain.addOnChangeScreenListener(new LoginMain.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
               usuarioLogado = (Usuario)userData;
               txtIdUsuario.setText(usuarioLogado.getId().toString());
               txtNomeUsuario.setText(usuarioLogado.getNome());
            }
        });
            carregarCombo(); 
    }
//==============================================================================
    /**
     * Cria a lista de perfis e povoa o comboBox.
     */
    private void carregarCombo(){
            //Criar uma lista
            ObservableList<String> lista = FXCollections.observableArrayList(
                    "","Entrega OK", "Aguardando aprovação", "Aguardando material",
                    "Na oficina", "Abandonado pelo Cliente", "Garantia", "Orçamento reprovado");
            //Jogar a lista no combo
            cbSituacaoOS.getItems().addAll(lista);
        
    }

    @FXML
    private void onMouseClickedSairOS(MouseEvent event) {
        // Recuperando o stage atual
        Stage stageAtual = (Stage) lblSairOS.getScene().getWindow();
        // Fecha o stage atual
        stageAtual.close();
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

    @FXML
    private void sairComEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Stage stageAtual = (Stage) btnSair.getScene().getWindow();
            stageAtual.close();
        }
    }

    @FXML
    private void onActionSair(ActionEvent event) {
        Stage stageAtual = (Stage) btnSair.getScene().getWindow();
        stageAtual.close();
    }

}
