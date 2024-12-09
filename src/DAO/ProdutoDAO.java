package DAO;
import conexao.ConexaoBD; 
import entidade.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, descricao, preco, estoque, categoria_id FROM produto";

        try (Connection conn = ConexaoBD.getConexaoBD(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getInt("estoque"),
                    rs.getInt("categoria_id")
                );
                produtos.add(produto);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
        return produtos;
    }

    public boolean inserirProduto(Produto produto) {
        String sql = "INSERT INTO produto (nome, descricao, preco, estoque, categoria_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexaoBD(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getEstoque());
            stmt.setInt(5, produto.getCategoriaId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
            return false;
        }
    }
    public void calcularEstatisticasPreco() {
        String sql = "SELECT AVG(preco) AS media, MIN(preco) AS menor, MAX(preco) AS maior FROM Produto";
        try (Connection conn = ConexaoBD.getConexaoBD();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                System.out.println("Preço médio: " + rs.getDouble("media"));
                System.out.println("Menor preço: " + rs.getDouble("menor"));
                System.out.println("Maior preço: " + rs.getDouble("maior"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public boolean deletarProduto(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
    
        try (Connection conn = ConexaoBD.getConexaoBD(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarProduto(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, descricao = ?, preco = ?, estoque = ?, categoria_id = ? WHERE id = ?";
    
        try (Connection conn = ConexaoBD.getConexaoBD(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getEstoque());
            stmt.setInt(5, produto.getCategoriaId());
            stmt.setInt(6, produto.getId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
            return false;
        }
    }

    public void calcularSomaEQuantidadeProdutos() {
        String sql = "SELECT SUM(preco) AS soma_total, COUNT(id) AS quantidade_total FROM produto";
        try (Connection conn = ConexaoBD.getConexaoBD();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            if (rs.next()) {
                double somaTotal = rs.getDouble("soma_total");
                int quantidadeTotal = rs.getInt("quantidade_total");
    
                System.out.printf("Soma total dos preços: %.2f\n", somaTotal);
                System.out.println("Quantidade total de produtos: " + quantidadeTotal);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao calcular soma e quantidade dos produtos: " + e.getMessage());
        }
    }    
}
