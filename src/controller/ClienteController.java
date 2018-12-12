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
import model.Cliente;
import model.MascarasFX;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Idelfonso
 */
public class ClienteController implements Initializable {

    private Cliente clienteAtual;
    private Usuario usuarioLogado = null;

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
    @FXML
    private TableView<Cliente> tableView;
    //Dados da Tabela
    private ObservableList<Cliente> dados;
    @FXML
    private BorderPane bpPrincipal;
    @FXML
    private Label lblTitulo;

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
        // Recebendo o usuario logado da tela principal.
        LoginMain.addOnChangeScreenListener(new LoginMain.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                usuarioLogado = (Usuario) userData;
                //System.out.println(newScreen);
                //System.out.println(usuarioLogado);
            }
        });

        clienteAtual = null;

        MascarasFX.mascaraCPF(txtCpfCliente);
        MascarasFX.mascaraTelefone(txtTelCliente);
        MascarasFX.mascaraData(dtNascimento);
        MascarasFX.mascaraRG(txtRgCliente);
        MascarasFX.mascaraCEP(txtEndCep);

        configurarTabela();
        carregarTableView();

    }
//==============================================================================    

//==============================================================================
    /**
     * Faz a configuração da tabela e das colunas
     */
    private void configurarTabela() {

        //Configurando as colunas da tabela
        TableColumn<Cliente, String> colId = new TableColumn("Id:");
        TableColumn<Cliente, String> colNome = new TableColumn("Nome");
        TableColumn<Cliente, String> colDataNasc = new TableColumn("Data Nasc");
        TableColumn<Cliente, String> colCpf = new TableColumn("CPF");
        TableColumn<Cliente, String> colRg = new TableColumn("RG");
        TableColumn<Cliente, String> colTelefone = new TableColumn("Telefone");
        TableColumn<Cliente, String> colRua = new TableColumn("Rua");
        TableColumn<Cliente, String> colNumero = new TableColumn("Num");
        TableColumn<Cliente, String> colBairro = new TableColumn("Bairro");
        TableColumn<Cliente, String> colCidade = new TableColumn("Cidade");
        TableColumn<Cliente, String> colEstado = new TableColumn("Estado");
        TableColumn<Cliente, String> colCep = new TableColumn("Cep");

        //Configurar como os valores serão lidos (nome dos atributos)
        colId.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        colCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colCep.setCellValueFactory(new PropertyValueFactory<>("cep"));

        //Configurando a largura das colunas da tabela
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colId.setMaxWidth(1f * Integer.MAX_VALUE * 5); // 5% width
        colNome.setMaxWidth(1f * Integer.MAX_VALUE * 15); // 15% width
        colDataNasc.setMaxWidth(1f * Integer.MAX_VALUE * 8); // 8% width
        colCpf.setMaxWidth(1f * Integer.MAX_VALUE * 8); // 8% width
        colRg.setMaxWidth(1f * Integer.MAX_VALUE * 8); // 8% width
        colTelefone.setMaxWidth(1f * Integer.MAX_VALUE * 8); // 8% width
        colRua.setMaxWidth(1f * Integer.MAX_VALUE * 8); // 8% width
        colNumero.setMaxWidth(1f * Integer.MAX_VALUE * 8); // 8% width
        colBairro.setMaxWidth(1f * Integer.MAX_VALUE * 8); // 8% width
        colCidade.setMaxWidth(1f * Integer.MAX_VALUE * 8); // 8% width
        colEstado.setMaxWidth(1f * Integer.MAX_VALUE * 8); // 8% width
        colCep.setMaxWidth(1f * Integer.MAX_VALUE * 8); // 8% width

        //Adiciona as colunas na tabela na ordem que devem aparecer
        tableView.getColumns().addAll(colId, colNome, colDataNasc, colCpf,
                colRg, colTelefone, colRua, colNumero, colBairro, colCidade, colEstado, colCep);
    }

    /**
     * Vai carregar os dados na tabela
     */
    private void carregarTableView() {
        try {
            //Convertendo o ArrayList no ObservableList com os dados do Banco
            dados = FXCollections.observableArrayList(Cliente.listar());

            //Joga os dados na tabela para exibir
            tableView.setItems(dados);
        } catch (Exception e) {

        }
    }

    private void updateList() {
        tableView.getItems().clear();
        Cliente.listar().forEach((c) -> {
            //Cliente.findName(txtNomeCliente.getText()+"%").forEach((c)->{
            tableView.getItems().add(c);
        });
    }
