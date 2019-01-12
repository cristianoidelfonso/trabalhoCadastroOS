
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
public class DAOOrdemServico extends ConnectionFactory {

    private PreparedStatement pst;
    private ResultSet rs;
    
//------------------------------------------------------------------------------
    public Integer create(OrdemServico os) {

        Integer generatedId = null;
        getConexao();
        try {
            //insert into tbl_os(idUsuario, idCliente, tipo, situacao, produto, descricao, valor)
            String sql = "INSERT INTO tbl_os (idUsuario, nomeUsuario, idCliente, dataOS, tipo, situacao, produto, descricao, valor)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, os.getIdUsuario());
            pst.setString(2, os.getNomeUsuario());
            pst.setInt(3, os.getIdCliente());
            pst.setDate(4, java.sql.Date.valueOf(os.getDataOS()));
            pst.setString(5, os.getTipo());
            pst.setString(6, os.getSituacao());
            pst.setString(7, os.getProduto());
            pst.setString(8, os.getDescricao());
            pst.setDouble(9, os.getValor());
            
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
            alerta.setHeaderText("Não foi possivel salvar a O.S. no banco de dados.");
            alerta.setContentText(e.getMessage());
            alerta.show();
        } finally {
            ConnectionFactory.fecharConexao(conn, pst, rs);
        }      
        return generatedId;
    }
    //--------------------------------------------------------------------------
    public void update(OrdemServico os) {
        getConexao();
        try {
            //insert into tbl_os(idUsuario, idCliente, dataOS, produto, descricao, valor)
            String sql = "UPDATE tbl_os SET produto = ?, descricao = ?, valor = ?  WHERE idOS = ?";

            pst = conn.prepareStatement(sql);

            pst.setString(1, os.getProduto());
            pst.setString(2, os.getDescricao());
            pst.setDouble(3, os.getValor());
            pst.setInt(4, os.getIdOS());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());

        } finally {
            fecharConexao(conn, pst);
        }
    }
//------------------------------------------------------------------------------  
    public OrdemServico buscarOS(int idOS) {
        OrdemServico resultado = new OrdemServico();
        getConexao();
        try {
            
            String sql = "SELECT tbl_os.* , tbl_cliente.nome NOME "
                        + "FROM tbl_os INNER JOIN tbl_cliente "
                        + "ON (tbl_os.idCliente = tbl_cliente.idCliente)"
                        + "WHERE idOS = ?";
            
            //String sql = "SELECT * FROM tbl_os WHERE idOS = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idOS);

            rs = pst.executeQuery();

            if (rs.next()) {
                resultado.setIdOS(rs.getInt("idOS"));
                resultado.setIdUsuario(rs.getInt("idUsuario"));
                resultado.setNomeUsuario(rs.getString("nomeUsuario"));
                resultado.setIdCliente(rs.getInt("idCliente"));
                resultado.setNomeCliente(rs.getString("NOME"));
                resultado.setDataOS(rs.getDate("dataOS").toLocalDate());
                resultado.setTipo(rs.getString("tipo"));
                resultado.setSituacao(rs.getString("situacao"));
                resultado.setProduto(rs.getString("produto"));
                resultado.setDescricao(rs.getString("descricao"));
                resultado.setValor(rs.getDouble("valor"));                              
            }
        } catch (SQLException e) {
        } finally {
            fecharConexao(conn, pst, rs);
        }
        return resultado;
    } 
//------------------------------------------------------------------------------
    public ArrayList<OrdemServico> findOS(Integer idCliente) {
        ArrayList<OrdemServico> lista = new ArrayList<>();
        getConexao();
        try {
            //Comando
            String sql = "SELECT * FROM tbl_os WHERE idCliente = ?";
            //Preparar o SQL
            pst = conn.prepareStatement(sql);

            pst.setInt(1, idCliente);
            //Executa consulta no bd
            rs = pst.executeQuery();

            //Enquanto tiver resultado no BD
            while (rs.next()) {
                OrdemServico os = new OrdemServico();
                    os.setIdOS(rs.getInt("idOS"));
                    os.setIdUsuario(rs.getInt("idUsuario"));
                    os.setNomeUsuario(rs.getString("nomeUsuario"));
                    os.setIdCliente(rs.getInt("idCliente"));
                    os.setDataOS(rs.getDate("dataOS").toLocalDate());
                    os.setProduto(rs.getString("produto"));
                    os.setDescricao(rs.getString("descricao"));
                    os.setValor(rs.getDouble("valor"));
                
                //adiciona o resultado na lista
                lista.add(os);

            }//while
        } catch (SQLException e) {

        }
        return lista;
    }
    
//------------------------------------------------------------------------------
    public ArrayList<OrdemServico> buscaTodas() {
        ArrayList<OrdemServico> lista = new ArrayList<>();
        getConexao();
        try {
            //Comando
            String sql = "SELECT * FROM tbl_os";
            //String sql = "SELECT tbl_os.idOS, tbl_cliente.nome NOME, tbl_cliente.idCliente ID_Cliente, tbl_os.dataOS, "
            //        + "tipo, situacao, produto, descricao, valor, idUsuario, nomeUsuario "
            //        + "FROM tbl_os INNER JOIN tbl_cliente ORDER BY tbl_cliente.nome;";
            //Preparar o SQL
            pst = conn.prepareStatement(sql);

            //Executa consulta no bd
            rs = pst.executeQuery();

            //Enquanto tiver resultado no BD
            while (rs.next()) {
                OrdemServico os = new OrdemServico();
                    os.setIdOS(rs.getInt("idOS"));
                    os.setIdUsuario(rs.getInt("idUsuario"));
                    os.setNomeUsuario(rs.getString("nomeUsuario"));
                    os.setIdCliente(rs.getInt("idCliente"));
                    os.setNomeCliente(rs.getString("NOME"));
                    os.setDataOS(rs.getDate("dataOS").toLocalDate());
                    os.setTipo(rs.getString("tipo"));
                    os.setSituacao(rs.getString("situacao"));
                    os.setProduto(rs.getString("produto"));
                    os.setDescricao(rs.getString("descricao"));
                    os.setValor(rs.getDouble("valor"));
                
                //adiciona o resultado na lista
                lista.add(os);

            }//while
        } catch (SQLException e) {

        }
        return lista;
    }
    
//------------------------------------------------------------------------------
    
     public void delete(OrdemServico os) {
        getConexao();
        try {
            String sql = "DELETE FROM tbl_os WHERE idOS = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, os.getIdOS());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
//------------------------------------------------------------------------------     
}
