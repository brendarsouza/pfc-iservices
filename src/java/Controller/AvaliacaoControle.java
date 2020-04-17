/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AgendaDeServicoDAO;
import DAO.AvaliacaoDAO;
import DAO.ClienteDAO;
import DAO.LogsDAO;
import DAO.PedidoDAO;
import DAO.ProfissionalDAO;
import DAO.UsuarioDAO;
import Model.AgendaDeServico;
import Model.Status_Orcamento;
import Model.Avaliacao;
import Model.Cliente;
import Model.Logs;
import Model.Profissional;
import Model.Status_Avaliacao;
import Model.Status_Servico;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author brenda
 */
@WebServlet(name = "AvaliacaoControle", urlPatterns = {"/AvaliacaoControle", "/avaliarServico", "/buscarPedidosAvaliadosCliente", "/avaliarServicoRecebido", "/buscarAvaliacoesCliente", "/consultarNaoAvaliados", "/buscarTodasAvaliacoesProfissional", "/buscarAvaliadosProfissional", "/buscarAvaliacoesPendentesProfissional"})
public class AvaliacaoControle extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.equals(request.getContextPath() + "/buscarAvaliacoesCliente")) {
            try {
                buscarAvaliacoesCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else if (uri.equals(request.getContextPath() + "/buscarPedidosAvaliadosCliente")) {
            try {
                buscarPedidosAvaliadosCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (uri.equals(request.getContextPath() + "/consultarNaoAvaliados")) {
            try {
                consultarNaoAvaliados(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/buscarTodasAvaliacoesProfissional")) {
            try {
                buscarTodasAvaliacoesProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else if (uri.equals(request.getContextPath() + "/buscarAvaliadosProfissional")) {
            try {
                buscarAvaliadosProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else if (uri.equals(request.getContextPath() + "/buscarAvaliacoesPendentesProfissional")) {
            try {
                buscarAvaliacoesPendentesProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("idAvaliacao") != null) {
            AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
            AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
            
            request.setAttribute("avaliacao", avaliacaoDAO.getById(Integer.parseInt(request.getParameter("idAvaliacao"))));
            /* direciona para a página de edição de cadastro*/
            request.getRequestDispatcher("Avaliacao/AvaliarServicoCliente.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
 
        if (uri.equals(request.getContextPath() + "/avaliarServicoRecebido")) {
            try {
                avaliarServicoRecebido(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AvaliacaoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    public void avaliarServicoRecebido(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, ServletException, Exception {

        Cliente cliente = new Cliente();
        AgendaDeServico agenda = new AgendaDeServico();
        int idAvaliacao = Integer.parseInt(request.getParameter("txtIdAvaliacao"));
        
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        
        Avaliacao avaliacao = avaliacaoDAO.getById(idAvaliacao);
        cliente.setId(Integer.parseInt(request.getParameter("txtIdCliente")));
        agenda.setId(Integer.parseInt(request.getParameter("txtIdAgenda")));
        avaliacao.setAgenda(agenda);
        Date dataPublicacao = new Date();
        avaliacao.setDataPublicacao(dataPublicacao);
        avaliacao.setComentario(request.getParameter("txtComentario"));
        int avaliacaoCusto = Integer.parseInt(request.getParameter("custo"));
        int avaliacaoGeral = Integer.parseInt(request.getParameter("geral"));
        int avaliacaoRapidez = Integer.parseInt(request.getParameter("rapidez"));
        avaliacao.setAvaliacaoGeral(avaliacaoGeral);
        avaliacao.setAvaliacaoRapidez(avaliacaoRapidez);
        avaliacao.setAvaliacaoCusto(avaliacaoCusto);
        avaliacao.setStatus(Status_Avaliacao.AVALIADO);

        Profissional profissional = new Profissional();
        ProfissionalDAO profissionalDAO = new ProfissionalDAO();

        int idProfissional = Integer.parseInt(request.getParameter("txtIdProfissional"));
        profissional.setId(idProfissional);
        avaliacaoDAO.atualizar(avaliacao);
        Profissional profissionalRecuperado = profissionalDAO.consultarPorId(idProfissional);
        
        int idPedido = Integer.parseInt(request.getParameter("txtIdPedido"));
        double mediaAvCusto = profissional.calcularMediaAvCusto(profissionalRecuperado.getMediaAvaliacaoCusto(), avaliacaoCusto);
        double mediaAvGeral = profissional.calcularMediaAvGeral(profissionalRecuperado.getMediaAvaliacaoGeral(), avaliacaoGeral);
        double mediaAvRapidez = profissional.calcularMediaAvRapidez(profissionalRecuperado.getMediaAvaliacaoRapidez(), avaliacaoRapidez);
        profissionalRecuperado.setMediaAvaliacaoCusto(mediaAvCusto);
        profissionalRecuperado.setMediaAvaliacaoGeral(mediaAvGeral);
        profissionalRecuperado.setMediaAvaliacaoRapidez(mediaAvRapidez);

        profissionalDAO.atualizar(profissionalRecuperado);

        response.sendRedirect("Cliente/AvaliacaoConfirmada.jsp");

        int idUsuario = avaliacao.getAgenda().getOrcamento().getPedido().getCliente().getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Avaliação");
        logs1.setDescricaoEvento("O cliente realizou a avaliação do serviço.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);

    }

    public void buscarAvaliacoesCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");


        
        request.setAttribute("avaliacao", avaliacaoDAO.buscarAvaliacoesCliente(cliente.getId()));
//        request.setAttribute("pedido", pedidoDAO.buscarPedidosClientes(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarAvaliacoes.jsp").forward(request, response);

//        ClienteDAO clienteDAO = new ClienteDAO();
//        Cliente cliente = clienteDAO.getById(idConsultar);
        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Avaliação");
        logs1.setDescricaoEvento("O cliente buscou todas as avaliações.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }
    
    public void buscarPedidosAvaliadosCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");


        
        request.setAttribute("avaliacao", avaliacaoDAO.buscarPedidosAvaliadosCliente(cliente.getId()));
//        request.setAttribute("pedido", pedidoDAO.buscarPedidosClientes(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarPedidosAvaliados.jsp").forward(request, response);

//        ClienteDAO clienteDAO = new ClienteDAO();
//        Cliente cliente = clienteDAO.getById(idConsultar);
        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Avaliação");
        logs1.setDescricaoEvento("O cliente buscou todas as avaliações.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void buscarTodasAvaliacoesProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");

        
        request.setAttribute("avaliacao", avaliacaoDAO.buscarTodasAvaliacoesProfissional(profissional.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarAvaliacoes.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Avaliação");
        logs1.setDescricaoEvento("O profissional buscou todas as avaliações.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void buscarAvaliadosProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");

        
        request.setAttribute("avaliacao", avaliacaoDAO.buscarAvaliadosProfissional(profissional.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarAvaliados.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Avaliação");
        logs1.setDescricaoEvento("O profissional buscou todas os pedidos avaliados.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void buscarAvaliacoesPendentesProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");

        
        request.setAttribute("avaliacao", avaliacaoDAO.buscarAvaliacoesPendentesProfissional(profissional.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarAvaliacoesPendentes.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Avaliação");
        logs1.setDescricaoEvento("O profissional buscou todas as avaliações pendentes.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }
    
    public void consultarNaoAvaliados(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

        
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        request.setAttribute("avaliacao", avaliacaoDAO.buscarAgendamentosSemAvaliacaoCliente(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarServicosSemAvaliacao.jsp").forward(request, response);

//        ClienteDAO clienteDAO = new ClienteDAO();
//        Cliente cliente = clienteDAO.getById(idConsultar);
        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Avaliação");
        logs1.setDescricaoEvento("O cliente buscou todas os pedidos não avaliados.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

}
