/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import Model.Pedido;
import Model.Status_Pedido;
import Util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author brenda
 */
public class PedidoDAO {

    public PedidoDAO() {

    }

    Pedido pedido = new Pedido();

    private static PedidoDAO instance;
    EntityManager manager = new JPAUtil().getEntityManager();

    public static PedidoDAO getInstance() {
        if (instance == null) {
            instance = new PedidoDAO();
        }

        return instance;
    }

    public Pedido consultarPorId(int id) {

        Pedido pedido = null;
        try {
            //Consulta um pedido pelo seu ID.
            pedido = manager.find(Pedido.class, id);
        } finally {
            manager.close();
        }
        return pedido;
    }

    public Pedido getById(final int id) {
        return manager.find(Pedido.class, id);
    }

    public void cadastrar(Pedido pedido) {

        try {
            manager.getTransaction().begin();
            manager.persist(pedido);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void atualizar(Pedido pedido) {

        try {
            manager.getTransaction().begin();
            manager.merge(pedido);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void remover(Pedido pedido) {

        try {
            manager.getTransaction().begin();
            pedido = manager.find(Pedido.class, pedido.getId());
            manager.remove(pedido);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void removerPorId(final int id) {

        try {
            Pedido pedido = getById(id);
            remover(pedido);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public List<Pedido> buscarPedidosProfissional(int idConsultarPedidosProfissional) throws Exception {
        Query query = manager
                .createQuery("select p from Pedido p where p.profissional.id =:idConsultarPedidosProfissional");

        //retorno da lista de profissionais
        query.setParameter("idConsultarPedidosProfissional", idConsultarPedidosProfissional);

        return query.getResultList();
    }

    public List<Pedido> buscarPedidosEmEsperaProfissional(int idConsultarPedidosEmEsperaProfissional) throws Exception {
        Query query = manager
                .createQuery("select p from Pedido p where p.profissional.id =:idConsultarPedidosEmEsperaProfissional and p.status_Pedido =:STATUS_PEDIDO");

        //retorno da lista de profissionais
        query.setParameter("idConsultarPedidosEmEsperaProfissional", idConsultarPedidosEmEsperaProfissional);
        query.setParameter("STATUS_PEDIDO", Status_Pedido.EM_ESPERA);

        return query.getResultList();
    }

    public List<Pedido> buscarPedidosAprovadosProfissional(int idConsultarPedidosApProfissional) throws Exception {
        Query query = manager
                .createQuery("select p from Pedido p where p.profissional.id =:idConsultarPedidosApProfissional and p.status_Pedido =:STATUS_PEDIDO");

        //retorno da lista de profissionais
        query.setParameter("idConsultarPedidosApProfissional", idConsultarPedidosApProfissional);
        query.setParameter("STATUS_PEDIDO", Status_Pedido.APROVADO);

        return query.getResultList();
    }

    public List<Pedido> buscarPedidosReprovadosProfissional(int idConsultarPedidosRepProfissional) throws Exception {
        Query query = manager
                .createQuery("select p from Pedido p where p.profissional.id =:idConsultarPedidosRepProfissional and p.status_Pedido =:STATUS_PEDIDO");

        //retorno da lista de profissionais
        query.setParameter("idConsultarPedidosRepProfissional", idConsultarPedidosRepProfissional);
        query.setParameter("STATUS_PEDIDO", Status_Pedido.REPROVADO);

        return query.getResultList();
    }

    public List<Pedido> buscarPedidosClientes(int idConsultar) throws Exception {
        //passar o parametro para a query
        TypedQuery<Pedido> query = manager.createNamedQuery("Pedido.buscarPedidosCliente", Pedido.class);

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);

        return query.getResultList();
    }

    public List<Pedido> buscarPedidosAprovadosClientes(int idConsultarAp) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select p from Pedido p where p.cliente.id =:idConsultarAp and p.status_Pedido =:STATUS_PEDIDO");

        //retorno da lista de profissionais
        query.setParameter("idConsultarAp", idConsultarAp);
        query.setParameter("STATUS_PEDIDO", Status_Pedido.APROVADO);

        return query.getResultList();
    }

    public List<Pedido> buscarPedidosReprovadosClientes(int idConsultarRep) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select p from Pedido p where p.cliente.id =:idConsultarRep and p.status_Pedido =:STATUS_PEDIDO");

        //retorno da lista de profissionais
        query.setParameter("idConsultarRep", idConsultarRep);
        query.setParameter("STATUS_PEDIDO", Status_Pedido.REPROVADO);

        return query.getResultList();
    }

    public List<Pedido> buscarPedidosEmEsperaClientes(int idConsultarEE) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select p from Pedido p where p.cliente.id =:idConsultarEE and p.status_Pedido =:STATUS_PEDIDO");

        //retorno da lista de profissionais
        query.setParameter("idConsultarEE", idConsultarEE);
        query.setParameter("STATUS_PEDIDO", Status_Pedido.EM_ESPERA);

        return query.getResultList();
    }

    public Pedido consultarPedidoPorIdCliente(int p) {
        Query query = manager
                .createQuery("select p from Pedido p where p.cliente.id =:pId");

        query.setParameter("pId", p);

        Pedido ped = (Pedido) query.getSingleResult();

        return ped;
    }

    @SuppressWarnings("unchecked")
    public List<Pedido> buscarTodos() {
        return manager.createQuery("FROM " + Pedido.class.getName()).getResultList();
    }

}
