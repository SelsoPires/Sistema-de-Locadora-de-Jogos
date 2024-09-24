package tela;

import java.io.IOException;
import java.util.Scanner;

import data.JogoDAO;
import data.UsuarioDAO;
import entidades.Jogo;
import entidades.Locacao;

public class MenuAdmin {
public static void menuAdmin() throws IOException {
    Locacao carrinho = new Locacao();
        Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n".repeat(50));
                System.out.println("=== Menu Administrador ===");
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Criar jogo");
                System.out.println("2 - Buscar jogo");
                System.out.println("3 - Listar jogos");
                System.out.println("4 - Excluir jogo");
                System.out.println("5 - Atualizar jogo");
                System.out.println("6 - Listar usuario");
                System.out.println("7 - Excluir usuario");
                System.out.println("0 - Sair");

                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println("INFORME OS DADOS DO JOGO\n\n");

                        System.out.println("Digite o TITULO: ");
                        scanner.nextLine();
                        String titulo = scanner.nextLine();

                        System.out.println("Digite a DESCRIÇÃO: ");
                        String descricao = scanner.nextLine();

                        System.out.println("Digite o NUMEDO DE DIAS que pode ser alugado: ");
                        int numdias = scanner.nextInt();

                        System.out.println("Digite o PREÇO: ");
                        double preco = scanner.nextDouble();

                        System.out.println("Digite a PLATAFORMA que pertence: ");
                        scanner.nextLine();
                        String plataforma = scanner.nextLine();

                        System.out.println("Digite quanto de MEMORIA: ");
                        int memoria = scanner.nextInt();

                        Jogo jogo = new Jogo(titulo.toLowerCase(), descricao, numdias, preco, plataforma, memoria);
                        JogoDAO.criar(jogo);
                        break;
                    case 2:
                        try {        
                            System.out.println("Digite o nome do jogo que deseja buscar: ");
                            scanner.nextLine();
                            String tituloJogo = scanner.nextLine();
                            JogoDAO.buscarJogoPeloTitulo(tituloJogo.toLowerCase());

                        } catch (Exception erro) {
                            System.out.println(erro.getMessage());
                        }
                        
                        break;
                    case 3:
                        Listar.listarJogos(1, carrinho);
                        Listar.options(opcao, 1, carrinho);

                        break;
                    case 4:
                        System.out.println("Digite o nome do jogo que deseja excluir: ");
                        scanner.nextLine();
                        String tituloJog = scanner.nextLine();
                        boolean jogoExcluido = JogoDAO.excluir(tituloJog);

                        if (jogoExcluido == true) {
                            System.out.println("Jogo excluido com sucesso.");
                        } else {
                            System.out.println("Jogo inexistente! Tente novamente");
                        }
                        break;
                    case 5:
                        System.out.println("Digite o nome do jogo que deseja atualizar: ");
                        scanner.nextLine();
                        String nomeJogo = scanner.nextLine();
                        Jogo j = JogoDAO.buscarJogoPeloTitulo(nomeJogo);

                            System.out.println("Digite o TITULO: ");
                            String titulo2 = scanner.nextLine();
                            j.setTitulo(titulo2.toLowerCase());

                            System.out.println("Digite o PREÇO: ");
                            double preco2 = scanner.nextDouble();
                            j.setPreco(preco2);

                            System.out.println("Digite a DESCRIÇÃO: ");
                            scanner.nextLine();
                            String descricao2 = scanner.nextLine();
                            j.setDescricao(descricao2);

                            System.out.println("Digite o NUMEDO DE DIAS que pode ser alugado: ");
                            int numdias2 = scanner.nextInt();
                            j.setNumdias(numdias2);
                            
                            System.out.println("Digite a PLATAFORMA que pertence: ");
                            scanner.nextLine();
                            String plataforma2 = scanner.nextLine();
                            j.setPlataforma(plataforma2);

                            System.out.println("Digite quanto de MEMORIA: ");
                            int memoria2 = scanner.nextInt();
                            j.setMemoria(memoria2);
                        JogoDAO.atualizar(j);
            
                        break;
                    case 6:
                        Listar.listarUser();
                        System.in.read();
                        break;
                    case 7:
                        System.out.println("Digite o login do usuario que você deseja excluir: ");
                        scanner.nextLine();
                        String loginUsuario = scanner.nextLine();
                        boolean usuarioExcluido = UsuarioDAO.excluir(loginUsuario);

                        if (usuarioExcluido == true) {
                            System.out.println("\nUsuário excluido com sucesso.\n");
                            System.in.read();
                        } else {
                            System.out.println("\nUsuário inexistente! Tente novamente.\n");
                            System.in.read();
                        }
                        break;
                    case 8:
                       
                      break;
                    case 9:
                        Listar.listarUser();
                        System.in.read();      
                            
                        break;
                    case 10:
                  
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
