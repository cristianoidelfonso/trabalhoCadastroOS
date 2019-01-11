
package controller;

import java.net.URL;
import java.time.LocalDate;
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
import model.Cliente;
import model.OrdemServico;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Idelfonso
 */
public class ConsultarOSController implements Initializable {
    
    private Usuario usuarioLogado = null;

    @FXML
    private Label lblSair;
    //Dados da Tabela
    private ObservableList<Cliente> dadosClientes;
    private ObservableList<OrdemServico> dadosOS;
    private String tipo;
    @FXML
    private TableView<OrdemServico> tableViewOS;

    /**
     * Initializes the controller class.
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
                //txtIdUsuario.setText(usuarioLogado.getId().toString());
                //txtNomeUsuario.setText(usuarioLogado.getNome());
                
                //testaPerfil();
            }
        });

        configurarTabela();

        // Carregando a data de emissão da OS
        //txtDtaEmissao.setText(LocalDate.now().format(dtf));
        //dataEntrega(txtDtaEmissao.getText());

    }    

    @FXML
    private void lblSairOnMouseClicked(MouseEvent event) {
    }
    
    //==============================================================================
    /**
     * Faz a configuração da tabela e das colunas
     */
    private void configurarTabela() {
        
        //idOS,nome,idCliente,dataOS, "
        //tipo,situacao,produto,descricao,valor,
        //idUsuario,nomeUsuario

        //Configurando as colunas da tabela
        TableColumn<OrdemServico, String> colNumOS = new TableColumn("Num_OS:");
        TableColumn<OrdemServico, String> colNome = new TableColumn("Cliente");
        TableColumn<OrdemServico, LocalDate> colData = new TableColumn("Data");

        TableColumn<OrdemServico, String> colTipo = new TableColumn("Tipo");
        TableColumn<OrdemServico, String> colSituacao = new TableColumn("Situação");
        TableColumn<OrdemServico, String> colProduto = new TableColumn("Produto");
        TableColumn<OrdemServico, String> colDescricao = new TableColumn("Descrição");
        TableColumn<OrdemServico, Double> colValor = new TableColumn("Valor");

        //Configurar como os valores serão lidos (nome dos atributos)
        colNumOS.setCellValueFactory(new PropertyValueFactory<>("idOS"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colData.setCellValueFactory(new PropertyValueFactory<>("dataOS"));
        
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        //Configurando a largura das colunas da tabela
        tableViewOS.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colNumOS.setMaxWidth(1f * Integer.MAX_VALUE * 6); // 5% width
        colNome.setMaxWidth(1f * Integer.MAX_VALUE * 20); // 15% width
        colData.setMaxWidth(1f * Integer.MAX_VALUE * 10); // 8% width
        colTipo.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        colSituacao.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        colProduto.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        //colDescricao.setMaxWidth(1f * Integer.MAX_VALUE * 25);
        colValor.setMaxWidth(1f * Integer.MAX_VALUE * 14);

        //Adiciona as colunas na tabela na ordem que devem aparecer
        tableViewOS.getColumns().addAll(colNumOS, colNome, colData, colTipo, colSituacao, colProduto, colValor);
    }

    // carrega a tabela de OS
    private void carregarTableViewOS() {
        try {
            //Convertendo o ArrayList no ObservableList com os dados do Banco
            dadosOS = FXCollections.observableArrayList(OrdemServico.buscaTodas());
            //Joga os dados na tabela para exibir
            tableViewOS.setItems(dadosOS);
        } catch (NumberFormatException e) {

        }
    }
/*
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
*/
//--------------------------------------------------------------------------
    
}
