/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosservlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fernando.tsuda
 */
@WebServlet(name = "ProdutoMVCServlet", urlPatterns = {"/produto-mvc"})
public class ProdutoMVCServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Produto p1 = new Produto(1L, "Bolo de chocolate",
                "descrição do bolo de chocolate",
                new BigDecimal(30.0), "http://lorempixel.com/g/300/300/");
        Produto p2 = new Produto(1L, "Bolo de cenoura",
                "descrição do bolo de cenoura", new BigDecimal(20.0), "http://lorempixel.com/g/300/300/");
        List<Produto> lista = Arrays.asList(p1, p2);
        request.setAttribute("listaProd", lista);

        // Encaminha requisição para JSP
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/jsp/produto-mvc.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("mensagemPost", "Tente recarregar a página e veja o que acontece");
        doGet(request, response);
    }

}
