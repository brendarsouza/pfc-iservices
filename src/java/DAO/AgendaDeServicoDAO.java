/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.AgendaDeServico;
import Model.Status_Orcamento;
import Model.Orcamento;
import Model.Status_Pedido;
import Model.Status_Servico;
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
public class AgendaDeServicoDAO {

    public AgendaDeServicoDAO() {

    }

    AgendaDeServico agendaDeServico = new AgendaDeServico();

    private static AgendaDeServicoDAO instance;
    EntityManager manager = new JPAUtil().getEntityManager();

    public static AgendaDeServicoDAO getInstance() {
        if (instance == null) {
            instance = new AgendaDeServicoDAO();
        }

        return instance;
    }

    public AgendaDeServico consultarPorId(int id) {

        AgendaDeServico agendaDeServico = null;
        try {
            //Consulta um profissional pelo seu ID.
            agendaDeServico = manager.find(AgendaDeServico.class, id);
        } finally {
            manager.close();
        }
        return agendaDeServico;
    }

    public AgendaDeServico getById(final int id) {
        return manager.find(AgendaDeServico.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<AgendaDeServico> buscarTodos() {
        return manager.createQuery("FROM " + AgendaDeServico.class.getName()).getResultList();
    }

    public void cadastrar(AgendaDeServico agendaDeServico) {

        try {
            manager.getTransaction().begin();
            manager.persist(agendaDeServico);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void atualizar(AgendaDeServico agendaDeServico) {

        try {
            manager.getTransaction().begin();
            manager.merge(agendaDeServico);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void remover(AgendaDeServico agendaDeServico) {

        try {
            manager.getTransaction().begin();
            agendaDeServico = manager.find(AgendaDeServico.class, agendaDeServico.getId());
            manager.remove(agendaDeServico);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void removerPorId(final int id) {

        try {
            AgendaDeServico agendaDeServico = getById(id);
            remover(agendaDeServico);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<AgendaDeServico> buscarAgendamentosCliente(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from AgendaDeServico a where a.orcamento.pedido.cliente.id = :idConsultar");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);

        return query.getResultList();
    }

    public List<AgendaDeServico> buscarAgendamentosConcluidosCliente(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from AgendaDeServico a where a.orcamento.pedido.cliente.id = :idConsultar and a.status_servico =:STATUS_SERVICO");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_SERVICO", Status_Servico.CONCLUIDO);
        return query.getResultList();
    }

    public List<AgendaDeServico> buscarAgendamentosEmEsperaCliente(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from AgendaDeServico a where a.orcamento.pedido.cliente.id = :idConsultar and a.status_servico =:STATUS_SERVICO");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_SERVICO", Status_Servico.EM_ESPERA);
        return query.getResultList();
    }

    public List<AgendaDeServico> buscarAgendamentosCanceladosCliente(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from AgendaDeServico a where a.orcamento.pedido.cliente.id = :idConsultar and a.status_servico =:STATUS_SERVICO");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_SERVICO", Status_Servico.CANCELADO);
        return query.getResultList();
    }

    public List<AgendaDeServico> buscarAgendamentosReagendadosCliente(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from AgendaDeServico a where a.orcamento.pedido.cliente.id = :idConsultar and a.status_servico =:STATUS_SERVICO");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_SERVICO", Status_Servico.REAGENDADO);
        return query.getResultList();
    }

    public List<AgendaDeServico> buscarAgendamentosProfissional(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from AgendaDeServico a where a.orcamento.pedido.profissional.id = :idConsultar");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);

        return query.getResultList();
    }

    public List<AgendaDeServico> buscarAgendamentosConcluidosProfissional(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from AgendaDeServico a where a.orcamento.pedido.profissional.id = :idConsultar and a.status_servico =:STATUS_SERVICO");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_SERVICO", Status_Servico.CONCLUIDO);

        return query.getResultList();
    }

    public List<AgendaDeServico> buscarAgendamentosCanceladosProfissional(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from AgendaDeServico a where a.orcamento.pedido.profissional.id = :idConsultar and a.status_servico =:STATUS_SERVICO");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_SERVICO", Status_Servico.CANCELADO);

        return query.getResultList();
    }

    public List<AgendaDeServico> buscarAgendamentosEmEsperaProfissional(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from AgendaDeServico a where a.orcamento.pedido.profissional.id = :idConsultar and a.status_servico =:STATUS_SERVICO");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_SERVICO", Status_Servico.EM_ESPERA);

        return query.getResultList();
    }

    public List<AgendaDeServico> buscarAgendamentosReagendadosProfissional(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from AgendaDeServico a where a.orcamento.pedido.profissional.id = :idConsultar and a.status_servico =:STATUS_SERVICO");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_SERVICO", Status_Servico.REAGENDADO);

        return query.getResultList();
    }

    public List<AgendaDeServico> verificarDataHoraAgendamento(Date dataAgendamento, Date horaAgendamento) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from AgendaDeServico a where a.dataAgendamento =:dataAgendamento and a.horaAgendamento =:horaAgendamento");

        //retorno da lista de profissionais
        query.setParameter("dataAgendamento", dataAgendamento);
        query.setParameter("horaAgendamento", horaAgendamento);

        return query.getResultList();

    }

}
