/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import Model.Usuario;
import Util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author brenda
 */
public class ClienteDAO {
    public ClienteDAO() {
    }

    private static ClienteDAO instance;
    EntityManager manager = new JPAUtil().getEntityManager();

    public static ClienteDAO getInstance() {
        if (instance == null) {
            instance = new ClienteDAO();
        }

        return instance;
    }
    
    public Cliente consultarPorId(int id) {

        Cliente cliente = new Cliente();
        try {
            //Consulta um cliente pelo seu ID.
            cliente = manager.find(Cliente.class, id);
        } finally {
            manager.close();
        }
        return cliente;
    }
    
    public Cliente consultarUsuarioId(Usuario u){
        Query query = manager
                .createQuery("select c from Cliente c where c.usuario =:pId");

        query.setParameter("pId", u);

        Cliente c = (Cliente) query.getSingleResult();
        return c;
    }
 
 
    
    public Cliente getById(final int id) {
        return manager.find(Cliente.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Cliente> buscarTodos() {
        return manager.createQuery("FROM " + Cliente.class.getName()).getResultList();
    }
    
    public void cadastrar(Cliente cliente) {

        try {
            manager.getTransaction().begin();
            manager.persist(cliente);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }
    
    public void atualizar(Cliente cliente) {

        try {
            manager = new JPAUtil().getEntityManager();
            manager.getTransaction().begin();
            manager.merge(cliente);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void remover(Cliente cliente) {

        try {
            manager.getTransaction().begin();
            cliente = manager.find(Cliente.class, cliente.getId());
            manager.remove(cliente);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void removerPorId(final int id) {

        try {
            Cliente cliente = getById(id);
            remover(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Cliente> verificarUnique(String email, String cpf, String rg, String razaoSocial, String cnpj) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select c from Cliente c where c.usuario.email =:email or c.cpf =:cpf or c.rg =:rg or c.cnpj =:cnpj or c.razaoSocial =:razaoSocial ");

        //retorno da lista de profissionais
        query.setParameter("email", email);
        query.setParameter("cpf", cpf);
        query.setParameter("rg", rg);
        query.setParameter("cnpj", cnpj);
        query.setParameter("razaoSocial", razaoSocial);

        return query.getResultList();

    }

   
    
}
