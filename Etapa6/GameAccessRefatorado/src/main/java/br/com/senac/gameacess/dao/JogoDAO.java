package br.com.senac.gameacess.dao; 

import br.com.senac.gameacess.model.Jogo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.List; 

public class JogoDAO {

    
    public List<Jogo> listarTodos() {
        List<Jogo> jogos = new ArrayList<>();
        String sql = "SELECT * FROM jogo";

        
        try (Connection conn = Conexao.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Jogo jogo = new Jogo();
                jogo.setId(rs.getInt("id_jogo"));
                jogo.setTitulo(rs.getString("titulo"));
                jogo.setGenero(rs.getString("genero"));
                jogo.setDesenvolvedora(rs.getString("desenvolvedora"));
                jogo.setDistribuidora(rs.getString("distribuidora"));
                jogo.setDataLancamento(rs.getString("data_lancamento"));
                jogo.setClassificacao(rs.getString("classificacao"));
                jogo.setDescricao(rs.getString("descricao"));
                jogo.setImagem(rs.getString("imagem"));
                jogo.setDisponibilidade(rs.getInt("disponibilidade"));
                
                jogos.add(jogo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar jogos: " + e.getMessage());
        }
        return jogos;
    }

    
    public boolean atualizarDisponibilidade(int idJogo, int disponibilidade) {
        String sql = "UPDATE jogo SET disponibilidade = ? WHERE id_jogo = ?";

        try (Connection conn = Conexao.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, disponibilidade);
            stmt.setInt(2, idJogo);
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; 

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar jogo: " + e.getMessage());
            return false;
        }
    }
}