package br.com.senac.game_access_web.controller;

import br.com.senac.game_access_web.model.*;
import br.com.senac.game_access_web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Base64;

@Controller
public class WebController {

    @Autowired private JogoRepository jogoRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ListaDesejosRepository listaRepository;
    @Autowired private CartaoCreditoRepository cartaoRepository;
    @Autowired private AluguelRepository aluguelRepository;

    @GetMapping("/") public String index() { return "index"; }
    @GetMapping("/login") public String login() { return "login"; }
    @GetMapping("/cadastro") public String cadastro() { return "cadastro"; }

    @GetMapping("/catalogo")
    public String catalogo(Model model) { 
        Usuario usuario = usuarioRepository.findById(1).orElse(null);
        if(usuario != null) {
            model.addAttribute("usuario", usuario);
            List<ListaDesejos> minhaLista = listaRepository.findByUsuario(usuario);
            model.addAttribute("minhaListaItens", minhaLista);

            List<Integer> idsSalvos = new ArrayList<>();
            for (ListaDesejos item : minhaLista) {
                idsSalvos.add(item.getJogo().getId_jogo());
            }
            model.addAttribute("idsSalvos", idsSalvos);
        }
        model.addAttribute("jogosAcao", jogoRepository.findByGeneroContaining("Ação"));
        model.addAttribute("jogosRPG", jogoRepository.findByGeneroContaining("RPG"));
        model.addAttribute("jogosEstrategia", jogoRepository.findByGeneroContaining("Estratégia"));
        model.addAttribute("jogosSimulacao", jogoRepository.findByGeneroContaining("Simulação"));
        model.addAttribute("jogosAventura", jogoRepository.findByGeneroContaining("Aventura"));
        try {
            model.addAttribute("listaGeneros", jogoRepository.findGenerosUnicos());
            model.addAttribute("notificacoes", jogoRepository.findTop5ByOrderByDataLancamentoDesc());
        } catch (Exception e) {}
        return "home"; 
    }

    
    @GetMapping("/marcar/{id}")
    @Transactional
    public String marcarJogo(@PathVariable Integer id) {
        Usuario usuario = usuarioRepository.findById(1).orElse(null);
        Optional<Jogo> jogoOpt = jogoRepository.findById(id);

        if (usuario != null && jogoOpt.isPresent()) {
            Jogo jogo = jogoOpt.get();
            if (listaRepository.existsByUsuarioAndJogo(usuario, jogo)) {
                listaRepository.deleteByUsuarioAndJogo(usuario, jogo);
            } else {
                ListaDesejos novoItem = new ListaDesejos();
                novoItem.setUsuario(usuario);
                novoItem.setJogo(jogo);
                listaRepository.save(novoItem);
            }
        }
        return "redirect:/catalogo"; 
    }
   

    @GetMapping("/detalhes/{id}")
    public String detalhes(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioRepository.findById(1).orElse(null);
        Optional<Jogo> jogoOpt = jogoRepository.findById(id);

        if (usuario != null && jogoOpt.isPresent()) {
            Jogo jogo = jogoOpt.get();
            model.addAttribute("usuario", usuario);
            model.addAttribute("jogo", jogo);
            boolean jaAlugado = aluguelRepository.existsByUsuarioAndJogoAndStatusAluguel(usuario, jogo, "ATIVO");
            model.addAttribute("jaAlugado", jaAlugado);
            model.addAttribute("meusCartoes", cartaoRepository.findByUsuario(usuario));
            return "detalhes";
        }
        return "redirect:/catalogo";
    }

    @PostMapping("/confirmarAluguel")
    public String confirmarAluguel(@RequestParam Integer idJogo, 
                                   @RequestParam Integer dias, 
                                   @RequestParam Double valorTotal, 
                                   @RequestParam(required = false) String senhaDigitada) {
        
        Usuario usuario = usuarioRepository.findById(1).orElse(null);
        Optional<Jogo> jogoOpt = jogoRepository.findById(idJogo);

        if (usuario != null && jogoOpt.isPresent()) {
            if (Boolean.TRUE.equals(usuario.getPedirSenhaCompra())) {
                if (senhaDigitada == null || !senhaDigitada.equals(usuario.getSenhaCompra())) {
                    return "redirect:/detalhes/" + idJogo + "?erroSenha=true";
                }
            }
            Aluguel novoAluguel = new Aluguel();
            novoAluguel.setUsuario(usuario);
            novoAluguel.setJogo(jogoOpt.get());
            novoAluguel.setDataAluguel(java.time.LocalDate.now());
            novoAluguel.setDataDevolucao(java.time.LocalDate.now().plusDays(dias));
            novoAluguel.setValorTotal(java.math.BigDecimal.valueOf(valorTotal));
            novoAluguel.setStatusAluguel("ATIVO");
            novoAluguel.setHorasJogadas(0);
            aluguelRepository.save(novoAluguel);
        }
        return "redirect:/detalhes/" + idJogo;
    }
    
