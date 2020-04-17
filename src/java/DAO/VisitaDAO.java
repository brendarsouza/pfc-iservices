/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Pedido;
import Model.Status_Pedido;
import Model.Status_Servico;
import Model.Status_Visita;
import Model.Visita;
import Util.JPAUtil;
import java.util.List;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author brenda
 */
public class VisitaDAO {

    public VisitaDAO() {

    }

    Visita visita = new Visita();

    private static VisitaDAO instance;
    EntityManager manager = new JPAUtil().getEntityManager();

    public static VisitaDAO getInstance() {
        if (instance == null) {
            instance = new VisitaDAO();
        }

        return instance;
    }

    public Visita consultarPorId(int id) {

        Visita visita = null;
        try {
            //Consulta um profissional pelo seu ID.
            visita = manager.find(Visita.class, id);
        } finally {
            manager.close();
        }
        return visita;
    }

    public Visita getById(final int id) {
        return manager.find(Visita.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Visita> buscarTodos() {
        return manager.createQuery("FROM " + Visita.class.getName()).getResultList();
    }

    public void cadastrar(Visita visita) {

        try {
            manager.getTransaction().begin();
            manager.persist(visita);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void atualizar(Visita visita) {

        try {
            manager.getTransaction().begin();
            manager.merge(visita);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void remover(Visita visita) {

        try {
            manager.getTransaction().begin();
            visita = manager.find(Visita.class, visita.getId());
            manager.remove(visita);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void removerPorId(final int id) {

        try {
            Visita visita = getById(id);
            remover(visita);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Visita> buscarVisitasAprovadaCliente(int idConsultar) throws Exception {
        Query query = manager
                .createQuery("select v from Visita v where v.pedido.cliente.id = :idConsultar and v.status_Visita =:Status_Visita order by v.dataVisita asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("Status_Visita", Status_Visita.APROVADA);

        return query.getResultList();
    }

    public List<Visita> buscarVisitasEmEsperaCliente(int idConsultar) throws Exception {
        Query query = manager
                .createQuery("select v from Visita v where v.pedido.cliente.id = :idConsultar and v.status_Visita =:Status_Visita order by v.dataVisita asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("Status_Visita", Status_Visita.EM_ESPERA);

        return query.getResultList();
    }

    public List<Visita> buscarVisitasReprovadaCliente(int idConsultar) throws Exception {
        Query query = manager
                .createQuery("select v from Visita v where v.pedido.cliente.id = :idConsultar and v.status_Visita =:Status_Visita order by v.dataVisita asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("Status_Visita", Status_Visita.REPROVADA);

        return query.getResultList();
    }

    public List<Visita> buscarVisitasCanceladaCliente(int idConsultar) throws Exception {
        Query query = manager
                .createQuery("select v from Visita v where v.pedido.cliente.id = :idConsultar and v.status_Visita =:Status_Visita order by v.dataOperacao asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("Status_Visita", Status_Visita.CANCELADA);

        return query.getResultList();
    }

    public List<Visita> buscarVisitasProfissional(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select v from Visita v where v.pedido.profissional.id = :idConsultar order by v.dataVisita asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);

        return query.getResultList();

    }

    public List<Visita> buscarVisitasRemarcadasProfissional(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select v from Visita v where v.pedido.profissional.id = :idConsultar and v.status_Visita =:STATUS_VISITA  order by v.dataVisita asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_VISITA", Status_Visita.REMARCADA);

        return query.getResultList();

    }

    public List<Visita> buscarVisitasRemarcadasCliente(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select v from Visita v where v.pedido.cliente.id = :idConsultar and v.status_Visita =:STATUS_VISITA order by v.dataVisita asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_VISITA", Status_Visita.REMARCADA);
        

        return query.getResultList();

    }

    public List<Visita> buscarVisitasAprovadaProfissional(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select v from Visita v where v.pedido.profissional.id = :idConsultar and v.status_Visita =:STATUS_VISITA order by v.dataVisita asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_VISITA", Status_Visita.APROVADA);

        return query.getResultList();

    }

    public List<Visita> verificarDataHoraVisita(Date dataVisita, Date horaVisita) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select v from Visita v where v.dataVisita =:dataVisita and v.horaVisita =:horaVisita");


        //retorno da lista de profissionais
        query.setParameter("dataVisita", dataVisita);
        query.setParameter("horaVisita", horaVisita);

        return query.getResultList();

    }

    public List<Visita> buscarVisitasCanceladaProfissional(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select v from Visita v where v.pedido.profissional.id = :idConsultar and v.status_Visita =:STATUS_VISITA order by v.dataVisita asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_VISITA", Status_Visita.CANCELADA);

        return query.getResultList();

    }

    public List<Visita> buscarVisitasReprovadaProfissional(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select v from Visita v where v.pedido.profissional.id = :idConsultar and v.status_Visita =:STATUS_VISITA order by v.dataVisita asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_VISITA", Status_Visita.REPROVADA);

        return query.getResultList();

    }

    public List<Visita> buscarVisitasEmEsperaProfissional(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select v from Visita v where v.pedido.profissional.id = :idConsultar and v.status_Visita =:STATUS_VISITA order by v.dataVisita asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_VISITA", Status_Visita.EM_ESPERA);

        return query.getResultList();

    }

    public List<Visita> buscarPedidosVisitasClientes(int idConsultar) throws Exception {
        //passar o parametro para a query
        TypedQuery<Visita> query = manager.createNamedQuery("Visita.buscarPedidosVisitasClientes", Visita.class);

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_PEDIDO", Status_Pedido.EM_ESPERA);
        query.setParameter("STATUS_VISITA", Status_Visita.CANCELADA);

        return query.getResultList();
    }
}
