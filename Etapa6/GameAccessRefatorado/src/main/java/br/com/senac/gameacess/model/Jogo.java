package br.com.senac.gameacess.model;

public class Jogo {
    private int id;
    private String titulo;
    private String genero;
    private String desenvolvedora;
    private String distribuidora;
    private String dataLancamento;
    private String classificacao;
    private String descricao;
    private String imagem;
    private int disponibilidade; 

    public Jogo() {
    }

    public Jogo(int id, String titulo, String genero, String desenvolvedora, 
                String distribuidora, String dataLancamento, String classificacao, 
                String descricao, String imagem, int disponibilidade) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.desenvolvedora = desenvolvedora;
        this.distribuidora = distribuidora;
        this.dataLancamento = dataLancamento;
        this.classificacao = classificacao;
        this.descricao = descricao;
        this.imagem = imagem;
        this.disponibilidade = disponibilidade;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public String getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(String distribuidora) {
        this.distribuidora = distribuidora;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(int disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}