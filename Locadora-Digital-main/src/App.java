import config.Conexao;
import data.UsuarioDAO;
import entidades.Locacao;
import entidades.Usuario;
import tela.MenuAdmin;
import tela.MenuCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n".repeat(50));
                System.out.println("Bem-vindo ao sistema de locadora digital!");
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Login");
                System.out.println("2 - Cadastrar");
                System.out.println("0 - Sair");

                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        login();
                        break;
                    case 2:
                        cadastrar();
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

    public static void login() throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Login ===");

            System.out.println("Digite seu login: ");
            String login = scanner.nextLine();

            System.out.println("Digite sua senha: ");
            String senha = scanner.nextLine();

            try (Connection conexao = Conexao.getConexao()) {
                String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setString(1, login);
                ps.setString(2, senha);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int tipoUsuario = rs.getInt("id");
                    if (tipoUsuario == 1) {
                        MenuAdmin.menuAdmin();
                    } else {
                        Locacao carrinho = new Locacao();
                        
                        MenuCliente.menuCliente(rs.getInt("id"), carrinho);
                    }

                } else {
                    System.out.println("Login ou senha incorretos. Tente novamente.");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fazer login: " + e.getMessage());
            }
        }
    }

    public static void cadastrar() throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            Usuario usuario = new Usuario();

            System.out.print("\nNome: ");
            usuario.setNome(scanner.nextLine());

            System.out.print("Username/email: ");
            String login = scanner.nextLine();
            boolean existeEmail = UsuarioDAO.verificarEmailExistente(login);

            if (existeEmail == true) {
                System.out.println("Esse username/email ja está cadastrado! Tente novamente.\n");
                return;
            } else {
                usuario.setLogin(login);
   
                System.out.print("Senha: ");
                usuario.setSenha(scanner.nextLine());
   
                UsuarioDAO.cadastrarUsuario(usuario);
                login();
            }
        }
    }

    
}


    
