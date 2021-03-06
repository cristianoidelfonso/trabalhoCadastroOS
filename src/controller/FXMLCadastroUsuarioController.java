package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.MascarasFX;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Idelfonso
 */
public class FXMLCadastroUsuarioController implements Initializable {

    private Usuario usuarioAtual;

    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXTextField txtSenha;
    @FXML
    private DatePicker dtDataNasc;
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

    @FXML
    private Label lblId;
    @FXML
    private TableView<Usuario> tableView;
    //Dados da Tabela
    private ObservableList<Usuario> dados;
    @FXML
    private Label lblTitulo;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        usuarioAtual = null;
        MascarasFX.mascaraCPF(txtCpf);
        MascarasFX.mascaraData(dtDataNasc);

        //lblId.setText("ID:" + Usuario.proximoId().toString());

        carregarCombo();
        cbPerfil.setValue("Admin");

        configurarTabela();
        carregarTableView();

    }
//------------------------------------------------------------------------------

    /**
     * Cria a lista de perfis e povoa o comboBox.
     */
    private void carregarCombo() {

        //Criar uma lista
        ObservableList<String> lista = FXCollections.observableArrayList("Admin", "Simples");

        //Jogar a lista no combo
        cbPerfil.getItems().addAll(lista);
    }
//------------------------------------------------------------------------------

    /**
     * Faz a configuração da tabela e das colunas
     */
    private void configurarTabela() {

        //Configurando as colunas da tabela
        TableColumn<Usuario, String> colId = new TableColumn("Id:");
        TableColumn<Usuario, String> colNome = new TableColumn("Nome");
        TableColumn<Usuario, String> colDataNasc = new TableColumn("Data Nasc");
        TableColumn<Usuario, String> colCpf = new TableColumn("CPF");
        TableColumn<Usuario, String> colPerfil = new TableColumn("Perfil");
        TableColumn<Usuario, String> colLogin = new TableColumn("Login");
        TableColumn<Usuario, String> colSenha = new TableColumn("Senha");

        //Configurar como os valores serão lidos (nome dos atributos)
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colPerfil.setCellValueFactory(new PropertyValueFactory<>("perfil"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));

        //Configurando a largura das colunas da tabela
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colId.setMaxWidth(1f * Integer.MAX_VALUE * 5); // 5% width
        colNome.setMaxWidth(1f * Integer.MAX_VALUE * 25); // 30% width
        colDataNasc.setMaxWidth(1f * Integer.MAX_VALUE * 16); // 16% width
        colCpf.setMaxWidth(1f * Integer.MAX_VALUE * 20); // 20% width
        colPerfil.setMaxWidth(1f * Integer.MAX_VALUE * 12); // 16% width
        colLogin.setMaxWidth(1f * Integer.MAX_VALUE * 11); // 9% width
        colSenha.setMaxWidth(1f * Integer.MAX_VALUE * 11); // 9% width

        //Adiciona as colunas na tabela na ordem que devem aparecer
        tableView.getColumns().addAll(colId, colNome, colDataNasc, colCpf, colPerfil, colLogin, colSenha);
    }
//------------------------------------------------------------------------------

    /**
     * Vai carregar os dados na tabela
     */
    private void carregarTableView() {
        try {
            //Convertendo o ArrayList no ObservableList com os dados do Banco
            dados = FXCollections.observableArrayList(Usuario.listar());

            //Joga os dados na tabela para exibir
            tableView.setItems(dados);
        } catch (Exception e) {

        }
    }
//------------------------------------------------------------------------------

    private void updateList() {
        tableView.getItems().clear();
        Usuario.listar().forEach((u) -> {
            tableView.getItems().add(u);
        });
    }
//------------------------------------------------------------------------------

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
//------------------------------------------------------------------------------

    @FXML
    private void btnSairAction(ActionEvent event) {
        Stage stageAtual = (Stage) btnSair.getScene().getWindow();
        stageAtual.close();
    }
//------------------------------------------------------------------------------

    @FXML
    private void sairComEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Stage stageAtual = (Stage) btnSair.getScene().getWindow();
            stageAtual.close();
        }
    }
