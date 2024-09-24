package data;

import config.Conexao;
import entidades.ItemLocacao;
import entidades.Jogo;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class JogoDAO {
    public static boolean criar(Jogo jogo){
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "INSERT INTO jogo(id, titulo, preco, descricao, numeroDias, plataforma, memoria)"
                +"VALUES (?,?,?,?,?,?,?)";
                
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, jogo.getId());
            ps.setString(2, jogo.getTitulo());
            ps.setDouble(3, jogo.getPreco());
            ps.setString(4, jogo.getDescricao());
            ps.setInt(5, jogo.getNumdias());
            ps.setString(6, jogo.getPlataforma());
            ps.setInt(7, jogo.getMemoria());  
            int resultado = ps.executeUpdate();
            ps.close();
            criarItem(jogo.getTitulo());

            return resultado > 0;
        }
        
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean atualizar(Jogo jogo){

        try {
            Connection conexao = Conexao.getConexao();
            String sql = "UPDATE jogo SET titulo = ?, preco = ?, descricao = ?, numeroDias = ?, plataforma = ?, memoria = ? WHERE id = ?";  
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, jogo.getTitulo());
            ps.setDouble(2, jogo.getPreco());
            ps.setString(3, jogo.getDescricao());
            ps.setInt(4, jogo.getNumdias());
            ps.setString(5, jogo.getPlataforma());
            ps.setInt(6, jogo.getMemoria());
            ps.setInt(7, jogo.getId());
            int resultado = ps.executeUpdate();
    
            ps.close();

            return resultado > 0;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean excluir(String titulo){
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "DELETE FROM jogo WHERE titulo=?";

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, titulo);
            int resultado = ps.executeUpdate();
            ps.close();

            return resultado > 0;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static Jogo buscarJogoPeloTitulo(String titulo) {
        Jogo j = new Jogo();
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT * FROM jogo WHERE titulo = ?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, titulo);
            ResultSet res = comando.executeQuery();

            if (res.next()) {
               
                j.setId(res.getInt("id"));
                j.setTitulo(res.getString("titulo"));
                j.setPreco(res.getDouble("preco"));
                j.setDescricao(res.getString("descricao"));
                j.setNumdias(res.getInt("numeroDias"));
                j.setPlataforma(res.getString("plataforma"));
                j.setMemoria(res.getInt("memoria"));
                res.close();
                comando.close();
                
            } else {
                System.out.println("\nJogo não encontrado\n");
            }
            

        } 
        catch (SQLException erro) {
            System.out.println(erro.getMessage());
        
        }
        return j;
    }

    public static ArrayList<Jogo> listarGeral(){
        ArrayList<Jogo> listaJogos = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT * FROM jogo";
            Statement st = conexao.createStatement();
            ResultSet res = st.executeQuery(sql);

            while (res.next()) {

                Jogo j = new Jogo();
                j.setId(res.getInt("id"));
                j.setTitulo(res.getString("titulo"));
                j.setPreco(res.getDouble("preco"));
                j.setDescricao(res.getString("descricao"));
                j.setNumdias(res.getInt("numeroDias"));
                j.setPlataforma(res.getString("plataforma"));
                // j.setMemoria(res.getInt("memoria"));
                listaJogos.add(j);
            }

            res.close();
            st.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }

        return listaJogos;
    }

    public static Jogo buscarJogoPeloID(int id) {
        Jogo j = new Jogo();
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT * FROM jogo WHERE id = ?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, id);
            ResultSet res = comando.executeQuery();
            if (res.next()) {
               
                j.setId(res.getInt("id"));
                j.setTitulo(res.getString("titulo"));
                j.setPreco(res.getDouble("preco"));
                j.setDescricao(res.getString("descricao"));
                j.setNumdias(res.getInt("numeroDias"));
                j.setPlataforma(res.getString("plataforma"));
                j.setMemoria(res.getInt("memoria"));
                res.close();
                comando.close();
                                
            } else {
                System.out.println("\nJogo não encontrado\n");
            }
            
            return j;

        } 
        catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return j;
        }
    }

    public static boolean criarItem(String titulo) {
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT * FROM jogo WHERE titulo = ?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, titulo);
            ResultSet res = comando.executeQuery();

            if (res.next()) {
                Jogo j = new Jogo();
                j.setId(res.getInt("id"));
                j.setTitulo(res.getString("titulo"));
                j.setPreco(res.getDouble("preco"));
                j.setDescricao(res.getString("descricao"));
                j.setNumdias(res.getInt("numeroDias"));
                j.setPlataforma(res.getString("plataforma"));
                j.setMemoria(res.getInt("memoria"));
                res.close();
                comando.close();

                ItemLocacao itemlocacao = new ItemLocacao();
                itemlocacao.setJogo(j);
                itemlocacao.setValor(j.getPreco());
                ItemLocaDAO.criar(itemlocacao);

                System.out.println(j);
            } else {
                System.out.println("\nJogo não encontrado\n");
            }
            
            return true;

        } 
        catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

}
