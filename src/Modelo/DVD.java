package Modelo;

public class DVD {

    private int iddvd;
    private int id_filme;
    private double preco_compra;
    private String data_compra;
    private String situacao;
    //VERIFICAR
    public String tituloFilme;

    public String getTituloFilme() {
        return tituloFilme;
    }

    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    public int getIddvd() {
        return iddvd;
    }

    public void setIddvd(int iddvd) {
        this.iddvd = iddvd;
    }

    public int getIdFilme() {
        return id_filme;
    }

    public void setIdFilme(int id_filme) {
        this.id_filme = id_filme;
    }

    public double getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(double preco_compra) {
        this.preco_compra = preco_compra;
    }

    public String getData_compra() {
        return data_compra;
    }

    public void setData_compra(String data_compra) {
        this.data_compra = data_compra;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

}
