package br.com.mauricio.goulart.controller;

import br.com.mauricio.goulart.model.Endereco;
import br.com.mauricio.goulart.resources.Constantes;
import br.com.mauricio.goulart.service.EnderecoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "EnderecoServlet", urlPatterns = {"/cadastro-endereco"})
public class EnderecoServlet extends HttpServlet {

    EnderecoService enderecoService = new EnderecoService();
    RequestDispatcher rd;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");

        List<Endereco> enderecoList = enderecoService.findAll();
        req.setAttribute("enderecosList", enderecoList);

        rd = req.getRequestDispatcher("pages/Endereco.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");

        switch (action) {
            case "Salvar":
                if (!enderecoService.save(req)) {
                    req.setAttribute("errorMessage", Constantes.VENDA_ERROR_400);
                }
                doGet(req,resp);
                break;
            case "Deletar":
                enderecoService.deleteByCep(req);
                doGet(req,resp);
                break;
        }
    }
}
