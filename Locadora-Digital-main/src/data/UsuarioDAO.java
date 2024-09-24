package data;

import config.Conexao;
import entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO {
    
    public static boolean cadastrarUsuario(Usuario usuario) {
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "INSERT INTO usuario (nome, login, senha) VALUES (?, ?, ?)";
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getLogin());
            comando.setString(3, usuario.getSenha());

            int resultado = comando.executeUpdate();

            comando.close();
            conexao.close();

            System.out.println("Cadastro feito com sucesso!");
            return resultado > 0;

        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

    public static boolean verificarEmailExistente(String login) {
        boolean loginExistente = false;

        try{

            Connection conexao = Conexao.getConexao();
            String sql = "SELECT * FROM usuario WHERE login = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();
            loginExistente = rs.next();

        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
        return loginExistente;
    }

    public static boolean excluir(String login){
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "DELETE FROM usuario WHERE login=?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, login);
            int resultado = comando.executeUpdate();
            comando.close();

            return resultado > 0;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static ArrayList<Usuario> listar(){
        ArrayList<Usuario> listarUser = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT * FROM usuario";
            Statement st = conexao.createStatement();
            ResultSet res = st.executeQuery(sql);

            while (res.next()) {
                Usuario u = new Usuario();// Associa a categoria ao jogo
                u.setLogin(res.getString("login"));
                u.setNome(res.getString("nome"));
                listarUser.add(u);
            }

            res.close();
            st.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }

        return listarUser;
    }
}

