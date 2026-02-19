package br.com.senac.game_access_web.repository;

import br.com.senac.game_access_web.model.ListaDesejos;
import br.com.senac.game_access_web.model.Usuario;
import br.com.senac.game_access_web.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ListaDesejosRepository extends JpaRepository<ListaDesejos, Integer> {
    
    List<ListaDesejos> findByUsuario(Usuario usuario);
    
    
    boolean existsByUsuarioAndJogo(Usuario usuario, Jogo jogo);
    
   
    void deleteByUsuarioAndJogo(Usuario usuario, Jogo jogo);
}