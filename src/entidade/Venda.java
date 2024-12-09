package entidade;

import java.util.Date;

public class Venda {

    private int id;
    private Date data;
    private int clienteId;
    private double total;

    // Construtor, Getters e Setters
    public Venda(int id, Date data, int clienteId, double total) {
        this.id = id;
        this.data = data;
        this.clienteId = clienteId;
        this.total = total;
    }

    public Venda() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