//------------------------------------------------------------------------------

    /**
     * Validação de todos os campos de entrada de dados do novo usuário.
     *
     * @param event
     */
    @FXML
    private void btnSalvarAction(ActionEvent event) throws SQLException {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("INFORMAÇÃO");

        try {
            if (txtNome.getText() == null || txtNome.getText().isEmpty()) {
                txtNome.requestFocus();
                throw new RuntimeException("O campo NOME nao pode ser vazio.");
            } else if (!txtNome.getText().matches("([A-ZÀ-ú]{1}[A-Za-zÀ-ú\\s]+)")) {
                txtNome.requestFocus();
                throw new RuntimeException("O campo NOME possui caracteres inválidos.");
            } else if (dtDataNasc.getValue() == null) {
                dtDataNasc.requestFocus();
                throw new RuntimeException("O campo DATA DE NASCIMENTO nao pode ser vazio.");
            } else if (txtCpf.getText() == null || txtCpf.getText().isEmpty()) {
                txtCpf.requestFocus();
                throw new RuntimeException("O campo CPF nao pode ser vazio.");
            } else if (txtLogin.getText() == null || txtLogin.getText().isEmpty()) {
                txtLogin.requestFocus();
                throw new RuntimeException("O campo LOGIN nao pode ser vazio.");
            } else if (!txtLogin.getText().matches("[\\w]{5,12}")) {
                txtLogin.requestFocus();
                throw new RuntimeException("O campo LOGIN possui caracteres inválidos.\n(Utilize min:3 / max:12 caracteres alfanuméricos.)");
            } else if (txtSenha.getText() == null || txtSenha.getText().isEmpty()) {
                txtSenha.requestFocus();
                throw new RuntimeException("O campo SENHA nao pode ser vazio.");
            } else if (!txtSenha.getText().matches("(\\w{5,10})")) {
                txtSenha.requestFocus();
                throw new RuntimeException("O campo SENHA possui caracteres inválidos.\n(Utilize min:5 / max:10 caracteres alfanuméricos.)");
            } else if (cbPerfil.getValue() == null || cbPerfil.getValue().isEmpty()) {
                cbPerfil.requestFocus();
                throw new RuntimeException("O campo PERFIL nao pode ser vazio.");
            }

            if (usuarioAtual != null) {
                usuarioAtual.setNome(txtNome.getText());
                usuarioAtual.setDataNasc(dtDataNasc.getValue());
                usuarioAtual.setCpf(txtCpf.getText());
                usuarioAtual.setLogin(txtLogin.getText());
                usuarioAtual.setSenha(txtSenha.getText());
                usuarioAtual.setPerfil(cbPerfil.getValue());

                usuarioAtual.save();
                //usuarioAtual = null;
                //updateList();

                // Se salvar com sucesso, imprime esta mensagem.
                alerta.setTitle("SUCESSO");
                alerta.setHeaderText("ALTERAÇÃO BEM SUCEDIDA!");
                alerta.setContentText("Os dados do usuário foram alterados com sucesso!");
                alerta.show();

                limparCampos();
                //txtNome.requestFocus();

            } else {
                Usuario usuario = new Usuario();
                // Validação do txtNome  
                //----------------------------------------------------------------------
                if (!txtNome.getText().equals("")) {
                    if (txtNome.getText().matches("([A-ZÀ-ú]{1}[A-Za-zÀ-ú\\s]+)")) {
                        usuario.setNome(txtNome.getText());
                    } else {
                        alerta.setHeaderText("NOME");
                        alerta.setContentText("O campo NOME não está preenchido corretamente."
                                + "\n(Utilize apenas letras, iniciando por letra maiúscula)");
                        alerta.show();
                        txtNome.setText("");
                        txtNome.requestFocus();
                        return;
                    }
                } else {
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
                        alerta.setHeaderText("DATA DE NASCIMENTO");
                        alerta.setContentText("O campo DATA DE NASCIMENTO não está preenchido corretamente."
                                + "\n(utilize o padrão (dd/MM/aaaa))");
                        alerta.show();
                        dtDataNasc.setValue(null);
                        dtDataNasc.requestFocus();
                        return;
                    }
                } else {
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
                    if (txtLogin.getText().matches("[\\w]{5,12}")) {
                        usuario.setLogin(txtLogin.getText());
                    } else {
                        alerta.setHeaderText("LOGIN");
                        alerta.setContentText("O campo LOGIN nao esta preenchido corretamente."
                                + "\n(Utilize min:3 / max:12 caracteres alfanuméricos)");
                        alerta.show();
                        txtLogin.setText("");
                        txtLogin.requestFocus();
                        return;
                    }
                } else {
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
                        alerta.setHeaderText("SENHA");
                        alerta.setContentText("O campo SENHA não esta preenchido corretamente."
                                + "\n(Utilize min:5 / max:10 caracteres alfanuméricos)");
                        alerta.show();
                        txtSenha.setText("");
                        txtSenha.requestFocus();
                        return;
                    }
                } else {
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
                        alerta.setHeaderText("PERFIL");
                        alerta.setContentText("O campo PERFIL não está preenchido corretamente.");
                        alerta.show();
                        cbPerfil.setValue("");
                        cbPerfil.requestFocus();
                        return;
                    }
                } else {
                    alerta.setHeaderText("Preencha o campo PERFIL.");
                    alerta.setContentText(" O campo PERFIL não pode ser vazio.");
                    alerta.show();
                    cbPerfil.setValue("");
                    cbPerfil.requestFocus();
                    return;
                }

                usuario.save();
                //updateList();

