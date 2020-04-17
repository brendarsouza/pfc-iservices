/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CategoriaProfissaoDAO;
import DAO.ProfissaoDAO;
import Model.CategoriaProfissao;
import Model.Profissao;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "CategoriaProfissaoControle", urlPatterns = {"/CategoriaProfissaoControle", "/cadastrarCategoria", "/editarCategoria", "/novaProfissao"})
public class CategoriaProfissaoControle extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String acao = request.getParameter("acao");
        //Se a ação for igual a listaCategoriasParaSelecao
        if (acao.equals("listaCategoriasParaSelecao")) {

            //busca a lista de categorias
            CategoriaProfissaoDAO categoriaDAO = new CategoriaProfissaoDAO();
            List<CategoriaProfissao> listaCategoriaProfissao = categoriaDAO.buscarTodasCategorias();

            //serializa para JSON
            Gson gson = new Gson();
            String listaJSON = gson.toJson(listaCategoriaProfissao);
            out.println(listaJSON);
        //Se a ação for igual a listaProfissaoParaCategoria
        } else if (acao.equals("listaProfissaoParaCategoria")) {

            //recupera o id da categoria
            int idcategoria = Integer.parseInt(request.getParameter("categoria"));

            ProfissaoDAO profissaoDAO = new ProfissaoDAO();
            List<Profissao> listaProfissoes = profissaoDAO.buscarProfissaoPorCategoria(idcategoria);

            //serializa para JSON
            Gson gson = new Gson();
            String listaJSON = gson.toJson(listaProfissoes);
            out.println(listaJSON);

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CategoriaProfissaoControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CategoriaProfissaoControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
