package entidades;

public class Jogo extends Item {
    private String plataforma;
    private int memoria;

    public Jogo(String titulo, String descricao, int numdias, double preco, String plataforma, int memoria){
        super(titulo, descricao, numdias, preco);
        this.plataforma = plataforma;
        this.memoria = memoria;
    }
    
    public Jogo(){
        super();
    }

    /*Getters */
    

    public String getPlataforma() {
        return plataforma;
    }

    public int getMemoria() {
        return memoria;
    }

    /*Setters */
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
  
    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    @Override
    public String toString() {
        return "Jogo"
            +"\n" + " ID: " + id
            +"\n" + " Titulo: " + titulo
            +"\n" + " Preco= R$" + preco
                +"\n" + " Descricao: " + descricao
                +"\n" + " NumeroDias: " + numdias
                +"\n" + " Plataforma: " + plataforma
                +"\n" + " Espaço de memoria: " + memoria + " GB"; 
        }

}
