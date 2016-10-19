package view;

import dao.ProdutoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import model.Produto;
import util.ErroSistema;
import util.MessageUtil;

/**
 *
 * @author lber_000
 */
@ManagedBean(name = "carrinhoBean")
@SessionScoped
public class CarrinhoBean implements Serializable {

    private List<Produto> carrinho;
    private Double total;

    @PostConstruct
    public void init() {
        carrinho = new ArrayList<>();
    }
    
    public void calculaTotal() {
        total = 0.0;
        for (Produto p : carrinho) {
            total += p.getValorvenda() * p.getQuantidadeVenda();
        }
    }

    public void adicionaCarrinho(ActionEvent evento) throws ErroSistema {
        Produto p = (Produto) evento.getComponent().getAttributes().get("produtoEscolhido");

        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }
        if (p != null) {

            if (p.getEstoque() > 0) {
                //decrementa do estoque do produto que foi adicionado no carrinho
                p.decrementaEsoque();
                p.incrementaQtd();//adicicona 1 a quantidade do produto no carrinho
                if (carrinho.contains(p)) {
                    MessageUtil.MensagemSucesso("Mais um(a) " + p.getNome() + " foi adicionado." );
                } else {
                    carrinho.add(p);
                    MessageUtil.MensagemSucesso(p.getNome() + " foi adicionado a lista.");
                }

                calculaTotal();
                return;
            } else {
                MessageUtil.MensagemErro("Estoque insuficiente.");
            }

        } else {
            MessageUtil.MensagemErro("Erro ao tentar adicionar o produto.");
        }

    }

    public void removeCarrinho(ActionEvent evento) throws ErroSistema {

        Produto p = (Produto) evento.getComponent().getAttributes().get("produtoEscolhido");
        if (p != null) {
            if (p.getQuantidadeVenda() > 1) {
                p.decrementaQtd();
            } else {
                p.setQuantidadeVenda(0);
                carrinho.remove(p);
            }
            p.incrementaEstoque();//devolve 1 produto ao estoque a cada click 

            calculaTotal();
        } else {
            MessageUtil.MensagemErro("Erro ao tentar remover o produto.");
        }
    }

    public List<Produto> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Produto> carrinho) {
        this.carrinho = carrinho;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
