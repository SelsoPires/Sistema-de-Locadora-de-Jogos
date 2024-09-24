package entidades;
import java.util.Date;
import java.util.ArrayList;

public class Locacao {
    private int id;
    private double valor;
    private Date data;
    private Usuario usuario; 
    private ArrayList<ItemLocacao> itensLocacao = new ArrayList<>();
    
    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }
   
    public double getValor() {
        return valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ArrayList<ItemLocacao> getItensLocacao() {
        return itensLocacao;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
  
    public void setData(Date data) {
        this.data = data;
    }
   
    public void setItensLocacao(ItemLocacao itemlocacao) {
        this.itensLocacao.add(itemlocacao);
    }
   
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public String toString() {
        return "Jogo"
            +"\n" + " ID: " + id
            +"\n" + " Valor do carrinho: R$" + valor
            +"\n" + "Data" + data
            +"\n" + " itens alugados: " + itensLocacao;
        }
}
