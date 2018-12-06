/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            String sql = "INSERT INTO tbl_os (idUsuario, idCliente, dataOS, tipo, situacao, produto, descricao, valor)"
                    + "VALUES (?,?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, os.getIdUsuario());
            pst.setInt(2, os.getIdCliente());
            pst.setDate(3, java.sql.Date.valueOf(os.getDataOS()));
            pst.setString(4, os.getTipo());
            pst.setString(5, os.getSituacao());
            pst.setString(6, os.getProduto());
            pst.setString(7, os.getDescricao());
            pst.setDouble(8, os.getValor());
            
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
    
    public OrdemServico find(int pk) {
        OrdemServico resultado = null;
        getConexao();
        try {
            String sql = "SELECT * FROM tbl_os WHERE idOS = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, pk);

            rs = pst.executeQuery();

            if (rs.next()) {
                OrdemServico os = new OrdemServico();
                        rs.getInt("idOS");
                        rs.getInt("idUsuario");
                        rs.getInt("idCliente");
                        rs.getDate("dataOS").toLocalDate();
                        rs.getString("tipo");
                        rs.getString("situacao");
                        rs.getString("produto");
                        rs.getString("descricao");
                        rs.getDouble("valor");
                        
                resultado = os;
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

}
