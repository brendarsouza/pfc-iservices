/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CategoriaProfissaoDAO;
import DAO.ClienteDAO;
import DAO.ProfissionalDAO;
import DAO.EnderecoDAO;
import DAO.LogsDAO;
import DAO.PedidoDAO;
import DAO.ProfissaoDAO;
import DAO.UsuarioDAO;
import Model.CategoriaProfissao;
import Model.Cliente;
import Model.Endereco;
import Model.Logs;
import Model.Pedido;
import Model.PerfilAcesso;
import Model.Profissao;
import Model.Profissional;
import Model.Sexo;
import Model.Status;
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
import javax.mail.MessagingException;
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
@WebServlet(name = "ProfissionalControle", urlPatterns = {"/ProfissionalControle", "/cadastrarProfissional", "/editarProfissional", "/areaProfissional"})
public class ProfissionalControle extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*o request.getRequestURI(); pega a uri que está vindo do navegador e atribui na variavel uri*/
        String uri = request.getRequestURI();
        /*se a uri for igual a cadastrarProfissional irá chamar o metodo cadastrarProfissional*/
        if (uri.equals(request.getContextPath() + "/cadastrarProfissional")) {
            try {
                cadastrarProfissional(request, response);
            } catch (ClassNotFoundException | SQLException | ParseException | MessagingException ex) {
                Logger.getLogger(ProfissionalControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else /*se a uri for igual a editarProfissional irá chamar o metodo editarProfissional*/ if (uri.equals(request.getContextPath() + "/editarProfissional")) {
            try {
                editarProfissional(request, response);
            } catch (ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(ProfissionalControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(request.getContextPath() + "/areaProfissional")) {
            areaProfissional(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*o request.getRequestURI(); pega a uri que está vindo do navegador e atribui na variavel uri*/
        String uri = request.getRequestURI();
        try {
            ProfissionalDAO profissionalDAO = new ProfissionalDAO();

            /* Recebe o id do profissional e confirma o valor não é nulo*/
            if (request.getParameter("idEditar") != null) {

                request.setAttribute("profissional", profissionalDAO.getById(Integer.parseInt(request.getParameter("idEditar"))));
                /* direciona para a página de edição de cadastro*/
                request.getRequestDispatcher("Profissional/EditarProfissional.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProfissionalControle.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500, "ERRO! Não achei -_- ");
        }

    }

    /* método para cadastrar Profissional*/
    public void cadastrarProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, ParseException, MessagingException {

//        Instânciando o objeto usuario
        Usuario usuario = new Usuario();

//        setando os valores recebidos do formulário
        String emailUsuario = request.getParameter("txtEmail");
        usuario.setEmail(emailUsuario);
        String senha = request.getParameter("txtSenha");
        usuario.setSenha(Usuario.encryptBlowfish(senha, senha));
        usuario.setPerfilAcesso(PerfilAcesso.PROFISSIONAL);
        usuario.setStatus(Status.ATIVO);

//        Instânciando o objeto endereco
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

//        Instânciando o objeto profissional
        Profissional profissional = new Profissional();

//       setando os valores recebidos do formulário
        profissional.setNome(request.getParameter("txtNome"));
        profissional.setSobrenome(request.getParameter("txtSobrenome"));
        String razaoSocial = request.getParameter("txtRazaoSocial");
        profissional.setRazaoSocial(razaoSocial);
        String cnpj = request.getParameter("txtCNPJ");
        profissional.setCnpj(cnpj);
        String cpf = request.getParameter("txtCPF");
        profissional.setCpf(cpf);
        String rg = request.getParameter("txtRG");
        profissional.setRg(rg);
        profissional.setTelefone(request.getParameter("txtTelefone"));
        profissional.setCelular(request.getParameter("txtCelular"));
        profissional.setEstadoAtendimento(request.getParameter("txtEstadoAtendimento"));
        profissional.setCidadeAtendimento(request.getParameter("txtCidadeAtendimento"));
        String sexo = request.getParameter("txtSexo");
        if ("FEMININO".equals(sexo)) {
            profissional.setSexo(Sexo.FEMININO);
        } else {
            profissional.setSexo(Sexo.MASCULINO);
        }
        Date dataNasc = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataNascimento"));
        Date dataAtual = new Date();
        boolean data;
        if (dataAtual.after(dataNasc)) {
            try {
                data = true;
                System.out.println("Correto! A data de nascimento está antes da data atual!");
                profissional.setDataNascimento(dataNasc);
//        Instânciando o objeto profissao

//        setando os valores recebidos do formulário
                int idProfissao = Integer.parseInt(request.getParameter("txtProfissao"));
                ProfissaoDAO profissaoDAO = new ProfissaoDAO();
                Profissao profissao = profissaoDAO.getById(idProfissao);
                profissional.setProfissao(profissao);

//        setando os valores recebidos do formulário
                profissional.setDescricaoServicos(request.getParameter("txtDescricaoServicos"));
                Date horarioInicio = new SimpleDateFormat("HH:mm").parse(request.getParameter("txtHorarioDeAtendimentoInicio"));
                profissional.setHorarioDeAtendimentoInicio(horarioInicio);
                Date horarioFim = new SimpleDateFormat("HH:mm").parse(request.getParameter("txtHorarioDeAtendimentoFim"));
                profissional.setHorarioDeAtendimentoFim(horarioFim);
                profissional.setFormacao(request.getParameter("txtFormacao"));
                profissional.setInstituicao(request.getParameter("txtInstituicao"));

                profissional.setProfissao(profissao);

                profissional.setUsuario(usuario);
                profissional.setEndereco(endereco);
                ProfissionalDAO profissionalDAO = new ProfissionalDAO();
                List<Profissional> p = profissionalDAO.verificarUnique(emailUsuario, cpf, rg, razaoSocial, cnpj);
                if (p.isEmpty()) {
//       Instânciando o objeto enderecoDAO
                    EnderecoDAO enderecoDAO = new EnderecoDAO();

//        chamando método cadastrar e fazendo a persistência dos dados
                    enderecoDAO.cadastrar(endereco);

//        Instânciando o objeto usuarioDAO
                    UsuarioDAO usuarioDAO = new UsuarioDAO();

//        chamando método cadastrar e fazendo a persistência dos dados
                    usuarioDAO.cadastrar(usuario);

//        Instânciando o objeto profissionalDAO
//        chamando método cadastrar e fazendo a persistência dos dados
                    profissionalDAO.cadastrar(profissional);

                    Logs logs = new Logs();

                    logs.setUsuario(usuario);
                    Date dataLog = new Date();
                    logs.setData(dataLog);
                    logs.setEvento("Cadastro de Profissional");
                    logs.setDescricaoEvento("O usuário realizou o cadastro com o perfil de Profissional.");

                    LogsDAO logsDAO = new LogsDAO();
                    logsDAO.cadastrar(logs);

//        Instânciando o objeto email
                    Email email = new Email();

                    email.setNomeDestinatario(profissional.getNome());
                    email.setEmailDestinatario(profissional.getUsuario().getEmail());
                    response.getWriter().println(profissional.getUsuario().getEmail());
                    email.setAssunto("Confirmação de Cadastro no ISERVICES PROFISSIONAL");
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
                Logger.getLogger(ProfissionalControle.class.getName()).log(Level.SEVERE, null, ex);
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

    /* método para cadastrar Profissional*/
    public void editarProfissional(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException, ParseException {
        int idProfissional = Integer.parseInt(request.getParameter("txtIdProfissional"));

//        cliente.setId(Integer.parseInt(request.getParameter("txtIdCliente")));
        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
        Profissional profissional = profissionalDAO.getById(idProfissional);
//       Instânciando o objeto usuario
        Usuario usuario = new Usuario();

//        setando os valores recebidos do formulário
        usuario.setId(Integer.parseInt(request.getParameter("txtIdUsuario")));
        usuario.setEmail(request.getParameter("txtEmail"));
        usuario.setSenha(profissional.getUsuario().getSenha());
        usuario.setPerfilAcesso(PerfilAcesso.PROFISSIONAL);
        usuario.setStatus(Status.ATIVO);

//       Instânciando o objeto endereco
        Endereco endereco = new Endereco();

        endereco.setId(Integer.parseInt(request.getParameter("txtIdEndereco")));
        endereco.setCep(request.getParameter("txtCEP"));
        endereco.setRua(request.getParameter("txtRua"));
        endereco.setNumero(request.getParameter("txtNumero"));
        endereco.setComplemento(request.getParameter("txtComplemento"));
        endereco.setBairro(request.getParameter("txtBairro"));
        endereco.setCidade(request.getParameter("txtCidade"));
        endereco.setEstado(request.getParameter("txtEstado"));
        endereco.setPais(request.getParameter("txtPais"));

//        Instânciando o objeto profissional
        profissional.setNome(request.getParameter("txtNome"));
        profissional.setSobrenome(request.getParameter("txtSobrenome"));
        profissional.setRazaoSocial(request.getParameter("txtRazaoSocial"));
        profissional.setCnpj(request.getParameter("txtCNPJ"));
        profissional.setCpf(request.getParameter("txtCPF"));
        profissional.setRg(request.getParameter("txtRG"));
        profissional.setTelefone(request.getParameter("txtTelefone"));
        profissional.setCelular(request.getParameter("txtCelular"));

        String sexo = request.getParameter("txtSexo");
        if ("FEMININO".equals(sexo)) {
            profissional.setSexo(Sexo.FEMININO);
        } else {
            profissional.setSexo(Sexo.MASCULINO);
        }
        Date dataNasc = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtDataNascimento"));
        Date dataAtual = new Date();
        boolean data;
        if (dataAtual.after(dataNasc)) {
            data = true;
            System.out.println("Correto! A data de nascimento está antes da data atual!");
            profissional.setDataNascimento(dataNasc);

            int idProfissao = Integer.parseInt(request.getParameter("txtProfissao"));
            ProfissaoDAO profissaoDAO = new ProfissaoDAO();
            Profissao profissao = profissaoDAO.getById(idProfissao);
            profissional.setProfissao(profissao);

            profissional.setDescricaoServicos(request.getParameter("txtDescricaoServicos"));
            Date horarioInicio = new SimpleDateFormat("HH:mm").parse(request.getParameter("txtHorarioDeAtendimentoInicio"));
            profissional.setHorarioDeAtendimentoInicio(horarioInicio);
            Date horarioFim = new SimpleDateFormat("HH:mm").parse(request.getParameter("txtHorarioDeAtendimentoFim"));
            profissional.setHorarioDeAtendimentoFim(horarioFim);
            profissional.setFormacao(request.getParameter("txtFormacao"));
            profissional.setInstituicao(request.getParameter("txtInstituicao"));

            profissional.setUsuario(usuario);
            profissional.setEndereco(endereco);
            profissional.setProfissao(profissao);

//        Instânciando o objeto enderecoDAO e atualizando os dados
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            enderecoDAO.atualizar(endereco);

//        Instânciando o objeto usuarioDAO e atualizando os dados
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.atualizar(usuario);

//        Instânciando o objeto profissionalDAO e atualizando os dados        
            profissionalDAO.atualizar(profissional);

            Logs logs = new Logs();

            logs.setUsuario(usuario);
            Date dataLog = new Date();
            logs.setData(dataLog);
            logs.setEvento("Atualização de Profissional");
            logs.setDescricaoEvento("O profissional realizou uma atualização em seu perfil.");

            LogsDAO logsDAO = new LogsDAO();
            logsDAO.cadastrar(logs);
            request.setAttribute("profissionalEditado", profissionalDAO.getById(profissional.getId()));
            request.getRequestDispatcher("Profissional/ConfirmacaoEdicao.jsp").forward(request, response);

//            response.sendRedirect("Profissional/ConfirmacaoEdicao.jsp");
        } else if (dataAtual.before(dataNasc)) {
            data = true;
            System.out.println("Erro! É impossível você nascer antes da data atual.");
            response.sendRedirect("Erro/ErroData.jsp");
        } else {
            System.out.println("Formato incorreto");
        }
    }

    public void areaProfissional(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
        HttpSession sessaoProfissional = request.getSession();
        int p = Integer.parseInt(request.getParameter("txtIdProfissional"));
        Profissional profissional = (Profissional) profissionalDAO.consultarPorId(p);
//        request.setAttribute("profissional", profissional);
        sessaoProfissional.setAttribute("profissional", profissional);

        RequestDispatcher rd = request.getRequestDispatcher("Profissional/AreaProfissional.jsp");

        rd.forward(request, response);

        Usuario usuario = profissional.getUsuario();
        Logs logs = new Logs();

        logs.setUsuario(usuario);
        Date dataLog = new Date();
        logs.setData(dataLog);
        logs.setEvento("Área do Profissional");
        logs.setDescricaoEvento("O profissional acessou à área do profissional.");

        LogsDAO logsDAO = new LogsDAO();
        logsDAO.cadastrar(logs);

    }
}
