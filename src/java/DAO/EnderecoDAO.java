/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Endereco;
import Util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author brenda
 */
public class EnderecoDAO {
    public EnderecoDAO() {
    }

    private static EnderecoDAO instance;
    EntityManager manager = new JPAUtil().getEntityManager();

    public static EnderecoDAO getInstance() {
        if (instance == null) {
            instance = new EnderecoDAO();
        }

        return instance;
    }

    public Endereco getById(final int id) {
        return manager.find(Endereco.class, id);
    }
    public Endereco consultarPorId(int id) {

        Endereco endereco = null;
        try {
            //Consulta um endereco pelo seu ID.
            endereco = manager.find(Endereco.class, id);
        } finally {
            manager.close();
        }
        return endereco;
    }
    
    @SuppressWarnings("unchecked")
    public List<Endereco> buscarTodos() {
        return manager.createQuery("FROM " + Endereco.class.getName()).getResultList();
    }
    public void cadastrar(Endereco endereco) {

        try {
            manager.getTransaction().begin();
            manager.persist(endereco);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void atualizar(Endereco endereco) {

        try {
            manager.getTransaction().begin();
            manager.merge(endereco);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void remover(Endereco endereco) {

        try {
            manager.getTransaction().begin();
            endereco = manager.find(Endereco.class, endereco.getId());
            manager.remove(endereco);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void removerPorId(final int id) {

        try {
            Endereco endereco = getById(id);
            remover(endereco);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
