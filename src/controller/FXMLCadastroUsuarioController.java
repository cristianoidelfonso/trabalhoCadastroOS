package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
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
    private Usuario usuario;
    @FXML
    private Label lblId;

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
        MascarasFX.mascaraData(dtDataNasc);

        lblId.setVisible(false);
        lblSair.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));

        carregarCombo();
        cbPerfil.setValue("Admin");

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
     * Validação de todos os campos de entrada de dados do novo usuário.
     *
     * @param event
     */
    @FXML
    private void btnSalvarAction(ActionEvent event) throws SQLException {

        //usuario = new Usuario();
        // Validação do txtNome
        //----------------------------------------------------------------------
        if (!txtNome.getText().equals("")) {
            if (txtNome.getText().matches("([A-Z]{1}[A-Za-z\\s]+)")) {
                usuario.setNome(txtNome.getText());
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("INFORMAÇÃO");
                alerta.setHeaderText("NOME");
                alerta.setContentText("O campo NOME não está preenchido corretamente."
                        + "\n(Utilize apenas letras, iniciando por letra maiúscula)");
                alerta.show();
                txtNome.setText("");
                txtNome.requestFocus();
                return;
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMAÇÃO");
            alerta.setHeaderText("NOME");
            alerta.setContentText("O campo NOME não pode ser vazio."
                    + "\n(Utilize apenas letras, iniciando por letra maiúscula)");
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
                alerta.setTitle("INFORMAÇÃO");
                alerta.setHeaderText("DATA DE NASCIMENTO");
                alerta.setContentText("O campo DATA DE NASCIMENTO não está preenchido corretamente."
                        + "\n(utilize o padrão (dd/MM/aaaa))");
                alerta.show();
                dtDataNasc.setValue(null);
                dtDataNasc.requestFocus();
                return;
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMAÇÃO");
            alerta.setHeaderText("DATA DE NASCIMENTO");
            alerta.setContentText(" O campo DATA DE NASCIMENTO não pode ser vazio."
                    + "\n(utilize o padrão (dd/MM/aaaa))");
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
                alerta.setTitle("INFORMAÇÃO");
                alerta.setHeaderText("CPF");
                alerta.setContentText("O campo CPF não esta preenchido com o padrão correto."
                        + "\n(Digite apenas números.)");
                alerta.show();
                txtCpf.setText("");
                txtCpf.requestFocus();
                return;
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMAÇÃO");
            alerta.setHeaderText("CPF");
            alerta.setContentText("O campo CPF não pode ser vazio."
                    + "\n(Digite apenas números.)");
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
                alerta.setTitle("INFORMAÇÃO");
                alerta.setHeaderText("LOGIN");
                alerta.setContentText("O campo LOGIN nao esta preenchido corretamente."
                        + "\n(Utilize min:3 / max:12 caracteres alfanuméricos)");
                alerta.show();
                txtLogin.setText("");
                txtLogin.requestFocus();
                return;
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMAÇÃO");
            alerta.setHeaderText("LOGIN");
            alerta.setContentText("O campo LOGIN não pode ser vazio."
                    + "\n(Utilize min:3 / max:12 caracteres alfanuméricos)");
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
                alerta.setTitle("INFORMAÇÃO");
                alerta.setHeaderText("SENHA");
                alerta.setContentText("O campo SENHA não esta preenchido corretamente."
                        + "\n(Utilize min:5 / max:10 caracteres alfanuméricos)");
                alerta.show();
                txtSenha.setText("");
                txtSenha.requestFocus();
                return;
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMAÇÃO");
            alerta.setHeaderText("SENHA");
            alerta.setContentText("O campo SENHA não pode ser vazio."
                    + "\n(Utilize min:5 / max:10 caracteres alfanuméricos)");
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
                alerta.setTitle("INFORMAÇÃO");
                alerta.setHeaderText("PERFIL");
                alerta.setContentText("O campo PERFIL não está preenchido corretamente.");
                alerta.show();
                cbPerfil.setValue("");
                cbPerfil.requestFocus();
                return;
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMAÇÃO");
            alerta.setHeaderText("Preencha o campo PERFIL.");
            alerta.setContentText(" O campo PERFIL não pode ser vazio.");
            alerta.show();
            cbPerfil.setValue("");
            cbPerfil.requestFocus();
            return;
        }

        System.out.println(usuario);

        if (usuario.getId() != null) {
            dao.atualizar(usuario);
        } else {
            usuario = new Usuario();
            if (dao.salvar(usuario)) {
                System.out.println(usuario.getId());
                limparCampos();
            }
        }
    }

    /**
     * Método que limpa todos os campos do formulário de cadastro de usuário.
     */
    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtLogin.setText("");
        txtSenha.setText("");
        dtDataNasc.setValue(null);
        cbPerfil.setValue("");
    }

    /**
     *
     * @param event
     */
    @FXML
    private void btnEditarAction(ActionEvent event) throws SQLException {

        if (!txtNome.getText().equals("")) {
            if ((usuario = dao.pesquisarNoBD(txtNome.getText())) != null) {
                txtNome.setText(usuario.getNome());
                dtDataNasc.setValue(usuario.getDataNasc());
                txtCpf.setText(usuario.getCpf());
                txtLogin.setText(usuario.getLogin());
                txtSenha.setText(usuario.getSenha());
                cbPerfil.setValue(usuario.getPerfil());

            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("INFORMAÇÃO");
                alerta.setHeaderText("Usuario ainda nao cadastrado.");
                alerta.setContentText("Para cadastrar "+txtNome.getText()+" , complete o formulário.");
                alerta.show();
                txtNome.setText("");
                txtNome.requestFocus();

            }

        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMAÇÃO");
            alerta.setHeaderText("Informe um nome de usuario");
            alerta.setContentText(" Um NOME precisa ser informado.");
            alerta.show();
            txtNome.setText("");
            txtNome.requestFocus();
        }

    }

    /**
     *
     * @param event
     */
    @FXML
    private void btnApagarAction(ActionEvent event) {
    }
}
