package br.com.senac.game_access_web.controller;

import br.com.senac.game_access_web.model.*;
import br.com.senac.game_access_web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.transaction.Transactional;
import jakarta.servlet.http.HttpSession;

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

    
    @PostMapping("/cadastrarUsuario")
    public String cadastrarUsuario(@RequestParam String nome, 
                                   @RequestParam String senha, 
                                   @RequestParam String confirmarSenha,
                                   @RequestParam(required = false) String email, 
                                   Model model) {
        
        if (!senha.equals(confirmarSenha)) {
            model.addAttribute("erroSenha", "As senhas não coincidem!");
            return "cadastro";
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNomeUsuario(nome);
        novoUsuario.setSenhaCompra(senha);
       
        novoUsuario.setControleParental(false);
        novoUsuario.setPedirSenhaCompra(false);
        usuarioRepository.save(novoUsuario);

        return "redirect:/login"; 
    }

    
    @PostMapping("/logar")
    public String logar(@RequestParam String nome, @RequestParam String senha, HttpSession session, Model model) {
        Usuario usuario = usuarioRepository.findAll().stream()
                .filter(u -> u.getNomeUsuario().equals(nome) && u.getSenhaCompra().equals(senha))
                .findFirst().orElse(null);

        if (usuario != null) {
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/catalogo";
        }

        model.addAttribute("erroLogin", "Usuário ou senha inválidos!");
        return "login";
    }

   
    @GetMapping("/catalogo")
    public String catalogo(HttpSession session, Model model) { 
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        List<ListaDesejos> minhaLista = listaRepository.findByUsuario(usuario);
        model.addAttribute("minhaListaItens", minhaLista);

        List<Integer> idsSalvos = new ArrayList<>();
        for (ListaDesejos item : minhaLista) {
            idsSalvos.add(item.getJogo().getId_jogo());
        }
        model.addAttribute("idsSalvos", idsSalvos);

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
    public String marcarJogo(@PathVariable Integer id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
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
    public String detalhes(@PathVariable Integer id, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario == null) return "redirect:/login";

        Optional<Jogo> jogoOpt = jogoRepository.findById(id);
        if (jogoOpt.isPresent()) {
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
                                   @RequestParam(required = false) String senhaDigitada,
                                   HttpSession session) {
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
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
    public String alugueis(HttpSession session, Model model) { 
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
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
            return "alugueis"; 
        }
        return "redirect:/login";
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
                               @RequestParam(value="pedirSenhaCompra", required=false) String pedirSenhaCompra,
                               HttpSession session) {
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
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
            session.setAttribute("usuarioLogado", usuario);
        }
        return "redirect:/configuracao";
    }

    @PostMapping("/salvarSeguranca")
    public String salvarSeguranca(@RequestParam("pinParental") String pinParental, 
                                   @RequestParam("senhaCompra") String senhaCompra,
                                   HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario != null) {
            usuario.setPinParental(pinParental);
            usuario.setSenhaCompra(senhaCompra);
            usuarioRepository.save(usuario);
            session.setAttribute("usuarioLogado", usuario);
        }
        return "redirect:/seguranca?sucesso=true"; 
    }

    @PostMapping("/salvarCartao")
    public String salvarCartao(@RequestParam String nomeTitular,
                               @RequestParam String numeroCartao,
                               @RequestParam String validade,
                               @RequestParam String cvv,
                               HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario != null) {
            CartaoCredito novoCartao = new CartaoCredito();
            novoCartao.setNomeTitular(nomeTitular);
            novoCartao.setNumeroCartao(numeroCartao);
            novoCartao.setValidade(validade);
            novoCartao.setCvv(cvv);
            novoCartao.setUsuario(usuario);

            cartaoRepository.save(novoCartao);
        }
        return "redirect:/configuracao"; 
    }

    @GetMapping("/configuracao") 
    public String configuracao(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario != null) {
            model.addAttribute("usuario", usuario);
            return "configuracao"; 
        }
        return "redirect:/login";
    }

    @GetMapping("/seguranca")
    public String seguranca(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario != null) {
            model.addAttribute("usuario", usuario);
            return "seguranca";
        }
        return "redirect:/login";
    }
}