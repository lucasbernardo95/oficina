package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lber
 */
@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "idusuario")
    private Integer id;

    @Column(name = "nome", length = 30, nullable = true)
    private String nome;

    @Column(name = "login", length = 20, nullable = false, unique = true)
    private String login;
    
    @Column(name = "senha", length = 15, nullable = false)
    private String senha;

    @Column(name = "tipo", length = 13, nullable = false)
    private TipoUsuario tipo;

    
    //um usuário compra diversos produtos
    @OneToMany( fetch = FetchType.LAZY)
    private List<Produto> produtos;
    
    //@OneToMany( fetch = FetchType.LAZY)
    //private List<Software> listaSoftware;
    
    //@OneToMany( fetch = FetchType.LAZY)
    //private List<Comentario> listaComentario;

    public Usuario() {
    }

    public Usuario(String nome, String login, String senha, TipoUsuario tipo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
