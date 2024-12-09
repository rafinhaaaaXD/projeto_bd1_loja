package DAO;
import conexao.ConexaoBD;
import entidade.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    public List<Venda> listarVendas() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda";

        try (Connection conn = ConexaoBD.getConexaoBD(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Venda venda = new Venda(
                        rs.getInt("id"),
                        rs.getDate("data"),
                        rs.getInt("cliente_id"),
                        rs.getDouble("total")
                );
                vendas.add(venda);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar vendas: " + e.getMessage());
        }
        return vendas;
    }

    public boolean inserirVenda(Venda venda) {
        String sql = "INSERT INTO venda (produto_id, data_venda, total) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexaoBD(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(2, new java.sql.Date(venda.getData().getTime()));
            stmt.setInt(1, venda.getClienteId());
            stmt.setDouble(3, venda.getTotal());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao inserir venda: " + e.getMessage());
            return false;
        }
    }
}