//                Se salvar com sucesso, imprime esta mensagem.
//                alerta.setTitle("SUCESSO");
//                alerta.setHeaderText("USUÁRIO CADASTRADO");
//                alerta.setContentText("Novo usuário foi cadastrado com sucesso");
//                alerta.show();
            }

            updateList();
            txtNome.requestFocus();

        } catch (RuntimeException e) {

            alerta.setContentText(e.getMessage());
            alerta.show();
            return;
        }
        usuarioAtual = null;
        limparCampos();
        txtNome.requestFocus();
    }
//------------------------------------------------------------------------------

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
//------------------------------------------------------------------------------

    /**
     *
     * @param event
     */
    @FXML
    private void btnEditarAction(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            txtNome.setText(tableView.getSelectionModel().getSelectedItem().getNome());
            lblId.setText("ID: " + tableView.getSelectionModel().getSelectedItem().getId().toString());
            lblId.setVisible(true);
            if (Usuario.find(txtNome.getText()) != null) {
                usuarioAtual = Usuario.find(txtNome.getText());
                preencherTela();
            }
        } else {
            if (txtNome.getText().equals("")) {
                txtNome.requestFocus();
                //throw new RuntimeException("Campo vazio");
            } else {
                if (Usuario.find(txtNome.getText()) != null) {
                    usuarioAtual = Usuario.find(txtNome.getText());
                    lblId.setText("ID: " + usuarioAtual.getId().toString());
                    lblId.setVisible(true);
                    preencherTela();
                } else {
                    Alert info = new Alert(Alert.AlertType.ERROR);
                    info.setTitle("Error");
                    info.setHeaderText("Usuario nao cadastrado");
                    info.setContentText("Para cadastrar " + txtNome.getText() + " preencha todos os campos.");
                    info.showAndWait();
                    limparCampos();
                    txtNome.setText("");
                    txtNome.requestFocus();
                }
            }
        }
    }
//------------------------------------------------------------------------------

    /**
     *
     * @param event
     */
    @FXML
    private void btnApagarAction(ActionEvent event) throws SQLException {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            usuarioAtual = Usuario.find(tableView.getSelectionModel().getSelectedItem().getNome());
            if (usuarioAtual != null) {
                System.out.println(usuarioAtual + "Botao Apagar");
                //Pegando o botao que foi pressionado
                Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
                conf.setTitle("EXCLUIR");
                conf.setHeaderText(null);
                conf.setContentText("Deseja excluir esse usuario?");
                Optional<ButtonType> btn = conf.showAndWait();
                //Verificar qual botão foi pressionado
                if (btn.get() == ButtonType.OK) { //Manda a classe de negocio excluir
                    usuarioAtual.delete();
                    //Atualizar a tabela
                    updateList();
                    //Mensagem de excluído com sucesso
                    Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                    sucesso.setTitle("SUCESSO");
                    sucesso.setHeaderText("Concluido com sucesso!");
                    sucesso.setContentText("O usuário foi excluido");
                    sucesso.showAndWait();
                    usuarioAtual = null;
                }
            }
        } else {
            System.out.println("Usuario nao selecionado");
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("INFORMAÇÂO");
            a.setHeaderText(null);
            a.setContentText("Selecione o usuário a ser excluído.");
            a.showAndWait();
        }
    }
//------------------------------------------------------------------------------

    private void preencherTela() {
        txtNome.setText(usuarioAtual.getNome());
        dtDataNasc.setValue(usuarioAtual.getDataNasc());
        txtCpf.setText(usuarioAtual.getCpf());
        txtLogin.setText(usuarioAtual.getLogin());
        txtSenha.setText(usuarioAtual.getSenha());
        cbPerfil.setValue(usuarioAtual.getPerfil());
    }
//------------------------------------------------------------------------------
    @FXML
    private void txtNomeOnKeyReleased(KeyEvent event) {
        
        if (!txtNome.getText().isEmpty() && txtNome.getText().matches("[A-z]{1,}")) {
            tableView.getItems().clear();
            Usuario.findName(txtNome.getText() + "%").forEach((u) -> {
                tableView.getItems().add(u);
            });
        }else{
            carregarTableView();
        }
    }
}
