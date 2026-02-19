package br.com.senac.game_access_web.model;
import jakarta.persistence.*;

@Entity
public class CartaoCredito {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numeroCartao;
    private String nomeTitular;
    private String validade;
    
    @ManyToOne @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNumeroCartao() { return numeroCartao; }
    public void setNumeroCartao(String numeroCartao) { this.numeroCartao = numeroCartao; }
    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }
    public String getValidade() { return validade; }
    public void setValidade(String validade) { this.validade = validade; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
