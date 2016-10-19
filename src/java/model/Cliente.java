/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author lber
 */
@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "idcliente")
    private Integer id;

    @Column(name = "nome", length = 30)
    private String nome;
    
    @Column(name = "endereco", length = 30)
    private String endereco;
    
    @Column(name = "placa", length = 8)
    private String placa;
    
    @Column(name = "cidade", length = 15)
    private String cidade;
    
    @Column(name = "bairro", length = 25)
    private String bairro;

    @Column(name = "email", length = 30)
    @Pattern(regexp = "[a-zA-Z0-9]+@+[a-zA-Z0-9]+[.]+com", message = "Informe um e-mail no formato xxx@xxx.com, ou xxx@xxx.com")
    private String email;

    @Column(name = "telefone", length = 15)
    private String telefone;

    @Column(name = "debito")
    private double debito;

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String endereco, String placa, String cidade, String bairro, String email, String telefone, double debito) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.placa = placa;
        this.cidade = cidade;
        this.bairro = bairro;
        this.email = email;
        this.telefone = telefone;
        this.debito = debito;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
