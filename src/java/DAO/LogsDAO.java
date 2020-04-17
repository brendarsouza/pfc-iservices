/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Logs;
import Util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author brenda
 */
public class LogsDAO {
    public LogsDAO() {

    }

    Logs logs = new Logs();

    
    EntityManager manager = new JPAUtil().getEntityManager();

    

    public Logs consultarPorId(int id) {

        Logs logs = null;
        try {
            //Consulta um profissional pelo seu ID.
            logs = manager.find(Logs.class, id);
        } finally {
            manager.close();
        }
        return logs;
    }

    public Logs getById(final int id) {
        return manager.find(Logs.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Logs> buscarTodos() {
        return manager.createQuery("FROM " + Logs.class.getName()).getResultList();
    }

    public void cadastrar(Logs logs) {

        try {
            manager.getTransaction().begin();
            manager.persist(logs);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void atualizar(Logs logs) {

        try {
            manager.getTransaction().begin();
            manager.merge(logs);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

   
}
