/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.UsuarioDAO;
import java.io.Serializable;
import model.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.SessionUtil;

/**
 *
 * @author lber
 */
@ManagedBean
@SessionScoped
public class UsuarioBean extends CrudBean<Usuario, UsuarioDAO> implements Serializable{

    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    
    @Override
    public UsuarioDAO getDao() {
        if(usuarioDAO == null)
            usuarioDAO = new UsuarioDAO();
        return usuarioDAO;//Retorna para o CrudBean, assim ele saberá qual é o objeto que deverá instaciar
    }

    //Recupera o usuário que foi setado na sessão no ato do login
    public void recuperaUsuario(){
        usuario = (Usuario) SessionUtil.getParamSession("usuario-logado");
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
}