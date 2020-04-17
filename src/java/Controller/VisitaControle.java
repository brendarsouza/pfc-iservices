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
@WebServlet(name = "VisitaControle", urlPatterns = {"/VisitaControle", "/agendarVisita", "/consultarVisitasAJAX", "/aceitarVisitaProfissional", "/consultarVisitasRemarcadasCliente", "/consultarVisitasCliente", "/consultarVisitasEmEsperaCliente", "/consultarVisitasAprovadasCliente", "/consultarVisitasReprovadasCliente", "/consultarVisitasCanceladasCliente", "/consultarVisitasProfissional", "/consultarVisitasRemarcadasProfissional", "/consultarVisitasAprovadasProfissional", "/consultarVisitasReprovadasProfissional", "/consultarVisitasEmEsperaProfissional", "/consultarVisitasCanceladasProfissional", "/cancelarVisitaProfissional", "/consultarVisitasCliente", "/cancelarVisita", "/reprovarVisita", "/aceitarVisita"})
public class VisitaControle extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();

        VisitaDAO visitaDAO = new VisitaDAO();
        if (uri.equals(request.getContextPath() + "/consultarVisitasCliente")) {
            try {
                consultarVisitasCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarVisitasRemarcadasCliente")) {
            try {
                consultarVisitasRemarcadasCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarVisitasEmEsperaCliente")) {
            try {
                consultarVisitasEmEsperaCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarVisitasAprovadasCliente")) {
            try {
                consultarVisitasAprovadasCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarVisitasReprovadasCliente")) {
            try {
                consultarVisitasReprovadasCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarVisitasCanceladasCliente")) {
            try {
                consultarVisitasCanceladasCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarVisitasProfissional")) {
            try {
                consultarVisitasProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarVisitasAprovadasProfissional")) {
            try {
                consultarVisitasAprovadasProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarVisitasCanceladasProfissional")) {
            try {
                consultarVisitasCanceladasProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarVisitasReprovadasProfissional")) {
            try {
                consultarVisitasReprovadasProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarVisitasEmEsperaProfissional")) {
            try {
                consultarVisitasEmEsperaProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarVisitasRemarcadasProfissional")) {
            try {
                consultarVisitasRemarcadasProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AgendaDeServicoControle.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.equals(request.getContextPath() + "/consultarVisitasAJAX")) {
            try {
                consultarVisitasAJAX(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (request.getParameter("idCancelarVisita") != null) {
            request.setAttribute("visita", visitaDAO.getById(Integer.parseInt(request.getParameter("idCancelarVisita"))));
            request.getRequestDispatcher("Visita/ConfirmarCancelamentoVisitaCliente.jsp").forward(request, response);

        } else if (request.getParameter("idCancelarVisitaProfissional") != null) {
            request.setAttribute("visita", visitaDAO.getById(Integer.parseInt(request.getParameter("idCancelarVisitaProfissional"))));
            request.getRequestDispatcher("Visita/ConfirmarCancelamentoVisitaProfissional.jsp").forward(request, response);

        } else if (request.getParameter("idReprovarVisita") != null) {
            request.setAttribute("visita", visitaDAO.getById(Integer.parseInt(request.getParameter("idReprovarVisita"))));
            request.getRequestDispatcher("Visita/ConfirmarReprovacaoVisita.jsp").forward(request, response);

        } else if (request.getParameter("idAceitarVisita") != null) {
            request.setAttribute("visita", visitaDAO.getById(Integer.parseInt(request.getParameter("idAceitarVisita"))));
            request.getRequestDispatcher("Visita/ConfirmarAceitacaoVisitaCliente.jsp").forward(request, response);

        } else if (request.getParameter("idAceitarVisitaProfissional") != null) {
            request.setAttribute("visita", visitaDAO.getById(Integer.parseInt(request.getParameter("idAceitarVisitaProfissional"))));
            request.getRequestDispatcher("Visita/ConfirmarAceitacaoVisitaProfissional.jsp").forward(request, response);

        } else if (request.getParameter("idAgendarPedido") != null) {
            try {

                PedidoDAO pedidoDAO = new PedidoDAO();
                AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
                OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
                Pedido pedido = pedidoDAO.getById(Integer.parseInt(request.getParameter("idAgendarPedido")));
                request.setAttribute("pedido", pedido);
                request.setAttribute("visita", visitaDAO.buscarVisitasProfissional(pedido.getProfissional().getId()));
                request.setAttribute("orcamento", orcamentoDAO.buscarOrcamentoProfissional(pedido.getProfissional().getId()));
                request.setAttribute("agenda", agendaDAO.buscarAgendamentosProfissional(pedido.getProfissional().getId()));
                /* direciona para a página com o formulário para realizar o pedido*/
                request.getRequestDispatcher("Visita/AgendarVisitaProfissional.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        String uri = request.getRequestURI();
        if (uri.equals(request.getContextPath() + "/cancelarVisita")) {
            try {
                cancelarVisita(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/agendarVisita")) {
            try {
                agendarVisita(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/cancelarVisitaProfissional")) {
            try {
                cancelarVisitaProfissional(request, response);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/reprovarVisita")) {
            try {
                reprovarVisita(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/aceitarVisita")) {
            try {
                aceitarVisita(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/aceitarVisitaProfissional")) {
            try {
                aceitarVisitaProfissional(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void consultarVisitasRemarcadasCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {

        VisitaDAO visitaDAO = new VisitaDAO();
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        request.setAttribute("visitaRemarcada", visitaDAO.buscarVisitasRemarcadasCliente(cliente.getId()));

        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarVisitasRemarcadas.jsp").forward(request, response);

        int idUsuario = cliente.getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O cliente realizou uma consulta de todas suas visitas.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarVisitasCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
        VisitaDAO visitaDAO = new VisitaDAO();
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        request.setAttribute("pedido", pedidoDAO.buscarPedidosClientes(cliente.getId()));
        request.setAttribute("visitaRemarcada", visitaDAO.buscarVisitasRemarcadasCliente(cliente.getId()));
        request.setAttribute("visitaAprovada", visitaDAO.buscarVisitasAprovadaCliente(cliente.getId()));
        request.setAttribute("visitaEm_Espera", visitaDAO.buscarVisitasEmEsperaCliente(cliente.getId()));
        request.setAttribute("visitaReprovada", visitaDAO.buscarVisitasReprovadaCliente(cliente.getId()));
        request.setAttribute("visitaCancelada", visitaDAO.buscarVisitasCanceladaCliente(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarVisitas.jsp").forward(request, response);

        int idUsuario = cliente.getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O cliente realizou uma consulta de todas suas visitas.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarVisitasEmEsperaCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
        VisitaDAO visitaDAO = new VisitaDAO();
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        request.setAttribute("pedido", pedidoDAO.buscarPedidosClientes(cliente.getId()));
        request.setAttribute("visitaEm_Espera", visitaDAO.buscarVisitasEmEsperaCliente(cliente.getId()));

        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarVisitasEmEspera.jsp").forward(request, response);

//        ClienteDAO clienteDAO = new ClienteDAO();
//        Cliente cliente = clienteDAO.getById(idConsultar);
        int idUsuario = cliente.getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O cliente realizou uma consulta de todas suas visitas.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarVisitasAprovadasCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
        VisitaDAO visitaDAO = new VisitaDAO();
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        request.setAttribute("pedido", pedidoDAO.buscarPedidosClientes(cliente.getId()));

        request.setAttribute("visitaAprovada", visitaDAO.buscarVisitasAprovadaCliente(cliente.getId()));/*,Status_Orcamento.APROVADO*/

 /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarVisitasAprovadas.jsp").forward(request, response);

        int idUsuario = cliente.getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O cliente realizou uma consulta de todas suas visitas.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarVisitasReprovadasCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
        VisitaDAO visitaDAO = new VisitaDAO();
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        request.setAttribute("pedido", pedidoDAO.buscarPedidosClientes(cliente.getId()));
        request.setAttribute("visitaReprovada", visitaDAO.buscarVisitasReprovadaCliente(cliente.getId()));

        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarVisitasReprovadas.jsp").forward(request, response);

        int idUsuario = cliente.getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O cliente realizou uma consulta de todas suas visitas.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarVisitasCanceladasCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
        VisitaDAO visitaDAO = new VisitaDAO();
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        request.setAttribute("pedido", pedidoDAO.buscarPedidosClientes(cliente.getId()));
        request.setAttribute("visitaCancelada", visitaDAO.buscarVisitasCanceladaCliente(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarVisitasCanceladas.jsp").forward(request, response);

        int idUsuario = cliente.getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O cliente realizou uma consulta de todas suas visitas.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarVisitasProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");
        VisitaDAO visitaDAO = new VisitaDAO();
        request.setAttribute("visita", visitaDAO.buscarVisitasProfissional(profissional.getId()));
        request.setAttribute("visitaRemarcada", visitaDAO.buscarVisitasRemarcadasProfissional(profissional.getId()));

        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarVisitas.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O profissional realizou uma consulta de todas as visitas.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarVisitasRemarcadasProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");
        VisitaDAO visitaDAO = new VisitaDAO();
        request.setAttribute("visitaRemarcada", visitaDAO.buscarVisitasRemarcadasProfissional(profissional.getId()));

        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarVisitasRemarcadas.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O profissional realizou uma consulta de todas as visitas remarcadas.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarVisitasAprovadasProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");
        VisitaDAO visitaDAO = new VisitaDAO();
        request.setAttribute("visita", visitaDAO.buscarVisitasAprovadaProfissional(profissional.getId()));

        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarVisitasAprovadas.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O profissional realizou uma consulta de todas as visitas aprovadas.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarVisitasCanceladasProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");
        VisitaDAO visitaDAO = new VisitaDAO();
        request.setAttribute("visita", visitaDAO.buscarVisitasCanceladaProfissional(profissional.getId()));

        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarVisitasCanceladas.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O profissional realizou uma consulta de todas as visitas canceladas.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarVisitasReprovadasProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");
        VisitaDAO visitaDAO = new VisitaDAO();
        request.setAttribute("visita", visitaDAO.buscarVisitasReprovadaProfissional(profissional.getId()));

        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarVisitasReprovadas.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O profissional realizou uma consulta de todas as visitas reprovadas.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarVisitasEmEsperaProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");
        VisitaDAO visitaDAO = new VisitaDAO();
        request.setAttribute("visita", visitaDAO.buscarVisitasEmEsperaProfissional(profissional.getId()));

        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarVisitasEmEspera.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O profissional realizou uma consulta de todas as visitas em espera.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void cancelarVisita(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {

        int idVisita = Integer.parseInt(request.getParameter("txtIdVisita"));
        int idPedido = Integer.parseInt(request.getParameter("txtIdPedido"));

        VisitaDAO visitaDAO = new VisitaDAO();
        Visita visita = visitaDAO.getById(idVisita);

        String novaDataVisita = request.getParameter("txtDataVisita");
        String novoHorarioVisita = request.getParameter("txtHorarioVisita");

        Date dataAtual = new Date();
        visita.setDataOperacao(dataAtual);

        visita.setObservacao(request.getParameter("txtObservacao"));
//        String toString = novaDataVisita;

        if (!"".equals(novaDataVisita) && !"".equals(novoHorarioVisita)) {
            System.out.println("Nova data visita: " + novaDataVisita);

            Date dataVisita = new SimpleDateFormat("yyyy-MM-dd").parse(novaDataVisita);

            Date horarioAtual = new Date();

            boolean data;
            if (dataAtual.before(dataVisita) || dataAtual.equals(dataVisita)) {
                data = true;
                System.out.println("Correto! A data da visita está após da data atual!");
                visita.setDataVisita(dataVisita);

                Date horarioVisita = new SimpleDateFormat("HH:mm").parse(novoHorarioVisita);
                boolean horaVisita;

//            if (horarioVisita.after(dataAtual)) { 
                if ((horarioVisita.after(visita.getPedido().getHorarioInicialDisponivel())) || (horarioVisita.equals(visita.getPedido().getHorarioInicialDisponivel())) && (horarioVisita.before(visita.getPedido().getHorarioFinalDisponivel()))) {
                    horaVisita = true;

                    visita.setHoraVisita(horarioVisita);
                    visita.setStatus_Visita(Status_Visita.REMARCADA);

                    PedidoDAO pedidoDAO = new PedidoDAO();
                    Pedido pedido = pedidoDAO.getById(idPedido);
//                    pedido.setStatus_Pedido(Status_Pedido.EM_ESPERA);
                    pedido.setDataDesejada(dataVisita);
                    visita.setPedido(pedido);

                    pedidoDAO.atualizar(pedido);

                    visitaDAO.atualizar(visita);
                    Date dataVisitaAnterior = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataVisitaAnterior"));

//        Instânciando o objeto email 
                    Email email = new Email();

                    email.setNomeDestinatario(visita.getPedido().getCliente().getNome());
                    email.setEmailDestinatario(visita.getPedido().getCliente().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
                    email.setAssunto("ISERVICES - Pedido Cancelado");

                    email.setMensagem("A visita que estava marcada para o dia " + dataVisitaAnterior + " foi cancelada."
                            + "\nNúmero do Pedido: " + visita.getPedido().getId()
                            + "\nNome do Profissional " + visita.getPedido().getProfissional().getNome()
                            + "\nNome do Cliente " + visita.getPedido().getCliente().getNome()
                            + "\nNova data solicitada "
                            + "\nData: " + visita.getDataVisita()
                            + "\nHorário: " + visita.getHoraVisita()
                            + "\nISERVICES. "
                    );

//        envia o email
                    if (email.enviar()) {
                        response.getWriter().println("Enviado com sucesso");
                    } else {
                        response.getWriter().println("Nao enviou");
                    }
                    Email email2 = new Email();

                    email2.setNomeDestinatario(visita.getPedido().getProfissional().getNome());
                    email2.setEmailDestinatario(visita.getPedido().getProfissional().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
                    email2.setAssunto("ISERVICES - Pedido Cancelado");

                    email2.setMensagem("A visita que estava marcada para o dia " + dataVisitaAnterior + " foi cancelada."
                            + "\nNúmero do Pedido: " + visita.getPedido().getId()
                            + "\nNome do Profissional " + visita.getPedido().getProfissional().getNome()
                            + "\nNome do Cliente " + visita.getPedido().getCliente().getNome()
                            + "\nNova data solicitada "
                            + "\nData: " + visita.getDataVisita()
                            + "\nHorário: " + visita.getHoraVisita()
                            + "\nISERVICES. "
                    );

//        envia o email
                    if (email2.enviar()) {
                        response.getWriter().println("Enviado com sucesso");
                    } else {
                        response.getWriter().println("Nao enviou");
                    }

                    request.setAttribute("visita", visita);

                    response.sendRedirect("Visita/CancelamentoConfirmadoCliente.jsp");

                    int idUsuario = Integer.parseInt(request.getParameter("txtIdUsuario"));

                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    Usuario usuario = usuarioDAO.getById(idUsuario);
                    Logs logs1 = new Logs();

                    logs1.setUsuario(usuario);
                    Date dataLog1 = new Date();
                    logs1.setData(dataLog1);
                    logs1.setEvento("Agenda");
                    if (usuario.getPerfilAcesso() == PerfilAcesso.CLIENTE) {
                        logs1.setDescricaoEvento("O cliente cancelou a visita marcada e a reagendou.");
                    } else if (usuario.getPerfilAcesso() == PerfilAcesso.PROFISSIONAL) {
                        logs1.setDescricaoEvento("O profissional cancelou a visita marcada e a reagendou.");
                    }
                    LogsDAO logsDAO = new LogsDAO();
                    logsDAO.cadastrar(logs1);

                } else {

                    horaVisita = false;
                    request.setAttribute("visita", visita);
                    response.sendRedirect("Erro/ErroHorarioPedido.jsp");
                }

            } else if (dataAtual.after(dataVisita)) {
                data = true;
                System.out.println("Erro! É impossível solicitar um pedido antes da data atual.");
                response.sendRedirect("Erro/ErroDataPedido.jsp");
            } else {
                System.out.println("Formato incorreto");
            }
        } else {
            visita.setStatus_Visita(Status_Visita.CANCELADA);

            PedidoDAO pedidoDAO = new PedidoDAO();
            Pedido pedido = pedidoDAO.getById(idPedido);
            pedido.setStatus_Pedido(Status_Pedido.REPROVADO);
            visita.setPedido(pedido);

            pedidoDAO.atualizar(pedido);

            visitaDAO.atualizar(visita);
            System.out.println("A visita foi cancelada e não foi reagendada.");

            request.setAttribute("visita", visita);

            response.sendRedirect("Visita/CancelamentoConfirmadoCliente.jsp");

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

    public void cancelarVisitaProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {

        int idVisita = Integer.parseInt(request.getParameter("txtIdVisita"));
        int idPedido = Integer.parseInt(request.getParameter("txtIdPedido"));

        VisitaDAO visitaDAO = new VisitaDAO();
        Visita visita = visitaDAO.getById(idVisita);

        String novaDataVisita = request.getParameter("txtDataVisita");
        String novoHorarioVisita = request.getParameter("txtHorarioVisita");

        Date dataAtual = new Date();
        visita.setDataOperacao(dataAtual);

        visita.setObservacao(request.getParameter("txtObservacao"));
//        String toString = novaDataVisita;

        if (!"".equals(novaDataVisita) && !"".equals(novoHorarioVisita)) {
            System.out.println("Nova data visita: " + novaDataVisita);

            Date dataVisita = new SimpleDateFormat("yyyy-MM-dd").parse(novaDataVisita);

            Date horarioAtual = new Date();

            boolean data;
            if (dataAtual.before(dataVisita) || dataAtual.equals(dataVisita)) {
                data = true;
                System.out.println("Correto! A data da visita está após da data atual!");
                visita.setDataVisita(dataVisita);

                Date horarioVisita = new SimpleDateFormat("HH:mm").parse(novoHorarioVisita);
                boolean horaVisita;

//            if (horarioVisita.after(dataAtual)) { 
                if ((horarioVisita.after(visita.getPedido().getHorarioInicialDisponivel())) || (horarioVisita.equals(visita.getPedido().getHorarioInicialDisponivel())) && (horarioVisita.before(visita.getPedido().getHorarioFinalDisponivel()))) {
                    horaVisita = true;

                    visita.setHoraVisita(horarioVisita);
                    visita.setStatus_Visita(Status_Visita.REMARCADA);

                    PedidoDAO pedidoDAO = new PedidoDAO();
                    Pedido pedido = pedidoDAO.getById(idPedido);
                    pedido.setDataDesejada(dataVisita);
                    visita.setPedido(pedido);

                    pedidoDAO.atualizar(pedido);

                    visitaDAO.atualizar(visita);
                    Date dataVisitaAnterior = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataVisitaAnterior"));

//        Instânciando o objeto email 
                    Email email = new Email();

                    email.setNomeDestinatario(visita.getPedido().getCliente().getNome());
                    email.setEmailDestinatario(visita.getPedido().getCliente().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
                    email.setAssunto("ISERVICES - Pedido Cancelado");

                    email.setMensagem("A visita que estava marcada para o dia " + dataVisitaAnterior + " foi cancelada."
                            + "\nNúmero do Pedido: " + visita.getPedido().getId()
                            + "\nNome do Profissional " + visita.getPedido().getProfissional().getNome()
                            + "\nNome do Cliente " + visita.getPedido().getCliente().getNome()
                            + "\nNova data solicitada "
                            + "\nData: " + visita.getDataVisita()
                            + "\nHorário: " + visita.getHoraVisita()
                            + "\nISERVICES. "
                    );

//        envia o email
                    if (email.enviar()) {
                        response.getWriter().println("Enviado com sucesso");
                    } else {
                        response.getWriter().println("Nao enviou");
                    }
                    Email email2 = new Email();

                    email2.setNomeDestinatario(visita.getPedido().getProfissional().getNome());
                    email2.setEmailDestinatario(visita.getPedido().getProfissional().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
                    email2.setAssunto("ISERVICES - Pedido Cancelado");

                    email2.setMensagem("A visita que estava marcada para o dia " + dataVisitaAnterior + " foi cancelada."
                            + "\nNúmero do Pedido: " + visita.getPedido().getId()
                            + "\nNome do Profissional " + visita.getPedido().getProfissional().getNome()
                            + "\nNome do Cliente " + visita.getPedido().getCliente().getNome()
                            + "\nNova data solicitada "
                            + "\nData: " + visita.getDataVisita()
                            + "\nHorário: " + visita.getHoraVisita()
                            + "\nISERVICES. "
                    );

//        envia o email
                    if (email2.enviar()) {
                        response.getWriter().println("Enviado com sucesso");
                    } else {
                        response.getWriter().println("Nao enviou");
                    }

                    request.setAttribute("visita", visita);

                    response.sendRedirect("Visita/CancelamentoConfirmadoProfissional.jsp");

                    int idUsuario = Integer.parseInt(request.getParameter("txtIdUsuario"));

                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    Usuario usuario = usuarioDAO.getById(idUsuario);
                    Logs logs1 = new Logs();

                    logs1.setUsuario(usuario);
                    Date dataLog1 = new Date();
                    logs1.setData(dataLog1);
                    logs1.setEvento("Agenda");
                    if (usuario.getPerfilAcesso() == PerfilAcesso.CLIENTE) {
                        logs1.setDescricaoEvento("O cliente cancelou a visita marcada e a reagendou.");
                    } else if (usuario.getPerfilAcesso() == PerfilAcesso.PROFISSIONAL) {
                        logs1.setDescricaoEvento("O profissional cancelou a visita marcada e a reagendou.");
                    }
                    LogsDAO logsDAO = new LogsDAO();
                    logsDAO.cadastrar(logs1);

                } else {

                    horaVisita = false;
                    request.setAttribute("visita", visita);
                    response.sendRedirect("Erro/ErroHorarioPedido.jsp");
                }

            } else if (dataAtual.after(dataVisita)) {
                data = true;
                System.out.println("Erro! É impossível solicitar um pedido antes da data atual.");
                response.sendRedirect("Erro/ErroDataPedido.jsp");
            } else {
                System.out.println("Formato incorreto");
            }
        } else {
            visita.setStatus_Visita(Status_Visita.CANCELADA);

            PedidoDAO pedidoDAO = new PedidoDAO();
            Pedido pedido = pedidoDAO.getById(idPedido);
            pedido.setStatus_Pedido(Status_Pedido.REPROVADO);
            visita.setPedido(pedido);

            pedidoDAO.atualizar(pedido);

            visitaDAO.atualizar(visita);
            System.out.println("A visita foi cancelada e não foi reagendada.");

            request.setAttribute("visita", visita);

            response.sendRedirect("Visita/CancelamentoConfirmadoProfissional.jsp");

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

    public void reprovarVisita(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {

        int idVisita = Integer.parseInt(request.getParameter("txtIdVisita"));
        int idPedido = Integer.parseInt(request.getParameter("txtIdPedido"));

        VisitaDAO visitaDAO = new VisitaDAO();
        Visita visita = visitaDAO.getById(idVisita);

        visita.setObservacao(request.getParameter("txtObservacao"));

        visita.setStatus_Visita(Status_Visita.REPROVADA);

        visitaDAO.atualizar(visita);
        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido pedido = pedidoDAO.getById(idPedido);
        pedido.setStatus_Pedido(Status_Pedido.REPROVADO);
        pedidoDAO.atualizar(pedido);
        Date dataVisitaAnterior = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataVisitaAnterior"));

//        Instânciando o objeto email 
        Email email = new Email();

        email.setNomeDestinatario(visita.getPedido().getCliente().getNome());
        email.setEmailDestinatario(visita.getPedido().getCliente().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
        email.setAssunto("ISERVICES - Pedido Cancelado");

        email.setMensagem("A visita que estava marcada para o dia " + dataVisitaAnterior + " foi recusada."
                + "\nNúmero do Pedido: " + visita.getPedido().getId()
                + "\nNome do Profissional " + visita.getPedido().getProfissional().getNome()
                + "\nNome do Cliente " + visita.getPedido().getCliente().getNome()
                + "\nNova data solicitada "
                + "\nData: " + visita.getDataVisita()
                + "\nHorário: " + visita.getHoraVisita()
                + "\nISERVICES. "
        );

//        envia o email
        if (email.enviar()) {
            response.getWriter().println("Enviado com sucesso");
        } else {
            response.getWriter().println("Nao enviou");
        }
        Email email2 = new Email();

        email2.setNomeDestinatario(visita.getPedido().getProfissional().getNome());
        email2.setEmailDestinatario(visita.getPedido().getProfissional().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
        email2.setAssunto("ISERVICES - Pedido Cancelado");

        email2.setMensagem("A visita que estava marcada para o dia " + dataVisitaAnterior + " foi cancelada."
                + "\nNúmero do Pedido: " + visita.getPedido().getId()
                + "\nNome do Profissional " + visita.getPedido().getProfissional().getNome()
                + "\nNome do Cliente " + visita.getPedido().getCliente().getNome()
                + "\nNova data solicitada "
                + "\nData: " + visita.getDataVisita()
                + "\nHorário: " + visita.getHoraVisita()
                + "\nISERVICES. "
        );

//        envia o email
        if (email2.enviar()) {
            response.getWriter().println("Enviado com sucesso");
        } else {
            response.getWriter().println("Nao enviou");
        }

        request.setAttribute("visita", visita);

        response.sendRedirect("Visita/CancelamentoConfirmadoCliente.jsp");

        int idUsuario = pedido.getCliente().getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O cliente recusou a visita agendada.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);

    }

    public void aceitarVisita(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        int idVisita = Integer.parseInt(request.getParameter("txtIdVisita"));
        int idPedido = Integer.parseInt(request.getParameter("txtIdPedido"));

        VisitaDAO visitaDAO = new VisitaDAO();
        Visita visita = visitaDAO.getById(idVisita);

        visita.setStatus_Visita(Status_Visita.APROVADA);
        Date dataAtual = new Date();
        visita.setDataOperacao(dataAtual);

        visitaDAO.atualizar(visita);
        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido pedido = pedidoDAO.getById(idPedido);

        Date dataVisitaAnterior = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataVisitaAnterior"));

//        Instânciando o objeto email 
        Email email = new Email();

        email.setNomeDestinatario(visita.getPedido().getCliente().getNome());
        email.setEmailDestinatario(visita.getPedido().getCliente().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
        email.setAssunto("ISERVICES - Pedido Aprovado");

        email.setMensagem("A visita que estava marcada para o dia " + dataVisitaAnterior + " foi aprovada."
                + "\nNúmero do Pedido: " + visita.getPedido().getId()
                + "\nNome do Profissional " + visita.getPedido().getProfissional().getNome()
                + "\nNome do Cliente " + visita.getPedido().getCliente().getNome()
                + "\nData Antiga: " + visita.getPedido().getCliente().getNome()
                + "\nNova data marcada para receber a visita "
                + "\n Nova data: " + visita.getDataVisita()
                + "\n Novo horário: " + visita.getHoraVisita()
                + "\nISERVICES. "
        );

//        envia o email
        if (email.enviar()) {
            response.getWriter().println("Enviado com sucesso");
        } else {
            response.getWriter().println("Nao enviou");
        }
        Email email2 = new Email();

        email2.setNomeDestinatario(visita.getPedido().getProfissional().getNome());
        email2.setEmailDestinatario(visita.getPedido().getProfissional().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
        email2.setAssunto("ISERVICES - Pedido Aprovado");

        email2.setMensagem("A visita que estava marcada para o dia " + dataVisitaAnterior + " foi aprovada."
                + "\nNúmero do Pedido: " + visita.getPedido().getId()
                + "\nNome do Profissional " + visita.getPedido().getProfissional().getNome()
                + "\nData antiga " + dataVisitaAnterior
                + "\nNova data marcada para receber a visita "
                + "\nData: " + visita.getDataVisita()
                + "\nHorário: " + visita.getHoraVisita()
                + "\nISERVICES. "
        );

//        envia o email
        if (email2.enviar()) {
            response.getWriter().println("Enviado com sucesso");
        } else {
            response.getWriter().println("Nao enviou");
        }

        request.setAttribute("visita", visita);

        response.sendRedirect("Visita/AceitacaoVisitaConfirmadaCliente.jsp");

        int idUsuario = visita.getPedido().getCliente().getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O cliente aceitou a visita agendada.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void aceitarVisitaProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        int idVisita = Integer.parseInt(request.getParameter("txtIdVisita"));
        int idPedido = Integer.parseInt(request.getParameter("txtIdPedido"));

        VisitaDAO visitaDAO = new VisitaDAO();
        Visita visita = visitaDAO.getById(idVisita);

        visita.setStatus_Visita(Status_Visita.APROVADA);
        Date dataAtual = new Date();
        visita.setDataOperacao(dataAtual);

        visitaDAO.atualizar(visita);

        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido pedido = pedidoDAO.getById(idPedido);
        pedido.setStatus_Pedido(Status_Pedido.APROVADO);
        pedidoDAO.atualizar(pedido);

        Date dataVisitaAnterior = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataVisitaAnterior"));

//        Instânciando o objeto email 
        Email email = new Email();

        email.setNomeDestinatario(visita.getPedido().getCliente().getNome());
        email.setEmailDestinatario(visita.getPedido().getCliente().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
        email.setAssunto("ISERVICES - Pedido Aprovado");

        email.setMensagem("A visita que estava marcada para o dia " + dataVisitaAnterior + " foi aprovada."
                + "\nNúmero do Pedido: " + visita.getPedido().getId()
                + "\nNome do Profissional " + visita.getPedido().getProfissional().getNome()
                + "\nNome do Cliente " + visita.getPedido().getCliente().getNome()
                + "\nNova data marcada para receber a visita "
                + "\nData: " + visita.getDataVisita()
                + "\nHorário: " + visita.getHoraVisita()
                + "\nISERVICES. "
        );

//        envia o email
        if (email.enviar()) {
            response.getWriter().println("Enviado com sucesso");
        } else {
            response.getWriter().println("Nao enviou");
        }
        Email email2 = new Email();

        email2.setNomeDestinatario(visita.getPedido().getCliente().getNome());
        email2.setEmailDestinatario(visita.getPedido().getCliente().getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
        email2.setAssunto("ISERVICES - Pedido Aprovado");

        email2.setMensagem("A visita que estava marcada para o dia " + dataVisitaAnterior + " foi aprovada."
                + "\nNúmero do Pedido: " + visita.getPedido().getId()
                + "\nNome do Profissional " + visita.getPedido().getProfissional().getNome()
                + "\nNome do Cliente " + visita.getPedido().getCliente().getNome()
                + "\nNova data marcada para receber a visita "
                + "\nData: " + visita.getDataVisita()
                + "\nHorário: " + visita.getHoraVisita()
                + "\nISERVICES. "
        );

//        envia o email
        if (email2.enviar()) {
            response.getWriter().println("Enviado com sucesso");
        } else {
            response.getWriter().println("Nao enviou");
        }

        request.setAttribute("visita", visita);

        response.sendRedirect("Visita/AceitacaoVisitaConfirmadaProfissional.jsp");

        int idUsuario = pedido.getProfissional().getUsuario().getId();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Visita");
        logs1.setDescricaoEvento("O profissional aceitou a nova data para realizar a visita.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void agendarVisita(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {

        Visita visita = new Visita();
        Date dataVisita = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataVisita"));
        Date dataAtual = new Date();

        int idPedido = Integer.parseInt(request.getParameter("txtIdPedido"));
        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido pedido = pedidoDAO.getById(idPedido);

        int idCliente = Integer.parseInt(request.getParameter("txtIdCliente"));
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.consultarPorId(idCliente);

        int idProfissional = Integer.parseInt(request.getParameter("txtIdProfissional"));
        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
        Profissional profissional = profissionalDAO.consultarPorId(idProfissional);

        VisitaDAO visitaDAO = new VisitaDAO();

        //Se a data atual está ANTES da data da Visita OU a data Atual esta IGUAL dataVisita
        if (dataAtual.before(dataVisita) || dataAtual.equals(dataVisita)) {
            System.out.println("A Data da Visita está após ou igual a data atual");

            //Se a dataVisita Depois dataDesejada.pedido OU dataVisita Igual dataDesejada.Pedido
            if (dataVisita.after(pedido.getDataDesejada()) || dataVisita.equals(pedido.getDataDesejada())) {
                System.out.println("A Data da Visita está depois ou igual a data do Solicitada no pedido!");

                Date horarioVisita = new SimpleDateFormat("HH:mm").parse(request.getParameter("txtHorarioVisita"));

//             Se horarioVisita antes HorarioFinalDisponivel.Pedido          
                if (horarioVisita.before(pedido.getHorarioFinalDisponivel())) {
                    System.out.println("O horário da Visita está antes do Horário Final disponível solicitado pelo cliente");

                    //Se horario da visita depois horarioInicialDisponivel.Pedido OU horarioVisita igual HorarioInicialDisponivel.Pedido 
                    if ((horarioVisita.after(pedido.getHorarioInicialDisponivel())) || (horarioVisita.equals(pedido.getHorarioInicialDisponivel()))) {
                        System.out.println("O horario da visita está depois ou igual o horario inicial, solicitado pelo cliente");

                        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
                        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
                        List<Visita> validacaoVisita = visitaDAO.verificarDataHoraVisita(dataVisita, horarioVisita);
//                        List<Orcamento> validacaoOrcamento = orcamentoDAO.verificarDataHoraOrcamento(dataVisita, horarioVisita);
//                        List<AgendaDeServico> validacaoAgendamento = agendaDAO.verificarDataHoraAgendamento(dataVisita, horarioVisita);
                        if (validacaoVisita.isEmpty() ){
//                            && validacaoOrcamento.isEmpty() && validacaoAgendamento.isEmpty()) {
                            visita.setDataVisita(dataVisita);
                            visita.setHoraVisita(horarioVisita);
                            visita.setStatus_Visita(Status_Visita.EM_ESPERA);
                            pedido.setStatus_Pedido(Status_Pedido.APROVADO);

                            cliente.setId(idCliente);
                            pedido.setCliente(cliente);

                            profissional.setId(idProfissional);
                            pedido.setProfissional(profissional);

                            visita.setPedido(pedido);

                            pedidoDAO.atualizar(pedido);
                            visitaDAO.cadastrar(visita);

                            Email email = new Email();

                            Cliente pessoaCliente = new ClienteDAO().consultarPorId(idCliente);

                            email.setNomeDestinatario(pessoaCliente.getNome());
                            email.setEmailDestinatario(pessoaCliente.getUsuario().getEmail());
                            response.getWriter().println(pessoaCliente.getUsuario().getEmail());

                            email.setAssunto("ISERVICES - Agendar Visita");

                            email.setMensagem("O pedido efetuado, recebeu uma solicitação de Visita para gerar um orcamento"
                                    + "\n Número do Pedido: " + visita.getPedido().getId()
                                    + "\nNome do Profissional: " + visita.getPedido().getProfissional().getNome()
                                    + "\nDescrição do Pedido: " + visita.getPedido().getDescricaoProblema()
                                    + "\nData desejada para a visita:" + visita.getDataVisita() + " às " + visita.getHoraVisita()
                                    + "\nISERVICES. "
                            );
//        envia o email
                            if (email.enviar()) {
                                response.getWriter().println("Enviado com sucesso");
                            } else {
                                response.getWriter().println("Nao enviou");
                            }
                            request.setAttribute("cliente", cliente);
                            response.sendRedirect("Visita/VisitaAgendadaProfissional.jsp");

                            //logs
                            int idUsuario = cliente.getUsuario().getId();

                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                            Usuario usuario = usuarioDAO.getById(idUsuario);
                            Logs logs1 = new Logs();

                            logs1.setUsuario(usuario);
                            Date dataLog1 = new Date();
                            logs1.setData(dataLog1);
                            logs1.setEvento("Visita");
                            logs1.setDescricaoEvento("O cliente agendou uma visita.");
                            LogsDAO logsDAO = new LogsDAO();
                            logsDAO.cadastrar(logs1);
                        } else {
                            for (Visita v : validacaoVisita) {
//                                for (Orcamento o : validacaoOrcamento) {
//                                    for (AgendaDeServico a : validacaoAgendamento) {
                                        if (v.getStatus_Visita() == Status_Visita.CANCELADA || v.getStatus_Visita() == Status_Visita.REPROVADA){
//                                            || o.getStatus_Orcamento() == Status_Orcamento.REPROVADO || a.getStatus_servico() == Status_Servico.CANCELADO) {
                                            visita.setDataVisita(dataVisita);
                                            visita.setHoraVisita(horarioVisita);
                                            visita.setStatus_Visita(Status_Visita.EM_ESPERA);
                                            pedido.setStatus_Pedido(Status_Pedido.APROVADO);

                                            cliente.setId(idCliente);
                                            pedido.setCliente(cliente);

                                            profissional.setId(idProfissional);
                                            pedido.setProfissional(profissional);

                                            visita.setPedido(pedido);

                                            pedidoDAO.atualizar(pedido);
                                            visitaDAO.cadastrar(visita);

                                            Email email = new Email();

                                            Cliente pessoaCliente = new ClienteDAO().consultarPorId(idCliente);

                                            email.setNomeDestinatario(pessoaCliente.getNome());
                                            email.setEmailDestinatario(pessoaCliente.getUsuario().getEmail());
                                            response.getWriter().println(pessoaCliente.getUsuario().getEmail());

                                            email.setAssunto("ISERVICES - Agendar Visita");

                                            email.setMensagem("O pedido efetuado, recebeu uma solicitação de Visita para gerar um orcamento"
                                                    + "\n Número do Pedido: " + visita.getPedido().getId()
                                                    + "\nNome do Profissional: " + visita.getPedido().getProfissional().getNome()
                                                    + "\nDescrição do Pedido: " + visita.getPedido().getDescricaoProblema()
                                                    + "\nData desejada para a visita:" + visita.getDataVisita() + " às " + visita.getHoraVisita()
                                                    + "\nISERVICES. "
                                            );
//        envia o email
                                            if (email.enviar()) {
                                                response.getWriter().println("Enviado com sucesso");
                                            } else {
                                                response.getWriter().println("Nao enviou");
                                            }
                                            request.setAttribute("cliente", cliente);
                                            response.sendRedirect("Visita/VisitaAgendadaProfissional.jsp");

                                            //logs
                                            int idUsuario = cliente.getUsuario().getId();

                                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                                            Usuario usuario = usuarioDAO.getById(idUsuario);
                                            Logs logs1 = new Logs();

                                            logs1.setUsuario(usuario);
                                            Date dataLog1 = new Date();
                                            logs1.setData(dataLog1);
                                            logs1.setEvento("Visita");
                                            logs1.setDescricaoEvento("O cliente agendou uma visita.");
                                            LogsDAO logsDAO = new LogsDAO();
                                            logsDAO.cadastrar(logs1);
                                        } else {
                                            System.out.println("Erro! Já existe Visitas Agendadas nessa data e Horário.");
                                            response.sendRedirect("Erro/ErroDataHorarioVisita.jsp");
                                        }
//                                    }
//                                }
                            }
                        }
                    } else {
                        System.out.println("Erro! O horário da visita está antes do horario inicial, solicitado pelo cliente");
                        response.sendRedirect("Erro/ErroHorarioVisitaAntesHorarioInicialSolicitada.jsp");
                    }
                } else {
                    System.out.println("Erro! O horário da Visita está depois do Horário Final disponível, solicitado pelo cliente");
                    response.sendRedirect("Erro/ErroHorarioVisitaDepoisHorarioFinalSolicitada.jsp");
                }
            } else {
                System.out.println("Erro! A Data da Visita está antes a data do Solicitada no pedido!");
                response.sendRedirect("Erro/ErroDataVisitaAntesDataSolicitada.jsp");
            }
        } else {

            System.out.println("Erro! É impossível solicitar uma visita antes da data atual.");
            response.sendRedirect("Erro/ErroDataVisitaAntesDataAtual.jsp");

        }
    }

    public void consultarVisitasAJAX(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {

        int idProfissional = Integer.parseInt(request.getParameter("id"));

        VisitaDAO visitaDAO = new VisitaDAO();
        List<Visita> listaVisitas = visitaDAO.buscarVisitasProfissional(idProfissional);

        Gson gson = new Gson();
        String listaVisitasGson = gson.toJson(listaVisitas);

        response.getWriter().println(listaVisitasGson);
        System.out.println("Lista De visitas:" + listaVisitasGson);

    }

}
