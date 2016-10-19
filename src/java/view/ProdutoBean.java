package view;

import dao.ProdutoDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import model.Produto;
import model.Usuario;
import org.primefaces.event.RowEditEvent;
import util.ErroSistema;
import util.MessageUtil;
import util.SessionUtil;

/**
 *
 * @author lber_000
 */

@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable, CrudBean {

    private Produto produto;
    private List<Produto> lista;
    private ProdutoDAO pdao;
    private Usuario usuario;
    
    private UsuarioBean ubean;

    public ProdutoDAO getDao() {
        if (pdao == null) {
            pdao = new ProdutoDAO();
        }
        return pdao;
    }
    
    public void atualizarProduto(RowEditEvent evento) throws ErroSistema{
        this.produto = (Produto) evento.getObject();//recupera o objeto vindo no evento
        alterar();
    }
    
    public void cancelaAlteracao(RowEditEvent evento){
        MessageUtil.MensagemErro("Edição cancelada!");
    }

    @PostConstruct
    public void init() {
        novo();
        buscar();
        usuario = (Usuario) SessionUtil.getParamSession("usuario-logado");
    }

    @Override
    public void novo() {
        produto = new Produto();
    }

    @Override
    public void salvar() {
        try {
            if (!produto.getCodigo().equals("") && !produto.getNome().equals("")
                    && produto.getValorcompra() > 0 && produto.getValorvenda() > 0) {
                produto.setIdusuario(usuario);
                getDao().salvar(produto);
                MessageUtil.MensagemSucesso("Salvo com sucesso.");
                buscar();
            } else {
                MessageUtil.MensagemPerigo("Preencha os campos corretamente.");
            }
        } catch (ErroSistema ex) {
            MessageUtil.MensagemPerigo("Falha ao tentar salvar.");
        }
    }

    @Override
    public void alterar() {
        try {
            if(produto.getEstoque() >= 0 && !produto.getCodigo().equals("") && 
                    !produto.getNome().equals("") && produto.getValorcompra() > 0 
                    && produto.getValorvenda() > 0){
                getDao().editar(produto);
                MessageUtil.MensagemSucesso("Alterado com sucesso.");
            } else {
                MessageUtil.MensagemPerigo("Preencha os campos corretamente.");
            }
        } catch (ErroSistema ex) {
            MessageUtil.MensagemPerigo("Falha ao tentar alterar");
        }
    }

    @Override
    public void buscar() {
        try {
            lista = getDao().listar();
        } catch (ErroSistema ex) {
            MessageUtil.MensagemPerigo("Falha ao tentar buscar os produtos no banco.");
        }
    }
    
    public void apagar(ActionEvent event){
        produto = (Produto) event.getComponent().getAttributes().get("produtoEscolhido");
        MessageUtil.MensagemSucesso(""+produto.getNome());
        excluir();
    }

    @Override
    public void excluir() {
        try {
            MessageUtil.MensagemSucesso(""+produto.getCodigo());
            getDao().deletar(produto);
            MessageUtil.MensagemSucesso("Deletado com sucesso");
        } catch (ErroSistema ex) {
            MessageUtil.MensagemErro("Erro ao tentar excluir o produto.");
        }
        
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getLista() {
        return lista;
    }

    public void setLista(List<Produto> lista) {
        this.lista = lista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
