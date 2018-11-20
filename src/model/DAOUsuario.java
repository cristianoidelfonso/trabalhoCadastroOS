package model;

import conexao.ConnectionFactory;
import controller.FXMLCadastroUsuarioController;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Idelfonso
 */
public class DAOUsuario extends ConnectionFactory {

    private static PreparedStatement pst;
    private static ResultSet rs;

    public Integer create(Usuario usuario) {

        Integer generatedId = null;
        getConexao();

        try {
            String sql = "INSERT INTO tbl_usuario (nome, dataNasc, cpf, login, senha, perfil) VALUES (?,?,?,?,?,?)";
            //String sql = "INSERT INTO tbl_usuario VALUES (?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, usuario.getNome());
            pst.setDate(2, java.sql.Date.valueOf(usuario.getDataNasc()));
            pst.setString(3, usuario.getCpf());
            pst.setString(4, usuario.getLogin());
            pst.setString(5, usuario.getSenha());
            pst.setString(6, usuario.getPerfil());

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

    public void update(Usuario usuario) {
        getConexao();
        try {
            String sql = "UPDATE tbl_usuario SET nome = ?, dataNasc = ?, cpf = ?, "
                    + "login = ?, senha = ?, perfil = ? WHERE id = ?";

            pst = conn.prepareStatement(sql);
            pst.setString(1, usuario.getNome());
            pst.setDate(2, java.sql.Date.valueOf(usuario.getDataNasc()));
            pst.setString(3, usuario.getCpf());
            pst.setString(4, usuario.getLogin());
            pst.setString(5, usuario.getSenha());
            pst.setString(6, usuario.getPerfil());
            pst.setInt(7, usuario.getId());

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

    public void delete(Usuario usuario) {
        getConexao();
        try {
            String sql = "DELETE FROM tbl_usuario WHERE id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, usuario.getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public Usuario find(String pk) {
        Usuario resultado = null;
        getConexao();
        try {
            String sql = "SELECT * FROM tbl_usuario WHERE nome = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, pk);

            rs = pst.executeQuery();

            if (rs.next()) {
                Usuario usu = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("dataNasc").toLocalDate(),
                        rs.getString("cpf"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("perfil"));
                resultado = usu;
            }
        } catch (SQLException e) {
        } finally {
            fecharConexao(conn, pst, rs);
        }
        return resultado;
    }

    public Usuario find(int pk) {
        Usuario resultado = null;
        getConexao();
        try {
            String sql = "SELECT * FROM tbl_usuario WHERE id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, pk);

            rs = pst.executeQuery();

            if (rs.next()) {
                Usuario usu = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("dataNasc").toLocalDate(),
                        rs.getString("cpf"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("perfil"));
                resultado = usu;
            }
        } catch (SQLException e) {
        } finally {
            fecharConexao(conn, pst, rs);
        }
        return resultado;
    }

    /**
     * Retorna todos os produtos do banco de dados
     *
     * @return
     */
    public ArrayList<Usuario> listar(){
        ArrayList<Usuario> lista = new ArrayList<>();
        getConexao();
        try{
        //Comando
        String sql = "SELECT * FROM tbl_usuario";
        //Preparar o SQL
        pst = conn.prepareStatement(sql);
        //Executa consulta no bd
        rs = pst.executeQuery();

        //Enquanto tiver resultado no BD
        while (rs.next()) {
            //Cria o produto a partir do resultado do banco
            Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDate("dataNasc").toLocalDate(),
                    rs.getString("cpf"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getString("perfil"));

            //adiciona o resultado na lista
            lista.add(usuario);

        }//while
        }catch(SQLException e){
            
        }
        return lista;
    }
    
    //--------------------------------------------------------------------------
    /**
     * Pesquisar em tempo real, pelo nome que esta sendo digitado no campo de texto "NOME";
     */
    public void pesquisarUsuario(){
        getConexao();
        
        try {
            String sql = "SELECT * FROM tbl_usuario WHERE nome LIKE ?";
            pst = conn.prepareStatement(sql);
            //pst.setString(1, FXMLCadastroUsuarioController.txtNome.getText() + "%" );
            rs = pst.executeQuery();
            
            //tbl_usuario.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (SQLException e) {
        }
        
    }
    //--------------------------------------------------------------------------
}
