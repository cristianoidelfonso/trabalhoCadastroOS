package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DBNAME = "cadastroOS";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection conn = null;
    
    public static Connection getConexao(){  
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            return conn;
            //System.out.println("Conectado com sucesso");
            
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Ocorreu um  erro inesperado" + e);
            //System.out.println(e.toString());
        }
    }
//------------------------------------------------------------------------------    
    public static void fecharConexao(Connection conn) {
        if (conn != null)//se estiver conectado
        {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("Ocorreu um  erro inesperado" + e);
            }
        }
    }
//------------------------------------------------------------------------------
    public static void fecharConexao(Connection conn, PreparedStatement pst) {
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                throw new RuntimeException("Ocorreu um  erro inesperado" + e);
            }
        }
        fecharConexao(conn);
    }
//------------------------------------------------------------------------------
    public static void fecharConexao(Connection conn, PreparedStatement pst, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException("Ocorreu um  erro inesperado" + e);
            }
        }
        fecharConexao(conn, pst);
    }
}
//==============================================================================