package br.com.senac.game_access_web.repository;

import br.com.senac.game_access_web.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {
    
    
    List<Jogo> findByGeneroContaining(String genero);
    
    
    List<Jogo> findTop5ByOrderByDataLancamentoDesc();
    
    
    @Query("SELECT DISTINCT j.genero FROM Jogo j")
    List<String> findGenerosUnicos();
}
