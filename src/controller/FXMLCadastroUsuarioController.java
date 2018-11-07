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
import javafx.scene.control.Alert;
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
import model.MascarasFX;
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

        MascarasFX.mascaraCPF(txtCpf);
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

    
    /**
     * Validação de todos os campos de entrada de dados do novo usuário
     * 
     * @param event 
     */
    @FXML
    private void btnSalvarAction(ActionEvent event) {

        Usuario usuario = new Usuario();

        // Validação do txtNome
        //----------------------------------------------------------------------
        if (!txtNome.getText().equals("")) {
            if (txtNome.getText().matches("([A-Z]{1}[A-Za-z\\s]+)")) {
                usuario.setNome(txtNome.getText());
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("INFORMAÇÂO");
                alerta.setHeaderText("INFORMAÇÂO");
                alerta.setContentText("O campo NOME não está preenchido com o padrão correto.");
                alerta.show();
                txtNome.setText("");
                txtNome.requestFocus();
                return;
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMAÇÂO");
            alerta.setHeaderText("INFORMAÇÂO");
            alerta.setContentText("O campo NOME não está preenchido corretamente.");
            alerta.show();
            txtNome.setText("");
            txtNome.requestFocus();
            return;
        }

        // Validação da dtDataNasc
        //----------------------------------------------------------------------
        if (dtDataNasc.getValue() != null) {
            //System.out.println(dtDataNasc.getValue());
            
            if (dtDataNasc.getValue().toString().matches("(\\d{4}\\-\\d{2}\\-\\d{2})")) {
                usuario.setDataNasc(dtDataNasc.getValue());
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("INFORMAÇÂO");
                alerta.setHeaderText("INFORMAÇÂO");
                alerta.setContentText("O campo DATA DE NASCIMENTO não está preenchido corretamente.");
                alerta.show();
                dtDataNasc.setValue(null);
                dtDataNasc.requestFocus();
                return;
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMAÇÂO");
            alerta.setHeaderText("Preencha o campo Data de Nascimento.");
            alerta.setContentText(" O campo DATA DE NASCIMENTO não pode ser vazio.");
            alerta.show();
            dtDataNasc.setValue(null);
            dtDataNasc.requestFocus();
            return;

        }

        // Validação do txtCpf
        //----------------------------------------------------------------------
        if (!txtCpf.getText().equals("")) {
            if (txtCpf.getText().matches("(\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2})")) {
                usuario.setCpf(txtCpf.getText());
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("INFORMAÇÂO");
                alerta.setHeaderText("INFORMAÇÂO");
                alerta.setContentText("O campo CPF não esta preenchido com o padrão correto para o CPF.");
                alerta.show();
                txtCpf.setText("");
                txtCpf.requestFocus();
                return;
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMAÇÂO");
            alerta.setHeaderText("INFORMAÇÂO");
            alerta.setContentText("O campo CPF não esta preenchido corretamente.");
            alerta.show();
            txtCpf.setText("");
            txtCpf.requestFocus();
            return;
        }

        // Validação do txtLogin
        //----------------------------------------------------------------------
        if (!txtLogin.getText().equals("")) {
            if (txtLogin.getText().matches("[\\w]{3,12}")) {
                usuario.setLogin(txtLogin.getText());
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("INFORMAÇÂO");
                alerta.setHeaderText("INFORMAÇÂO");
                alerta.setContentText("O campo LOGIN nao aceita caracteres especiais");
                alerta.show();
                txtLogin.setText("");
                txtLogin.requestFocus();
                return;
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMAÇÂO");
            alerta.setHeaderText("INFORMAÇÂO");
            alerta.setContentText("O campo LOGIN não esta preenchido corretamente.");
            alerta.show();
            txtLogin.setText("");
            txtLogin.requestFocus();
            return;
        }

        // Validação do txtSenha
        //----------------------------------------------------------------------    
        if (!txtSenha.getText().equals("")) {
            if (txtSenha.getText().matches("(\\w{5,10})")) {
                usuario.setSenha(txtSenha.getText());
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("INFORMAÇÂO");
                alerta.setHeaderText("INFORMAÇÂO");
                alerta.setContentText("O campo SENHA não esta preenchido corretamente.");
                alerta.show();
                txtSenha.setText("");
                txtSenha.requestFocus();
                return;
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMAÇÂO");
            alerta.setHeaderText("INFORMAÇÂO");
            alerta.setContentText("O campo SENHA não esta preenchido corretamente.");
            alerta.show();
            txtSenha.setText("");
            txtSenha.requestFocus();
            return;
        }
        
         // Validação do cbPerfil
        //----------------------------------------------------------------------
        if (cbPerfil.getValue() != null) {
            //System.out.println(cbPerfil.getValue());
            
            if (cbPerfil.getValue().matches("[a-zA-Z]+")) {
                usuario.setPerfil(cbPerfil.getValue());
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("INFORMAÇÂO");
                alerta.setHeaderText("INFORMAÇÂO");
                alerta.setContentText("O campo PERFIL não está preenchido corretamente.");
                alerta.show();
                cbPerfil.setValue("");
                cbPerfil.requestFocus();
                return;
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMAÇÂO");
            alerta.setHeaderText("Preencha o campo PERFIL.");
            alerta.setContentText(" O campo PERFIL não pode ser vazio.");
            alerta.show();
            cbPerfil.setValue("");
            cbPerfil.requestFocus();
            return;
        }

        System.out.println(usuario);

        dao.salvar(usuario);
        limparCampos();
        
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO");
        alerta.setHeaderText("Salvo com sucesso");
        alerta.setContentText("Novo usuário foi cadastrado com sucesso");
        alerta.show();
        
    }
    
    
    private void limparCampos(){
        txtNome.setText("");
        txtCpf.setText("");
        txtLogin.setText("");
        txtSenha.setText("");
        dtDataNasc.setValue(null);
        cbPerfil.setValue("");
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
    }

    @FXML
    private void btnApagarAction(ActionEvent event) {
    }
}
