package aula;

/**
 *
 * @author Senac
 */
public class Produto {
    
    private int id;
    private String descricao;
    private double precocusto;
    private double precovenda;
    
    // ALT + INSERT, GERA OS GETTERS E SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecocusto() {
        return precocusto;
    }

    public void setPrecocusto(double precocusto) {
        this.precocusto = precocusto;
    }

    public double getPrecovenda() {
        return precovenda;
    }

    public void setPrecovenda(double precovenda) {
        this.precovenda = precovenda;
    }
    
}
