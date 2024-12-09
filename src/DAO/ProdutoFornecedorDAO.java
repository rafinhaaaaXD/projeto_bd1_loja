package DAO;
import conexao.ConexaoBD;
import entidade.ProdutoFornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoFornecedorDAO {

    public List<ProdutoFornecedor> listarProdutoFornecedores() {
        List<ProdutoFornecedor> produtoFornecedores = new ArrayList<>();
        String sql = "SELECT * FROM produtofornecedor";

        try (Connection conn = ConexaoBD.getConexaoBD(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ProdutoFornecedor produtoFornecedor = new ProdutoFornecedor(
                        rs.getInt("produto_id"), // Ajuste se necessário
                        rs.getInt("fornecedor_id"), // Ajuste se necessário
                        rs.getDouble("preco_fornecedor")
                );
                produtoFornecedores.add(produtoFornecedor);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produto-fornecedores: " + e.getMessage());
        }
        return produtoFornecedores;
    }

    public boolean inserirProdutoFornecedor(ProdutoFornecedor produtoFornecedor) {
        String sql = "INSERT INTO produtofornecedor (produto_id, fornecedor_id, preco_fornecedor) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexaoBD(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Use 'id' em vez de 'produto_id' ou 'fornecedor_id' para refletir a estrutura correta da tabela
            stmt.setInt(1, produtoFornecedor.getProdutoId()); // Produto id
            stmt.setInt(2, produtoFornecedor.getFornecedorId()); // Fornecedor id
            stmt.setDouble(3, produtoFornecedor.getPrecoFornecedor()); // Preço do fornecedor

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao inserir produto-fornecedor: " + e.getMessage());
            return false;
        }
    }
}
