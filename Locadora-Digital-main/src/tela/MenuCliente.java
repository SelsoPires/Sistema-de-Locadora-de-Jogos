package tela;

import java.util.Scanner;

import data.ItemLocaDAO;
import data.JogoDAO;
import data.LocacaoDAO;
import entidades.ItemLocacao;
import entidades.Jogo;
import entidades.Locacao;

public class MenuCliente {
public static void menuCliente(int id, Locacao carrinho) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("=== Menu Cliente ===");
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Listar jogos");
                System.out.println("2 - Buscar jogo");
                System.out.println("3 - Carrinho");
                System.out.println("4 - Meus jogos");
                System.out.println("0 - Sair");

                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        Listar.listarJogosDis(id, carrinho);
                        System.in.read();                  
                        break;
                    case 2:
                    try {        
                        System.out.println("Digite o nome do jogo que deseja buscar: ");
                        scanner.nextLine();
                        String tituloJogo = scanner.nextLine();
                        Jogo j = JogoDAO.buscarJogoPeloTitulo(tituloJogo.toLowerCase());
                        System.out.println(j);

                        ItemLocacao item = ItemLocaDAO.buscloc(j.getId());
                            if(item.getId() != 0){
                                Alugar.adicionaraoCarrinho(id, carrinho, j);
                            }else {
                                System.out.println("este jogo está alugado");
                            }
                    } catch (Exception erro) {
                        System.out.println(erro.getMessage());
                    }
                        break;
                    case 3:
                        Listar.mostrarCarrinho(carrinho,id);
                        break;
                    case 4:
                        LocacaoDAO.meusJogos(id);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }
    }
}
