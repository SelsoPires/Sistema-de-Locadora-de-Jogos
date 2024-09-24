package data;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import config.Conexao;
import entidades.Locacao;

public class LocacaoDAO {

    public static boolean criar(Locacao locacao, int id){
        Calendar c = Calendar.getInstance();
        java.sql.Date dt = new java.sql.Date(c.getTime().getTime()); 
        
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Connection conexao = Conexao.getConexao();
            String sql = "INSERT INTO locacao(id, data, valor, usuario_id)"
                +"VALUES (?,?,?,?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, locacao.getId());
            ps.setDate(2, dt);
            ps.setDouble(3, locacao.getValor());
            ps.setInt(4, id);
            int resultado = ps.executeUpdate();
            ps.close();

            return resultado > 0;

            
        } catch (Exception e) {
              System.out.println(e);
            return false;
        }
    }


    public static int idcarrinho(int user_id) {
        int j = 0;
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT * FROM locacao WHERE usuario_id = ?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, user_id);
            ResultSet res = comando.executeQuery();
    
            if (res.next()) {
                j = res.getInt("id");
            } else {
                j = 0;
                System.out.println("deu erro menor");
            }
            res.close();
            comando.close();
           
        } catch (Exception e) {
            System.out.println(e);
        }
        return j;
    }
    
    public static void meusJogos(int id) {
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT * FROM locacao";
            Statement st = conexao.createStatement();
            ResultSet res = st.executeQuery(sql);
        
            while (res.next()) {
                if (id == res.getInt("usuario_id")) {
                    ItemLocaDAO.meusJogos(res.getInt("id"));
                }
            }
    
            res.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Não foi possível apresentar os jogos disponíveis");
            System.out.println(e);
        }
    }
    
}