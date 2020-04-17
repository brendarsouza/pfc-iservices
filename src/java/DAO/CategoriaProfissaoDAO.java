/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.CategoriaProfissao;
import Model.Status_Categoria;
import Util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author brenda
 */
public class CategoriaProfissaoDAO {

    public CategoriaProfissaoDAO() {
    }

    private static CategoriaProfissaoDAO instance;
    EntityManager manager = new JPAUtil().getEntityManager();

    public static CategoriaProfissaoDAO getInstance() {
        if (instance == null) {
            instance = new CategoriaProfissaoDAO();
        }

        return instance;
    }

    public CategoriaProfissao getById(final int id) {
        return manager.find(CategoriaProfissao.class, id);
    }

    public CategoriaProfissao consultarPorId(int id) {

        CategoriaProfissao endereco = null;
        try {
            //Consulta um endereco pelo seu ID.
            endereco = manager.find(CategoriaProfissao.class, id);
        } finally {
            manager.close();
        }
        return endereco;
    }

    @SuppressWarnings("unchecked")
    public List<CategoriaProfissao> buscarTodos() {
        return manager.createQuery("FROM " + CategoriaProfissao.class.getName()).getResultList();
    }

    public List<CategoriaProfissao> buscarCategorias(CategoriaProfissao categoriaProf) {
        Query query = manager
                .createQuery("select c from CategoriaProfissao c where c.id =:pId and c.statusCategoria =:Status_Categoria order by c.categoriaNome asc");

        query.setParameter("pId", categoriaProf);
        query.setParameter("Status_Categoria", Status_Categoria.ATIVO);

        return query.getResultList();
    }

    public void cadastrar(CategoriaProfissao categoria) {

        try {
            manager.getTransaction().begin();
            manager.persist(categoria);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void atualizar(CategoriaProfissao categoria) {

        try {
            manager.getTransaction().begin();
            manager.merge(categoria);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void remover(CategoriaProfissao categoria) {

        try {
            manager.getTransaction().begin();
            categoria = manager.find(CategoriaProfissao.class, categoria.getId());
            manager.remove(categoria);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void removerPorId(final int id) {

        try {
            CategoriaProfissao categoria = getById(id);
            remover(categoria);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
 public List<CategoriaProfissao> buscarTodasCategorias() throws Exception {
        Query query = manager
                .createQuery("select c from CategoriaProfissao c where c.statusCategoria=:Status_Categoria order by c.categoriaNome asc");

        //retorno da lista de profissionais
        query.setParameter("Status_Categoria", Status_Categoria.ATIVO);

        return query.getResultList();
    }
    public List<CategoriaProfissao> buscarCategoriaAZ(String categoria) throws Exception {
        //passar o parametro para a query
        TypedQuery<CategoriaProfissao> query = manager.createNamedQuery("Categoria.buscarCategoriaAZ",CategoriaProfissao.class);
        //retorno da lista de profissionais
        query.setParameter("categoria", "%" + categoria + "%");

        return query.getResultList();
    }
    public List<CategoriaProfissao> buscarCategoriaZA(String categoria) throws Exception {
        //passar o parametro para a query
        TypedQuery<CategoriaProfissao> query = manager.createNamedQuery("Categoria.buscarCategoriaZA",CategoriaProfissao.class);
        //retorno da lista de profissionais
        query.setParameter("categoria", "%" + categoria + "%");

        return query.getResultList();
    }
}
