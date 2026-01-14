package br.com.senac.gameacess.model;

public class ServicoAluguel { 
    
    public double calcularPrecoLocacao(int dias, boolean isLancamento) {
        if (dias <= 0) {
            throw new IllegalArgumentException("Dias deve ser maior que zero");
        }

        double valorDiaria = isLancamento ? 15.00 : 10.00;
        double total = valorDiaria * dias;

        // Desconto de 10% se for mais de 3 dias
        if (dias > 3) {
            total = total * 0.90; 
        }

        return total;
    }
}
