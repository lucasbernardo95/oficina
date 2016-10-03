package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lber
 */
@Entity
@Table(name = "Venda")
public class Venda implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private Long idvenda;

    @JoinColumn(name = "idusuario")
    @OneToOne(optional = true)
    private Usuario idusuario;

    //um cliente solicita vários serviços, e esse pode ser solicitado por vários clientes
    @JoinColumn(name = "idcliente")
    @ManyToOne(optional = true)
    private Cliente cliente;

    @JoinColumn(name = "idproduto")
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Produto> produtos;

    @Column
    @Temporal(TemporalType.DATE)
    private Date datavenda;

    @Column
    private double valorcompra;

    @Column
    private boolean quitado;

    public Venda() {
    }

    public Venda(Long idvenda, Usuario idusuario, Cliente cliente, List<Produto> produtos, double valorcompra, boolean quitado) {
        this.idvenda = idvenda;
        this.idusuario = idusuario;
        this.cliente = cliente;
        this.produtos = produtos;
        this.datavenda = new java.sql.Date(System.currentTimeMillis());
        this.valorcompra = valorcompra;
        this.quitado = quitado;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Long getIdvenda() {
        return idvenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public void setIdvenda(Long idvenda) {
        this.idvenda = idvenda;
    }

    public Cliente getClientes() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDatavenda() {
        return datavenda;
    }

    public void setDatavenda(Date datavenda) {
        this.datavenda = datavenda;
    }

    public double getValorcompra() {
        return valorcompra;
    }

    public void setValorcompra(double valorcompra) {
        this.valorcompra = valorcompra;
    }

    public boolean isQuitado() {
        return quitado;
    }

    public void setQuitado(boolean quitado) {
        this.quitado = quitado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.idvenda);
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
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.idvenda, other.idvenda)) {
            return false;
        }
        return true;
    }

}
