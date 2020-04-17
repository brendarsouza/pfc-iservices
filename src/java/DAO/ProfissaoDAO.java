/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Profissao;
import Model.Status_Profissao;
import Model.Usuario;
import Util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author brenda
 */
public class ProfissaoDAO {

    public ProfissaoDAO() {
    }

    private static ProfissaoDAO instance;
    EntityManager manager = new JPAUtil().getEntityManager();

    public static ProfissaoDAO getInstance() {
        if (instance == null) {
            instance = new ProfissaoDAO();
        }

        return instance;
    }

    public Profissao getById(final int id) {
        return manager.find(Profissao.class, id);
    }

    public Profissao consultarPorId(int id) {

        Profissao endereco = null;
        try {
            //Consulta um endereco pelo seu ID.
            endereco = manager.find(Profissao.class, id);
        } finally {
            manager.close();
        }
        return endereco;
    }

    @SuppressWarnings("unchecked")
    public List<Profissao> buscarTodos() {
        return manager.createQuery("FROM " + Profissao.class.getName()).getResultList();
    }

    public void cadastrar(Profissao profissao) {

        try {
            manager.getTransaction().begin();
            manager.persist(profissao);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void atualizar(Profissao profissao) {

        try {
            manager.getTransaction().begin();
            manager.merge(profissao);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void remover(Profissao profissao) {

        try {
            manager.getTransaction().begin();
            profissao = manager.find(Profissao.class, profissao.getId());
            manager.remove(profissao);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void removerPorId(final int id) {

        try {
            Profissao profissao = getById(id);
            remover(profissao);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Profissao> buscarProfissaoPorCategoria(int idCategoria) {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select p from Profissao p where p.categoria.id =:idCategoria and p.statusProfissao =:Status_Profissao order by p.profissaoNome asc ");

        query.setParameter("idCategoria", idCategoria);
        query.setParameter("Status_Profissao", Status_Profissao.ATIVO);

        return query.getResultList();
    }

}
