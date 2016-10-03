/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import model.Cliente;
import model.Produto;
import model.Usuario;
import model.Venda;
import util.ErroSistema;
import util.MessageUtil;

/**
 *
 * @author lber
 */
@ManagedBean
@SessionScoped
public class VendaBean {

    private List<Produto> produtos;
    private List<Produto> produtosVenda;
    private List<Cliente> clientes;
    private Usuario usuario;
    private Cliente cliente;
    private String placa;
    private Integer idcliente;
    private boolean avista = false;
    private VendaDAO vdao;
    private double total = 0;

    @PostConstruct
    public void init() {
        try {
            ProdutoDAO pdao = new ProdutoDAO();
            ClienteDAO cdao = new ClienteDAO();
            clientes = cdao.listar();
            produtos = pdao.listar();
            produtosVenda = new ArrayList<>();
        } catch (ErroSistema ex) {
            MessageUtil.MensagemPerigo("Erro cao carregar lista de produtos!");
        }
    }

    private void calculaTotal(){   
        total = 0;
        for (Produto p : produtosVenda) {
            total += p.getValorvenda() * p.getQuantidadeVenda();
        }
    }
    
    public void venda() throws ErroSistema{
        calculaTotal();
        if(vdao == null)
            vdao = new VendaDAO();
        
        Venda venda = new Venda(null, usuario, cliente, produtosVenda, total, avista);
        vdao.salvar(venda);
        
        MessageUtil.MensagemSucesso("Compra realizada com sucesso!"+ total);
    }
    
    public void buscarCliente() throws ErroSistema {
        ClienteDAO cdao = getClienteDao();
        if (idcliente != null) {
            cliente = cdao.buscar(idcliente);
        }else if(!placa.equals("") || cliente == null){
            cliente = cdao.buscarClientePorPlaca(placa);
        }
        if (cliente == null) {
            MessageUtil.MensagemPerigo("Nenhum cliente encontrado com esse id!");
        }
    }
    

    public void adicionaCarrinho(ActionEvent evento) {
        Produto p = (Produto) evento.getComponent().getAttributes().get("produtoEscolhido");

        if (produtosVenda == null) {
            produtosVenda = new ArrayList<>();
        }
        if (p != null) {

            if (p.getEstoque() > 0) {
                //decrementa do estoque do produto que foi adicionado no carrinho
                p.decrementaEsoque();
                p.incrementaQtd();//adicicona 1 a quantidade do produto no carrinho
                calculaTotal();
                if (produtosVenda.contains(p)) {
                    MessageUtil.MensagemSucesso("Mais um(a) " + p.getNome() + " foi adicionado." + p.getQuantidadeVenda());
                    return;
                } else {
                    produtosVenda.add(p);
                    MessageUtil.MensagemSucesso("Novo produto adicionado");
                    return;
                }
            } else {
                MessageUtil.MensagemErro("Estoque insuficiente.");
            }

        } else {
            MessageUtil.MensagemErro("Erro ao tentar adicionar o produto.");
        }

    }

    public void removeCarrinho(ActionEvent evento) {
        
        Produto p = (Produto) evento.getComponent().getAttributes().get("produtoEscolhido");
        if (p != null) {
            if (p.getQuantidadeVenda() > 1) {
                p.decrementaQtd();               
            } else {
                p.setQuantidadeVenda(0);
                produtosVenda.remove(p);
            }p.incrementaEstoque();//devolve 1 produto ao estoque a cada click 
            calculaTotal();
        } else {
            MessageUtil.MensagemErro("Erro ao tentar remover o produto.");
        }
    }

    //retorna uma nova instancia de clienteDAO
    public ClienteDAO getClienteDao() {
        return new ClienteDAO();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutosVenda() {
        return produtosVenda;
    }

    public void setProdutosVenda(List<Produto> produtosVenda) {
        this.produtosVenda = produtosVenda;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public boolean isAvista() {
        return avista;
    }

    public void setAvista(boolean avista) {
        this.avista = avista;
    }

    public double getTotal() {
        return total;
    }

}
