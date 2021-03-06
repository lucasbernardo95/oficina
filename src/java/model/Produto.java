
package model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lber
 */

@Entity
@Table(name = "Produto")
public class Produto implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name = "idproduto")
    private Integer id;
    
    @Column(name = "código")
    private String codigo;
    
    @Column(name = "nome", length = 30)
    @Size(min = 5, max = 30, message = "O nome deve conter entre 3 e 30 dígitos")
    private String nome;
    
    @Column(name = "valorcompra")
    private double valorcompra;
    
    @Column(name = "valorvenda")
    private double valorvenda;
    
    @Column(name = "estoque")
    private int estoque;
    
    //Representa a quantidade que será vendida à um usuário
    @Column(name = "quantidadeVenda")
    private int quantidadeVenda;
    
    @JoinColumn(name = "idusuario")
    @ManyToOne( optional = true)
    private Usuario idusuario;
    

    public Produto() {
    }

    public Produto(String codigo, String nome, double valorcompra, double valorvenda, int estoque, Usuario idusuario) {
        this.codigo = codigo;
        this.nome = nome;
        this.valorcompra = valorcompra;
        this.valorvenda = valorvenda;
        this.estoque = estoque;
        this.idusuario = idusuario;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
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

    public double getValorcompra() {
        return valorcompra;
    }

    public void setValorcompra(double valorcompra) {
        this.valorcompra = valorcompra;
    }

    public double getValorvenda() {
        return valorvenda;
    }

    public void setValorvenda(double valorvenda) {
        this.valorvenda = valorvenda;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void decrementaEsoque(){
        this.estoque --;
    }
    
    public void incrementaEstoque(){
        this.estoque ++;
    }

    public void incrementaQtd(){
        this.quantidadeVenda ++;
    }
    
    public void decrementaQtd(){
        this.quantidadeVenda --;
    }

    public int getQuantidadeVenda() {
        return quantidadeVenda;
    }

    public void setQuantidadeVenda(int quantidadeVenda) {
        this.quantidadeVenda = quantidadeVenda;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