//------------------------------------------------------------------------------    

    @FXML
    private void onMouseClickedSair(MouseEvent event) {
        usuarioLogado = null;

        // Recuperando o stage atual
        Stage stageAtual = (Stage) lblSairCliente.getScene().getWindow();
        // Fecha o stage atual
        stageAtual.close();
    }

    @FXML
    private void onActionSair(ActionEvent event) {
        usuarioLogado = null;
        Stage stageAtual = (Stage) btnSairCliente.getScene().getWindow();
        stageAtual.close();
    }

    @FXML
    private void sairComEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            usuarioLogado = null;
            Stage stageAtual = (Stage) btnSairCliente.getScene().getWindow();
            stageAtual.close();
        }
    }

    @FXML
    private void onActionSalvar(ActionEvent event) throws SQLException {

        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(null);
        alerta.setHeaderText(null);

        try {
            if (txtNomeCliente.getText() == null || txtNomeCliente.getText().isEmpty()) {
                txtNomeCliente.requestFocus();
                throw new RuntimeException("O campo NOME nao pode ser vazio");
            } else if (dtNascimento.getValue() == null) {
                dtNascimento.requestFocus();
                throw new RuntimeException("O campo DATA DE NASCIMENTO nao pode ser vazio");
            } else if (txtCpfCliente.getText() == null || txtCpfCliente.getText().isEmpty()) {
                txtCpfCliente.requestFocus();
                throw new RuntimeException("O campo CPF nao pode ser vazio");
            } else if (txtRgCliente.getText() == null || txtRgCliente.getText().isEmpty()) {
                txtRgCliente.requestFocus();
                throw new RuntimeException("O campo RG nao pode ser vazio");

            } else if (txtTelCliente.getText() == null || txtTelCliente.getText().isEmpty()) {
                txtTelCliente.requestFocus();
                throw new RuntimeException("O campo TELEFONE nao pode ser vazio");
            } else if (txtEndRua.getText() == null || txtEndRua.getText().isEmpty()) {
                txtEndRua.requestFocus();
                throw new RuntimeException("O campo RUA nao pode ser vazio");
            } else if (txtEndNum.getText() == null || txtEndNum.getText().isEmpty()) {
                txtEndNum.requestFocus();
                throw new RuntimeException("O campo NUMERO nao pode ser vazio");
            } else if (txtEndBairro.getText() == null || txtEndBairro.getText().isEmpty()) {
                txtEndBairro.requestFocus();
                throw new RuntimeException("O campo BAIRRO nao pode ser vazio");
            } else if (txtEndCidade.getText() == null || txtEndCidade.getText().isEmpty()) {
                txtEndCidade.requestFocus();
                throw new RuntimeException("O campo CIDADE nao pode ser vazio");
            } else if (cbEndEstado.getValue() == null || cbEndEstado.getValue().isEmpty()) {
                cbEndEstado.requestFocus();
                throw new RuntimeException("O campo ESTADO nao pode ser vazio");
            } else if (txtEndCep.getText() == null || txtEndCep.getText().isEmpty()) {
                txtEndCep.requestFocus();
                throw new RuntimeException("O campo CEP nao pode ser vazio");
            }

            if (clienteAtual != null) {
                clienteAtual.setNome(txtNomeCliente.getText());
                clienteAtual.setDtNasc(dtNascimento.getValue());
                clienteAtual.setCpf(txtCpfCliente.getText());
                clienteAtual.setRg(txtRgCliente.getText());
                clienteAtual.setTelefone(txtTelCliente.getText());
                clienteAtual.setRua(txtEndRua.getText());
                clienteAtual.setNumero(txtEndNum.getText());
                clienteAtual.setBairro(txtEndBairro.getText());
                clienteAtual.setCidade(txtEndCidade.getText());
                clienteAtual.setEstado(cbEndEstado.getValue());
                clienteAtual.setCep(txtEndCep.getText());

                clienteAtual.save();
                updateList();

                limparTudo();

            } else {
                Cliente cliente = new Cliente();
                cliente.setNome(txtNomeCliente.getText());
                cliente.setDtNasc(dtNascimento.getValue());
                cliente.setCpf(txtCpfCliente.getText());
                cliente.setRg(txtRgCliente.getText());
                cliente.setTelefone(txtTelCliente.getText());
                cliente.setRua(txtEndRua.getText());
                cliente.setNumero(txtEndNum.getText());
                cliente.setBairro(txtEndBairro.getText());
                cliente.setCidade(txtEndCidade.getText());
                cliente.setEstado(cbEndEstado.getValue());
                cliente.setCep(txtEndCep.getText());

                cliente.save();
                updateList();

                limparTudo();

            }

        } catch (RuntimeException e) {
            alerta.setContentText(e.getMessage());
        }
        alerta.showAndWait();

    }

    private void limparTudo() {
        txtNomeCliente.setText("");
        txtCpfCliente.setText("");
        txtRgCliente.setText("");
        txtTelCliente.setText("");
        dtNascimento.setValue(null);
        txtEndRua.setText("");
        txtEndNum.setText("");
        txtEndBairro.setText("");
        txtEndCidade.setText("");
        cbEndEstado.setValue(null);
        txtEndCep.setText("");
    }
