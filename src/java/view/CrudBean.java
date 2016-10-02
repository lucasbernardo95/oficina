/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.GenericDAO;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import util.ErroSistema;
import util.MessageUtil;

/**
 * @author lber
 */
public abstract class CrudBean<E, D extends GenericDAO> {

    private E entidade;
    private List<E> entidades;
    
    public void novo() throws InstantiationException, IllegalAccessException {
        novaEntidade();
    }
    
    public void salvar() throws ErroSistema, InstantiationException, IllegalAccessException {
        
        getDao().salvar(entidade);
        novaEntidade();
        MessageUtil.MensagemSucesso("Salvo com sucesso!");      
    }
    
    public void editar(E entidade) throws ErroSistema {
        this.entidade = entidade;
        getDao().merge(entidade);//Método merge serve para atualizar ou salvar um objeto passado no banco
    }
    
    public void deletar(E entidade) throws ErroSistema {
        getDao().deletar(entidade);
        entidades.remove(entidade);
        MessageUtil.MensagemSucesso("Deletado com sucesso!");
    }
    
    public void buscar() throws ErroSistema {
        entidades = getDao().listar();
        if (entidades == null || entidades.size() < 1) {
            MessageUtil.MensagemSucesso("Não há nada cadastrado!");
        }
    }

    //getters e setters
    public E getEntidade() {
        return entidade;
    }
    
    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }
    
    public List<E> getEntidades() {
        return entidades;
    }
    
    public void setEntidades(List<E> entidades) {
        this.entidades = entidades;
    }

    /**
     * Resposável por instanciar um objeto DAO do tipo D passado no diamante.
     */
    public abstract D getDao();

    //Cria uma nova entidade de acordo com o tipo passado no diamante.
    public void novaEntidade() throws InstantiationException, IllegalAccessException{
        Class<E> classe = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        entidade = classe.newInstance();
    }

}
