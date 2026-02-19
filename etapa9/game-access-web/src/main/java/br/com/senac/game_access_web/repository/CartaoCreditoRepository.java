package br.com.senac.game_access_web.repository;
import br.com.senac.game_access_web.model.CartaoCredito;
import br.com.senac.game_access_web.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Integer> {
    List<CartaoCredito> findByUsuario(Usuario usuario);
}