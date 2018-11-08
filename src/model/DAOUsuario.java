package model;

import conexao.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

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
            //String sql = "INSERT INTO tbl_usuario (nome, dataNasc, cpf, login, senha, perfil) VALUES (?,?,?,?,?,?)";
            String sql = "INSERT INTO tbl_usuario VALUES (?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(2, usuario.getNome());
            pst.setDate(3, java.sql.Date.valueOf(usuario.getDataNasc()));
            pst.setString(4, usuario.getCpf());
            pst.setString(5, usuario.getLogin());
            pst.setString(6, usuario.getSenha());
            pst.setString(7, usuario.getPerfil());

            pst.executeUpdate();
            
            rs = pst.getGeneratedKeys();
            if(rs.next()){
                generatedId = rs.getInt("id");
            }

            // Se salvar com sucesso, imprime esta mensagem.
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("SUCESSO");
            alerta.setHeaderText("Salvo com sucesso");
            alerta.setContentText("Novo usuário foi cadastrado com sucesso");
            alerta.show();

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

    public void atualizar(Usuario usuario) {
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
            //pst.setInt(7, usuario.getId());

            pst.executeUpdate();

            // Se salvar com sucesso, imprime esta mensagem.
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("SUCESSO");
            alerta.setHeaderText("Alterado com sucesso!");
            alerta.setContentText("Novo usuário foi cadastrado com sucesso");
            alerta.show();

        } catch (SQLException e) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("WARNING");
            alerta.setHeaderText("Não foi possivel alterar os dados desse usuário no banco de dados.");
            alerta.setContentText(e.getMessage());
            alerta.show();

        } finally {
            fecharConexao(conn, pst);
        }
    }

    private void apagar() {

    }

    public Usuario find(String n) {
        Usuario resultado = null;
        getConexao();
        try {
            String sql = "SELECT * FROM tbl_usuario WHERE nome = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, n);

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
}
