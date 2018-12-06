package model;

import conexao.ConnectionFactory;
import static conexao.ConnectionFactory.conn;
import static conexao.ConnectionFactory.getConexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author Idelfonso
 */
public class DAOCliente extends ConnectionFactory {

    private static PreparedStatement pst;
    private static ResultSet rs;

    public Integer create(Cliente cliente) {

        Integer generatedId = null;
        getConexao();

        try {
            String sql = "INSERT INTO tbl_cliente (nome, dataNasc, cpf, rg, telefone, "
                    + "rua, numero, bairro, cidade, estado, cep) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            //String sql = "INSERT INTO tbl_usuario VALUES (?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, cliente.getNome());
            pst.setDate(2, java.sql.Date.valueOf(cliente.getDataNasc()));
            pst.setString(3, cliente.getCpf());
            pst.setString(4, cliente.getRg());
            pst.setString(5, cliente.getTelefone());
            pst.setString(6, cliente.getRua());
            pst.setString(7, cliente.getNumero());
            pst.setString(8, cliente.getBairro());
            pst.setString(9, cliente.getCidade());
            pst.setString(10, cliente.getEstado());
            pst.setString(11, cliente.getCep());

            if (pst.executeUpdate() != 0) {
                System.out.println("Salvo com sucesso");
            }

            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
                System.out.println("ID gerado: " + generatedId);
            }

        } catch (SQLException e) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("WARNING");
            alerta.setHeaderText("Não foi possivel salvar este usuário no banco de dados.");
            alerta.setContentText(e.getMessage());
            alerta.show();

        } finally {
            ConnectionFactory.fecharConexao(conn, pst, rs);
        }
        return generatedId;
    }

//------------------------------------------------------------------------------
    public void update(Cliente cliente) {
        getConexao();
        try {
            String sql = "UPDATE tbl_cliente SET nome = ?, dataNasc = ?, cpf = ?, "
                    + "rg = ?, telefone = ?, rua = ? , numero = ?, bairro = ?, cidade = ?, "
                    + "estado = ?, cep = ?  WHERE id = ?";

            pst = conn.prepareStatement(sql);
            pst.setString(1, cliente.getNome());
            pst.setDate(2, java.sql.Date.valueOf(cliente.getDataNasc()));
            pst.setString(3, cliente.getCpf());
            pst.setString(4, cliente.getRg());
            pst.setString(5, cliente.getTelefone());
            pst.setString(6, cliente.getRua());
            pst.setString(7, cliente.getNumero());
            pst.setString(8, cliente.getBairro());
            pst.setString(9, cliente.getCidade());
            pst.setString(10, cliente.getEstado());
            pst.setString(11, cliente.getCep());
            pst.setInt(12, cliente.getIdCliente());

            pst.executeUpdate();

        } catch (SQLException e) {
//            Alert alerta = new Alert(Alert.AlertType.WARNING);
//            alerta.setTitle("WARNING");
//            alerta.setHeaderText("Não foi possivel alterar os dados desse usuário no banco de dados.");
//            alerta.setContentText(e.getMessage());
//            alerta.show();
            throw new RuntimeException(e.getMessage());

        } finally {
            fecharConexao(conn, pst);
        }
    }
//------------------------------------------------------------------------------    

    public Cliente find(String nome) {
        Cliente resultado = null;
        getConexao();
        try {
            String sql = "SELECT * FROM tbl_cliente WHERE nome LIKE BINARY ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, nome);

            rs = pst.executeQuery();

            if (rs.next()) {
                Cliente cli = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nome"),
                        rs.getDate("dataNasc").toLocalDate(),
                        rs.getString("cpf"),
                        rs.getString("rg"),
                        rs.getString("telefone"),
                        rs.getString("rua"),
                        rs.getString("numero"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("cep"));
                resultado = cli;
            }
        } catch (SQLException e) {
        } finally {
            fecharConexao(conn, pst, rs);
        }
        return resultado;
    }
//==== AQUI ====================================================================    
    public ArrayList<Cliente> findName(String nome){
        ArrayList<Cliente> lista = new ArrayList<>();
        getConexao();
        try{
        //Comando
        String sql = "SELECT * FROM tbl_cliente WHERE nome LIKE ?";
        //Preparar o SQL
        pst = conn.prepareStatement(sql);
        pst.setString(1, nome);
        //Executa consulta no bd
        rs = pst.executeQuery();

        //Enquanto tiver resultado no BD
        while (rs.next()) {
            //Cria o produto a partir do resultado do banco
            Cliente cliente = new Cliente(
                    rs.getInt("idCliente"),
                    rs.getString("nome"),
                    rs.getDate("dataNasc").toLocalDate(),
                    rs.getString("cpf"),
                    rs.getString("rg"),
                    rs.getString("telefone"),
                    rs.getString("rua"),
                    rs.getString("numero"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("cep"));

            //adiciona o resultado na lista
            lista.add(cliente);

        }//while
        }catch(SQLException e){
            
        }
        return lista;
    }   
    
//------------------------------------------------------------------------------
    
    public Cliente find(int pk) {
        Cliente resultado = null;
        getConexao();
        try {
            String sql = "SELECT * FROM tbl_cliente WHERE idCliente = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, pk);

            rs = pst.executeQuery();

            if (rs.next()) {
                Cliente cli = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nome"),
                        rs.getDate("dataNasc").toLocalDate(),
                        rs.getString("cpf"),
                        rs.getString("rg"),
                        rs.getString("telefone"),
                        rs.getString("rua"),
                        rs.getString("numero"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("cep"));
                resultado = cli;
            }
        } catch (SQLException e) {
        } finally {
            fecharConexao(conn, pst, rs);
        }
        return resultado;
    }

//------------------------------------------------------------------------------
    
    public ArrayList<Cliente> listar(){
        ArrayList<Cliente> lista = new ArrayList<>();
        getConexao();
        try{
        //Comando
        String sql = "SELECT * FROM tbl_cliente";
        //Preparar o SQL
        pst = conn.prepareStatement(sql);
        //Executa consulta no bd
        rs = pst.executeQuery();

        //Enquanto tiver resultado no BD
        while (rs.next()) {
            //Cria o produto a partir do resultado do banco
            Cliente cliente = new Cliente(
                    rs.getInt("idCliente"),
                    rs.getString("nome"),
                    rs.getDate("dataNasc").toLocalDate(),
                    rs.getString("cpf"),
                    rs.getString("rg"),
                    rs.getString("telefone"),
                    rs.getString("rua"),
                    rs.getString("numero"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("cep"));

            //adiciona o resultado na lista
            lista.add(cliente);

        }//while
        }catch(SQLException e){
            
        }
        return lista;
    }   
    
//------------------------------------------------------------------------------    

}
