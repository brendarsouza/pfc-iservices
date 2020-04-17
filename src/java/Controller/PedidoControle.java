/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ClienteDAO;
import DAO.LogsDAO;
import DAO.OrcamentoDAO;
import DAO.PedidoDAO;
import DAO.ProfissionalDAO;
import DAO.UsuarioDAO;
import DAO.VisitaDAO;
import Model.Status_Orcamento;
import Model.Cliente;
import Model.Logs;
import Model.Orcamento;
import Model.Pedido;
import Model.PerfilAcesso;
import Model.Profissional;
import Model.Status_Pedido;
import Model.Status_Visita;
import Model.Usuario;
import Model.Visita;
import Util.Email;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author brenda
 */
@WebServlet(name = "PedidoControle", urlPatterns = {"/PedidoControle", "/aceitarPedido", "/enviarSolicitacao", "/recusarPedido"})
public class PedidoControle extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ClienteDAO clienteDAO = new ClienteDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
        String uri = request.getRequestURI();
       
        
        if (request.getParameter("idRecusarPedido") != null) {
            request.setAttribute("pedido", pedidoDAO.consultarPorId(Integer.parseInt(request.getParameter("idRecusarPedido"))));
            /* direciona para a página com o formulário para realizar o pedido*/
            request.getRequestDispatcher("Pedido/ConfirmarRecusaPedidoProfissional.jsp").forward(request, response);

        } else if (request.getParameter("idEnviarEmail") != null) {

            request.setAttribute("pessoaProfissional", profissionalDAO.consultarPorId(Integer.parseInt(request.getParameter("idEnviarEmail"))));

            /* direciona para a página com o formulário para realizar o pedido*/
            request.getRequestDispatcher("Pedido/FormularioPedidoCliente.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals(request.getContextPath() + "/recusarPedido")) {
            try {
                recusarPedido(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }

            /*se a uri for igual a enviarSolicitacao irá chamar o metodo enviarSolicitacaoDeServico*/
        } else if (uri.equals(request.getContextPath() + "/enviarSolicitacao")) {
            try {
                enviarSolicitacaoDeServico(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    //    /* Esse método é responsável por receber os dados do pedido enviar para o profissional escolhido*/
    public void enviarSolicitacaoDeServico(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {

        Pedido pedido = new Pedido();

        pedido.setDescricaoProblema(request.getParameter("txtDescricaoProblema"));
        pedido.setStatus_Pedido(Status_Pedido.EM_ESPERA);

        Date dataPedido = new Date();
        pedido.setDataPedido(dataPedido);
        Date dataDesejada = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataDesejada"));

        try {
            //Se a dataPedido(DataAtual) for Antes da DataDesejada
            if (dataPedido.before(dataDesejada) || dataPedido.equals(dataDesejada)) {

                System.out.println("Correto! A data do pedido está após da data atual!");
                pedido.setDataDesejada(dataDesejada);

                Date horarioInicial = new SimpleDateFormat("HH:mm").parse(request.getParameter("txtHorarioInicialDisponivel"));
                Date horarioFinal = new SimpleDateFormat("HH:mm").parse(request.getParameter("txtHorarioFinalDisponivel"));

                ProfissionalDAO profissionalDAO = new ProfissionalDAO();
                int idProfissional = Integer.parseInt(request.getParameter("txtIdProfissional"));
                Profissional profissional = profissionalDAO.consultarPorId(idProfissional);

                //Se o horarioInicial Depois do HorarioDeAtendimentoInicioProfissional OU horarioInicial Igual o HorarioDeAtendimentoInicioProfissional 
                if (horarioInicial.after(profissional.getHorarioDeAtendimentoInicio()) || horarioInicial.equals(profissional.getHorarioDeAtendimentoInicio())) {

                    System.out.println(horarioInicial);
                    //Se o horarioInicial Antes HorarioFinal
                    if (horarioInicial.before(horarioFinal)) {
                        System.out.println("Horario Final: " + horarioFinal);
                        //Se o horarioInicial Antes do HorarioAtendimentoFinalProfissional
                        if (horarioInicial.before(profissional.getHorarioDeAtendimentoFim())) {
                            //Se o horarioInicial diferente do horarioFinal
                            if ((horarioInicial != (horarioFinal))) {
                                //Se horarioFinal antes horarioAtendimentoFimProfissional OU horarioFinal IGUAL horaioAtendimentoFimProfissional
                                if ((horarioFinal.before(profissional.getHorarioDeAtendimentoFim())) || (horarioFinal.equals(profissional.getHorarioDeAtendimentoFim()))) {

                                    //Se horarioFinal DEPOIS horarioAtendimentoInicioProfissional E horarioFinal depois horarioInicial
                                    if ((horarioFinal.after(profissional.getHorarioDeAtendimentoInicio())) && (horarioFinal.after(horarioInicial))) {

                                        //horario fim antes horario de atendimento fim
                                        pedido.setHorarioInicialDisponivel(horarioInicial);
                                        pedido.setHorarioFinalDisponivel(horarioFinal);
                                        int idCliente = Integer.parseInt(request.getParameter("txtIdCliente"));

//        instância o objeto cliente e seta o id
                                        Cliente cliente = new Cliente();
                                        cliente.setId(idCliente);

                                        pedido.setCliente(cliente);

//        instância o objeto prof (profissional) e seta o id
                                        Profissional prof = new Profissional();
                                        prof.setId(idProfissional);

                                        pedido.setProfissional(prof);

                                        //        instância o objeto pedidoDAO e faz o cadastro        
                                        PedidoDAO pedidoDAO = new PedidoDAO();
                                        pedidoDAO.cadastrar(pedido);

//        Instânciando o objeto email 
                                        Email email = new Email();

                                        email.setNomeDestinatario(profissional.getNome());
                                        email.setEmailDestinatario(profissional.getUsuario().getEmail());
                                        response.getWriter().println(profissional.getUsuario().getEmail());

//        busca o cliente por id para informar ao profissional os dados do pedido
                                        Cliente pessoaCliente = new ClienteDAO().consultarPorId(idCliente);

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
                                        email.setAssunto("ISERVICES - Solicitação de Serviços");
                                        email.setMensagem("Você recebeu uma nova solicitação de serviço!"
                                                + "\nNome do Cliente: " + pessoaCliente.getNome()
                                                + "\nEmail do Cliente: " + pessoaCliente.getUsuario().getEmail()
                                                + "\nEndereco do Cliente: " + pessoaCliente.getEndereco().getRua() + ", nº " + pessoaCliente.getEndereco().getNumero()
                                                + " - " + pessoaCliente.getEndereco().getBairro() + " - " + pessoaCliente.getEndereco().getCidade() + " - " + pessoaCliente.getEndereco().getEstado() + " - " + pessoaCliente.getEndereco().getPais()
                                                + "\nDescrição do Problema: " + pedido.getDescricaoProblema()
                                                + "\nData desejada que inicie o serviço: " + pedido.getDataDesejada()
                                                + "\nO cliente está disponível das " + pedido.getHorarioInicialDisponivel()
                                                + "hs às " + pedido.getHorarioFinalDisponivel()
                                                + "hs."
                                                + "\n"
                                                + "\nData do Pedido: " + pedido.getDataPedido()
                                                + "\nISERVICES. "
                                        );

//        envia o email
                                        if (email.enviar()) {
                                            response.getWriter().println("Enviado com sucesso");
                                        } else {
                                            response.getWriter().println("Nao enviou");
                                        }
                                        request.setAttribute("cliente", cliente);

                                        response.sendRedirect("Cliente/ConfirmacaoEnvioSolicitacao.jsp");
                                        //        Cadastrar o acesso do cliente à todos os pedidos

                                        int idUsuario = Integer.parseInt(request.getParameter("txtIdUsuario"));

                                        UsuarioDAO usuarioDAO = new UsuarioDAO();

                                        Usuario usuario = usuarioDAO.getById(idUsuario);

                                        Logs logs1 = new Logs();

                                        logs1.setUsuario(usuario);
                                        Date dataLog1 = new Date();
                                        logs1.setData(dataLog1);
                                        logs1.setEvento("Pedido");
                                        if (usuario.getPerfilAcesso().equals(PerfilAcesso.CLIENTE)) {
                                            logs1.setDescricaoEvento("O cliente efetuou um pedido.");
                                        } else if (usuario.getPerfilAcesso().equals(PerfilAcesso.PROFISSIONAL)) {
                                            logs1.setDescricaoEvento("O profissional efetuou um pedido.");
                                        }
                                        LogsDAO logsDAO = new LogsDAO();
                                        logsDAO.cadastrar(logs1);
                                    } else {

//                                    horarioFim = false;
                                        //horario desejado para finalização do serviço está posterior ao horário de Inicio do serviço ou o horario de inicio de atendimento do profissional;
                                        System.out.println("Horário desejado para finalização do serviço está posterior ao horário de Inicio do serviço ou o horario de inicio de atendimento do profissional;");
                                        response.sendRedirect("Erro/ErroHoraPedidoPosteriorHorarioInicioOuHoraAtendimentoProfissional.jsp");
                                    }
                                } else {
//                                horarioInicio = false;
                                    //horario desejado para finalização do serviço está posterior ao horario de atendimento do profissional    
                                    System.out.println("Horário desejado para finalização do serviço está posterior ao horário de atendimento do profissional");
                                    response.sendRedirect("Erro/ErroHoraPedidoPosteriorHoraAtendimento.jsp");
                                }
                            } else {
//                            horarioInicio = false;
                                //Horário desejado para o início do serviço coincide com o horário desejado para finalizar o serviço ou o horário desejado para iniciar o serviço está após o horário desejado para finalização do serviço! 
                                System.out.println("Horário desejado para o início do serviço coincide com o horário desejado para finalizar o serviço ou o horário desejado para iniciar o serviço está após o horário desejado para finalização do serviço! ");
                                response.sendRedirect("Erro/ErroHorarioInicioIgualOuPosteriorHorarioFim.jsp");
                            }
                        } else {
//                        horarioInicio = false;
                            //O horário desejado para iniciar o serviço está posterior ao horário de atendimento do profissional
                            System.out.println("O horário desejado para iniciar o serviço está posterior ao horário de atendimento do profissional! ");
                            response.sendRedirect("Erro/ErroHoraPedidoPosteriorHoraAtendimento.jsp");
                        }
                    } else {
                        //Erro! O horário desejado para iniciar o serviço está posterior ao horario de finalização do serviço!
                        System.out.println("Erro! O horário desejado para iniciar o serviço está posterior ao horario de finalização do serviço!");
                        response.sendRedirect("Erro/ErroHoraInicialPedidoPosteriorHoraFinal.jsp");
                    }
                } else {

                    System.out.println("Erro! O horário desejado para iniciar o serviço está posterior ao horário de atendimento do profissional!");
                    response.sendRedirect("Erro/ErroHoraPedidoPosteriorHoraAtendimento.jsp");
                }
            } else {
                System.out.println("Erro! O Data desejada do serviço está no passado!");
                response.sendRedirect("Erro/ErroDataPedido.jsp");
            }
        } catch (Exception erro) {
            // Exibe uma mensagem de erro para o usuário.
            request.setAttribute("erro", erro);
            RequestDispatcher rd = request.getRequestDispatcher("//erro.jsp");
            rd.forward(request, response);

        }
    }

    public void recusarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {

//        Pedido pedido = new Pedido();
        PedidoDAO pedidoDAO = new PedidoDAO();
        int idPedido = Integer.parseInt(request.getParameter("txtIdPedido"));
        Pedido pedido = pedidoDAO.getById(idPedido);
//        pedido.setId(Integer.parseInt(request.getParameter("txtIdPedido")));        
        pedido.setDescricaoProblema(request.getParameter("txtDescricaoProblema"));
        Date dataDesejada = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataDesejada"));
        pedido.setDataDesejada(dataDesejada);
        Date horarioInicial = new SimpleDateFormat("HH:mm").parse(request.getParameter("txtHorarioInicialDisponivel"));
        pedido.setHorarioInicialDisponivel(horarioInicial);
        Date horarioFinal = new SimpleDateFormat("HH:mm").parse(request.getParameter("txtHorarioFinalDisponivel"));
        pedido.setHorarioFinalDisponivel(horarioFinal);
        pedido.setStatus_Pedido(Status_Pedido.REPROVADO);

        Date dataPedido = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataPedido"));
        pedido.setDataPedido(dataPedido);

        Cliente cliente = new Cliente();
        int idCliente = Integer.parseInt(request.getParameter("txtIdCliente"));
        cliente.setId(idCliente);
        pedido.setCliente(cliente);
        Profissional profissional = new Profissional();
        profissional.setId(Integer.parseInt(request.getParameter("txtIdProfissional")));
        pedido.setProfissional(profissional);

//        instância o objeto pedidoDAO e faz o cadastro        
        pedidoDAO.atualizar(pedido);
//        Instânciando o objeto email 
        Email email = new Email();

        Cliente pessoaCliente = new ClienteDAO().consultarPorId(idCliente);
        email.setNomeDestinatario(pessoaCliente.getNome());
        email.setEmailDestinatario(pessoaCliente.getUsuario().getEmail());

//        Envio de email para profissional, com o pedido do cliente e com os dados do cliente
        email.setAssunto("ISERVICES - Pedido Recusado");

        email.setMensagem("O pedido efetuado, foi recusado"
                + "\nNúmero do Pedido: " + pedido.getId()
                + "\nNome do Profissional " + pedido.getProfissional().getNome()
                + "\nData do Pedido: " + pedido.getDataPedido()
                + "\nISERVICES. "
        );

//        envia o email
        if (email.enviar()) {
            response.getWriter().println("Enviado com sucesso");
        } else {
            response.getWriter().println("Nao enviou");
        }
        request.setAttribute("cliente", cliente);

        response.sendRedirect("Pedido/PedidoRecusadoProfissional.jsp");

        int idUsuario = profissional.getUsuario().getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getById(idUsuario);
        Logs logs1 = new Logs();

        logs1.setUsuario(usuario);
        Date dataLog1 = new Date();
        logs1.setData(dataLog1);
        logs1.setEvento("Pedido");
        logs1.setDescricaoEvento("O pedido realizado pelo cliente foi recusado pelo profissional.");
        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs1);

    }

//    public void agendarVisita(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, Exception {
//
//        int idProfissional = Integer.parseInt(request.getParameter("txtIdProfissional"));
//        int idPedido = Integer.parseInt(request.getParameter("txtIdPedido"));
//        int idCliente = Integer.parseInt(request.getParameter("txtIdCliente"));
//
//        Visita visita = new Visita();
//        Date dataVisita = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataVisita"));
//        Date dataAtual = new Date();
//        Date horarioAtual = new Date();
//        PedidoDAO pedidoDAO = new PedidoDAO();
//        Pedido pedido = pedidoDAO.getById(idPedido);
//        ClienteDAO clienteDAO = new ClienteDAO();
//        Cliente cliente = clienteDAO.consultarPorId(idCliente);
//        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
//        Profissional profissional = profissionalDAO.consultarPorId(idProfissional);
//        boolean data;
//        VisitaDAO visitaDAO = new VisitaDAO();
//        List<Visita> visitas = visitaDAO.buscarVisitasProfissional(idProfissional);
//        String visitasJSON = null;
//        Gson gson = new Gson();
//        visitasJSON = gson.toJson(visitas);
//
//        response.getWriter().println(visitasJSON);
//        for (Visita v : visitas) {
//            if (dataVisita != (v.getDataVisita())) {
//                System.out.println("Já existe uma visita nesse dia!");
//            } else {
//                System.out.println("A data é diferente");
//            }
//        }
//        if (dataAtual.before(dataVisita) || dataAtual.equals(dataVisita)) {
//            data = true;
//            System.out.println("Correto! A data da visita está após da data atual!");
//            visita.setDataVisita(dataVisita);
//
//            Date horarioVisita = new SimpleDateFormat("HH:mm").parse(request.getParameter("txtHorarioVisita"));
//            boolean horaVisita;
//
////            if (horarioVisita.after(dataAtual)) { 
//            if ((horarioVisita.after(pedido.getHorarioInicialDisponivel())) || (horarioVisita.equals(pedido.getHorarioInicialDisponivel())) && (horarioVisita.before(pedido.getHorarioFinalDisponivel()))) {
//                horaVisita = true;
//
//                visita.setHoraVisita(horarioVisita);
//                visita.setStatus_Visita(Status_Visita.EM_ESPERA);
//                pedido.setStatus_Pedido(Status_Pedido.APROVADO);
//
//                cliente.setId(idCliente);
//                pedido.setCliente(cliente);
//
//                profissional.setId(idProfissional);
//                pedido.setProfissional(profissional);
//
//                visita.setPedido(pedido);
//
//                pedidoDAO.atualizar(pedido);
//                visitaDAO.cadastrar(visita);
//
////        Instânciando o objeto email 
//                Email email = new Email();
//
//                Cliente pessoaCliente = new ClienteDAO().consultarPorId(idCliente);
//
//                email.setNomeDestinatario(pessoaCliente.getNome());
//                email.setEmailDestinatario(pessoaCliente.getUsuario().getEmail());
//                response.getWriter().println(pessoaCliente.getUsuario().getEmail());
//
//                email.setAssunto("ISERVICES - Agendar Visita");
//
//                email.setMensagem("O pedido efetuado, recebeu uma solicitação de Visita para gerar um orcamento"
//                        + "\n Número do Pedido: " + visita.getPedido().getId()
//                        + "\nNome do Profissional: " + visita.getPedido().getProfissional().getNome()
//                        + "\nDescrição do Pedido: " + visita.getPedido().getDescricaoProblema()
//                        + "\nData desejada para a visita:" + visita.getDataVisita() + " às " + visita.getHoraVisita()
//                        + "\nISERVICES. "
//                );
////        envia o email
//                if (email.enviar()) {
//                    response.getWriter().println("Enviado com sucesso");
//                } else {
//                    response.getWriter().println("Nao enviou");
//                }
//                request.setAttribute("cliente", cliente);
//                response.sendRedirect("Visita/VisitaAgendadaProfissional.jsp");
//
//                //logs
//                int idUsuario = cliente.getUsuario().getId();
//
//                UsuarioDAO usuarioDAO = new UsuarioDAO();
//                Usuario usuario = usuarioDAO.getById(idUsuario);
//                Logs logs1 = new Logs();
//
//                logs1.setUsuario(usuario);
//                Date dataLog1 = new Date();
//                logs1.setData(dataLog1);
//                logs1.setEvento("Visita");
//                logs1.setDescricaoEvento("O cliente agendou uma visita.");
//                LogsDAO logsDAO = new LogsDAO();
//                logsDAO.cadastrar(logs1);
//
//            } else {
//
//                horaVisita = false;
//                request.setAttribute("profissional", profissional);
//                response.sendRedirect("Erro/ErroHorarioPedido.jsp");
//            }
//
//        } else if (dataAtual.after(dataVisita)) {
//            data = true;
//            System.out.println("Erro! É impossível solicitar um pedido antes da data atual.");
//            response.sendRedirect("Erro/ErroDataPedido.jsp");
//        } else {
//            System.out.println("Formato incorreto");
//        }
//    }

   

}
