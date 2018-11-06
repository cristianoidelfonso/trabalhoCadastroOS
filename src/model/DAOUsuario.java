package model;

import conexao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author Idelfonso
 */
public class DAOUsuario {
    
    public static void salvar(Usuario usuario) {
        Connection conn = ConnectionFactory.getConexao();
        try{
            String sql = "INSERT INTO tbl_usuario(nome, dataNasc, cpf, login, senha, perfil) VALUES(?,?,?,?,?,?) ";
            
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1,usuario.getNome());
                pst.setDate(2, java.sql.Date.valueOf(usuario.getDataNasc()));
                pst.setString(3, usuario.getCpf());
                pst.setString(4, usuario.getLogin());
                pst.setString(5, usuario.getSenha());
                pst.setString(6, usuario.getPerfil());
                
                pst.executeUpdate();
            }
            
            
        } catch (SQLException e) {
           Alert alerta = new Alert(Alert.AlertType.WARNING);
           alerta.setTitle("WARNING");
           alerta.setHeaderText("Não foi possivel salvar este usuário no banco de dados.");
           alerta.setContentText(e.getMessage());
           alerta.show();
         
        }finally{
            ConnectionFactory.fecharConexao(conn);
        }
        
    }
    
    private void editar(){
        
    }
    
    private void apagar(){
        
    }
       
}
