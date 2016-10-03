/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import model.Cliente;
import model.Produto;
import model.Usuario;
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
    private Usuario usuario;
    private Cliente cliente;
    
    @PostConstruct
    public void init() {
        try {
            ProdutoDAO pdao = new ProdutoDAO();
            produtos = pdao.listar();
            produtosVenda = new ArrayList<>(); 
        } catch (ErroSistema ex) {
            MessageUtil.MensagemPerigo("Erro cao carregar lista de produtos!");
        }
    }
    
    public void buscarCliente(Integer id) throws ErroSistema{
        ClienteDAO dao = getClienteDao();
        cliente = dao.buscar(id);
        if(cliente == null)
            MessageUtil.MensagemPerigo("Nenhum cliente encontrado com esse id!");
    }
    
    public void adicionaCarrinho(ActionEvent evento){
        Produto p = (Produto) evento.getComponent().getAttributes().get("produtoEscolhido");
        if(produtosVenda == null)
            produtosVenda = new ArrayList<>(); 
        if(p != null){
            if (produtosVenda.contains(p)) {
                
                produtos.get(produtos.indexOf(p)).setQuantidade(
                    produtos.get(produtos.indexOf(p)).getQuantidade() - 1);
                
                produtosVenda.get(produtosVenda.indexOf(p)).setQuantidade( 
                        produtosVenda.get(produtosVenda.indexOf(p)).getQuantidade() + 1 );
                MessageUtil.MensagemSucesso("Mais um(a) "+ p.getNome() + " foi adicionado.");
                return;
            } else {
                produtosVenda.add(p);
                MessageUtil.MensagemSucesso("Novo produto adicionado");
                return;
            }
        }else
            MessageUtil.MensagemErro("Erro ao tentar adicionar o produto.");
    }
    
    public void removeCarrinho(ActionEvent evento){
        Produto p = (Produto) evento.getComponent().getAttributes().get("produtoEscolhido");
        if(p != null)
            produtosVenda.remove(p);
        else
            MessageUtil.MensagemErro("Erro ao tentar remover o produto.");
    }
    
    //retorna uma nova instancia de clienteDAO
    public ClienteDAO getClienteDao(){
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
    
}
