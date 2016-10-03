package dao;

import java.util.List;
import model.Cliente;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author lber
 */
public class ClienteDAO extends GenericDAO<Cliente> {

    public Cliente buscarClientePorPlaca(String placa) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta = sessao.createQuery("from Cliente c where c.placa = :parametro");
        consulta.setString("parametro", placa);
        Cliente c = (Cliente) consulta.uniqueResult();
        sessao.close();
        return c;

    }

}