    @GetMapping("/alugueis") 
    public String alugueis(Model model) { 
        Usuario usuario = usuarioRepository.findById(1).orElse(null);
        if(usuario != null) {
            List<Aluguel> todosAlugueis = aluguelRepository.findByUsuarioOrderByDataAluguelDesc(usuario);
            java.time.LocalDate hoje = java.time.LocalDate.now();
            
            for(Aluguel a : todosAlugueis) {
                if("ATIVO".equals(a.getStatusAluguel()) && a.getDataDevolucao().isBefore(hoje)) {
                    a.setStatusAluguel("FINALIZADO");
                    aluguelRepository.save(a);
                }
            }
            model.addAttribute("usuario", usuario);
            model.addAttribute("meusCartoes", cartaoRepository.findByUsuario(usuario));
            model.addAttribute("historico", todosAlugueis);
        }
        return "alugueis"; 
    }

    @PostMapping("/estenderAluguel")
    public String estenderAluguel(@RequestParam Integer idAluguel, 
                                  @RequestParam Integer dias, 
                                  @RequestParam Double valorAdicional) {
        Optional<Aluguel> aluguelOpt = aluguelRepository.findById(idAluguel);
        if(aluguelOpt.isPresent()) {
            Aluguel aluguel = aluguelOpt.get();
            aluguel.setDataDevolucao(aluguel.getDataDevolucao().plusDays(dias));
            aluguel.setValorTotal(aluguel.getValorTotal().add(java.math.BigDecimal.valueOf(valorAdicional)));
            aluguelRepository.save(aluguel);
        }
        return "redirect:/alugueis";
    }

    @PostMapping("/salvarConfig")
    public String salvarConfig(@RequestParam("nomeUsuario") String nomeUsuario, 
                               @RequestParam(value="imagemArquivo", required=false) MultipartFile imagemArquivo, 
                               @RequestParam(value="controleParental", required=false) String controleParental, 
                               @RequestParam(value="pedirSenhaCompra", required=false) String pedirSenhaCompra) {
        
        Usuario usuario = usuarioRepository.findById(1).orElse(null);
        if(usuario != null) {
            usuario.setNomeUsuario(nomeUsuario);
            
            if (imagemArquivo != null && !imagemArquivo.isEmpty()) {
                try {
                    byte[] bytes = imagemArquivo.getBytes();
                    String base64Img = Base64.getEncoder().encodeToString(bytes);
                    usuario.setFotoPerfil("data:image/jpeg;base64," + base64Img);
                } catch (Exception e) { e.printStackTrace(); }
            }

            usuario.setControleParental(controleParental != null);
            usuario.setPedirSenhaCompra(pedirSenhaCompra != null);
            usuarioRepository.save(usuario);
        }
        return "redirect:/configuracao";
    }

    @PostMapping("/salvarSeguranca")
    public String salvarSeguranca(@RequestParam("pinParental") String pinParental, 
                                   @RequestParam("senhaCompra") String senhaCompra) {
        Usuario usuario = usuarioRepository.findById(1).orElse(null);
        if(usuario != null) {
            usuario.setPinParental(pinParental);
            usuario.setSenhaCompra(senhaCompra);
            usuarioRepository.save(usuario);
        }
        return "redirect:/seguranca?sucesso=true"; 
    }

    @GetMapping("/configuracao") 
    public String configuracao(Model model) {
        usuarioRepository.findById(1).ifPresent(u -> model.addAttribute("usuario", u));
        return "configuracao"; 
    }

    @GetMapping("/seguranca")
    public String seguranca(Model model) {
        usuarioRepository.findById(1).ifPresent(u -> model.addAttribute("usuario", u));
        return "seguranca";
    }
}
