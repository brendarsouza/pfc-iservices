/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AgendaDeServicoDAO;
import DAO.ClienteDAO;
import DAO.LogsDAO;
import DAO.OrcamentoDAO;
import DAO.ProfissionalDAO;
import DAO.PedidoDAO;
import DAO.UsuarioDAO;
import DAO.VisitaDAO;
import Model.AgendaDeServico;
import Model.Status_Orcamento;
import Model.Cliente;
import Model.Logs;
import Model.Orcamento;
import Model.Pedido;
import Model.Profissional;
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
@WebServlet(name = "OrcamentoControle", urlPatterns = {"/OrcamentoControle", "/enviarOrcamento", "/consultarOrcamentosEmEsperaProfissional", "/consultarOrcamentosAprovadosProfissional", "/consultarOrcamentosReprovadosProfissional", "/aceitarOrcamento", "/recusarOrcamento", "/consultarOrcamentoProfissional", "/consultarOrcamentoAJAX", "/consultarOrcamentoCliente", "/consultarOrcamentosAprovadosCliente", "/consultarOrcamentosEmEsperaCliente", "/consultarOrcamentosReprovadosCliente"})
public class OrcamentoControle extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*o request.getRequestURI(); pega a uri que está vindo do navegador e atribui na variavel uri*/
        String uri = request.getRequestURI();
        if (uri.equals(request.getContextPath() + "/enviarOrcamento")) {
            try {
                enviarOrcamento(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else /*se a uri for igual a cadastrarProfissional irá chamar o metodo cadastrarProfissional*/ if (uri.equals(request.getContextPath() + "/aceitarOrcamento")) {
            try {
                aceitarOrcamento(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/recusarOrcamento")) {
            try {
                recusarOrcamento(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*o request.getRequestURI(); pega a uri que está vindo do navegador e atribui na variavel uri*/
        String uri = request.getRequestURI();

        try {
            OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
            PedidoDAO pedidoDAO = new PedidoDAO();
            VisitaDAO visitaDAO = new VisitaDAO();

            AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();

            /* Recebe o id do profissional escolhido e confirma o valor não é nulo*/
            if (request.getParameter("idPedido") != null) {
                Pedido pedido = pedidoDAO.getById(Integer.parseInt(request.getParameter("idPedido")));
                request.setAttribute("pedido", pedido);
                request.setAttribute("visita", visitaDAO.buscarVisitasProfissional(pedido.getProfissional().getId()));
                request.setAttribute("orcamento", orcamentoDAO.buscarOrcamentoProfissional(pedido.getProfissional().getId()));
                request.setAttribute("agenda", agendaDAO.buscarAgendamentosProfissional(pedido.getProfissional().getId()));
                /* direciona para a página com o formulário para realizar o pedido*/
                request.getRequestDispatcher("Pedido/FormularioOrcamentoProfissional.jsp").forward(request, response);
            } else /* Recebe o id do profissional e confirma o valor não é nulo*/ if (request.getParameter("idAceitarOrcamento") != null) {

                request.setAttribute("orcamento", orcamentoDAO.getById(Integer.parseInt(request.getParameter("idAceitarOrcamento"))));
//                request.setAttribute("orcamento", orcamentoDAO.getById(Integer.parseInt(request.getParameter("idOrcamento"))));
                /* direciona para a página de edição de cadastro*/
                request.getRequestDispatcher("Pedido/ConfirmarOrcamentoCliente.jsp").forward(request, response);

            } else if (request.getParameter("idRecusarOrcamento") != null) {
                /* consulta na DAO (PessoaDAO), se há esse id*/
                request.setAttribute("orcamento", orcamentoDAO.getById(Integer.parseInt(request.getParameter("idRecusarOrcamento"))));
//                request.setAttribute("orcamento", orcamentoDAO.getById(Integer.parseInt(request.getParameter("idOrcamento"))));
                /* direciona para a página de edição de cadastro*/
                request.getRequestDispatcher("Pedido/ConfirmarRecusaOrcamentoCliente.jsp").forward(request, response);

//            } else if (request.getParameter("idConsultarOrcamentoCliente") != null) {
//                consultarOrcamentoCliente(request, response);
            } else if (uri.equals(request.getContextPath() + "/consultarOrcamentoProfissional")) {
                try {
                    consultarOrcamentoProfissional(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (uri.equals(request.getContextPath() + "/consultarOrcamentoAJAX")) {
                try {
                    consultarOrcamentoAJAX(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (uri.equals(request.getContextPath() + "/consultarOrcamentosAprovadosProfissional")) {
                try {
                    consultarOrcamentosAprovadosProfissional(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (uri.equals(request.getContextPath() + "/consultarOrcamentosReprovadosProfissional")) {
                try {
                    consultarOrcamentosReprovadosProfissional(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (uri.equals(request.getContextPath() + "/consultarOrcamentosEmEsperaProfissional")) {
                try {
                    consultarOrcamentosEmEsperaProfissional(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (uri.equals(request.getContextPath() + "/consultarOrcamentoCliente")) {
                try {
                    consultarOrcamentoCliente(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (uri.equals(request.getContextPath() + "/consultarOrcamentosAprovadosCliente")) {
                try {
                    consultarOrcamentosAprovadosCliente(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (uri.equals(request.getContextPath() + "/consultarOrcamentosEmEsperaCliente")) {
                try {
                    consultarOrcamentosEmEsperaCliente(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (uri.equals(request.getContextPath() + "/consultarOrcamentosReprovadosCliente")) {
                try {
                    consultarOrcamentosReprovadosCliente(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(OrcamentoControle.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500, "ERRO! Não achei -_- ");
        }

    }

    public void enviarOrcamento(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {

        Orcamento orcamento = new Orcamento();

        orcamento.setDescricaoSolucao(request.getParameter("txtDescricaoSolucao"));
        orcamento.setPrazo(request.getParameter("txtPrazo"));
        orcamento.setGastos(request.getParameter("txtGastos"));
        orcamento.setPrecoFinal(Double.parseDouble(request.getParameter("txtPrecoFinal")));
        orcamento.setStatus_Orcamento(Status_Orcamento.EM_ESPERA);

        Date dataOrcamento = new Date();
        orcamento.setDataOrcamento(dataOrcamento);

        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
        int idProfissional = Integer.parseInt(request.getParameter("txtIdProfissional"));
        Profissional profissional = profissionalDAO.consultarPorId(idProfissional);

        PedidoDAO pedidoDAO = new PedidoDAO();
        int idPedido = Integer.parseInt(request.getParameter("txtIdPedido"));
        Pedido pedido = pedidoDAO.getById(idPedido);
        pedido.setStatus_Pedido(Status_Pedido.APROVADO);

        Date horarioVisita = new SimpleDateFormat("HH:mm").parse(request.getParameter("txtHorarioVisita"));

        Date dataPrevistaInicio = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataPrevistaInicio"));
        //Se a dataPrevistaInicio está apos dataOrcamento(dataAtual) OU a dataPrevistaInicio está igual dataOrcamento(dataAtual)
        if (dataPrevistaInicio.after(dataOrcamento) || dataPrevistaInicio.equals(dataOrcamento)) {
            System.out.println("Correto! A data do orçamento está após ou igual a data prevista para inciar o serviço.");

            //Se a dataPrevistaIncicio está depois da DataDesejadaParaReceberOServiço OU dataPrevistaInicio esta igual a DataDesejadaParaReceberOServiço
            if (dataPrevistaInicio.after(pedido.getDataDesejada()) || dataPrevistaInicio.equals(pedido.getDataDesejada())) {
                System.out.println("Correto! A data prevista para o inicio do serviço está após da data solicitada no pedido.");

                //Se horarioVisita está depois do HorarioInicialDisponivel.Pedido OU horarioVisita está igual horarioInicialDisponivel.Pedido
                if (horarioVisita.after(pedido.getHorarioInicialDisponivel()) || horarioVisita.equals(pedido.getHorarioInicialDisponivel())) {

                    //Se horarioVisita antes do HorarioFinalDisponivel.Pedido 
                    if (horarioVisita.before(pedido.getHorarioFinalDisponivel()) || horarioVisita.equals(pedido.getHorarioFinalDisponivel())) {
                        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
                        VisitaDAO visitaDAO = new VisitaDAO();
                        AgendaDeServicoDAO agendaDAO = new AgendaDeServicoDAO();
//                        List<Visita> validacaoVisita = visitaDAO.verificarDataHoraVisita(dataPrevistaInicio, horarioVisita);
                        List<Orcamento> validacaoOrcamento = orcamentoDAO.verificarDataHoraOrcamento(dataPrevistaInicio, horarioVisita);
//                        List<AgendaDeServico> validacaoAgendamento = agendaDAO.verificarDataHoraAgendamento(dataPrevistaInicio, horarioVisita);
                        //se a listagem de visitas estiver vazia e lista de orcmentos estiver vazia e lista de agendamentos
                        if (validacaoOrcamento.isEmpty()){
//                                validacaoVisita.isEmpty() && validacaoOrcamento.isEmpty() && validacaoAgendamento.isEmpty()) {

                            orcamento.setDataPrevistaInicio(dataPrevistaInicio);
                            orcamento.setHoraVisita(horarioVisita);

                            ClienteDAO clienteDAO = new ClienteDAO();
                            int idCliente = Integer.parseInt(request.getParameter("txtIdCliente"));

                            Cliente cliente = clienteDAO.getById(idCliente);

                            cliente.setId(idCliente);

                            profissional.setId(idProfissional);

                            pedido.setId(idPedido);

                            orcamento.setPedido(pedido);

                            pedido.setCliente(cliente);
                            pedidoDAO.atualizar(pedido);
                            orcamentoDAO.cadastrar(orcamento);

//        Instânciando o objeto email 
                            Email email = new Email();

                            Cliente pessoaCliente = new ClienteDAO().consultarPorId(idCliente);
                            email.setNomeDestinatario(pessoaCliente.getNome());
                            email.setEmailDestinatario(pessoaCliente.getUsuario().getEmail());

                            Profissional pessoaProfissional = new ProfissionalDAO().consultarPorId(idProfissional);

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
                            email.setAssunto("ISERVICES - Orçamento");

                            email.setMensagem("O pedido efetuado, recebeu um orcamento"
                                    + "\nNome do Profissional: " + pessoaProfissional.getNome()
                                    + "\nEmail do Profissional: " + pessoaProfissional.getUsuario().getEmail()
                                    + "\nDescrição da Solução: " + orcamento.getDescricaoSolucao()
                                    + "\nData prevista para o início do serviço: " + orcamento.getDataPrevistaInicio()
                                    + "\nPrazo para realização do Serviço: " + orcamento.getPrazo()
                                    + "\nGastos previstos " + orcamento.getGastos()
                                    + "\n"
                                    + "\nPreço total: R$" + orcamento.getPrecoFinal()
                                    + "\nData do Orçamento: " + orcamento.getDataOrcamento() + " às " + orcamento.getDataOrcamento().getHours() + ":" + orcamento.getDataOrcamento().getMinutes() + " hs."
                                    + "\nISERVICES. "
                            );

//        envia o email
                            if (email.enviar()) {
                                response.getWriter().println("Enviado com sucesso");
                            } else {
                                response.getWriter().println("Nao enviou");
                            }
                            request.setAttribute("cliente", cliente);

                            response.sendRedirect("Pedido/ConfirmacaoEnvioOrcamentoProfissional.jsp");

//                    Cliente cliente = clienteDAO.getById(idConsultar);
                            int idUsuario = profissional.getUsuario().getId();

                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                            Usuario usuario = usuarioDAO.getById(idUsuario);
                            Logs logs1 = new Logs();

                            logs1.setUsuario(usuario);
                            Date dataLog1 = new Date();
                            logs1.setData(dataLog1);
                            logs1.setEvento("Orçamento");
                            logs1.setDescricaoEvento("O profissional realizou um orçamento baseado no pedido.");
                            LogsDAO logsDAO = new LogsDAO();
                            logsDAO.cadastrar(logs1);
                        } else {
                            
//                            for (Visita v : validacaoVisita) {
                                for (Orcamento o : validacaoOrcamento) {
//                                    for (AgendaDeServico a : validacaoAgendamento) {
                                        //se status da visita for cancelado ou a reprovada ou status orcamento for reprovado ou agenda de servicos for  cancelado
                                        if (o.getStatus_Orcamento() == Status_Orcamento.REPROVADO ){
//                                                v.getStatus_Visita() == Status_Visita.CANCELADA || v.getStatus_Visita() == Status_Visita.REPROVADA || o.getStatus_Orcamento() == Status_Orcamento.REPROVADO || a.getStatus_servico() == Status_Servico.CANCELADO) {
                                            orcamento.setDataPrevistaInicio(dataPrevistaInicio);
                                            orcamento.setHoraVisita(horarioVisita);

                                            ClienteDAO clienteDAO = new ClienteDAO();
                                            int idCliente = Integer.parseInt(request.getParameter("txtIdCliente"));

                                            Cliente cliente = clienteDAO.consultarPorId(idCliente);

                                            cliente.setId(idCliente);

                                            profissional.setId(idProfissional);

                                            pedido.setId(idPedido);

                                            orcamento.setPedido(pedido);

                                            pedido.setCliente(cliente);
                                            orcamentoDAO.cadastrar(orcamento);

//        Instânciando o objeto email 
                                            Email email = new Email();

                                            Cliente pessoaCliente = new ClienteDAO().consultarPorId(idCliente);
                                            email.setNomeDestinatario(pessoaCliente.getNome());
                                            email.setEmailDestinatario(pessoaCliente.getUsuario().getEmail());

                                            Profissional pessoaProfissional = new ProfissionalDAO().consultarPorId(idProfissional);

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
                                            email.setAssunto("ISERVICES - Orçamento");

                                            email.setMensagem("O pedido efetuado, recebeu um orcamento"
                                                    + "\nNome do Profissional: " + pessoaProfissional.getNome()
                                                    + "\nEmail do Profissional: " + pessoaProfissional.getUsuario().getEmail()
                                                    + "\nDescrição da Solução: " + orcamento.getDescricaoSolucao()
                                                    + "\nData prevista para o início do serviço: " + orcamento.getDataPrevistaInicio()
                                                    + "\nPrazo para realização do Serviço: " + orcamento.getPrazo()
                                                    + "\nGastos previstos " + orcamento.getGastos()
                                                    + "\n"
                                                    + "\nPreço total: R$" + orcamento.getPrecoFinal()
                                                    + "\nData do Orçamento: " + orcamento.getDataOrcamento() + " às " + orcamento.getDataOrcamento().getHours() + ":" + orcamento.getDataOrcamento().getMinutes() + " hs."
                                                    + "\nISERVICES. "
                                            );
//        envia o email
                                            if (email.enviar()) {
                                                response.getWriter().println("Enviado com sucesso");
                                            } else {
                                                response.getWriter().println("Nao enviou");
                                            }
                                            request.setAttribute("cliente", cliente);

                                            response.sendRedirect("Pedido/ConfirmacaoEnvioOrcamentoProfissional.jsp");

//                    Cliente cliente = clienteDAO.getById(idConsultar);
                                            int idUsuario = profissional.getUsuario().getId();

                                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                                            Usuario usuario = usuarioDAO.getById(idUsuario);
                                            Logs logs1 = new Logs();

                                            logs1.setUsuario(usuario);
                                            Date dataLog1 = new Date();
                                            logs1.setData(dataLog1);
                                            logs1.setEvento("Orçamento");
                                            logs1.setDescricaoEvento("O profissional realizou um orçamento baseado no pedido.");
                                            LogsDAO logsDAO = new LogsDAO();
                                            logsDAO.cadastrar(logs1);
                                        } else {
                                            System.out.println("Erro! Já existe Visitas Agendadas nessa data e Horário.");
                                            response.sendRedirect("Erro/ErroDataHorarioVisita.jsp");
                                        }
//                                    }
                                }
//                            }
                        }
                    } else {

                        System.out.println("Erro! Horário Visita está após do Horário Final Disponível solicitado no Pedido");
                        response.sendRedirect("Erro/ErroHorarioOrcamentoPosteriorHorarioFinalSolicitado.jsp");
                    }
                } else {
                    System.out.println("Erro! Horário Visita está antes do Horario Inicial Disponível solicitado no Pedido");
                    response.sendRedirect("Erro/ErroHorarioOrcamentoAntesHorarioInicialSolicitada.jsp");
                }
            } else {
                System.out.println("Erro! A data prevista para iniciar o serviço está antes da data desejada para receber o serviço, conforme solicitado no Pedido!");
                response.sendRedirect("Erro/ErroDataOrcamentoAntesDataDesejadaReceberServico.jsp");
            }
        } else {

            System.out.println("Erro! A data prevista para iniciar o serviço está antes da data orçamento!");
            response.sendRedirect("Erro/ErroDataOrcamentoAntesDataPrevistaIniciarServico.jsp");
        }
    }

    /* método para cadastrar Profissional*/
    public void aceitarOrcamento(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, ParseException {

//       Instânciando o objeto usuario
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
//        orcamento.setId(Integer.parseInt(request.getParameter("txtIdOrcamento")));
        int idOrcamento = Integer.parseInt(request.getParameter("txtIdOrcamento"));
        Orcamento orcamento = orcamentoDAO.getById(idOrcamento);
        orcamento.setStatus_Orcamento(Status_Orcamento.APROVADO);

        Pedido pedido = new Pedido();
        pedido.setId(Integer.parseInt(request.getParameter("txtIdPedido")));
        pedido.setDescricaoProblema(request.getParameter("txtDescricaoProblema"));
        Date dataDesejada = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataDesejada"));
        pedido.setDataDesejada(dataDesejada);
        Date horarioInicial = new SimpleDateFormat("HH:mm").parse(request.getParameter("txtHorarioInicialDisponivel"));
        pedido.setHorarioInicialDisponivel(horarioInicial);
        Date horarioFinal = new SimpleDateFormat("HH:MM").parse(request.getParameter("txtHorarioFinalDisponivel"));
        pedido.setHorarioFinalDisponivel(horarioFinal);
        pedido.setStatus_Pedido(Status_Pedido.APROVADO);

        Date dataPedido = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataPedido"));
        pedido.setDataPedido(dataPedido);

        Cliente cliente = new Cliente();
        int idCliente = Integer.parseInt(request.getParameter("txtIdCliente"));
        cliente.setId(idCliente);
        pedido.setCliente(cliente);
        Profissional profissional = new Profissional();
        int idProfissional = Integer.parseInt(request.getParameter("txtIdProfissional"));
        profissional.setId(idProfissional);
        pedido.setProfissional(profissional);

//        instância o objeto pedidoDAO e faz o cadastro        
        PedidoDAO pedidoDAO = new PedidoDAO();
        pedidoDAO.atualizar(pedido);

        orcamento.setPedido(pedido);
        AgendaDeServicoDAO agendaDeServicoDAO = new AgendaDeServicoDAO();
        AgendaDeServico agendaDeServico = new AgendaDeServico();
        agendaDeServico.setDataAgendamento(orcamento.getDataPrevistaInicio());
        agendaDeServico.setOrcamento(orcamento);
        agendaDeServico.setStatus_servico(Status_Servico.EM_ESPERA);
        agendaDeServico.setHoraAgendamento(orcamento.getHoraVisita());

        agendaDeServicoDAO.cadastrar(agendaDeServico);

        orcamentoDAO.atualizar(orcamento);

        //        Instânciando o objeto email 
        Email email = new Email();
        Cliente pessoaCliente = new ClienteDAO().consultarPorId(idCliente);
        Profissional pessoaProfissional = new ProfissionalDAO().consultarPorId(idProfissional);

        email.setNomeDestinatario(pessoaProfissional.getNome());
        email.setEmailDestinatario(pessoaProfissional.getUsuario().getEmail());
        response.getWriter().println(pessoaProfissional.getUsuario().getEmail());

        email.setAssunto("ISERVICES - Orcamento Aprovado");

        email.setMensagem("O orcamento efetuado, foi aprovado!"
                + "\n Número do Pedido: " + orcamento.getPedido().getId()
                + "\n Número do Orçamento: " + orcamento.getId()
                + "\nNome do Cliente: " + pessoaCliente.getNome()
                + "\nData Agendada: " + orcamento.getDataPrevistaInicio()
                + "\nDescrição do Pedido: " + orcamento.getPedido().getDescricaoProblema()
                + "\nDescrição da Solução: " + orcamento.getDescricaoSolucao()
                + "\nValor total serviço: " + orcamento.getPrecoFinal()
                + "\n"
                + "\nISERVICES. "
        );
//        envia o email
        if (email.enviar()) {
            response.getWriter().println("Enviado com sucesso");
        } else {
            response.getWriter().println("Nao enviou");
        }
        request.setAttribute("cliente", cliente);

        response.sendRedirect("Pedido/ConfirmacaoOrcamentoCliente.jsp");

//        ClienteDAO clienteDAO = new ClienteDAO();
//        Cliente cliente = clienteDAO.getById(idConsultar);
        int idUsuario = Integer.parseInt(request.getParameter("txtIdUsuario"));
//                cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Orçamento");
        logs1.setDescricaoEvento("O cliente aceitou o orçamento de recebido.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);

    }

    public void recusarOrcamento(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, ParseException {

//       Instânciando o objeto usuario
        int idOrcamento = Integer.parseInt(request.getParameter("txtIdOrcamento"));
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        Orcamento orcamento = orcamentoDAO.getById(idOrcamento);

        orcamento.setDescricaoSolucao(request.getParameter("txtDescricaoSolucao"));
        Date dataOrcamento = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataOrcamento"));
        orcamento.setDataOrcamento(dataOrcamento);
        Date dataInicio = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataInicio"));
        orcamento.setDataPrevistaInicio(dataInicio);
        orcamento.setPrazo(request.getParameter("txtPrazo"));

        orcamento.setGastos(request.getParameter("txtGastos"));
        orcamento.setPrecoFinal(Double.parseDouble(request.getParameter("txtPrecoFinal")));
        orcamento.setStatus_Orcamento(Status_Orcamento.REPROVADO);

        PedidoDAO pedidoDAO = new PedidoDAO();
        int idPedido = Integer.parseInt(request.getParameter("txtIdPedido"));
        Pedido pedido = pedidoDAO.getById(idPedido);
        pedido.setId(idPedido);
        pedido.setStatus_Pedido(Status_Pedido.REPROVADO);
        int idProfissional = Integer.parseInt(request.getParameter("txtIdProfissional"));
        int idCliente = Integer.parseInt(request.getParameter("txtIdCliente"));
        orcamento.setPedido(pedido);
//        Instânciando o objeto usuarioDAO e atualizando os dados
        pedidoDAO.atualizar(pedido);

        orcamentoDAO.atualizar(orcamento);

//        Instânciando o objeto email 
        Email email = new Email();
        Profissional pessoaProfissional = new ProfissionalDAO().consultarPorId(idProfissional);
        Cliente cliente = new ClienteDAO().consultarPorId(idCliente);

        email.setNomeDestinatario(pessoaProfissional.getNome());
        email.setEmailDestinatario(pessoaProfissional.getUsuario().getEmail());
        response.getWriter().println(pessoaProfissional.getUsuario().getEmail());

        email.setAssunto("ISERVICES - Orcamento Recusado");

        email.setMensagem("O orcamento efetuado, foi reprovado"
                + "\n Número do Pedido: " + orcamento.getPedido().getId()
                + "\n Número do Orçamento: " + orcamento.getId()
                + "\n Nome do Cliente: " + cliente.getNome()
                + "\n Data Orcamento: " + orcamento.getDataOrcamento()
                + "\n"
                + "\n ISERVICES. "
        );
//        envia o email
        if (email.enviar()) {
            response.getWriter().println("Enviado com sucesso");
        } else {
            response.getWriter().println("Nao enviou");
        }
        request.setAttribute("orcamento", orcamento);

        response.sendRedirect("Pedido/OrcamentoRecusadoCliente.jsp");

        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Orçamento");
        logs1.setDescricaoEvento("O cliente recusou o orçamento de recebido.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);

    }

    public void consultarOrcamentoAJAX(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {

        int idProfissional = Integer.parseInt(request.getParameter("id"));

        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        List<Orcamento> listaOrcamentos = orcamentoDAO.buscarOrcamentoProfissional(idProfissional);

        Gson gson = new Gson();
        String listaOrcamentoGson = gson.toJson(listaOrcamentos);

        response.getWriter().println(listaOrcamentoGson);
        System.out.println("Lista de orcamento:" + listaOrcamentoGson);

    }

    public void consultarOrcamentoProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");

        request.setAttribute("orcamento", orcamentoDAO.buscarOrcamentoProfissional(profissional.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarOrcamentos.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Orçamento");
        logs1.setDescricaoEvento("O profissional realizou uma busca de todos os orçamentos.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarOrcamentosAprovadosProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");

        request.setAttribute("orcamento", orcamentoDAO.buscarOrcamentosAprovadosProfissional(profissional.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarOrcamentosAprovados.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Orçamento");
        logs1.setDescricaoEvento("O profissional realizou uma busca de todos os orçamentos aprovados.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarOrcamentosReprovadosProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");

        request.setAttribute("orcamento", orcamentoDAO.buscarOrcamentosReprovadosProfissional(profissional.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarOrcamentosReprovados.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Orçamento");
        logs1.setDescricaoEvento("O profissional realizou uma busca de todos os orçamentos reprovados.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarOrcamentosEmEsperaProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        Profissional profissional = (Profissional) request.getSession().getAttribute("profissional");

        request.setAttribute("orcamento", orcamentoDAO.buscarOrcamentosEmEsperaProfissional(profissional.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Profissional/ListarOrcamentosEmEspera.jsp").forward(request, response);

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Orçamento");
        logs1.setDescricaoEvento("O profissional realizou uma busca de todos os orçamentos em espera.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarOrcamentoCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        request.setAttribute("orcamento", orcamentoDAO.buscarOrcamentoClientes(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarOrcamentos.jsp").forward(request, response);

        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Orçamento");
        logs1.setDescricaoEvento("O cliente realizou uma busca de todos os orçamentos.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarOrcamentosAprovadosCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        request.setAttribute("orcamento", orcamentoDAO.buscarOrcamentosAprovadosClientes(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarOrcamentosAprovados.jsp").forward(request, response);

        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Orçamento");
        logs1.setDescricaoEvento("O cliente realizou uma busca de todos os orçamentos aprovados.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarOrcamentosReprovadosCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        request.setAttribute("orcamento", orcamentoDAO.buscarOrcamentosReprovadosClientes(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarOrcamentosReprovados.jsp").forward(request, response);

        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Orçamento");
        logs1.setDescricaoEvento("O cliente realizou uma busca de todos os orçamentos reprovados.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }

    public void consultarOrcamentosEmEsperaCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        request.setAttribute("orcamento", orcamentoDAO.buscarOrcamentosEmEsperaClientes(cliente.getId()));
        /* direciona para a pagina de listagem de Pedidos*/
        request.getRequestDispatcher("Cliente/ListarOrcamentosEmEspera.jsp").forward(request, response);

        int idUsuario = cliente.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Orçamento");
        logs1.setDescricaoEvento("O cliente realizou uma busca de todos os orçamentos em espera.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);
    }
}
