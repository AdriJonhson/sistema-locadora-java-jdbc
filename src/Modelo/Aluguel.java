package Modelo;

public class Aluguel {
    private int idAluguel;
    private int idDvd;
    private int idCliente;
    private String hora;
    private String dataAluguel;
    private String dataDevolucao;

    public int getIdAluguel() {
        return idAluguel;
    }

    public void setIdAluguel(int idAluguel) {
        this.idAluguel = idAluguel;
    }

    public int getIdDvd() {
        return idDvd;
    }

    public void setIdDvd(int idDvd) {
        this.idDvd = idDvd;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(String dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
    
    
}
