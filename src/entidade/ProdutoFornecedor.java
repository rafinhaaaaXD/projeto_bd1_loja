package entidade;

public class ProdutoFornecedor {

    private int produtoId;
    private int fornecedorId;
    private double precoFornecedor;

    // Construtor, Getters e Setters
    public ProdutoFornecedor(int produtoId, int fornecedorId, double precoFornecedor) {
        this.produtoId = produtoId;
        this.fornecedorId = fornecedorId;
        this.precoFornecedor = precoFornecedor;
    }

    public ProdutoFornecedor() {
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(int fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public double getPrecoFornecedor() {
        return precoFornecedor;
    }

    public void setPrecoFornecedor(double precoFornecedor) {
        this.precoFornecedor = precoFornecedor;
    }
}
