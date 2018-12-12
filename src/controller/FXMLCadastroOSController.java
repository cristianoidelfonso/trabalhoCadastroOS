package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Cliente;
import model.DAOOrdemServico;
import model.OrdemServico;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Idelfonso
 */
public class FXMLCadastroOSController implements Initializable {

    private Usuario usuarioLogado = null;
    private Cliente clienteSelecionado = null;
    private OrdemServico osAtual;
    private DAOOrdemServico dao;

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
    private TableView<Cliente> tableView;
    @FXML
    private JFXTextField txtProduto;
    @FXML
    private JFXTextField txtDataEntrega;
    @FXML
    private JFXTextField txtDescricao;
    @FXML
    private JFXTextField txtIdUsuario;
    @FXML
    private JFXTextField txtNomeUsuario;
    @FXML
    private JFXTextField txtValor;
    //Dados da Tabela
    private ObservableList<Cliente> dadosClientes;
    private ObservableList<OrdemServico> dadosOS;
    private String tipo;
    @FXML
    private TableView<OrdemServico> tableViewOS;

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
                usuarioLogado = (Usuario) userData;
                txtIdUsuario.setText(usuarioLogado.getId().toString());
                txtNomeUsuario.setText(usuarioLogado.getNome());
            }
        });
        // Carregando o combobox da situação da OS
        carregarCombo();

        configurarTabela();

        // Carregando a data de emissão da OS
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txtDtaEmissao.setText(LocalDate.now().format(dtf));

    }
//==============================================================================

    /**
     * Cria a lista de perfis e povoa o comboBox.
     */
    private void carregarCombo() {
        //Criar uma lista
        ObservableList<String> lista = FXCollections.observableArrayList(
                "", "Entrega OK", "Aguardando aprovação", "Aguardando material",
                "Na oficina", "Abandonado pelo Cliente", "Garantia", "Orçamento reprovado");
        //Jogar a lista no combo
        cbSituacaoOS.getItems().addAll(lista);
    }

