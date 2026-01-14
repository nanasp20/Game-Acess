package br.com.senac.gameacess.main;

import br.com.senac.gameacess.dao.JogoDAO;
import br.com.senac.gameacess.dao.UsuarioDAO;
import br.com.senac.gameacess.model.Jogo;
import br.com.senac.gameacess.model.Usuario;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA GAME ACCESS (BACKEND) ===");
        
       
        System.out.println("\n[1] Testando Cadastro e Login...");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario userTeste = new Usuario("teste2025@email.com", "123456");

        
        if(usuarioDAO.cadastrar(userTeste)) {
            System.out.println("-> Usuário cadastrado com sucesso.");
        } else {
            System.out.println("-> Erro: Talvez o usuário já exista.");
        }

        
        if(usuarioDAO.autenticar("teste2025@email.com", "123456")) {
            System.out.println("-> Login realizado com sucesso!");
        } else {
            System.out.println("-> Falha no login.");
        }

       
        System.out.println("\n[2] Testando Listagem de Jogos...");
        JogoDAO jogoDAO = new JogoDAO();
        List<Jogo> jogos = jogoDAO.listarTodos();

        if (jogos.isEmpty()) {
            System.out.println("-> Nenhum jogo encontrado no banco.");
        } else {
            for (Jogo j : jogos) {
                String status = (j.getDisponibilidade() == 1) ? "Disponível" : "Alugado";
                System.out.println("ID: " + j.getId() + " | " + j.getTitulo() + " (" + status + ")");
            }

            
            System.out.println("\n[3] Testando Aluguel do primeiro jogo da lista...");
            Jogo primeiroJogo = jogos.get(0);
            
            if (jogoDAO.atualizarDisponibilidade(primeiroJogo.getId(), 0)) {
                System.out.println("-> Sucesso! O jogo '" + primeiroJogo.getTitulo() + "' agora consta como ALUGADO.");
            } else {
                System.out.println("-> Erro ao atualizar disponibilidade.");
            }
        }
    }
}
