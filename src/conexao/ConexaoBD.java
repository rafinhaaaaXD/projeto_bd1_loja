package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private static final String URL = "jdbc:mysql://localhost:3306/db_loja";
    private static final String USER = "root";
    private static final String SENHA = "092419";

    private static Connection conectar;

    public static Connection getConexaoBD() {
        try {
            if (conectar == null || conectar.isClosed()) {
                conectar = DriverManager.getConnection(URL, USER, SENHA);
                System.out.println("Conexão com o banco de dados estabelecida com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao estabelecer conexão com o banco de dados: " + e.getMessage());
            throw new RuntimeException("Não foi possível conectar ao banco de dados.", e);
        }
        return conectar;
    }
}
