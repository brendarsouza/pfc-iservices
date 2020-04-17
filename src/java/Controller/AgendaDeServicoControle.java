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
import DAO.OrcamentoDAO;
import DAO.PedidoDAO;
import DAO.ProfissionalDAO;
import DAO.UsuarioDAO;
import DAO.VisitaDAO;
import Model.AgendaDeServico;
import Model.Status_Orcamento;
import Model.Avaliacao;
import Model.Cliente;
import Model.Logs;
import Model.Orcamento;
import Model.Pedido;
import Model.PerfilAcesso;
import Model.Profissional;
import Model.Status_Avaliacao;
import Model.Status_Pedido;
import Model.Status_Servico;
import Model.Status_Visita;
import Model.Usuario;
import Model.Visita;
import Util.Email;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author brenda
 */
@WebServlet(name = "AgendaDeServicoControle", urlPatterns = {"/AgendaDeServicoControle", "/consultarAgendaAJAX", "/consultarReagendadosCliente", "/consultarReagendadosProfissional", "/consultarAgendaCliente", "/consultarAgendaConcluidaCliente", "/consultarAgendaEmEsperaCliente", "/consultarAgendaCanceladaCliente", "/consultarAgendaProfissional", "/cancelarAgendamento", "/aceitarAgendamento", "/aceitarAgendamentoProfissional", "/recusarAgendamento", "/recusarAgendamentoProfissional", "/cancelarAgendamentoProfissional", "/finalizarPedido", "/consultarAgendaConcluidaProfissional", "/consultarAgendaEmEsperaProfissional", "/consultarAgendaCanceladaProfissional"})
public class AgendaDeServicoControle extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String uri = request.getRequestURI();
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
        VisitaDAO visitaDAO = new VisitaDAO();
        if (uri.equals(request.getContextPath() + "/consultarAgendaCliente")) {
            try {
                consultarAgendaCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarReagendadosCliente")) {
            try {
                consultarReagendadosCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarAgendaConcluidaCliente")) {
            try {
                consultarAgendaConcluidaCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarAgendaEmEsperaCliente")) {
            try {
                consultarAgendaEmEsperaCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarAgendaCanceladaCliente")) {
            try {
                consultarAgendaCanceladaCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarAgendaProfissional")) {
            try {
                consultarAgendaProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarAgendaConcluidaProfissional")) {
            try {
                consultarAgendaConcluidaProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarAgendaAJAX")) {
            try {
                consultarAgendaAJAX(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarAgendaCanceladaProfissional")) {
            try {
                consultarAgendaCanceladaProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarReagendadosProfissional")) {
            try {
                consultarReagendadosProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.equals(request.getContextPath() + "/consultarAgendaEmEsperaProfissional")) {
            try {
                consultarAgendaEmEsperaProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("idCancelarAgendamento") != null) {
            request.setAttribute("agenda", agendaDAO.getById(Integer.parseInt(request.getParameter("idCancelarAgendamento"))));
            request.getRequestDispatcher("Agenda/ConfirmarCancelamentoAgendaCliente.jsp").forward(request, response);

        } else if (request.getParameter("idCancelarAgendamentoProfissional") != null) {
            request.setAttribute("agenda", agendaDAO.getById(Integer.parseInt(request.getParameter("idCancelarAgendamentoProfissional"))));
            request.getRequestDispatcher("Agenda/ConfirmarCancelamentoAgendaProfissional.jsp").forward(request, response);

        } else if (request.getParameter("idRecusarAgendamento") != null) {
            request.setAttribute("agenda", agendaDAO.getById(Integer.parseInt(request.getParameter("idRecusarAgendamento"))));
            request.getRequestDispatcher("Agenda/ConfirmarRecusaAgendaCliente.jsp").forward(request, response);
            
        } else if (request.getParameter("idRecusarAgendamentoProfissional") != null) {
            request.setAttribute("agenda", agendaDAO.getById(Integer.parseInt(request.getParameter("idRecusarAgendamentoProfissional"))));
            request.getRequestDispatcher("Agenda/ConfirmarRecusaAgendaProfissional.jsp").forward(request, response);

        } else if (request.getParameter("idAceitarAgendamento") != null) {
            request.setAttribute("agenda", agendaDAO.getById(Integer.parseInt(request.getParameter("idAceitarAgendamento"))));
            request.getRequestDispatcher("Agenda/ConfirmarAceiteAgendaCliente.jsp").forward(request, response);
        } else if (request.getParameter("idAceitarAgendamentoProfissional") != null) {
            request.setAttribute("agenda", agendaDAO.getById(Integer.parseInt(request.getParameter("idAceitarAgendamentoProfissional"))));
            request.getRequestDispatcher("Agenda/ConfirmarAceiteAgendaProfissional.jsp").forward(request, response);

        } else if (request.getParameter("idFinalizar") != null) {

            request.setAttribute("agenda", agendaDAO.getById(Integer.parseInt(request.getParameter("idFinalizar"))));
            /* direciona para a página com o formulário para realizar o pedido*/
            request.getRequestDispatcher("Agenda/FinalizarPedidoProfissional.jsp").forward(request, response);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String uri = request.getRequestURI();
        if (uri.equals(request.getContextPath() + "/cancelarAgendamento")) {
            try {
                cancelarAgendamento(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/cancelarAgendamentoProfissional")) {
            try {
                cancelarAgendamentoProfissional(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/recusarAgendamento")) {
            try {
                recusarAgendamento(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else if (uri.equals(request.getContextPath() + "/recusarAgendamentoProfissional")) {
            try {
                recusarAgendamentoProfissional(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/aceitarAgendamento")) {
            try {
                aceitarAgendamento(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/aceitarAgendamentoProfissional")) {
            try {
                aceitarAgendamentoProfissional(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/finalizarPedido")) {
            try {
                finalizarPedido(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void consultarAgendaCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
//        int idConsultar = Integer.parseInt(request.getParameter("idConsultar"));
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        request.setAttribute("agenda", agendaDAO.buscarAgendamentosCliente(cliente.getId()));
        request.setAttribute("avaliacao", avaliacaoDAO.buscarAgendamentosSemAvaliacaoCliente(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarAgenda.jsp").forward(request, response);

//        ClienteDAO clienteDAO = new ClienteDAO();
//        Cliente cliente = clienteDAO.getById(idConsultar);
        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Agenda");
        logs1.setDescricaoEvento("O cliente realizou uma busca de todos seus agendamentos.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarAgendaConcluidaCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();

        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

        request.setAttribute("agenda", agendaDAO.buscarAgendamentosConcluidosCliente(cliente.getId()));
        request.setAttribute("avaliacao", avaliacaoDAO.buscarAgendamentosSemAvaliacaoCliente(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarAgendaConcluida.jsp").forward(request, response);

        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Agenda");
        logs1.setDescricaoEvento("O cliente realizou uma busca de todos seus agendamentos concluídos.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarAgendaEmEsperaCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
//        int idConsultar = Integer.parseInt(request.getParameter("idConsultar"));
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        request.setAttribute("agenda", agendaDAO.buscarAgendamentosEmEsperaCliente(cliente.getId()));
//        request.setAttribute("avaliacao", avaliacaoDAO.buscarAgendamentosSemAvaliacaoCliente(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarAgendaEmEspera.jsp").forward(request, response);

//        ClienteDAO clienteDAO = new ClienteDAO();
//        Cliente cliente = clienteDAO.getById(idConsultar);
        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Agenda");
        logs1.setDescricaoEvento("O cliente realizou uma busca de todos os agendamentos em espera.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarAgendaCanceladaCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
//        PedidoDAO pedidoDAO = new PedidoDAO();
//        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
//        int idConsultar = Integer.parseInt(request.getParameter("idConsultar"));
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        request.setAttribute("agenda", agendaDAO.buscarAgendamentosCanceladosCliente(cliente.getId()));
//        request.setAttribute("avaliacao", avaliacaoDAO.buscarAgendamentosSemAvaliacaoCliente(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarAgendaCancelada.jsp").forward(request, response);

//        ClienteDAO clienteDAO = new ClienteDAO();
//        Cliente cliente = clienteDAO.getById(idConsultar);
        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Agenda");
        logs1.setDescricaoEvento("O cliente realizou uma busca de todos os agendamentos cancelados.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarReagendadosCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();

        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        request.setAttribute("agenda", agendaDAO.buscarAgendamentosReagendadosCliente(cliente.getId()));
//        request.setAttribute("avaliacao", avaliacaoDAO.buscarAgendamentosSemAvaliacaoCliente(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarPedidosReagendados.jsp").forward(request, response);

//        ClienteDAO clienteDAO = new ClienteDAO();
//        Cliente cliente = clienteDAO.getById(idConsultar);
        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Agenda");
        logs1.setDescricaoEvento("O cliente realizou uma busca de todos os pedidos reagendados.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarAgendaProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();

        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");

        request.setAttribute("agenda", agendaDAO.buscarAgendamentosProfissional(profissional.getId()));

        request.getRequestDispatcher("Profissional/ListarAgenda.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Agenda");
        logs1.setDescricaoEvento("O profissional realizou uma busca de todos seus agendamentos.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarReagendadosProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();

        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");

        request.setAttribute("agenda", agendaDAO.buscarAgendamentosReagendadosProfissional(profissional.getId()));

        request.getRequestDispatcher("Profissional/ListarPedidosReagendados.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Agenda");
        logs1.setDescricaoEvento("O profissional realizou uma busca de todos os agendamentos reagendados.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarAgendaConcluidaProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();

        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");

        request.setAttribute("agenda", agendaDAO.buscarAgendamentosConcluidosProfissional(profissional.getId()));

        request.getRequestDispatcher("Profissional/ListarAgendaConcluida.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Agenda");
        logs1.setDescricaoEvento("O profissional realizou uma busca de todos seus agendamentos concluídos.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarAgendaCanceladaProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();

        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");

        request.setAttribute("agenda", agendaDAO.buscarAgendamentosCanceladosProfissional(profissional.getId()));

        request.getRequestDispatcher("Profissional/ListarAgendaCancelada.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Agenda");
        logs1.setDescricaoEvento("O profissional realizou uma busca de todos seus agendamentos cancelados.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarAgendaEmEsperaProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();

        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");

        request.setAttribute("agenda", agendaDAO.buscarAgendamentosEmEsperaProfissional(profissional.getId()));

        request.getRequestDispatcher("Profissional/ListarAgendaEmEspera.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Agenda");
        logs1.setDescricaoEvento("O profissional realizou uma busca de todos seus agendamentos em espera.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void cancelarAgendamento(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
        int idAgenda = Integer.parseInt(request.getParameter("txtIdAgenda"));
        AgendaDeServico agenda = agendaDAO.getById(idAgenda);

        String novaDataAgendamento = request.getParameter("txtDataAgendamento");
        String novoHoraAgendamento = request.getParameter("txtHoraAgendamento");
        Date dataAtual = new Date();
        agenda.setDataFinalizacao(dataAtual);
        agenda.setObservacao(request.getParameter("txtObservacao"));

        if (!"".equals(novaDataAgendamento) && !"".equals(novoHoraAgendamento)) {
            Date dataAgendamento = new SimpleDateFormat("yyyy-MM-dd").parse(novaDataAgendamento);
            Date horarioAtual = new Date();
            boolean data;
            if (dataAtual.before(dataAgendamento) || dataAtual.equals(dataAgendamento)) {
                data = true;
                System.out.println("Correto! A data da visita está após da data atual!");
                agenda.setDataAgendamento(dataAgendamento);
                Date horaAgendamento = new SimpleDateFormat("HH:mm").parse(novoHoraAgendamento);
                boolean horaVisita;

                if ((horaAgendamento.after(agenda.getOrcamento().getPedido().getHorarioInicialDisponivel())) || (horaAgendamento.equals(agenda.getOrcamento().getPedido().getHorarioInicialDisponivel())) && (horaAgendamento.before(agenda.getOrcamento().getPedido().getHorarioFinalDisponivel()))) {
                    agenda.setHoraAgendamento(horaAgendamento);
                    agenda.setStatus_servico(Status_Servico.REAGENDADO);
                    OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
                    int idOrcamento = Integer.parseInt(request.getParameter("txtIdOrcamento"));
                    Orcamento orcamento = orcamentoDAO.getById(idOrcamento);
                    agenda.setOrcamento(orcamento);
                    orcamentoDAO.atualizar(orcamento);
                    agendaDAO.atualizar(agenda);

                    //        Instânciando o objeto email 
                    Email email = new Email();

                    email.setNomeDestinatario(agenda.getOrcamento().getPedido().getCliente().getNome());
                    email.setEmailDestinatario(agenda.getOrcamento().getPedido().getCliente().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
                    email.setAssunto("ISERVICES - Agendamento Cancelado");

                    email.setMensagem("O agendamento que estava marcado foi cancelado."
                            + "\nNúmero do Pedido: " + agenda.getOrcamento().getPedido().getId()
                            + "\nNome do Profissional " + agenda.getOrcamento().getPedido().getProfissional().getNome()
                            + "\nNome do Cliente " + agenda.getOrcamento().getPedido().getCliente().getNome()
                            + "\nNova data solicitada "
                            + "\nData: " + agenda.getDataAgendamento()
                            + "\nHorário: " + agenda.getHoraAgendamento()
                            + "\nISERVICES. "
                    );

//        envia o email
                    if (email.enviar()) {
                        response.getWriter().println("Enviado com sucesso");
                    } else {
                        response.getWriter().println("Nao enviou");
                    }
                    Email email2 = new Email();

                    email2.setNomeDestinatario(agenda.getOrcamento().getPedido().getProfissional().getNome());
                    email2.setEmailDestinatario(agenda.getOrcamento().getPedido().getProfissional().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
                    email2.setAssunto("ISERVICES - Agendamento Cancelado");

                    email2.setMensagem("O agendamento que estava marcada foi cancelado."
                            + "\nNúmero do Pedido: " + agenda.getOrcamento().getPedido().getId()
                            + "\nNome do Profissional " + agenda.getOrcamento().getPedido().getProfissional().getNome()
                            + "\nNome do Cliente " + agenda.getOrcamento().getPedido().getCliente().getNome()
                            + "\nNova data solicitada "
                            + "\nData: " + agenda.getDataAgendamento()
                            + "\nHorário: " + agenda.getHoraAgendamento()
                            + "\nISERVICES. "
                    );

//        envia o email
                    if (email2.enviar()) {
                        response.getWriter().println("Enviado com sucesso");
                    } else {
                        response.getWriter().println("Nao enviou");
                    }
                    request.setAttribute("agenda", agenda);

                    response.sendRedirect("Agenda/CancelamentoConfirmadoCliente.jsp");

                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    Usuario usuario = usuarioDAO.getById(agenda.getOrcamento().getPedido().getCliente().getId());
                    Logs logs1 = new Logs();

                    logs1.setUsuario(usuario);
                    Date dataLog1 = new Date();
                    logs1.setData(dataLog1);
                    logs1.setEvento("Agenda");
                    logs1.setDescricaoEvento("O cliente cancelou o agendamento.");
                    LogsDAO logsDAO = new LogsDAO();
                    logsDAO.cadastrar(logs1);
                } else {

                    horaVisita = false;
                    request.setAttribute("agenda", agenda);
                    response.sendRedirect("Erro/ErroHorarioPedido.jsp");
                }

            } else if (dataAtual.after(dataAgendamento)) {
                data = true;
                System.out.println("Erro! É impossível solicitar um pedido antes da data atual.");
                response.sendRedirect("Erro/ErroDataPedido.jsp");
            } else {
                System.out.println("Formato incorreto");
            }
        } else {
            agenda.setStatus_servico(Status_Servico.CANCELADO);

            OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
            int idOrcamento = Integer.parseInt(request.getParameter("txtIdOrcamento"));
            Orcamento orcamento = orcamentoDAO.getById(idOrcamento);
            orcamento.setStatus_Orcamento(Status_Orcamento.REPROVADO);
            agenda.setOrcamento(orcamento);
            orcamentoDAO.atualizar(orcamento);
            agendaDAO.atualizar(agenda);
            System.out.println("A visita foi cancelada e não foi reagendada.");

            request.setAttribute("agenda", agenda);

            response.sendRedirect("Agenda/CancelamentoConfirmadoCliente.jsp");

            int idUsuario = Integer.parseInt(request.getParameter("txtIdUsuario"));

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.getById(idUsuario);
            Logs logs1 = new Logs();

            logs1.setUsuario(usuario);
            Date dataLog1 = new Date();
            logs1.setData(dataLog1);
            logs1.setEvento("Agenda");
            if (usuario.getPerfilAcesso() == PerfilAcesso.CLIENTE) {
                logs1.setDescricaoEvento("O cliente cancelou a visita marcada e não a reagendou.");
            } else if (usuario.getPerfilAcesso() == PerfilAcesso.PROFISSIONAL) {
                logs1.setDescricaoEvento("O profissional cancelou a visita marcada e não a reagendou.");
            }
            LogsDAO logsDAO = new LogsDAO();
            logsDAO.cadastrar(logs1);
        }
    }

    public void cancelarAgendamentoProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
        int idAgenda = Integer.parseInt(request.getParameter("txtIdAgenda"));
        AgendaDeServico agenda = agendaDAO.getById(idAgenda);

        String novaDataAgendamento = request.getParameter("txtDataAgendamento");
        String novoHoraAgendamento = request.getParameter("txtHoraAgendamento");
        Date dataAtual = new Date();
        agenda.setDataFinalizacao(dataAtual);
        agenda.setObservacao(request.getParameter("txtObservacao"));

        if (!"".equals(novaDataAgendamento) && !"".equals(novoHoraAgendamento)) {
            Date dataAgendamento = new SimpleDateFormat("yyyy-MM-dd").parse(novaDataAgendamento);
            Date horarioAtual = new Date();
            boolean data;
            if (dataAtual.before(dataAgendamento) || dataAtual.equals(dataAgendamento)) {
                data = true;
                System.out.println("Correto! A data da visita está após da data atual!");
                agenda.setDataAgendamento(dataAgendamento);
                Date horaAgendamento = new SimpleDateFormat("HH:mm").parse(novoHoraAgendamento);
                boolean horaVisita;

                if ((horaAgendamento.after(agenda.getOrcamento().getPedido().getHorarioInicialDisponivel())) || (horaAgendamento.equals(agenda.getOrcamento().getPedido().getHorarioInicialDisponivel())) && (horaAgendamento.before(agenda.getOrcamento().getPedido().getHorarioFinalDisponivel()))) {
                    agenda.setHoraAgendamento(horaAgendamento);
                    agenda.setStatus_servico(Status_Servico.REAGENDADO);
                    OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
                    int idOrcamento = Integer.parseInt(request.getParameter("txtIdOrcamento"));
                    Orcamento orcamento = orcamentoDAO.getById(idOrcamento);

                    agenda.setOrcamento(orcamento);
                    orcamentoDAO.atualizar(orcamento);
                    agendaDAO.atualizar(agenda);

                    //        Instânciando o objeto email 
                    Email email = new Email();

                    email.setNomeDestinatario(agenda.getOrcamento().getPedido().getCliente().getNome());
                    email.setEmailDestinatario(agenda.getOrcamento().getPedido().getCliente().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
                    email.setAssunto("ISERVICES - Agendamento Cancelado");

                    email.setMensagem("O agendamento que estava marcado foi cancelado."
                            + "\nNúmero do Pedido: " + agenda.getOrcamento().getPedido().getId()
                            + "\nNome do Profissional " + agenda.getOrcamento().getPedido().getProfissional().getNome()
                            + "\nNome do Cliente " + agenda.getOrcamento().getPedido().getCliente().getNome()
                            + "\nNova data solicitada "
                            + "\nData: " + agenda.getDataAgendamento()
                            + "\nHorário: " + agenda.getHoraAgendamento()
                            + "\nISERVICES. "
                    );

//        envia o email
                    if (email.enviar()) {
                        response.getWriter().println("Enviado com sucesso");
                    } else {
                        response.getWriter().println("Nao enviou");
                    }
                    Email email2 = new Email();

                    email2.setNomeDestinatario(agenda.getOrcamento().getPedido().getProfissional().getNome());
                    email2.setEmailDestinatario(agenda.getOrcamento().getPedido().getProfissional().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
                    email2.setAssunto("ISERVICES - Agendamento Cancelado");

                    email2.setMensagem("O agendamento que estava marcada foi cancelado."
                            + "\nNúmero do Pedido: " + agenda.getOrcamento().getPedido().getId()
                            + "\nNome do Profissional " + agenda.getOrcamento().getPedido().getProfissional().getNome()
                            + "\nNome do Cliente " + agenda.getOrcamento().getPedido().getCliente().getNome()
                            + "\nNova data solicitada "
                            + "\nData: " + agenda.getDataAgendamento()
                            + "\nHorário: " + agenda.getHoraAgendamento()
                            + "\nISERVICES. "
                    );

//        envia o email
                    if (email2.enviar()) {
                        response.getWriter().println("Enviado com sucesso");
                    } else {
                        response.getWriter().println("Nao enviou");
                    }
                    request.setAttribute("agenda", agenda);

                    response.sendRedirect("Agenda/CancelamentoConfirmadoProfissional.jsp");

                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    Usuario usuario = usuarioDAO.getById(agenda.getOrcamento().getPedido().getProfissional().getId());
                    Logs logs1 = new Logs();

                    logs1.setUsuario(usuario);
                    Date dataLog1 = new Date();
                    logs1.setData(dataLog1);
                    logs1.setEvento("Agenda");
                    logs1.setDescricaoEvento("O profissional cancelou o agendamento.");
                    LogsDAO logsDAO = new LogsDAO();
                    logsDAO.cadastrar(logs1);
                } else {

                    horaVisita = false;
                    request.setAttribute("agenda", agenda);
                    response.sendRedirect("Erro/ErroHorarioPedido.jsp");
                }

            } else if (dataAtual.after(dataAgendamento)) {
                data = true;
                System.out.println("Erro! É impossível solicitar um pedido antes da data atual.");
                response.sendRedirect("Erro/ErroDataPedido.jsp");
            } else {
                System.out.println("Formato incorreto");
            }
        } else {
            agenda.setStatus_servico(Status_Servico.CANCELADO);

            OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
            int idOrcamento = Integer.parseInt(request.getParameter("txtIdOrcamento"));
            Orcamento orcamento = orcamentoDAO.getById(idOrcamento);
            orcamento.setStatus_Orcamento(Status_Orcamento.REPROVADO);
            agenda.setOrcamento(orcamento);
            orcamentoDAO.atualizar(orcamento);
            agendaDAO.atualizar(agenda);
            System.out.println("A visita foi cancelada e não foi reagendada.");

            request.setAttribute("agenda", agenda);

            response.sendRedirect("Agenda/CancelamentoConfirmadoProfissional.jsp");

            int idUsuario = Integer.parseInt(request.getParameter("txtIdUsuario"));

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.getById(idUsuario);
            Logs logs1 = new Logs();

            logs1.setUsuario(usuario);
            Date dataLog1 = new Date();
            logs1.setData(dataLog1);
            logs1.setEvento("Agenda");
            if (usuario.getPerfilAcesso() == PerfilAcesso.CLIENTE) {
                logs1.setDescricaoEvento("O cliente cancelou a visita marcada e não a reagendou.");
            } else if (usuario.getPerfilAcesso() == PerfilAcesso.PROFISSIONAL) {
                logs1.setDescricaoEvento("O profissional cancelou a visita marcada e não a reagendou.");
            }
            LogsDAO logsDAO = new LogsDAO();
            logsDAO.cadastrar(logs1);
        }
    }

    public void finalizarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
        int idAgenda = Integer.parseInt(request.getParameter("txtIdAgenda"));
        AgendaDeServico agenda = agendaDAO.getById(idAgenda);
        agenda.setStatus_servico(Status_Servico.CONCLUIDO);
        //atribuir a data e hora de finalização do serviço

        Date dataFinalizacao = new Date();
        agenda.setDataFinalizacao(dataFinalizacao);

        agenda.setObservacao(request.getParameter("txtObservacao"));

        Orcamento orcamento = new Orcamento();
        orcamento.setId(Integer.parseInt(request.getParameter("txtIdOrcamento")));
        agenda.setOrcamento(orcamento);

        int idCliente = Integer.parseInt(request.getParameter("txtIdCliente"));
        Cliente cliente = new ClienteDAO().consultarPorId(idCliente);
//        instância o objeto pedidoDAO e faz o cadastro        
        agendaDAO.atualizar(agenda);

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setAgenda(agenda);

        int idProfissional = Integer.parseInt(request.getParameter("txtIdProfissional"));
        ProfissionalDAO profissionalDAO = new ProfissionalDAO();

        Profissional profissional = profissionalDAO.getById(idProfissional);

        avaliacao.setStatus(Status_Avaliacao.PENDENTE);

        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        avaliacaoDAO.cadastrar(avaliacao);

//        Instânciando o objeto email 
        Email email = new Email();

        email.setNomeDestinatario(cliente.getNome());
        email.setEmailDestinatario(cliente.getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
        email.setAssunto("ISERVICES - Foi Finalizado");

        email.setMensagem("O pedido já foi finalizado com sucesso. Faça a avalição do serviço recebido!\nISERVICES. "
        );

//        envia o email
        if (email.enviar()) {
            response.getWriter().println("Enviado com sucesso");
        } else {
            response.getWriter().println("Nao enviou");
        }
        Email email2 = new Email();

        email2.setNomeDestinatario(profissional.getNome());
        email2.setEmailDestinatario(profissional.getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
        email2.setAssunto("ISERVICES - Foi Finalizado");

        email2.setMensagem("O pedido já foi finalizado com sucesso! \nISERVICES. "
        );
        request.setAttribute("agenda", agenda);
        ///criar uma pagina para finalizado o servico com sucesso
        response.sendRedirect("Agenda/PedidoFinalizadoProfissional.jsp");

        int idUsuario = agenda.getOrcamento().getPedido().getProfissional().getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O profissional finalizou o pedido.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);

    }

    public void aceitarAgendamento(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
        int idAgenda = Integer.parseInt(request.getParameter("txtIdAgenda"));

        AgendaDeServico agenda = agendaDAO.getById(idAgenda);

        agenda.setStatus_servico(Status_Servico.EM_ESPERA);

        int idOrcamento = agenda.getOrcamento().getId();

        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        Orcamento orcamento = orcamentoDAO.getById(idOrcamento);

        agenda.setDataAgendamento(orcamento.getDataPrevistaInicio());
        agenda.setHoraAgendamento(orcamento.getHoraVisita());

        orcamento.setStatus_Orcamento(Status_Orcamento.APROVADO);

        orcamentoDAO.atualizar(orcamento);
        agendaDAO.atualizar(agenda);

//        Instânciando o objeto email 
        Email email = new Email();

        email.setNomeDestinatario(agenda.getOrcamento().getPedido().getProfissional().getNome());
        email.setEmailDestinatario(agenda.getOrcamento().getPedido().getProfissional().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
        email.setAssunto("ISERVICES - Agendamento Aceito");

        email.setMensagem("A nova data de agendamento foi aceita pelo Cliente"
                + "\nNúmero do Pedido: " + agenda.getOrcamento().getPedido().getId()
                + "\nNome do Profissional " + agenda.getOrcamento().getPedido().getCliente().getNome()
                + "\nData do Agendamento: " + agenda.getDataAgendamento()
                + " às " + agenda.getHoraAgendamento()
                + "\nISERVICES. "
        );

//        envia o email
        if (email.enviar()) {
            response.getWriter().println("Enviado com sucesso");
        } else {
            response.getWriter().println("Nao enviou");
        }
        request.setAttribute("agenda", agenda);

        response.sendRedirect("Agenda/NovoAgendamentoAceitoCliente.jsp");

        int idUsuario = agenda.getOrcamento().getPedido().getCliente().getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Agenda");
        logs1.setDescricaoEvento("O cliente aceitou o novo agendamento.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void aceitarAgendamentoProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
        int idAgenda = Integer.parseInt(request.getParameter("txtIdAgenda"));

        AgendaDeServico agenda = agendaDAO.getById(idAgenda);

        agenda.setStatus_servico(Status_Servico.EM_ESPERA);

        int idOrcamento = agenda.getOrcamento().getId();

        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        Orcamento orcamento = orcamentoDAO.getById(idOrcamento);

        agenda.setDataAgendamento(orcamento.getDataPrevistaInicio());
        agenda.setHoraAgendamento(orcamento.getHoraVisita());

        orcamento.setStatus_Orcamento(Status_Orcamento.APROVADO);

        orcamentoDAO.atualizar(orcamento);
        agendaDAO.atualizar(agenda);

//        Instânciando o objeto email 
        Email email = new Email();

        email.setNomeDestinatario(agenda.getOrcamento().getPedido().getProfissional().getNome());
        email.setEmailDestinatario(agenda.getOrcamento().getPedido().getProfissional().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
        email.setAssunto("ISERVICES - Agendamento Aceito");

        email.setMensagem("A nova data de agendamento foi aceita pelo Profissional"
                + "\nNúmero do Pedido: " + agenda.getOrcamento().getPedido().getId()
                + "\nNome do Profissional " + agenda.getOrcamento().getPedido().getCliente().getNome()
                + "\nData do Agendamento: " + agenda.getDataAgendamento()
                + " às " + agenda.getHoraAgendamento()
                + "\nISERVICES. "
        );

//        envia o email
        if (email.enviar()) {
            response.getWriter().println("Enviado com sucesso");
        } else {
            response.getWriter().println("Nao enviou");
        }
        request.setAttribute("agenda", agenda);

        response.sendRedirect("Agenda/NovoAgendamentoAceitoProfissional.jsp");

        int idUsuario = agenda.getOrcamento().getPedido().getProfissional().getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Agenda");
        logs1.setDescricaoEvento("O profissional aceitou o novo agendamento.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void recusarAgendamento(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
        int idAgenda = Integer.parseInt(request.getParameter("txtIdAgenda"));

        AgendaDeServico agenda = agendaDAO.getById(idAgenda);

        agenda.setStatus_servico(Status_Servico.CANCELADO);

        int idOrcamento = agenda.getOrcamento().getId();

        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        Orcamento orcamento = orcamentoDAO.getById(idOrcamento);

        orcamento.setStatus_Orcamento(Status_Orcamento.REPROVADO);

        orcamentoDAO.atualizar(orcamento);
        agendaDAO.atualizar(agenda);

//        Instânciando o objeto email 
        Email email = new Email();

        email.setNomeDestinatario(agenda.getOrcamento().getPedido().getProfissional().getNome());
        email.setEmailDestinatario(agenda.getOrcamento().getPedido().getProfissional().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
        email.setAssunto("ISERVICES - Agendamento Cancelado");

        email.setMensagem("A nova data de agendamento foi recusada pelo Cliente, ou seja o pedido está cancelado"
                + "\nNúmero do Pedido: " + agenda.getOrcamento().getPedido().getId()
                + "\nNome do Profissional " + agenda.getOrcamento().getPedido().getCliente().getNome()
                + "\nData do Agendamento: " + agenda.getDataAgendamento()
                + " às " + agenda.getHoraAgendamento()
                + "\nISERVICES. "
        );

//        envia o email
        if (email.enviar()) {
            response.getWriter().println("Enviado com sucesso");
        } else {
            response.getWriter().println("Nao enviou");
        }
        request.setAttribute("agenda", agenda);

        response.sendRedirect("Agenda/NovoAgendamentoRecusadoCliente.jsp");

        int idUsuario = agenda.getOrcamento().getPedido().getCliente().getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Agenda");
        logs1.setDescricaoEvento("O cliente cancelou o novo agendamento.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void recusarAgendamentoProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
        int idAgenda = Integer.parseInt(request.getParameter("txtIdAgenda"));

        AgendaDeServico agenda = agendaDAO.getById(idAgenda);

        agenda.setStatus_servico(Status_Servico.CANCELADO);

        int idOrcamento = agenda.getOrcamento().getId();

        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        Orcamento orcamento = orcamentoDAO.getById(idOrcamento);

        orcamento.setStatus_Orcamento(Status_Orcamento.REPROVADO);

        orcamentoDAO.atualizar(orcamento);
        agendaDAO.atualizar(agenda);

//        Instânciando o objeto email 
        Email email = new Email();

        email.setNomeDestinatario(agenda.getOrcamento().getPedido().getCliente().getNome());
        email.setEmailDestinatario(agenda.getOrcamento().getPedido().getCliente().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
        email.setAssunto("ISERVICES - Agendamento Cancelado");

        email.setMensagem("A nova data de agendamento foi recusada pelo profissional, ou seja o pedido está cancelado"
                + "\nNúmero do Pedido: " + agenda.getOrcamento().getPedido().getId()
                + "\nNome do Profissional " + agenda.getOrcamento().getPedido().getCliente().getNome()
                + "\nData do Agendamento: " + agenda.getDataAgendamento()
                + " às " + agenda.getHoraAgendamento()
                + "\nISERVICES. "
        );

//        envia o email
        if (email.enviar()) {
            response.getWriter().println("Enviado com sucesso");
        } else {
            response.getWriter().println("Nao enviou");
        }
        request.setAttribute("agenda", agenda);

        response.sendRedirect("Agenda/NovoAgendamentoRecusadoProfissional.jsp");

        int idUsuario = agenda.getOrcamento().getPedido().getProfissional().getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Agenda");
        logs1.setDescricaoEvento("O profissional cancelou o novo agendamento.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    
    public void consultarAgendaAJAX(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {

        int idProfissional = Integer.parseInt(request.getParameter("id"));

        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
        List<AgendaDeServico> listaAgendas = agendaDAO.buscarAgendamentosProfissional(idProfissional);

        Gson gson = new Gson();
        String listaAgendaGson = gson.toJson(listaAgendas);

        response.getWriter().println(listaAgendaGson);
        System.out.println("Lista De agenda:" + listaAgendaGson);

    }

}
