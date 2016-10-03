/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Produto;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author lber
 */
public class ProdutoDAO extends GenericDAO<Produto>{
    
    public Produto buscarProduto(String id){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria consulta = sessao.createCriteria(Produto.class);

            consulta.add(Restrictions.idEq(id));

            //uniqueResult() É usado quando se quer retornar apenas um elemento
            Produto resultado = (Produto) consulta.uniqueResult();
            return resultado;

    }
    
}
