/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ClienteDAO;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import model.Cliente;
import org.primefaces.event.RowEditEvent;
import util.ErroSistema;
import util.MessageUtil;

/**
 *
 * @author lber_000
 */
@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable, CrudBean {

    private Cliente cliente;
    private List<Cliente> clientes;
    private String placa;
    private Integer id;
    private ClienteDAO cdao;

    @PostConstruct
    public void init() {
        try {
            clientes = getDao().listar();
            novo();
        } catch (ErroSistema ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void novo() {
        cliente = new Cliente();
    }

    public ClienteDAO getDao() {
        if (cdao == null) {
            cdao = new ClienteDAO();
        }
        return cdao;
    }
    
    public void atualizarCliente(RowEditEvent evento) throws ErroSistema{
        this.cliente = (Cliente) evento.getObject();//recupera o objeto vindo no evento
        alterar();
    }
    
    public void cancelaAlteracao(RowEditEvent evento){
        MessageUtil.MensagemErro("Edição cancelada!");
    }

    @Override
    public void salvar() {
        try {
            if(!cliente.getNome().equals("") && !cliente.getTelefone().equals("") && !cliente.getPlaca().equals("")){
                getDao().salvar(cliente);
                buscar();
                novo();
                MessageUtil.MensagemSucesso("Salvo com sucesso.");
            } else {
                MessageUtil.MensagemPerigo("Preencha os dados corretamnete.");
            }
        } catch (ErroSistema ex) {
            MessageUtil.MensagemErro("Erro ao tentar salvar");
        }
    }

    @Override
    public void alterar() {
        try {
            if(!cliente.getNome().equals("") && !cliente.getTelefone().equals("") && !cliente.getPlaca().equals("")){
                getDao().editar(cliente);
            } else {
                MessageUtil.MensagemPerigo("Preencha os dados corretamnete.");
            }
        } catch (ErroSistema ex) {
            MessageUtil.MensagemErro("Erro ao tentar salvar");
        }
    }
    
    public void apagar(ActionEvent event){
        cliente = (Cliente) event.getComponent().getAttributes().get("clienteEscolhido");
        excluir();
    }

    @Override
    public void buscar() {
        try {
            if (id != null) {

                cliente = getDao().buscar(id);

            } else if (!placa.equals("") || cliente == null) {
                cliente = getDao().buscarClientePorPlaca(placa);
            }
            if (cliente == null) {
                MessageUtil.MensagemPerigo("Nenhum cliente encontrado com esse id!");
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void excluir() {
        try {
            getDao().deletar(cliente);
            MessageUtil.MensagemSucesso("Exclído com sucesso.");
        } catch (ErroSistema ex) {
            MessageUtil.MensagemErro("" + ex);
        }
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
