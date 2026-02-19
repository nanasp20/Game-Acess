package br.com.senac.game_access_web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    @Column(name = "nome_usuario")
    private String nomeUsuario; 

    private String email;
    private String senha;
    private BigDecimal saldo;
    
    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "controle_parental")
    private Boolean controleParental;

    @Column(name = "pedir_senha_compra")
    private Boolean pedirSenhaCompra;

   
    @Column(name = "pin_parental")
    private String pinParental;

    @Column(name = "senha_compra")
    private String senhaCompra;

    

    public Integer getId_usuario() { return id_usuario; }
    public void setId_usuario(Integer id_usuario) { this.id_usuario = id_usuario; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
    
    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }

    public Boolean getControleParental() { return controleParental; }
    public void setControleParental(Boolean controleParental) { this.controleParental = controleParental; }

    public Boolean getPedirSenhaCompra() { return pedirSenhaCompra; }
    public void setPedirSenhaCompra(Boolean pedirSenhaCompra) { this.pedirSenhaCompra = pedirSenhaCompra; }

    
    public String getPinParental() { return pinParental; }
    public void setPinParental(String pinParental) { this.pinParental = pinParental; }

    public String getSenhaCompra() { return senhaCompra; }
    public void setSenhaCompra(String senhaCompra) { this.senhaCompra = senhaCompra; }
}