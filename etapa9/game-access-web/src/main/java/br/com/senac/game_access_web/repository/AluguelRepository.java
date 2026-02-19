package br.com.senac.game_access_web.repository;

import br.com.senac.game_access_web.model.Aluguel;
import br.com.senac.game_access_web.model.Usuario;
import br.com.senac.game_access_web.model.Jogo; // <--- FALTAVA ESSE AQUI
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {
    
    List<Aluguel> findByUsuarioOrderByDataAluguelDesc(Usuario usuario);
    
    
    boolean existsByUsuarioAndJogoAndStatusAluguel(Usuario usuario, Jogo jogo, String status);
}