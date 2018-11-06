package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.DAOUsuario;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Idelfonso
 */
public class FXMLCadastroUsuarioController implements Initializable {

    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXTextField txtSenha;
    @FXML
    private JFXDatePicker dtDataNasc;
    @FXML
    private JFXTextField txtCpf;
    @FXML
    private BorderPane bpCadUsu;
    @FXML
    private JFXButton btnSalvarUsu;
    @FXML
    private JFXButton btnEditarUsu;
    @FXML
    private JFXButton btnApagarUsu;
    @FXML
    private JFXButton btnSair;
    @FXML
    private JFXComboBox<String> cbPerfil;
    @FXML
    private Label lblSair;

    private DAOUsuario dao;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        lblSair.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));

        carregarCombo();

    }

    private void carregarCombo() {

        //Criar uma lista
        ObservableList<String> lista = FXCollections.observableArrayList("Admin", "Simples");

        //Jogar a lista no combo
        cbPerfil.getItems().addAll(lista);
    }

    /**
     * Recupera o stage atual e fecha em seguida
     *
     * @param event
     */
    @FXML
    private void lblSairAction(MouseEvent event) {
        // Recuperando o stage atual
        Stage stageAtual = (Stage) lblSair.getScene().getWindow();
        // Fecha o stage atual
        stageAtual.close();
    }

    @FXML
    private void lblSairMouseExited(MouseEvent event) {
        lblSair.setTextFill(Paint.valueOf("#000000"));
        lblSair.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
    }

    @FXML
    private void lblSairMouseEntered(MouseEvent event) {
        lblSair.setTextFill(Paint.valueOf("#FF0000"));
        lblSair.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

    }

    @FXML
    private void btnSairAction(ActionEvent event) {
        Stage stageAtual = (Stage) btnSair.getScene().getWindow();
        stageAtual.close();
    }

    @FXML
    private void sairComEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Stage stageAtual = (Stage) btnSair.getScene().getWindow();
            stageAtual.close();
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {

        Usuario usuario = new Usuario();

        usuario.setLogin(txtLogin.getText());
        usuario.setSenha(txtSenha.getText());
        usuario.setNome(txtNome.getText());
        usuario.setCpf(txtCpf.getText());
        usuario.setPerfil(cbPerfil.getValue());
        usuario.setDataNasc(dtDataNasc.getValue());

        System.out.println(usuario);

        dao.salvar(usuario);

    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
    }

    @FXML
    private void btnApagarAction(ActionEvent event) {
    }
}
