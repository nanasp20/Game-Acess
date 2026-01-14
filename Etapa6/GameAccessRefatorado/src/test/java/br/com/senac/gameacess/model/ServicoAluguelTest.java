package br.com.senac.gameacess.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServicoAluguelTest { 

    public ServicoAluguelTest() {
    }

    @Test
    public void testeCalculoJogoComum() {
        System.out.println("Teste: Jogo Comum por 2 dias");
        ServicoAluguel servico = new ServicoAluguel();
        
        double resultado = servico.calcularPrecoLocacao(2, false);
        
        assertEquals(20.0, resultado); 
    }

    @Test
    public void testeCalculoLancamentoComDesconto() {
        System.out.println("Teste: Lançamento por 5 dias (com desconto)");
        ServicoAluguel servico = new ServicoAluguel();
        
        double resultado = servico.calcularPrecoLocacao(5, true);
        
        assertEquals(67.5, resultado);
    }
}