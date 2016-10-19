/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ProdutoDAO;
import dao.VendaDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.Cliente;
import model.Produto;
import model.Usuario;
import model.Venda;
import util.ErroSistema;
import util.MessageUtil;
import util.SessionUtil;

/**
 *
 * @author lber
 */
@ManagedBean
@SessionScoped
public class VendaBean {

    private static List<Produto> produtos;
    private List<Produto> produtosVenda;
    private Usuario usuario;
    private boolean avista = false;
    private VendaDAO vdao;
    private Venda venda;

    @ManagedProperty(value = "#{carrinhoBean}")
    private CarrinhoBean cbean;

    @ManagedProperty(value = "#{clienteBean}")
    private ClienteBean clientebean;
    
    @PostConstruct
    public void init() {
        novo();
    }

    public VendaDAO getDao() {
        if (vdao == null) {
            vdao = new VendaDAO();
        }
        return vdao;
    }

    public void venda() throws ErroSistema {

        usuario = (Usuario) SessionUtil.getParamSession("usuario-logado");

        if (!cbean.getCarrinho().isEmpty() && !clientebean.getCliente().getNome().equals("")) {
            venda = new Venda(null, usuario, clientebean.getCliente(), cbean.getCarrinho(), cbean.getTotal(), avista);
            getDao().salvar(venda);

            for (Produto p : cbean.getCarrinho()) {
                ProdutoDAO pdao = new ProdutoDAO();
                p.setQuantidadeVenda(0);
                pdao.editar(p);
            }
            cbean.getCarrinho().clear();

            MessageUtil.MensagemSucesso("Compra realizada com sucesso!");
        } else if (clientebean.getCliente().getNome().equals("")) {
            MessageUtil.MensagemPerigo("Erro\nInforme o cliente.");
        } else if (cbean.getCarrinho().isEmpty()) {
            MessageUtil.MensagemPerigo("Erro\nNenhum produto foi adicionado ao carrinho.");
        }
    }

    public void novo() {
        venda = new Venda();
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

    public List<Produto> getProdutosVenda() {
        return produtosVenda;
    }

    public void setProdutosVenda(List<Produto> produtosVenda) {
        this.produtosVenda = produtosVenda;
    }

    public boolean isAvista() {
        return avista;
    }

    public void setAvista(boolean avista) {
        this.avista = avista;
    }

    public CarrinhoBean getCbean() {
        return cbean;
    }

    public void setCbean(CarrinhoBean cbean) {
        this.cbean = cbean;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public ClienteBean getClientebean() {
        return clientebean;
    }

    public void setClientebean(ClienteBean clientebean) {
        this.clientebean = clientebean;
    }
}
