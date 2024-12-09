package DAO;
import conexao.ConexaoBD;
import entidade.ItemVenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemVendaDAO {

    public List<ItemVenda> listarItemVendas() {
        List<ItemVenda> itemVendas = new ArrayList<>();
        String sql = "SELECT * FROM itemvenda";

        try (Connection conn = ConexaoBD.getConexaoBD(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ItemVenda itemVenda = new ItemVenda(
                        rs.getInt("venda_id"),
                        rs.getInt("produto_id"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco_unitario")
                );
                itemVendas.add(itemVenda);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar item-vendas: " + e.getMessage());
        }
        return itemVendas;
    }

    public boolean inserirItemVenda(ItemVenda itemVenda) {
        String sql = "INSERT INTO itemvenda (venda_id, produto_id, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexaoBD(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemVenda.getVendaId());
            stmt.setInt(2, itemVenda.getProdutoId());
            stmt.setInt(3, itemVenda.getQuantidade());
            stmt.setDouble(4, itemVenda.getPrecoUnitario());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao inserir item-venda: " + e.getMessage());
            return false;
        }
    }

    public void calcularEstatisticasVendas() {
        String sql = """
            SELECT AVG(iv.quantidade * iv.preco_unitario) AS media_venda,
                   MIN(iv.quantidade * iv.preco_unitario) AS menor_venda,
                   MAX(iv.quantidade * iv.preco_unitario) AS maior_venda
            FROM itemvenda iv;
        """;
        try (Connection conn = ConexaoBD.getConexaoBD();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                System.out.println("Média de vendas: " + rs.getDouble("media_venda"));
                System.out.println("Menor valor de venda: " + rs.getDouble("menor_venda"));
                System.out.println("Maior valor de venda: " + rs.getDouble("maior_venda"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao calcular estatísticas de vendas: " + e.getMessage());
        }
    }  
}
