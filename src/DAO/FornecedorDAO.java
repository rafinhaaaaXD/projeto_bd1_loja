package DAO;
import conexao.ConexaoBD;
import entidade.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public List<Fornecedor> listarFornecedores() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor";

        try (Connection conn = ConexaoBD.getConexaoBD(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("contato")
                );
                fornecedores.add(fornecedor);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar fornecedores: " + e.getMessage());
        }
        return fornecedores;
    }

    public boolean inserirFornecedor(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor (nome, contato) VALUES (?, ?)";
    
        try (Connection conn = ConexaoBD.getConexaoBD(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getContato());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao inserir fornecedor: " + e.getMessage());
            return false;
        }
    }
}
