/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ClienteDAO;
import DAO.EnderecoDAO;
import DAO.LogsDAO;
import DAO.PedidoDAO;
import DAO.ProfissionalDAO;
import DAO.UsuarioDAO;
import Model.Cliente;
import Model.Endereco;
import Model.Logs;
import Model.Pedido;
import Model.PerfilAcesso;
import Model.Pessoa;
import Model.Profissional;
import Model.Status;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author brenda
 */
@WebServlet(name = "ControleAcessoCliente", urlPatterns = {"/ControleAcessoCliente"})
public class ControleAcessoCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

//            A variavel acao recebe o valor do parametro acao            
            String acao = request.getParameter("acao");
            LogsDAO logsDAO = new LogsDAO();

//            se acao for Entrar            
            if (acao.equals("Entrar")) {
                Usuario usuario = new Usuario();

                usuario.setEmail(request.getParameter("txtEmail"));
                String senha = request.getParameter("txtSenha");
                usuario.setSenha(Usuario.encryptBlowfish(senha, senha));
//                usuario.setSenha(usuario.criptografa(request.getParameter("txtSenha")));

//                Instância o objeto usuarioDAO                
                UsuarioDAO usuarioDAO = new UsuarioDAO();

//                valida os dados o login do cliente                
                Usuario usuarioAutenticado = usuarioDAO.validarLogin(usuario);

//                se o usuarioAuticado não for nulo                
                if (usuarioAutenticado != null) {

                    Logs logs1 = new Logs();

                    logs1.setUsuario(usuarioAutenticado);
                    Date dataLog1 = new Date();
                    logs1.setData(dataLog1);
                    logs1.setEvento("Login Cliente");
                    logs1.setDescricaoEvento("O usuário foi autenticado no sistema.");

                    logsDAO.cadastrar(logs1);

                    //se o perfil de acesso for Cliente                    
                    if (usuarioAutenticado.getPerfilAcesso() == PerfilAcesso.CLIENTE) {
                        if (usuarioAutenticado.getStatus() == Status.ATIVO) {
                            Logs logs2 = new Logs();
                            logs2.setUsuario(usuarioAutenticado);
                            Date dataLog2 = new Date();
                            logs2.setData(dataLog2);
                            logs2.setEvento("Login Cliente");
                            logs2.setDescricaoEvento("O usuário autenticado realizou o login no sistema.");

                            logsDAO.cadastrar(logs2);

                            //cria uma sessao para o usuario
                            HttpSession sessaoUsuario = request.getSession();
                            sessaoUsuario.setAttribute("usuarioAutenticado", usuarioAutenticado);

                            ClienteDAO clienteDAO = new ClienteDAO();

//                        consulta pelo usuarioAutenticado.getId()
                            Usuario usuarioP = (Usuario) usuarioDAO.consultarPorId(usuarioAutenticado.getId());
                            Cliente clienteUsuario = (Cliente) clienteDAO.consultarUsuarioId(usuarioP);
                            sessaoUsuario.setAttribute("cliente", clienteUsuario);

                            RequestDispatcher rd = request.getRequestDispatcher("Cliente/AreaCliente.jsp");

                            Logs logs3 = new Logs();
                            logs3.setUsuario(usuarioP);
                            Date dataLog3 = new Date();
                            logs3.setData(dataLog3);
                            logs3.setEvento("Acesso à Área de Cliente");
                            logs3.setDescricaoEvento("O usuário acessou a àrea do cliente.");
                            logsDAO.cadastrar(logs3);

                            rd.forward(request, response);
                        }
                        //se não estiver ativo
                        RequestDispatcher rd = request.getRequestDispatcher("/LoginCliente/AtivarLogin.jsp");

                        rd.forward(request, response);
                    } else {
//                        se perfil de acesso não for Profissional
                        RequestDispatcher rd = request.getRequestDispatcher("/erroAutenticacao.jsp");

                        rd.forward(request, response);

                    }
                } //se for igual a nulo   
                else {
                    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                    request.setAttribute("msg", "Login ou Senha Incorreto!");
                    rd.forward(request, response);
                }
//                se a ação for Sair 
            } else if (acao.equals("Sair")) {
                HttpSession sessaoUsuario = request.getSession();
                sessaoUsuario.removeAttribute("usuarioAutenticado");
                response.sendRedirect("logout.jsp");

                int idUsuario = Integer.parseInt(request.getParameter("txtIdUsuario"));

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuarioConsultado = usuarioDAO.getById(idUsuario);

                Logs logs4 = new Logs();
                logs4.setUsuario(usuarioConsultado);
                Date dataLog4 = new Date();
                logs4.setData(dataLog4);
                logs4.setEvento("Login Cliente");
                logs4.setDescricaoEvento("O usuário autenticado realizou o logout do sistema.");
                logsDAO.cadastrar(logs4);

//                se a ação for Inativar
            } else if (acao.equals("Inativar")) {
                Usuario usuario = new Usuario();

                usuario.setEmail(request.getParameter("txtEmail"));
                String senha = request.getParameter("txtSenha");
                usuario.setSenha(Usuario.encryptBlowfish(senha, senha));

//                Instância o objeto usuarioDAO                
                UsuarioDAO usuarioDAO = new UsuarioDAO();

//                valida os dados o login do cliente                
                Usuario usuarioAutenticado = usuarioDAO.validarLogin(usuario);

//                se o usuarioAuticado não for nulo                
                if (usuarioAutenticado != null) {

                    Logs logs1 = new Logs();

                    logs1.setUsuario(usuarioAutenticado);
                    Date dataLog1 = new Date();
                    logs1.setData(dataLog1);
                    logs1.setEvento("Login Cliente");
                    logs1.setDescricaoEvento("O usuário foi autenticado no sistema.");

                    logsDAO.cadastrar(logs1);

                    //se o perfil de acesso for Cliente                    
                    if (usuarioAutenticado.getPerfilAcesso() == PerfilAcesso.CLIENTE) {

                        Logs logs2 = new Logs();
                        logs2.setUsuario(usuarioAutenticado);
                        Date dataLog2 = new Date();
                        logs2.setData(dataLog2);
                        logs2.setEvento("Login Cliente");
                        logs2.setDescricaoEvento("O usuário autenticado realizou o login no sistema.");

                        logsDAO.cadastrar(logs2);

                        //cria uma sessao para o usuario
                        HttpSession sessaoUsuario = request.getSession();
                        sessaoUsuario.setAttribute("usuarioAutenticado", usuarioAutenticado);
//                        consulta pelo usuarioAutenticado.getId()
                        Usuario usuarioP = (Usuario) usuarioDAO.consultarPorId(usuarioAutenticado.getId());
                        usuarioP.setStatus(Status.INATIVO);
                        UsuarioDAO usuario1DAO = new UsuarioDAO();
                        usuario1DAO.atualizar(usuarioP);

                        RequestDispatcher rd = request.getRequestDispatcher("LoginCliente/InativacaoConfirmada.jsp");

                        Logs logs5 = new Logs();
                        logs5.setUsuario(usuarioP);
                        Date dataLog5 = new Date();
                        logs5.setData(dataLog5);
                        logs5.setEvento("Login Cliente");
                        logs5.setDescricaoEvento("O usuário inativou o acesso.");
                        logsDAO.cadastrar(logs5);

                        rd.forward(request, response);
                    } else {
//                        se perfil de acesso não for Profissional
                        RequestDispatcher rd = request.getRequestDispatcher("/erroAutenticacao.jsp");
                        rd.forward(request, response);
                    }
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                    request.setAttribute("msg", "Login ou Senha Incorreto!");
                    rd.forward(request, response);
                }
            } else if (acao.equals("Ativar")) {
                Usuario usuario = new Usuario();

                usuario.setEmail(request.getParameter("txtEmail"));
                String senha = request.getParameter("txtSenha");
                usuario.setSenha(Usuario.encryptBlowfish(senha, senha));

//                Instância o objeto usuarioDAO                
                UsuarioDAO usuarioDAO = new UsuarioDAO();

//                valida os dados o login do cliente                
                Usuario usuarioAutenticado = usuarioDAO.validarLogin(usuario);

//                se o usuarioAuticado não for nulo                
                if (usuarioAutenticado != null) {
//
                    Logs logs1 = new Logs();

                    logs1.setUsuario(usuarioAutenticado);
                    Date dataLog1 = new Date();
                    logs1.setData(dataLog1);
                    logs1.setEvento("Login Cliente");
                    logs1.setDescricaoEvento("O usuário foi autenticado no sistema.");

                    logsDAO.cadastrar(logs1);

                    //se o perfil de acesso for Cliente                    
                    if (usuarioAutenticado.getPerfilAcesso() == PerfilAcesso.CLIENTE) {
                        if (usuarioAutenticado.getStatus() == Status.INATIVO) {
//                        

                            //cria uma sessao para o usuario
                            HttpSession sessaoUsuario = request.getSession();
                            sessaoUsuario.setAttribute("usuarioAutenticado", usuarioAutenticado);

                            ClienteDAO clienteDAO = new ClienteDAO();

//                        consulta pelo usuarioAutenticado.getId()
                            Usuario usuarioP = (Usuario) usuarioDAO.consultarPorId(usuarioAutenticado.getId());

                            usuarioP.setStatus(Status.ATIVO);
                            usuarioDAO.atualizar(usuarioP);

                            Logs logs2 = new Logs();
                            logs2.setUsuario(usuarioAutenticado);
                            Date dataLog2 = new Date();
                            logs2.setData(dataLog2);
                            logs2.setEvento("Login Cliente");
                            logs2.setDescricaoEvento("O usuário autenticado foi ativado no sistema.");

                            logsDAO.cadastrar(logs2);

                            Cliente clienteUsuario = (Cliente) clienteDAO.consultarUsuarioId(usuarioP);
                            sessaoUsuario.setAttribute("cliente", clienteUsuario);

                            RequestDispatcher rd = request.getRequestDispatcher("Cliente/AreaCliente.jsp");

                            Logs logs3 = new Logs();
                            logs3.setUsuario(usuarioP);
                            Date dataLog3 = new Date();
                            logs3.setData(dataLog3);
                            logs3.setEvento("Acesso à Área de Cliente");
                            logs3.setDescricaoEvento("O usuário acessou a àrea do cliente.");
                            logsDAO.cadastrar(logs3);

                            rd.forward(request, response);

                        }
//                        se perfil de acesso não for Profissional
                        RequestDispatcher rd = request.getRequestDispatcher("/LoginCliente/LoginCliente.jsp");
                        rd.forward(request, response);
                        return;

                    }
//                        se perfil de acesso não for Profissional
                    RequestDispatcher rd = request.getRequestDispatcher("/erroAutenticacao.jsp");
                    rd.forward(request, response);
                }
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                request.setAttribute("msg", "Login ou Senha Incorreto!");
                rd.forward(request, response);
            }

        } catch (Exception erro) {
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            request.setAttribute("erro", erro);
            rd.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
