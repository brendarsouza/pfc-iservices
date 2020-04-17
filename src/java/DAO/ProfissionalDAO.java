/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;

import Model.Profissional;
import Model.Usuario;
import Util.JPAUtil;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;

/**
 *
 * @author brenda
 */
public class ProfissionalDAO {

    public ProfissionalDAO() {
    }

    private static ProfissionalDAO instance;
    EntityManager manager = new JPAUtil().getEntityManager();

    public static ProfissionalDAO getInstance() {
        if (instance == null) {
            instance = new ProfissionalDAO();
        }

        return instance;
    }

    public Profissional consultarPorId(int id) {

       return manager.find(Profissional.class, id);
    }

    public Profissional getById(final int id) {
        return manager.find(Profissional.class, id);
    }

    public Profissional consultarUsuarioId(Usuario u) {
        Query query = manager
                .createQuery("select p from Profissional p where p.usuario =:pId");

        query.setParameter("pId", u);

        Profissional p = (Profissional) query.getSingleResult();
        return p;
    }

    @SuppressWarnings("unchecked")
    public List<Profissional> buscarTodos() {
        return manager.createQuery("FROM " + Profissional.class.getName()).getResultList();
    }

    public void cadastrar(Profissional profissional) {

        try {
            manager.getTransaction().begin();
            manager.persist(profissional);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void atualizar(Profissional profissional) {
        EntityManager manager1 = new JPAUtil().getEntityManager();
        try {
            manager1.getTransaction().begin();
            manager1.merge(profissional);
            manager1.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void remover(Profissional profissional) {

        try {
            manager.getTransaction().begin();
            profissional = manager.find(Profissional.class, profissional.getId());
            manager.remove(profissional);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void removerPorId(final int id) {

        try {
            Profissional profissional = getById(id);
            remover(profissional);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public List<Profissional> buscarProfissionaisPorProfissao(int profissao, String cidadeAtendimento) throws Exception {
        //passar o parametro para a query
        TypedQuery<Profissional> query = manager.createNamedQuery("Profissional.buscarProfissionais", Profissional.class);

        //retorno da lista de profissionais
        query.setParameter("profissao", profissao);
        query.setParameter("cidadeAtendimento", "%" + cidadeAtendimento + "%");

        return query.getResultList();
    }
   
    public List<Profissional> buscarProfissionaisPorFiltros(int profissao, String cidadeAtendimento, Date horarioAtendimentoInicio, Date horarioAtendimentoFim) throws Exception {
        //passar o parametro para a query
        TypedQuery<Profissional> query = manager.createNamedQuery("Profissional.buscarProfissionaisPorFiltros", Profissional.class);

        //retorno da lista de profissionais
        query.setParameter("profissao", profissao);
        query.setParameter("cidadeAtendimento", "%" + cidadeAtendimento + "%");
        
        query.setParameter("horarioAtendimentoInicio", horarioAtendimentoInicio);
        query.setParameter("horarioAtendimentoFim", horarioAtendimentoFim);

        return query.getResultList();
    }

    public List<Profissional> buscarProfissionaisPorAvaliacaoServico(int profissao, String cidadeAtendimento, Date horarioAtendimentoInicio, Date horarioAtendimentoFim) throws Exception {
        //passar o parametro para a query
        TypedQuery<Profissional> query = manager.createNamedQuery("Profissional.buscarProfissionaisPorAvaliacaoServico", Profissional.class);

        //retorno da lista de profissionais
        query.setParameter("profissao", profissao);
        query.setParameter("cidadeAtendimento", "%" + cidadeAtendimento + "%");
        
        query.setParameter("horarioAtendimentoInicio", horarioAtendimentoInicio);
        query.setParameter("horarioAtendimentoFim", horarioAtendimentoFim);

        return query.getResultList();
    }

    public List<Profissional> buscarProfissionaisPorAvaliacaoCusto(int profissao, String cidadeAtendimento, Date horarioAtendimentoInicio, Date horarioAtendimentoFim) throws Exception {
        //passar o parametro para a query
        TypedQuery<Profissional> query = manager.createNamedQuery("Profissional.buscarProfissionaisPorAvaliacaoCusto", Profissional.class);

        //retorno da lista de profissionais
        query.setParameter("profissao", profissao);
        query.setParameter("cidadeAtendimento", "%" + cidadeAtendimento + "%");
        
        query.setParameter("horarioAtendimentoInicio", horarioAtendimentoInicio);
        query.setParameter("horarioAtendimentoFim", horarioAtendimentoFim);

        return query.getResultList();
    }

    public List<Profissional> buscarProfissionaisPorAvaliacaoRapidez(int profissao, String cidadeAtendimento, Date horarioAtendimentoInicio, Date horarioAtendimentoFim) throws Exception {
        //passar o parametro para a query
        TypedQuery<Profissional> query = manager.createNamedQuery("Profissional.buscarProfissionaisPorAvaliacaoRapidez", Profissional.class);

        //retorno da lista de profissionais
        query.setParameter("profissao", profissao);
        query.setParameter("cidadeAtendimento", "%" + cidadeAtendimento + "%");
        
        query.setParameter("horarioAtendimentoInicio", horarioAtendimentoInicio);
        query.setParameter("horarioAtendimentoFim", horarioAtendimentoFim);

        return query.getResultList();
    }

    public List<Profissional> buscarProfissionaisPorAvaliacaoAZ(int profissao, String cidadeAtendimento, Date horarioAtendimentoInicio, Date horarioAtendimentoFim) throws Exception {
        //passar o parametro para a query
        TypedQuery<Profissional> query = manager.createNamedQuery("Profissional.buscarProfissionaisPorAvaliacaoAZ", Profissional.class);

        //retorno da lista de profissionais
        query.setParameter("profissao", profissao);
        query.setParameter("cidadeAtendimento", "%" + cidadeAtendimento + "%");
        
        query.setParameter("horarioAtendimentoInicio", horarioAtendimentoInicio);
        query.setParameter("horarioAtendimentoFim", horarioAtendimentoFim);

        return query.getResultList();
    }

    public List<Profissional> buscarProfissionaisPorAvaliacaoZA(int profissao, String cidadeAtendimento, Date horarioAtendimentoInicio, Date horarioAtendimentoFim) throws Exception {
        //passar o parametro para a query
        TypedQuery<Profissional> query = manager.createNamedQuery("Profissional.buscarProfissionaisPorAvaliacaoZA", Profissional.class);

        //retorno da lista de profissionais
        query.setParameter("profissao", profissao);
        query.setParameter("cidadeAtendimento", "%" + cidadeAtendimento + "%");
        
        query.setParameter("horarioAtendimentoInicio", horarioAtendimentoInicio);
        query.setParameter("horarioAtendimentoFim", horarioAtendimentoFim);

        return query.getResultList();
    }

    public List<Profissional> verificarUnique(String email, String cpf, String rg, String razaoSocial, String cnpj) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select p from Profissional p where p.usuario.email =:email or p.cpf =:cpf or p.rg =:rg or p.cnpj =:cnpj or p.razaoSocial =:razaoSocial ");

        //retorno da lista de profissionais
        query.setParameter("email", email);
        query.setParameter("cpf", cpf);
        query.setParameter("rg", rg);
        query.setParameter("cnpj", cnpj);
        query.setParameter("razaoSocial", razaoSocial);

        return query.getResultList();

    }
}
