package br.com.senac.game_access_web.repository;

import br.com.senac.game_access_web.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
   
    Usuario findByEmail(String email);
}
