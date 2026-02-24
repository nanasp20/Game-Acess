package br.com.senac.game_access_web.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cartao_credito")
public class CartaoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_titular")
    private String nomeTitular;

    @Column(name = "numero_cartao")
    private String numeroCartao;

    @Column(name = "validade")
    private String validade;
    
    
    @Column(name = "cvv")
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }

    public String getNumeroCartao() { return numeroCartao; }
    public void setNumeroCartao(String numeroCartao) { this.numeroCartao = numeroCartao; }

    public String getValidade() { return validade; }
    public void setValidade(String validade) { this.validade = validade; }

    
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
