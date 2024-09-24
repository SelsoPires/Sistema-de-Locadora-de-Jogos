package tela;
import data.ItemLocaDAO;
import entidades.ItemLocacao;
import entidades.Jogo;
import entidades.Locacao;

import java.util.Scanner;


public class Alugar {
    
    
    public static void adicionaraoCarrinho(int id, Locacao carrinho, Jogo jogo){
        Scanner scan = new Scanner(System.in);
            int options;
            System.out.println("Deseja adicionar " + jogo.getTitulo() + " ao Carrinho?"+ "\n 1-Sim"+"\n 2-Não");
            options = scan.nextInt();
            if (options == 1) {
                ItemLocacao itemlocacao = ItemLocaDAO.buscloc(jogo.getId());

                carrinho.setValor(carrinho.getValor() + itemlocacao.getValor());

                carrinho.getItensLocacao().add(itemlocacao);
                System.out.println(carrinho);
            }else{
                    
            }
        }
    }