//==============================================================================
    /**
     * Faz a configuração da tabela e das colunas
     */
    private void configurarTabela() {

        //Configurando as colunas da tabela
        TableColumn<Cliente, String> colId = new TableColumn("Id:");
        TableColumn<Cliente, String> colNome = new TableColumn("Nome");
        TableColumn<Cliente, String> colTelefone = new TableColumn("Telefone");

        TableColumn<OrdemServico, String> colNumOS = new TableColumn("Num OS");
        TableColumn<OrdemServico, String> colProduto = new TableColumn("Produto");
        TableColumn<OrdemServico, LocalDate> colData = new TableColumn("Emissão");

        //Configurar como os valores serão lidos (nome dos atributos)
        colId.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        colNumOS.setCellValueFactory(new PropertyValueFactory<>("idOS"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colData.setCellValueFactory(new PropertyValueFactory<>("dataOS"));

        //Configurando a largura das colunas da tabela
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewOS.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colId.setMaxWidth(1f * Integer.MAX_VALUE * 7); // 5% width
        colNome.setMaxWidth(1f * Integer.MAX_VALUE * 60); // 15% width
        colTelefone.setMaxWidth(1f * Integer.MAX_VALUE * 33); // 8% width

        colNumOS.setMaxWidth(1f * Integer.MAX_VALUE * 15);
        colProduto.setMaxWidth(1f * Integer.MAX_VALUE * 55);
        colData.setMaxWidth(1f * Integer.MAX_VALUE * 30);

        //Adiciona as colunas na tabela na ordem que devem aparecer
        tableView.getColumns().addAll(colId, colNome, colTelefone);
        tableViewOS.getColumns().addAll(colNumOS, colProduto, colData);
    }

    /**
     * Vai carregar os dados na tabela de clientes
     */
    private void carregarTableView() {
        try {
            //Convertendo o ArrayList no ObservableList com os dados do Banco
            dadosClientes = FXCollections.observableArrayList(Cliente.findName(txtNomeCliente.getText() + "%"));
            //Joga os dados na tabela para exibir
            tableView.setItems(dadosClientes);
        } catch (Exception e) {

        }
    }

    // carrega a tabela de OS
    private void carregarTableViewOS() {
        try {
            //Convertendo o ArrayList no ObservableList com os dados do Banco
            dadosOS = FXCollections.observableArrayList(OrdemServico.findOS(Integer.parseInt(txtIdCliente.getText())));
            //Joga os dados na tabela para exibir
            tableViewOS.setItems(dadosOS);
        } catch (NumberFormatException e) {

        }
    }

    //Atualiza a tabela de clientes
    private void updateList() {
        tableView.getItems().clear();
        Cliente.findName(txtNomeCliente.getText() + "%").forEach((c) -> {
            tableView.getItems().add(c);
        });
    }

    //Atualiza a tabela OS
    private void updateListOS() {
        tableViewOS.getItems().clear();
        OrdemServico.findOS(Integer.parseInt(txtNumOS.getText())).forEach((os) -> {
            tableViewOS.getItems().add(os);
        });
    }
//--------------------------------------------------------------------------
    @FXML
    private void txtNomeClienteOnKeyReleased(KeyEvent event) {
        updateList();
    }
//------------------------------------------------------------------------------
    @FXML
    private void onMouseClickedSairOS(MouseEvent event) {
        // Recuperando o stage atual
        Stage stageAtual = (Stage) lblSairOS.getScene().getWindow();
        // Fecha o stage atual
        stageAtual.close();
    }
//------------------------------------------------------------------------------
    @FXML
    private void onActionSalvar(ActionEvent event) {

        if (osAtual != null) {
            //osAtual.setIdOS(Integer.parseInt(txtNumOS.getText()));
            osAtual.setIdCliente(Integer.parseInt(txtIdCliente.getText()));
            osAtual.setIdUsuario(Integer.parseInt(txtIdUsuario.getText()));
            osAtual.setNomeUsuario(txtNomeUsuario.getText());
            osAtual.setDataOS(LocalDate.now());
            osAtual.setTipo(radioButtonSelected());
            osAtual.setSituacao(cbSituacaoOS.getValue());
            osAtual.setProduto(txtProduto.getText());
            osAtual.setDescricao(txtDescricao.getText());
            osAtual.setValor(Double.parseDouble(txtValor.getText()));

            osAtual.save();
            limparCampos();

        } else {
            osAtual = new OrdemServico();
            //osAtual.setIdOS(Integer.parseInt(txtNumOS.getText()));
            osAtual.setIdCliente(Integer.parseInt(txtIdCliente.getText()));
            osAtual.setIdUsuario(Integer.parseInt(txtIdUsuario.getText()));
            osAtual.setNomeUsuario(txtNomeUsuario.getText());
            osAtual.setDataOS(LocalDate.now());
            osAtual.setTipo(radioButtonSelected());
            osAtual.setSituacao(cbSituacaoOS.getValue());
            osAtual.setProduto(txtProduto.getText());
            osAtual.setDescricao(txtDescricao.getText());
            osAtual.setValor(Double.parseDouble(txtValor.getText()));

            System.out.println(osAtual);

            osAtual.save();
            limparCampos();

            System.out.println("Criado com sucesso!!!");
        }
    }
//------------------------------------------------------------------------------
    private String radioButtonSelected() {
        if (rbOrcamento.isSelected()) {
            tipo = "Orçamento";
        }
        if (rbOrdemServico.isSelected()) {
            tipo = "Ordem de Serviço";
        }
        return tipo;
    }
//------------------------------------------------------------------------------
    private void limparCampos() {
        txtNumOS.setText("");
        cbSituacaoOS.setValue("");
        txtNomeCliente.setText("");
        txtIdCliente.setText("");
        txtProduto.setText("");
        txtDescricao.setText("");
        txtValor.setText("");

        tableView.getItems().clear();
    }
//------------------------------------------------------------------------------
    @FXML
    private void onActionEditar(ActionEvent event) {
    }
//------------------------------------------------------------------------------
    @FXML
    private void onActionApagar(ActionEvent event) {
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
    @FXML
    private void onActionSair(ActionEvent event) {
        Stage stageAtual = (Stage) btnSair.getScene().getWindow();
        stageAtual.close();
    }
//------------------------------------------------------------------------------
    @FXML
    private void tableViewOnMouseClicked(MouseEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            clienteSelecionado = Cliente.find(tableView.getSelectionModel().getSelectedItem().getIdCliente());
            txtNomeCliente.setText(clienteSelecionado.getNome());
            txtIdCliente.setText(clienteSelecionado.getIdCliente().toString());

            carregarTableViewOS();

        }
    }
//------------------------------------------------------------------------------
}
