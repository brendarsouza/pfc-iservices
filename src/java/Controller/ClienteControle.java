/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ClienteDAO;
import DAO.EnderecoDAO;
import DAO.LogsDAO;
import DAO.OrcamentoDAO;
import DAO.PedidoDAO;
import DAO.UsuarioDAO;
import Model.Cliente;
import Model.Endereco;
import Model.Logs;
import Model.PerfilAcesso;
import Model.Sexo;
import Model.Status;
import Model.Usuario;
import Util.Email;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.crypto.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import org.postgresql.util.UnixCrypt;

/**
 *
 * @author brenda
 */
@WebServlet(name = "ClienteControle", urlPatterns = {"/ClienteControle", "/areaCliente", "/cadastrarCliente", "/editarCliente"})
public class ClienteControle extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.equals(request.getContextPath() + "/cadastrarCliente")) {
            try {
                cadastrarCliente(request, response);
            } catch (ClassNotFoundException | ParseException | MessagingException ex) {
                Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/editarCliente")) {
            try {
                editarCliente(request, response);
            } catch (ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/areaCliente")) {
            areaCliente(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();

        try {
            if (request.getParameter("idEditar") != null) {
                ClienteDAO clienteDAO = new ClienteDAO();
                request.setAttribute("editarPessoa", clienteDAO.consultarPorId(Integer.parseInt(request.getParameter("idEditar"))));
                request.getRequestDispatcher("Cliente/EditarCliente.jsp").forward(request, response);
            }
        } catch (IOException | NumberFormatException | ServletException ex) {
            Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500, "ERRO! Não consegui completar a operação.");
        } catch (Exception ex) {
            Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /* método para cadastrar Cliente*/
    public void cadastrarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, ServletException, ParseException, MessagingException {
        //        instânciando o objeto usuario
        Usuario usuario = new Usuario();
        //        setando os valores recebidos do formulário
        String emailUsuario = request.getParameter("txtEmail");
        usuario.setEmail(emailUsuario);
        String senha = request.getParameter("txtSenha");
        usuario.setSenha(Usuario.encryptBlowfish(senha, senha));

        usuario.setPerfilAcesso(PerfilAcesso.CLIENTE);
        usuario.setStatus(Status.ATIVO);
        //        instânciando o objeto endereco
        Endereco endereco = new Endereco();
        //        setando os valores recebidos do formulário
        endereco.setCep(request.getParameter("txtCEP"));
        endereco.setRua(request.getParameter("txtRua"));
        endereco.setNumero(request.getParameter("txtNumero"));
        endereco.setComplemento(request.getParameter("txtComplemento"));
        endereco.setBairro(request.getParameter("txtBairro"));
        endereco.setCidade(request.getParameter("txtCidade"));
        endereco.setEstado(request.getParameter("txtEstado"));
        endereco.setPais(request.getParameter("txtPais"));

        Cliente cliente = new Cliente();
        //        setando os valores recebidos do formulário
        cliente.setNome(request.getParameter("txtNome"));
        cliente.setSobrenome(request.getParameter("txtSobrenome"));
        String razaoSocial = request.getParameter("txtRazaoSocial");
        cliente.setRazaoSocial(razaoSocial);
        String cnpj = request.getParameter("txtCNPJ");
        cliente.setCnpj(cnpj);
        String cpf = request.getParameter("txtCPF");
        cliente.setCpf(cpf);
        String rg = request.getParameter("txtRG");
        cliente.setRg(rg);
        cliente.setTelefone(request.getParameter("txtTelefone"));
        cliente.setCelular(request.getParameter("txtCelular"));
        String sexo = request.getParameter("txtSexo");
        if ("FEMININO".equals(sexo)) {
            cliente.setSexo(Sexo.FEMININO);
        } else {
            cliente.setSexo(Sexo.MASCULINO);
        }
        Date dataNasc = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataNascimento"));
        Date dataAtual = new Date();
        boolean data;
        if (dataAtual.after(dataNasc)) {
            try {
                data = true;
                System.out.println("Correto! A data de nascimento está antes da data atual!");
                cliente.setDataNascimento(dataNasc);
                cliente.setUsuario(usuario);
                cliente.setEndereco(endereco);
                
                ClienteDAO clienteDAO = new ClienteDAO();
                List<Cliente> c = clienteDAO.verificarUnique(emailUsuario, cpf, rg, razaoSocial, cnpj);
                if (c.isEmpty()) {
//        instânciando o objeto enderecoDAO e cadastrando os dados
                    EnderecoDAO enderecoDAO = new EnderecoDAO();
                    enderecoDAO.cadastrar(endereco);

//        instânciando o objeto usuarioDAO e cadastrando os dados
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    usuarioDAO.cadastrar(usuario);

//        instânciando o objeto clienteDAO e cadastrando os dados
                    clienteDAO.cadastrar(cliente);

                    Logs logs = new Logs();

                    logs.setUsuario(usuario);
                    Date dataLog = new Date();
                    logs.setData(dataLog);
                    logs.setEvento("Cadastro de Cliente");
                    logs.setDescricaoEvento("O usuário realizou o cadastro com o perfil de Cliente.");

                    LogsDAO logsDAO = new LogsDAO();
                    logsDAO.cadastrar(logs);

                    Email email = new Email();
                    email.setNomeDestinatario(cliente.getNome());
                    email.setEmailDestinatario(cliente.getUsuario().getEmail());
                    email.setAssunto("Confirmação de Cadastro no ISERVICES");
                    email.setMensagem("Seu cadastro foi confirmado!");

//        realiza o envio de e-mail
                    if (email.enviar()) {
                        response.getWriter().println("Enviado com sucesso");
                    } else {
                        response.getWriter().println("Nao enviou");
                    }

                    response.sendRedirect("CadastroRealizado.jsp");
                } else {
                    System.out.println("Erro! Dados duplicados.");
                    response.sendRedirect("ErroDadosDuplicados.jsp");

                }
            } catch (Exception ex) {
                Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro! Dados duplicados.");
                response.sendRedirect("ErroDadosDuplicados.jsp");
            }

        } else if (dataAtual.before(dataNasc)) {
            data = true;
            System.out.println("Erro! É impossível você nascer antes da data atual.");
            response.sendRedirect("Erro/ErroData.jsp");
        } else {
            System.out.println("Formato incorreto");
        }

    }

    /* método para editar Cliente*/
    public void editarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, ParseException {
//        Instânciando o objeto usuario
        int idCliente = Integer.parseInt(request.getParameter("txtIdCliente"));

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.consultarPorId(idCliente);

        Usuario usuario = new Usuario();

//        setando os valores recebidos do formulário
        usuario.setId(Integer.parseInt(request.getParameter("txtIdUsuario")));
        usuario.setEmail(request.getParameter("txtEmail"));
        usuario.setSenha(cliente.getUsuario().getSenha());
        usuario.setPerfilAcesso(PerfilAcesso.CLIENTE);
        usuario.setStatus(Status.ATIVO);

//        Instânciando o objeto endereco
        Endereco endereco = new Endereco();

//        setando os valores recebidos do formulário
        endereco.setId(Integer.parseInt(request.getParameter("txtIdEndereco")));
        endereco.setCep(request.getParameter("txtCEP"));
        endereco.setRua(request.getParameter("txtRua"));
        endereco.setNumero(request.getParameter("txtNumero"));
        endereco.setComplemento(request.getParameter("txtComplemento"));
        endereco.setBairro(request.getParameter("txtBairro"));
        endereco.setCidade(request.getParameter("txtCidade"));
        endereco.setEstado(request.getParameter("txtEstado"));
        endereco.setPais(request.getParameter("txtPais"));

//        setando os valores recebidos do formulário
        cliente.setNome(request.getParameter("txtNome"));
        cliente.setSobrenome(request.getParameter("txtSobrenome"));
        cliente.setRazaoSocial(request.getParameter("txtRazaoSocial"));
        cliente.setCnpj(request.getParameter("txtCNPJ"));
        cliente.setCpf(request.getParameter("txtCPF"));
        cliente.setRg(request.getParameter("txtRG"));
        cliente.setTelefone(request.getParameter("txtTelefone"));
        cliente.setCelular(request.getParameter("txtCelular"));

        String sexo = request.getParameter("txtSexo");
        if ("FEMININO".equals(sexo)) {
            cliente.setSexo(Sexo.FEMININO);
        } else {
            cliente.setSexo(Sexo.MASCULINO);
        }
        Date dataNasc = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataNascimento"));
        Date dataAtual = new Date();
        boolean data;
        if (dataAtual.after(dataNasc)) {
            data = true;
            System.out.println("Correto! A data de nascimento está antes da data atual!");
            cliente.setDataNascimento(dataNasc);

            cliente.setEndereco(endereco);

            cliente.setUsuario(usuario);

//        instânciando o objeto usuarioDAO e atualizando os dados
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.atualizar(usuario);

//        instânciando o objeto enderecoDAO e atualizando os dados        
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            enderecoDAO.atualizar(endereco);

            clienteDAO.atualizar(cliente);

            Logs logs = new Logs();

            logs.setUsuario(usuario);
            Date dataLog = new Date();
            logs.setData(dataLog);
            logs.setEvento("Atualização de Cliente");
            logs.setDescricaoEvento("O usuário realizou uma edição em seu cadastro com o perfil de Cliente.");

            LogsDAO logsDAO = new LogsDAO();
            logsDAO.cadastrar(logs);

            request.setAttribute("clienteEditado", clienteDAO.consultarPorId(cliente.getId()));
            request.getRequestDispatcher("Cliente/ConfirmacaoEdicao.jsp").forward(request, response);
//            response.sendRedirect("Cliente/ConfirmacaoEdicao.jsp");
        } else if (dataAtual.before(dataNasc)) {
            data = true;
            System.out.println("Erro! É impossível você nascer antes da data atual.");
            response.sendRedirect("Erro/ErroData.jsp");
        } else {
            System.out.println("Formato incorreto");
        }

    }

    public void areaCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClienteDAO clienteDAO = new ClienteDAO();
        HttpSession sessaoUsuario = request.getSession();
        int c = Integer.parseInt(request.getParameter("txtIdCliente"));
        Cliente cliente = (Cliente) clienteDAO.consultarPorId(c);
        request.setAttribute("cliente", cliente);
        sessaoUsuario.setAttribute("cliente", cliente);

        Usuario usuario = cliente.getUsuario();

        Logs logs = new Logs();

        logs.setUsuario(usuario);
        Date dataLog = new Date();
        logs.setData(dataLog);
        logs.setEvento("Acesso à Área de Cliente");
        logs.setDescricaoEvento("O usuário acessou a àrea do cliente.");

        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs);

        RequestDispatcher rd = request.getRequestDispatcher("Cliente/AreaCliente.jsp");

        rd.forward(request, response);

    }

}
