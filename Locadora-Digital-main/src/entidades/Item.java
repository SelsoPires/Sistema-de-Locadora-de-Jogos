package entidades;

public abstract class Item {

    int id;
    String titulo;
    String descricao;
    int numdias;
    double preco;

    public Item( String titulo, String descricao, int numdias, double preco){
        this.titulo = titulo;
        this.descricao = descricao;
        this.numdias = numdias;
        this.preco = preco;
    }

    public Item(int id, String titulo, String descricao, int numdias, double preco){
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.numdias = numdias;
        this.preco = preco;
    }


    public Item(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getNumdias() {
        return numdias;
    }
    public void setNumdias(int numdias) {
        this.numdias = numdias;
    }

    
}
