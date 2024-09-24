package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import config.Conexao;
import entidades.ItemLocacao;
import entidades.Jogo;
import entidades.Locacao;

public class ItemLocaDAO {

    public static boolean criar(ItemLocacao itemlocacao){
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "INSERT INTO itemlocacao(id, valor, Jogo_id, Locacao_id)"
                +"VALUES (?,?,?,?)";
             PreparedStatement ps = conexao.prepareStatement(sql);
             Locacao locacao = new Locacao();
             itemlocacao.setLocacao(locacao);
             itemlocacao.getLocacao().setId(0);
            ps.setInt(1, itemlocacao.getId());
            ps.setDouble(2, itemlocacao.getValor());
            ps.setInt(3, itemlocacao.getJogo().getId());
            ps.setInt(4, itemlocacao.getLocacao().getId());
            int resultado = ps.executeUpdate();
            ps.close();

            return resultado > 0;

            
        } catch (Exception e) {
              System.out.println(e);
            return false;
        }
    }

    public static boolean atualizar(ItemLocacao itemlocacao) throws Exception{
        try {
        Connection conn = Conexao.getConexao();
         String sql = "UPDATE itemlocacao SET Valor= ?, Locacao_id = ? WHERE Id=?";
         PreparedStatement stmt = conn.prepareStatement(sql);
         
         stmt.setDouble(1,itemlocacao.getValor());
         stmt.setInt(2,itemlocacao.getLocacao().getId());
         stmt.setInt(3,itemlocacao.getId());
         int linhasAfetadas = stmt.executeUpdate();
         stmt.close();
         if (linhasAfetadas==0){
             throw new Exception("Não foi possível localizar o Item de Locação com os dados fornecidos!");
         }  
         return linhasAfetadas > 0;
        } catch (Exception e) {
           System.out.println(e.getMessage());
           return false;
        }  
     }


    public static void ListarDis(){
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT * FROM itemlocacao";
            Statement st = conexao.createStatement();
            ResultSet res = st.executeQuery(sql);
            
            while (res.next()) {
                ItemLocacao il = new ItemLocacao();
                il.setId(res.getInt("id"));
                il.setValor(res.getDouble("valor"));
                Jogo j = new Jogo();
                    il.setJogo(j);
                    il.getJogo().setId(res.getInt("jogo_id"));
                Locacao lo = new Locacao();
                    il.setLocacao(lo);
                    il.getLocacao().setId(res.getInt("locacao_id"));
                if (il.getLocacao().getId()== 0) {
                System.out.println(JogoDAO.buscarJogoPeloID(il.getJogo().getId()));
                }
            }
            res.close();
            st.close();
        } 
        catch (Exception e) {
            System.out.println("Não foi possível apresentar os jogos disponíveis");
            System.out.println(e);
        }
    }

    public static ItemLocacao buscloc(int jogo_id){
            ItemLocacao item = new ItemLocacao();
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT * FROM itemlocacao WHERE Jogo_id = ?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, jogo_id);
            ResultSet res = comando.executeQuery();

            while (res.next()) {
                ItemLocacao il = new ItemLocacao();
                il.setId(res.getInt("id"));
                il.setValor(res.getDouble("valor"));
                Jogo j = new Jogo();
                    il.setJogo(j);
                    il.getJogo().setId(res.getInt("jogo_id"));
                Locacao lo = new Locacao();
                    il.setLocacao(lo);
                    il.getLocacao().setId(res.getInt("locacao_id"));
                if (il.getLocacao().getId()== 0) {
                item = il;
                }
            }
            res.close();
            comando.close();
        } 
        catch (Exception e) {
            System.out.println("Jogo indisponível");
            System.out.println(e);
        }
        return item;
    }

    public static void meusJogos(int locaid){
    try {
        Connection conn = Conexao.getConexao();
        String sql = "SELECT * FROM itemlocacao";
        Statement st = conn.createStatement();
        ResultSet res = st.executeQuery(sql);
        
        while (res.next()) {
            ItemLocacao il = new ItemLocacao();
            il.setId(res.getInt("id"));
            il.setValor(res.getDouble("valor"));
            Jogo j = new Jogo();
                il.setJogo(j);
                il.getJogo().setId(res.getInt("jogo_id"));
            Locacao lo = new Locacao();
                il.setLocacao(lo);
                il.getLocacao().setId(res.getInt("locacao_id"));
            if (il.getLocacao().getId()== locaid) {
            System.out.println(JogoDAO.buscarJogoPeloID(il.getJogo().getId()));
            }
        }
        res.close();
        st.close();
    } 
    catch (Exception e) {
        System.out.println("Não foi possível apresentar os seus jogos");
        System.out.println(e);
    }
}
}
        
