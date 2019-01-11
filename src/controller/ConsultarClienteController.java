
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Cliente;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Idelfonso
 */
public class ConsultarClienteController implements Initializable {
    
    private Usuario usuarioLogado = null;

    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblSair;
    @FXML
    private TableView<Cliente> tableView;
    //Dados da Tabela
    private ObservableList<Cliente> dados;

    /**
     * Initializes the controller class.
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
        
        configurarTabela();
        carregarTableView();
        
    }    

    @FXML
    private void lblSairOnMouseClicked(MouseEvent event) {
        // Recuperando o stage atual
        Stage stageAtual = (Stage) lblSair.getScene().getWindow();
        // Fecha o stage atual
        stageAtual.close();
    }
    
    
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
        colCpf.setMaxWidth(1f * Integer.MAX_VALUE * 10); // 8% width
        colRg.setMaxWidth(1f * Integer.MAX_VALUE * 8); // 8% width
        colTelefone.setMaxWidth(1f * Integer.MAX_VALUE * 10); // 8% width
        colRua.setMaxWidth(1f * Integer.MAX_VALUE * 8); // 8% width
        colNumero.setMaxWidth(1f * Integer.MAX_VALUE * 5); // 5% width
        colBairro.setMaxWidth(1f * Integer.MAX_VALUE * 8); // 8% width
        colCidade.setMaxWidth(1f * Integer.MAX_VALUE * 10); // 8% width
        colEstado.setMaxWidth(1f * Integer.MAX_VALUE * 5); // 5% width
        colCep.setMaxWidth(1f * Integer.MAX_VALUE * 8); // 8% width

        //Adiciona as colunas na tabela na ordem que devem aparecer
        tableView.getColumns().addAll(colId, colNome, colDataNasc, colCpf,
                colRg, colTelefone, colRua, colNumero, colBairro, colCidade, colEstado, colCep);
    }
//------------------------------------------------------------------------------
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
//------------------------------------------------------------------------------
    
}
