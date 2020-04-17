/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ClienteDAO;
import DAO.LogsDAO;
import DAO.PedidoDAO;
import DAO.ProfissionalDAO;
import DAO.UsuarioDAO;
import DAO.VisitaDAO;
import Model.Cliente;
import Model.Logs;
import Model.Pedido;
import Model.PerfilAcesso;
import Model.Profissional;
import Model.Status_Pedido;
import Model.Usuario;
import Util.Email;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
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
@WebServlet(name = "ConsultasControle", urlPatterns = {"/ConsultasControle", "/consultarProfissionais", "/buscarProfissionaisParaSolicitacao", "/buscarProfissionaisParaSolicitacaoProfissional", "/consultarTodosPedidosProfissional", "/consultarPedidosEmEsperaProfissional", "/consultarPedidosAprovadosProfissional", "/consultarPedidosReprovadosProfissional", "/consultarPedidosCliente", "/consultarPedidosAprovadosCliente", "/consultarPedidosReprovadosCliente", "/consultarPedidosEmEsperaCliente", "/buscarProfissionaisParaSolicitacaoFiltro"})
public class ConsultasControle extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*o request.getRequestURI(); pega a uri que está vindo do navegador e atribui na variavel uri*/
        String uri = request.getRequestURI();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*o request.getRequestURI(); pega a uri que está vindo do navegador e atribui na variavel uri*/
        String uri = request.getRequestURI();

        /*se a uri for igual a consultarProfissionais irá chamar o metodo consultarProfissionais*/
        if (uri.equals(request.getContextPath() + "/consultarProfissionais")) {
            try {
                consultarProfissionais(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            }
            /*se a uri for igual a deletarProfissional irá chamar o metodo deletarProfissional*/
        } else if (uri.equals(request.getContextPath() + "/buscarProfissionaisParaSolicitacao")) {
            try {
                buscarProfissionaisParaSolicitacao(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            }
            /*se a uri for igual a consultarPedidos irá chamar o metodo consultarPedidos*/
        } else if (uri.equals(request.getContextPath() + "/buscarProfissionaisParaSolicitacaoProfissional")) {
            try {
                buscarProfissionaisParaSolicitacaoProfissional(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            }
            /*se a uri for igual a consultarPedidos irá chamar o metodo consultarPedidos*/
        } else if (uri.equals(request.getContextPath() + "/buscarProfissionaisParaSolicitacaoFiltro")) {
            try {
                buscarProfissionaisParaSolicitacaoFiltro(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            }
            /*se a uri for igual a consultarPedidos irá chamar o metodo consultarPedidos*/
        } else if (uri.equals(request.getContextPath() + "/consultarTodosPedidosProfissional")) {
            try {
                consultarPedidosProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarPedidosEmEsperaProfissional")) {
            try {
                consultarPedidosEmEsperaProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarPedidosAprovadosProfissional")) {
            try {
                consultarPedidosAprovadosProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.equals(request.getContextPath() + "/consultarPedidosReprovadosProfissional")) {
            try {
                consultarPedidosReprovadosProfissional(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.equals(request.getContextPath() + "/consultarPedidosCliente")) {
            try {
                consultarPedidosCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarPedidosAprovadosCliente")) {
            try {
                consultarPedidosAprovadosCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarPedidosEmEsperaCliente")) {
            try {
                consultarPedidosEmEsperaCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/consultarPedidosReprovadosCliente")) {
            try {
                consultarPedidosReprovadosCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ConsultasControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            ProfissionalDAO profissionalDAO = new ProfissionalDAO();

            /* Recebe o id do profissional e confirma o valor não é nulo*/
            if (request.getParameter("idEditar") != null) {
                
                request.setAttribute("profissional", profissionalDAO.consultarPorId(Integer.parseInt(request.getParameter("idEditar"))));
                /* direciona para a página de edição de cadastro*/
                request.getRequestDispatcher("Profissional/EditarProfissional.jsp").forward(request, response);

            }
        } catch (Exception ex) {
            Logger.getLogger(ProfissionalControle.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500, "ERRO! Não achei -_- ");
        }

    }

    /* Esse método é utilizado para buscar profissionais que atendem as profissoes pesquisadas por usuários que não estão logadas*/
    public void consultarProfissionais(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException {

//        Recupera o valor recebido no formulário e atribui na variável profissaoPesquisada
        int profissaoPesquisada = Integer.parseInt(request.getParameter("txtProfissao"));
        String cidadeAtendimento = request.getParameter("txtCidadeAtendimento");

        ProfissionalDAO profissionalDAO = new ProfissionalDAO();

        try {

            List<Profissional> listaProfissionais = profissionalDAO.buscarProfissionaisPorProfissao(profissaoPesquisada, cidadeAtendimento);

            request.setAttribute("listaPessoas", listaProfissionais);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(ProfissionalControle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * Esse método é utilizado para buscar profissionais que atendem a
     * profissao pesquisada de acordo com a área de trabalho e a região de atendimento e desejam efetuar um pedido.
     * 
     */
    public void buscarProfissionaisParaSolicitacao(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException {
        int profissaoPesquisada = Integer.parseInt(request.getParameter("txtProfissao"));
        String cidadeAtendimento = request.getParameter("txtCidadeAtendimento");
        ProfissionalDAO profissionalDAO = new ProfissionalDAO();

        try {

            List<Profissional> listaProfissionais = profissionalDAO.buscarProfissionaisPorProfissao(profissaoPesquisada, cidadeAtendimento);
            request.setAttribute("listaProfissionais", listaProfissionais);
            request.getRequestDispatcher("Consultas/ListarProfissional.jsp").forward(request, response);

            Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
            UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.getById(cliente.getId());
            
            Logs logs1 = new Logs();
            logs1.setUsuario(usuario);
            Date dataLog1 = new Date();
            logs1.setData(dataLog1);
            logs1.setEvento("Consulta");
            
            if (usuario.getPerfilAcesso().equals(PerfilAcesso.CLIENTE)) {
                logs1.setDescricaoEvento("O cliente realizou uma busca por profissional.");
            } else if (usuario.getPerfilAcesso().equals(PerfilAcesso.PROFISSIONAL)) {
                logs1.setDescricaoEvento("O profissional realizou uma busca por profissional.");
            }
                LogsDAO logsDAO = new LogsDAO();
            logsDAO.cadastrar(logs1);

        } catch (Exception ex) {
            Logger.getLogger(ProfissionalControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarProfissionaisParaSolicitacaoProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException {
//        Recupera o valor recebido no formulário e atribui na variável profissaoPesquisada
        int profissaoPesquisada = Integer.parseInt(request.getParameter("txtProfissao"));
        String cidadeAtendimento = request.getParameter("txtCidadeAtendimento");
        ProfissionalDAO profissionalDAO = new ProfissionalDAO();

        try {
            /*            passa por parâmetro a profissão pesquisada para o método buscarProfissionaisPorProfissao
            Traz uma lista de pessoas que exerce a profissão pesquisada*/
            List<Profissional> listaProfissionais = profissionalDAO.buscarProfissionaisPorProfissao(profissaoPesquisada, cidadeAtendimento);
            request.setAttribute("listaProfissionais", listaProfissionais);
            request.getRequestDispatcher("Consultas/ListarProfissional.jsp").forward(request, response);

//            int idCliente = Integer.parseInt(request.getParameter("txtIdCliente"));
//
//            //        Cadastrar o acesso do cliente à todos os pedidos
//            ClienteDAO clienteDAO = new ClienteDAO();
//            Cliente cliente = clienteDAO.getById(idCliente);
            Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.getById(cliente.getId());
            Logs logs1 = new Logs();

            logs1.setUsuario(usuario);
            Date dataLog1 = new Date();
            logs1.setData(dataLog1);
            logs1.setEvento("Consulta");
            if (usuario.getPerfilAcesso().equals(PerfilAcesso.CLIENTE)) {
                logs1.setDescricaoEvento("O cliente realizou uma busca por profissional.");
            } else if (usuario.getPerfilAcesso().equals(PerfilAcesso.PROFISSIONAL)) {
                logs1.setDescricaoEvento("O profissional realizou uma busca por profissional.");
            }
            LogsDAO logsDAO = new LogsDAO();
            logsDAO.cadastrar(logs1);

        } catch (Exception ex) {
            Logger.getLogger(ProfissionalControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarProfissionaisParaSolicitacaoFiltro(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, ParseException {
//        Recupera o valor recebido no formulário e atribui na variável profissaoPesquisada
        int profissaoPesquisada = Integer.parseInt(request.getParameter("txtProfissao"));
        String cidadeAtendimento = request.getParameter("txtCidadeAtendimento");

        Date horarioAtendimentoInicio = new SimpleDateFormat("HH:mm").parse(request.getParameter("txtHorarioDeAtendimentoInicio"));
        Date horarioAtendimentoFim = new SimpleDateFormat("HH:mm").parse(request.getParameter("txtHorarioDeAtendimentoFim"));

//fazer uma consulta com o id do profissional
        String ordenacao = request.getParameter("txtOrdenacao");
        ProfissionalDAO profissionalDAO = new ProfissionalDAO();

        if ("AVALIACAOSERVICO".equals(ordenacao)) {
            try {
                /*            passa por parâmetro a profissão pesquisada para o método buscarProfissionaisPorProfissao
            Traz uma lista de pessoas que exerce a profissão pesquisada*/
                List<Profissional> listaProfissionaisFiltrados = profissionalDAO.buscarProfissionaisPorAvaliacaoServico(profissaoPesquisada, cidadeAtendimento, horarioAtendimentoInicio, horarioAtendimentoFim);
                request.setAttribute("listaProfissionaisFiltrados", listaProfissionaisFiltrados);
                request.getRequestDispatcher("Consultas/ListaProfissionaisFiltrados.jsp").forward(request, response);

                Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.getById(cliente.getId());
                Logs logs1 = new Logs();

                logs1.setUsuario(usuario);
                Date dataLog1 = new Date();
                logs1.setData(dataLog1);
                logs1.setEvento("Consulta");
                if (usuario.getPerfilAcesso().equals(PerfilAcesso.CLIENTE)) {
                    logs1.setDescricaoEvento("O cliente realizou uma busca por profissional e ordenou por Avaliação de Serviço.");
                } else if (usuario.getPerfilAcesso().equals(PerfilAcesso.PROFISSIONAL)) {
                    logs1.setDescricaoEvento("O profissional realizou uma busca por profissional e ordenou por Avaliação de Serviço.");

                }
                LogsDAO logsDAO = new LogsDAO();
                logsDAO.cadastrar(logs1);

            } catch (Exception ex) {
                Logger.getLogger(ProfissionalControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("AVALIACAOCUSTO".equals(ordenacao)) {
            try {
                /*            passa por parâmetro a profissão pesquisada para o método buscarProfissionaisPorProfissao
            Traz uma lista de pessoas que exerce a profissão pesquisada*/
                List<Profissional> listaProfissionaisFiltrados = profissionalDAO.buscarProfissionaisPorAvaliacaoCusto(profissaoPesquisada, cidadeAtendimento, horarioAtendimentoInicio, horarioAtendimentoFim);
                request.setAttribute("listaProfissionaisFiltrados", listaProfissionaisFiltrados);
                request.getRequestDispatcher("Consultas/ListaProfissionaisFiltrados.jsp").forward(request, response);
                Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.getById(cliente.getId());
                Logs logs1 = new Logs();

                logs1.setUsuario(usuario);
                Date dataLog1 = new Date();
                logs1.setData(dataLog1);
                logs1.setEvento("Consulta");
                if (usuario.getPerfilAcesso().equals(PerfilAcesso.CLIENTE)) {
                    logs1.setDescricaoEvento("O cliente realizou uma busca por profissional e ordenou por Avaliação de Custo.");
                } else if (usuario.getPerfilAcesso().equals(PerfilAcesso.PROFISSIONAL)) {
                    logs1.setDescricaoEvento("O profissional realizou uma busca por profissional e ordenou por Avaliação de Custo.");

                }
                LogsDAO logsDAO = new LogsDAO();
                logsDAO.cadastrar(logs1);
            } catch (Exception ex) {
                Logger.getLogger(ProfissionalControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("AVALIACAORAPIDEZ".equals(ordenacao)) {
            try {
                /*            passa por parâmetro a profissão pesquisada para o método buscarProfissionaisPorProfissao
            Traz uma lista de pessoas que exerce a profissão pesquisada*/
                List<Profissional> listaProfissionaisFiltrados = profissionalDAO.buscarProfissionaisPorAvaliacaoRapidez(profissaoPesquisada, cidadeAtendimento, horarioAtendimentoInicio, horarioAtendimentoFim);
                request.setAttribute("listaProfissionaisFiltrados", listaProfissionaisFiltrados);
                request.getRequestDispatcher("Consultas/ListaProfissionaisFiltrados.jsp").forward(request, response);
                Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.getById(cliente.getId());
                Logs logs1 = new Logs();

                logs1.setUsuario(usuario);
                Date dataLog1 = new Date();
                logs1.setData(dataLog1);
                logs1.setEvento("Consulta");
                if (usuario.getPerfilAcesso().equals(PerfilAcesso.CLIENTE)) {
                    logs1.setDescricaoEvento("O cliente realizou uma busca por profissional e ordenou por Avaliação de Rapidez.");
                } else if (usuario.getPerfilAcesso().equals(PerfilAcesso.PROFISSIONAL)) {
                    logs1.setDescricaoEvento("O profissional realizou uma busca por profissional e ordenou por Avaliação de Rapidez.");

                }
                LogsDAO logsDAO = new LogsDAO();
                logsDAO.cadastrar(logs1);
            } catch (Exception ex) {
                Logger.getLogger(ProfissionalControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("A-Z".equals(ordenacao)) {
            try {
                /*            passa por parâmetro a profissão pesquisada para o método buscarProfissionaisPorProfissao
            Traz uma lista de pessoas que exerce a profissão pesquisada*/
                List<Profissional> listaProfissionaisFiltrados = profissionalDAO.buscarProfissionaisPorAvaliacaoAZ(profissaoPesquisada, cidadeAtendimento, horarioAtendimentoInicio, horarioAtendimentoFim);
                request.setAttribute("listaProfissionaisFiltrados", listaProfissionaisFiltrados);
                request.getRequestDispatcher("Consultas/ListaProfissionaisFiltrados.jsp").forward(request, response);
                Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.getById(cliente.getId());
                Logs logs1 = new Logs();

                logs1.setUsuario(usuario);
                Date dataLog1 = new Date();
                logs1.setData(dataLog1);
                logs1.setEvento("Consulta");
                if (usuario.getPerfilAcesso().equals(PerfilAcesso.CLIENTE)) {
                    logs1.setDescricaoEvento("O cliente realizou uma busca por profissional e ordenou os nomes de A-Z.");
                } else if (usuario.getPerfilAcesso().equals(PerfilAcesso.PROFISSIONAL)) {
                    logs1.setDescricaoEvento("O profissional realizou uma busca por profissional e ordenou os nomes de A-Z.");

                }
                LogsDAO logsDAO = new LogsDAO();
                logsDAO.cadastrar(logs1);
            } catch (Exception ex) {
                Logger.getLogger(ProfissionalControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("Z-A".equals(ordenacao)) {
            try {
                /*            passa por parâmetro a profissão pesquisada para o método buscarProfissionaisPorProfissao
            Traz uma lista de pessoas que exerce a profissão pesquisada*/
                List<Profissional> listaProfissionaisFiltrados = profissionalDAO.buscarProfissionaisPorAvaliacaoZA(profissaoPesquisada, cidadeAtendimento, horarioAtendimentoInicio, horarioAtendimentoFim);
                request.setAttribute("listaProfissionaisFiltrados", listaProfissionaisFiltrados);
                request.getRequestDispatcher("Consultas/ListaProfissionaisFiltrados.jsp").forward(request, response);

                Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.getById(cliente.getId());
                Logs logs1 = new Logs();

                logs1.setUsuario(usuario);
                Date dataLog1 = new Date();
                logs1.setData(dataLog1);
                logs1.setEvento("Consulta");
                if (usuario.getPerfilAcesso().equals(PerfilAcesso.CLIENTE)) {
                    logs1.setDescricaoEvento("O cliente realizou uma busca por profissional e ordenou os nomes de Z-A.");
                } else if (usuario.getPerfilAcesso().equals(PerfilAcesso.PROFISSIONAL)) {
                    logs1.setDescricaoEvento("O profissional realizou uma busca por profissional e ordenou os nomes de Z-A.");

                }
                LogsDAO logsDAO = new LogsDAO();
                logsDAO.cadastrar(logs1);
            } catch (Exception ex) {
                Logger.getLogger(ProfissionalControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void consultarPedidosProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {

        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");
        PedidoDAO pedidoDAO = new PedidoDAO();
        
        request.setAttribute("pedido", pedidoDAO.buscarPedidosProfissional(profissional.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarPedidos.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Pedido");
        logs1.setDescricaoEvento("O profissional realizou busca de todos os Pedidos.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);

    }

    public void consultarPedidosEmEsperaProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");


        
        request.setAttribute("pedido", pedidoDAO.buscarPedidosEmEsperaProfissional(profissional.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarPedidosEmEspera.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Pedido");
        logs1.setDescricaoEvento("O profissional realizou busca de todos os Pedidos Em Espera.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);

    }

    public void consultarPedidosAprovadosProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");


        
        request.setAttribute("pedido", pedidoDAO.buscarPedidosAprovadosProfissional(profissional.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarPedidosAprovados.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Pedido");
        logs1.setDescricaoEvento("O profissional realizou busca de todos os Pedidos Aprovados.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);

    }

    public void consultarPedidosReprovadosProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");


        
        request.setAttribute("pedido", pedidoDAO.buscarPedidosReprovadosProfissional(profissional.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarPedidosReprovados.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Pedido");
        logs1.setDescricaoEvento("O profissional realizou busca de todos os Pedidos Reprovados.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);

    }

    public void consultarPedidosCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
        
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        request.setAttribute("pedido", pedidoDAO.buscarPedidosClientes(cliente.getId()));

        VisitaDAO visitaDAO = new VisitaDAO();

        request.setAttribute("pedidoVisita", visitaDAO.buscarPedidosVisitasClientes(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarPedidos.jsp").forward(request, response);

//        Cadastrar o acesso do cliente à todos os pedidos
        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Pedido");
        logs1.setDescricaoEvento("O cliente realizou busca de todos os Pedidos.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);

    }

    public void consultarPedidosAprovadosCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
//        int idConsultarAp = Integer.parseInt(request.getParameter("idConsultarAp"));
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        request.setAttribute("pedido", pedidoDAO.buscarPedidosAprovadosClientes(cliente.getId()));

        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarPedidosAprovados.jsp").forward(request, response);

        //        Cadastrar o acesso do cliente à todos os pedidos
//        ClienteDAO clienteDAO = new ClienteDAO();
//        Cliente cliente = clienteDAO.getById(idConsultarAp);
        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Pedido");
        logs1.setDescricaoEvento("O cliente realizou busca de todos os Pedidos Aprovados.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarPedidosReprovadosCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
//        int idConsultarRep = Integer.parseInt(request.getParameter("idConsultarRep"));
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        
        request.setAttribute("pedido", pedidoDAO.buscarPedidosReprovadosClientes(cliente.getId()));

        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarPedidosReprovados.jsp").forward(request, response);
        //        Cadastrar o acesso do cliente à todos os pedidos
//        ClienteDAO clienteDAO = new ClienteDAO();
//        Cliente cliente = clienteDAO.getById(idConsultarRep);

        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Pedido");
        logs1.setDescricaoEvento("O cliente realizou busca de todos os Pedidos Reprovados.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarPedidosEmEsperaCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
//        int idConsultarEE = Integer.parseInt(request.getParameter("idConsultarEE"));
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        
        request.setAttribute("pedido", pedidoDAO.buscarPedidosEmEsperaClientes(cliente.getId()));
        VisitaDAO visitaDAO = new VisitaDAO();

        request.setAttribute("pedidoVisita", visitaDAO.buscarPedidosVisitasClientes(cliente.getId()));

        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarPedidosEmEspera.jsp").forward(request, response);

        //        Cadastrar o acesso do cliente à todos os pedidos
//        ClienteDAO clienteDAO = new ClienteDAO();
//        Cliente cliente = clienteDAO.getById(idConsultarEE);
        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Pedido");
        logs1.setDescricaoEvento("O cliente realizou busca de todos os Pedidos que estão Em Espera.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);

//        Logs logs2 = new Logs();
        logs1.setUsuario(usuario);
        Date dataLog2 = new Date();
        logs1.setData(dataLog2);
        logs1.setEvento("Pedido");
        logs1.setDescricaoEvento("O cliente realizou busca de todos os Pedidos que estão aguardando a confirmação da visita.");

        logsDAO.cadastrar(logs1);

    }

}
