package br.com.senac.game_access_web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column; // Importante para o TEXT
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_jogo;

    private String titulo;
    private String genero;
    private String desenvolvedora;
    private String distribuidora;

    // --- CORREÇÃO 1: Mapeamento correto da data ---
    // No banco é "data_lancamento", no Java é "dataLancamento"
    @Column(name = "data_lancamento") 
    private LocalDate dataLancamento; 

    private String classificacao;

    // --- CORREÇÃO 2: Definir como TEXT para não dar erro de tamanho ---
    @Column(columnDefinition = "TEXT")
    private String descricao; 

    private String imagem;
    private BigDecimal preco_aluguel;
    private Boolean disponibilidade;
    
    private String plataforma;
    private String tamanho;
    private String jogadores;
    
    @Column(name = "url_banner")
    private String urlBanner; // Banner grande
    
    @Column(name = "url_trailer")
    private String urlTrailer; // Link do video

    // --- GETTERS E SETTERS ATUALIZADOS ---
    
    public Integer getId_jogo() { return id_jogo; }
    public void setId_jogo(Integer id_jogo) { this.id_jogo = id_jogo; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getDesenvolvedora() { return desenvolvedora; }
    public void setDesenvolvedora(String desenvolvedora) { this.desenvolvedora = desenvolvedora; }

    public String getDistribuidora() { return distribuidora; }
    public void setDistribuidora(String distribuidora) { this.distribuidora = distribuidora; }

    // Getter e Setter da Data corrigidos
    public LocalDate getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(LocalDate dataLancamento) { this.dataLancamento = dataLancamento; }

    public String getClassificacao() { return classificacao; }
    public void setClassificacao(String classificacao) { this.classificacao = classificacao; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
    
    public BigDecimal getPreco_aluguel() { return preco_aluguel; }
    public void setPreco_aluguel(BigDecimal preco_aluguel) { this.preco_aluguel = preco_aluguel; }

    public Boolean getDisponibilidade() { return disponibilidade; }
    public void setDisponibilidade(Boolean disponibilidade) { this.disponibilidade = disponibilidade; }

    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }

    public String getJogadores() { return jogadores; }
    public void setJogadores(String jogadores) { this.jogadores = jogadores; }
    
    public String getUrlBanner() { return urlBanner; }
    public void setUrlBanner(String urlBanner) { this.urlBanner = urlBanner; }

    public String getUrlTrailer() { return urlTrailer; }
    public void setUrlTrailer(String urlTrailer) { this.urlTrailer = urlTrailer; }
}