/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.UsuarioDAO;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import model.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;
import util.ErroSistema;
import util.MessageUtil;
import util.SessionUtil;

/**
 *
 * @author lber
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable, CrudBean {

    private UsuarioDAO usuarioDAO;
    private Usuario usuario, usuarioLogado;
    private List<Usuario> lista;

    public UsuarioDAO getDao() {
        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO();
        }
        return usuarioDAO;//Retorna para o CrudBean, assim ele saberá qual é o objeto que deverá instaciar
    }

    @PostConstruct
    public void init() {
        recuperaUsuario();
        novo();
        buscar();
    }
    
    public void atualizar(RowEditEvent evento) throws ErroSistema{
        this.usuario = (Usuario) evento.getObject();//recupera o objeto vindo no evento
        alterar();
    }
    
    public void cancelar(RowEditEvent evento){
        MessageUtil.MensagemErro("Edição cancelada!");
    }
    
    public void alteraDados() {
        usuario = (Usuario) SessionUtil.getParamSession("usuario-logado");
    }

    //Recupera o usuário que foi setado na sessão no ato do login
    public void recuperaUsuario() {
        usuarioLogado = (Usuario) SessionUtil.getParamSession("usuario-logado");
    }

    @Override
    public void novo() {
        usuario = new Usuario();
    }

    @Override
    public void salvar() {

        try {

            if (!usuario.getLogin().equals("") && !usuario.getNome().equals("")
                    && !usuario.getSenha().equals("")) {

                getDao().salvar(usuario);
                buscar();
                novo();
                MessageUtil.MensagemSucesso("Salvo com sucesso.");
            } else {
                MessageUtil.MensagemPerigo("Preencha todos os campos corretamente.");
            }
        } catch (ErroSistema ex) {
            MessageUtil.MensagemErro("Erro ao tentar salvar.");
        }

    }

    @Override
    public void alterar() {
        try {

            if (!usuario.getLogin().equals("") && !usuario.getNome().equals("")
                    && !usuario.getSenha().equals("")) {

                getDao().editar(usuario);
                MessageUtil.MensagemSucesso("Alterado com sucesso.");
            } else {
                MessageUtil.MensagemPerigo("Preencha todos os campos corretamente.");
            }
        } catch (ErroSistema ex) {
            MessageUtil.MensagemErro("Erro ao tentar alterar.");
        }
    }

    @Override
    public void buscar() {
        try {
            lista = getDao().listar();
        } catch (ErroSistema ex) {
            MessageUtil.MensagemPerigo("Erro ao tentar buscar os usuários no banco.");
        }
    }

    public void apagar(ActionEvent event){
        usuario = (Usuario) event.getComponent().getAttributes().get("usuarioEscolhido");
        excluir();
    }
    
    @Override
    public void excluir() {
        try {
            getDao().deletar(usuario);
            MessageUtil.MensagemSucesso("Excluído com sucesso.");
        } catch (ErroSistema ex) {
            MessageUtil.MensagemErro(""+ex);
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }


}
