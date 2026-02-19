package br.com.senac.game_access_web.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "aluguel")
public class Aluguel {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluguel") // <--- CORREÇÃO 1: Nome exato da PK no banco
    private Integer id;

    @Column(name = "data_aluguel")
    private LocalDate dataAluguel;

    @Column(name = "data_devolucao_prevista")
    private LocalDate dataDevolucao;

    @Column(name = "status_aluguel")
    private String statusAluguel;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "horas_jogadas")
    private Integer horasJogadas;

    // --- CORREÇÃO 2: Foreign Keys com os nomes do seu banco (id_usuario/id_jogo) ---
    @ManyToOne 
    @JoinColumn(name = "id_usuario") 
    private Usuario usuario;

    @ManyToOne 
    @JoinColumn(name = "id_jogo") 
    private Jogo jogo;

    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getDataAluguel() { return dataAluguel; }
    public void setDataAluguel(LocalDate dataAluguel) { this.dataAluguel = dataAluguel; }

    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }

    public String getStatusAluguel() { return statusAluguel; }
    public void setStatusAluguel(String statusAluguel) { this.statusAluguel = statusAluguel; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public Integer getHorasJogadas() { return horasJogadas; }
    public void setHorasJogadas(Integer horasJogadas) { this.horasJogadas = horasJogadas; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Jogo getJogo() { return jogo; }
    public void setJogo(Jogo jogo) { this.jogo = jogo; }
}
