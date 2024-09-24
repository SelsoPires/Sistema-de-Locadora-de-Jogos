package entidades;

public class ItemLocacao {
    private int id;
    private double valor;
    private Jogo jogo;
    private Locacao locacao;
    
    
    /*Getters */
    public int getId() {
        return id;
    }
   
    public double getValor() {
        return valor;
    }
    public Locacao getLocacao() {
        return locacao;
    }
    public Jogo getJogo() {
        return jogo;
    }

    /*Setters */
    public void setId(int id) {
        this.id = id;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
    public void setLocacao(Locacao lo) {
        this.locacao = lo;
    }

    @Override
    public String toString() {
        return "Jogo"
            +"\n" + " ID: " + id
            +"\n" + " Valor: R$" + valor
            +"\n" + "Data" + jogo.getTitulo();
        }
}
