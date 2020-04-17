/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author brenda
 */
import Model.Status_Orcamento;
import Model.Avaliacao;
import Model.Profissional;
import Model.Status_Avaliacao;
import Model.Status_Servico;
import Model.Usuario;
import Util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class AvaliacaoDAO {

    public AvaliacaoDAO() {

    }

    Avaliacao avaliacao = new Avaliacao();

    private static AvaliacaoDAO instance;
    EntityManager manager = new JPAUtil().getEntityManager();

    public static AvaliacaoDAO getInstance() {
        if (instance == null) {
            instance = new AvaliacaoDAO();
        }

        return instance;
    }

    public Avaliacao consultarPorId(int id) {

        Avaliacao categoria = null;
        try {
            //Consulta um profissional pelo seu ID.
            categoria = manager.find(Avaliacao.class, id);
        } finally {
            manager.close();
        }
        return categoria;
    }

    public Avaliacao getById(final int id) {
        return manager.find(Avaliacao.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Avaliacao> buscarTodos() {
        return manager.createQuery("FROM " + Avaliacao.class.getName()).getResultList();
    }

    public void cadastrar(Avaliacao avaliacao) {

        try {
            manager.getTransaction().begin();
            manager.persist(avaliacao);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void atualizar(Avaliacao avaliacao) {

        try {
            manager.getTransaction().begin();
            manager.merge(avaliacao);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void remover(Avaliacao avaliacao) {

        try {
            manager.getTransaction().begin();
            avaliacao = manager.find(Avaliacao.class, avaliacao.getId());
            manager.remove(avaliacao);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            manager.getTransaction().rollback();
        }
    }

    public void removerPorId(final int id) {

        try {
            Avaliacao avaliacao = getById(id);
            remover(avaliacao);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Avaliacao> buscarAvaliacoesCliente(int idConsultar) throws Exception {
        Query query = manager
                .createQuery("select a from Avaliacao a where a.agenda.orcamento.pedido.cliente.id = :idConsultar order by a.agenda.dataFinalizacao asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);

        return query.getResultList();
    }

    public List<Avaliacao> buscarPedidosAvaliadosCliente(int idConsultar) throws Exception {
        Query query = manager
                .createQuery("select a from Avaliacao a where a.agenda.orcamento.pedido.cliente.id = :idConsultar and a.status =:STATUS order by a.dataPublicacao asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS", Status_Avaliacao.AVALIADO);

        return query.getResultList();
    }

    public List<Avaliacao> buscarTodasAvaliacoesProfissional(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from Avaliacao a where a.agenda.orcamento.pedido.profissional.id = :idConsultar order by a.dataPublicacao asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);

        return query.getResultList();
    }

    public List<Avaliacao> buscarAvaliadosProfissional(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from Avaliacao a where a.agenda.orcamento.pedido.profissional.id = :idConsultar and a.status =:STATUS_AVALIACAO order by a.dataPublicacao asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_AVALIACAO", Status_Avaliacao.AVALIADO);

        return query.getResultList();
    }

    public List<Avaliacao> buscarAvaliacoesPendentesProfissional(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from Avaliacao a where a.agenda.orcamento.pedido.profissional.id = :idConsultar and a.status =:STATUS_AVALIACAO order by a.dataPublicacao asc");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_AVALIACAO", Status_Avaliacao.PENDENTE);

        return query.getResultList();
    }

    public List<Avaliacao> buscarAgendamentosSemAvaliacaoCliente(int idConsultar) throws Exception {
        //passar o parametro para a query
        Query query = manager
                .createQuery("select a from Avaliacao a where a.agenda.orcamento.pedido.cliente.id = :idConsultar and a.status =:STATUS_AVALIACAO");

        //retorno da lista de profissionais
        query.setParameter("idConsultar", idConsultar);
        query.setParameter("STATUS_AVALIACAO", Status_Avaliacao.PENDENTE);

        return query.getResultList();
    }

    /**
     * Realiza a media de avaliacao geral por profissional
     *
     * @param idConsultar
     * @return
     * @throws Exception
     *
     */
//    public double buscarMediaAvGeral(int idConsultar) throws Exception {
//
//        Query query = manager
//                .createQuery("select ROUND(AVG(a.avaliacaoGeral),2) from Avaliacao a where a.agenda.orcamento.pedido.profissional.id =:idConsultar GROUP BY a.agenda.orcamento.pedido.profissional.id ");
//
//        query.setParameter("idConsultar", idConsultar);
//
//        return (double) query.getSingleResult();
//    }
//
//    /**
//     * Realiza a media de avaliacao custo por profissional
//     *
//     * @param idConsultar
//     * @param idPedido
//     * @return
//     * @throws Exception
//     *
//     */
//    public double buscarMediaAvCusto(int idConsultar, int idPedido) throws Exception {
//
//        Query query = manager
//                .createQuery("select ROUND(AVG(a.avaliacaoCusto),2) from Avaliacao a where a.agenda.orcamento.pedido.profissional.id =:idConsultar and a.agenda.orcamento.pedido.id =:idPedido");
//
//        query.setParameter("idConsultar", idConsultar);
//        query.setParameter("idPedido", idPedido);
//        
//
//        return (double) query.getSingleResult();
//    }
//
//    /**
//     * Realiza a media de avaliacao rapidez por profissional
//     *
//     * @param idConsultar
//     * @return
//     * @throws Exception
//     *
//     */
//    public double buscarMediaAvRapidez(int idConsultar) throws Exception {
//
//        Query query = manager
//                .createQuery("select ROUND(AVG(a.avaliacaoRapidez),2) from Avaliacao a where a.agenda.orcamento.pedido.profissional.id =:idConsultar GROUP BY a.agenda.orcamento.pedido.profissional.id ");
//
//        query.setParameter("idConsultar", idConsultar);
//
//        return (double) query.getSingleResult();
//    }

    /**
     * Consulta avaliacao por id do Usuario
     *
     * @param idConsultar
     * @return
     * @throws Exception
     *
     */
    public Avaliacao consultarAvaliacaoUsuarioId(int u) {
        Query query = manager
                .createQuery("select a from Avaliacao a where a.agenda.orcamento.pedido.profissional.id =:pId");

        query.setParameter("pId", u);

        return (Avaliacao) query.getSingleResult();
    }

    /**
     * Consulta todas avaliacoes por id profissional
     *
     * @param idConsultar
     * @return
     * @throws Exception
     *
     */
    public Avaliacao consultarAvaliacaoProfissionais(Usuario u) {
        Query query = manager
                .createQuery("select a from Avaliacao a where a.agenda.orcamento.pedido.profissional.ido =:pId");

        query.setParameter("pId", u);

        return (Avaliacao) query.getSingleResult();
    }
}
