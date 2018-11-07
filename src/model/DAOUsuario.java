package model;

import conexao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author Idelfonso
 */
public class DAOUsuario {

    public static boolean salvar(Usuario usuario) {
        Connection conn = ConnectionFactory.getConexao();
        try {
            String sql = "INSERT INTO tbl_usuario(nome, dataNasc, cpf, login, senha, perfil) VALUES(?,?,?,?,?,?) ";

            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, usuario.getNome());
                pst.setDate(2, java.sql.Date.valueOf(usuario.getDataNasc()));
                pst.setString(3, usuario.getCpf());
                pst.setString(4, usuario.getLogin());
                pst.setString(5, usuario.getSenha());
                pst.setString(6, usuario.getPerfil());

                pst.executeUpdate();

                // Se salvar com sucesso, imprime esta mensagem.
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("SUCESSO");
                alerta.setHeaderText("Salvo com sucesso");
                alerta.setContentText("Novo usuário foi cadastrado com sucesso");
                alerta.show();
                
                return true;
            }

        } catch (SQLException e) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("WARNING");
            alerta.setHeaderText("Não foi possivel salvar este usuário no banco de dados.");
            alerta.setContentText(e.getMessage());
            alerta.show();

        } finally {
            ConnectionFactory.fecharConexao(conn);
        }
        
        return false;
        
    }

    private void editar(Usuario usuario) {
        Connection conn = ConnectionFactory.getConexao();
        try {
            String sql = "UPDATE tbl_usuario SET nome = ?, dataNasc = ?, cpf = ?, "
                    + "login = ?, senha = ?, perfil = ?) WHERE id = ?";

            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, usuario.getNome());
                pst.setDate(2, java.sql.Date.valueOf(usuario.getDataNasc()));
                pst.setString(3, usuario.getCpf());
                pst.setString(4, usuario.getLogin());
                pst.setString(5, usuario.getSenha());
                pst.setString(6, usuario.getPerfil());
                pst.setInt(7, usuario.getId());

                pst.executeUpdate();

                // Se salvar com sucesso, imprime esta mensagem.
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("SUCESSO");
                alerta.setHeaderText("Alterado com sucesso!");
                alerta.setContentText("Novo usuário foi cadastrado com sucesso");
                alerta.show();
            }

        } catch (SQLException e) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("WARNING");
            alerta.setHeaderText("Não foi possivel alterar os dados desse usuário no banco de dados.");
            alerta.setContentText(e.getMessage());
            alerta.show();

        } finally {
            ConnectionFactory.fecharConexao(conn);
        }
    }

    private void apagar() {

    }

    public static Usuario pesquisarNoBD(String n) throws SQLException {
        Connection conn = ConnectionFactory.getConexao();

        String sql = "SELECT * FROM tbl_usuario WHERE nome = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, n);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDate("dataNasc").toLocalDate(),
                    rs.getString("cpf"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getString("perfil")
            );
            return usuario;
        }
        return null;
    }
}
