/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Status_Orcamento;
import Model.Orcamento;
import Model.Status_Visita;
import Util.JPAUtil;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author brenda
 */
public class OrcamentoDAO {

    public OrcamentoDAO() {

    }

    Orcamento orcamento = new Orcamento();

    private static OrcamentoDAO instance;

    EntityManager manager = new JPAUtil().getEntityManager();

    public static OrcamentoDAO getInstance() {
        if (instance == null) {
            instance = new OrcamentoDAO();
        }

        return instance;
    }

    public Orcamento consultarPorId(int id) {

        Orcamento orcamento = null;
        try {
            //Consulta um pedido pelo seu ID.
            orcamento = manager.find(Orcamento.class, id);
        } finally {
            manager.close();
        }
        return orcamento;
    }

    public Orcamento getById(final int id) {
        return manager.find(Orcamento.class, id);
    }

    public void cadastrar(Orcamento orcamento) {

        try {
            manager.getTransaction().begin();
            manager.persist(orcamento);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void atualizar(Orcamento orcamento) {

        try {
            manager.getTransaction().begin();
            manager.merge(orcamento);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void remover(Orcamento orcamento) {

        try {
            manager.getTransaction().begin();
            orcamento = manager.find(Orcamento.class, orcamento.getId());
            manager.remove(orcamento);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void removerPorId(final int id) {

        try {
            Orcamento orcamento = getById(id);
            remover(orcamento);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Orcamento consultarOrcamentoId(int o) {
        Query query = manager
                .createQuery("select o from Orcamento o where o.pedido.cliente.id =:pId");

        query.setParameter("pId", o);

        Orcamento or = (Orcamento) query.getSingleResult();
        return or;
    }

    public List<Orcamento> buscarOrcamentoClientes(int idOrcamentoCliente) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select o from Orcamento o where o.pedido.cliente.id =:idOrcamentoCliente");

        //retorno da lista de profissionais
        query.setParameter("idOrcamentoCliente", idOrcamentoCliente);

        return query.getResultList();
    }

    public List<Orcamento> buscarOrcamentosAprovadosClientes(int idOrcamentoApCliente) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select o from Orcamento o where o.pedido.cliente.id =:idOrcamentoApCliente and o.status_Orcamento =:Status_Orcamento");

        //retorno da lista de profissionais
        query.setParameter("idOrcamentoApCliente", idOrcamentoApCliente);
        query.setParameter("Status_Orcamento", Status_Orcamento.APROVADO);

        return query.getResultList();
    }

    public List<Orcamento> buscarOrcamentosReprovadosClientes(int idOrcamentoRpCliente) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select o from Orcamento o where o.pedido.cliente.id =:idOrcamentoRpCliente and o.status_Orcamento  =:Status_Orcamento");

        //retorno da lista de profissionais
        query.setParameter("idOrcamentoRpCliente", idOrcamentoRpCliente);
        query.setParameter("Status_Orcamento", Status_Orcamento.REPROVADO);

        return query.getResultList();
    }

    public List<Orcamento> buscarOrcamentosEmEsperaClientes(int idOrcamentoEECliente) throws Exception {
        //passar o parametro para a query

        Query query = manager
                .createQuery("select o from Orcamento o where o.pedido.cliente.id =:idOrcamentoEECliente and o.status_Orcamento  =:Status_Orcamento");

        //retorno da lista de profissionais
        query.setParameter("idOrcamentoEECliente", idOrcamentoEECliente);
        query.setParameter("Status_Orcamento", Status_Orcamento.EM_ESPERA);

        return query.getResultList();
    }

    public List<Orcamento> buscarOrcamentoProfissional(int idOrcamentoProfissional) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select o from Orcamento o where o.pedido.profissional.id  =:idOrcamentoProfissional");

        //retorno da lista de profissionais
        query.setParameter("idOrcamentoProfissional", idOrcamentoProfissional);

        return query.getResultList();
    }

    public List<Orcamento> buscarOrcamentosAprovadosProfissional(int idOrcamentoApProfissional) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select o from Orcamento o where o.pedido.profissional.id =:idOrcamentoApProfissional and o.status_Orcamento  =:Status_Orcamento");

        //retorno da lista de profissionais
        query.setParameter("idOrcamentoApProfissional", idOrcamentoApProfissional);
        query.setParameter("Status_Orcamento", Status_Orcamento.APROVADO);

        return query.getResultList();
    }

    public List<Orcamento> buscarOrcamentosReprovadosProfissional(int idOrcamentoRpProfissional) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select o from Orcamento o where o.pedido.profissional.id =:idOrcamentoRpProfissional and o.status_Orcamento  =:Status_Orcamento");

        //retorno da lista de profissionais
        query.setParameter("idOrcamentoRpProfissional", idOrcamentoRpProfissional);
        query.setParameter("Status_Orcamento", Status_Orcamento.REPROVADO);

        return query.getResultList();
    }

    public List<Orcamento> buscarOrcamentosEmEsperaProfissional(int idOrcamentoEEProfissional) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select o from Orcamento o where o.pedido.profissional.id =:idOrcamentoEEProfissional and o.status_Orcamento  =:Status_Orcamento");

        //retorno da lista de profissionais
        query.setParameter("idOrcamentoEEProfissional", idOrcamentoEEProfissional);
        query.setParameter("Status_Orcamento", Status_Orcamento.EM_ESPERA);

        return query.getResultList();
    }

    public List<Orcamento> verificarDataHoraOrcamento(Date dataPrevistaInicio, Date horaVisita) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select o from Orcamento o where o.dataPrevistaInicio =:dataPrevistaInicio and o.horaVisita =:horaVisita ");

        //retorno da lista de profissionais
        query.setParameter("dataPrevistaInicio", dataPrevistaInicio);
        query.setParameter("horaVisita", horaVisita);

        return query.getResultList();

    }
}