//------------------------------------------------------------------------------

    @FXML
    private void onActionEditar(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            txtNomeCliente.setText(tableView.getSelectionModel().getSelectedItem().getNome());
            if (Cliente.find(txtNomeCliente.getText()) != null) {
                clienteAtual = Cliente.find(txtNomeCliente.getText());
                preencherTela();
            } else {
                if (txtNomeCliente.getText().equals("")) {
                    txtNomeCliente.requestFocus();
                    //throw new RuntimeException("Campo vazio");
                } else {
                    if (Usuario.find(txtNomeCliente.getText()) != null) {
                        clienteAtual = Cliente.find(txtNomeCliente.getText());
                        preencherTela();
                    } else {
                        Alert info = new Alert(Alert.AlertType.ERROR);
                        info.setTitle("Error");
                        info.setHeaderText("Usuario nao cadastrado");
                        info.setContentText("Para cadastrar " + txtNomeCliente.getText() + " preencha todos os campos.");
                        info.showAndWait();
                        limparTudo();
                        txtNomeCliente.setText("");
                        txtNomeCliente.requestFocus();
                    }
                }
            }
        }
    }

    private void preencherTela() {
        txtNomeCliente.setText(clienteAtual.getNome());
        txtCpfCliente.setText(clienteAtual.getCpf());
        txtRgCliente.setText(clienteAtual.getRg());
        txtTelCliente.setText(clienteAtual.getTelefone());
        dtNascimento.setValue(clienteAtual.getDataNasc());
        txtEndRua.setText(clienteAtual.getRua());
        txtEndNum.setText(clienteAtual.getNumero());
        txtEndBairro.setText(clienteAtual.getBairro());
        txtEndCidade.setText(clienteAtual.getCidade());
        cbEndEstado.setValue(clienteAtual.getEstado());
        txtEndCep.setText(clienteAtual.getCep());
    }
//------------------------------------------------------------------------------

    @FXML
    private void onActionApagar(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            clienteAtual = Cliente.find(tableView.getSelectionModel().getSelectedItem().getNome());
            System.out.println(clienteAtual);
            
            if (clienteAtual != null) {
                System.out.println(clienteAtual + "Botao Apagar");
                //Pegando o botao que foi pressionado
                Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
                conf.setTitle("EXCLUIR");
                conf.setHeaderText(null);
                conf.setContentText("Deseja excluir esse cliente?");
                Optional<ButtonType> btn = conf.showAndWait();
                //Verificar qual botão foi pressionado
                if (btn.get() == ButtonType.OK) { //Manda a classe de negocio excluir
                    clienteAtual.delete();
                    //Atualizar a tabela
                    updateList();
                    //Mensagem de excluído com sucesso
                    Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                    sucesso.setTitle("SUCESSO");
                    sucesso.setHeaderText("Concluido com sucesso!");
                    sucesso.setContentText("O usuário foi excluido");
                    sucesso.showAndWait();
                    //clienteAtual = null;
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

    @FXML
    private void txtNomeClienteOnKeyReleased(KeyEvent event) {
        if (!txtNomeCliente.getText().isEmpty() && txtNomeCliente.getText().matches("[A-z]{1,}")) {
            tableView.getItems().clear();
            Cliente.findName(txtNomeCliente.getText() + "%").forEach((c) -> {
                tableView.getItems().add(c);
            });
        }
    }
}
