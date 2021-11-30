package br.com.mauricio.goulart.controller;

import br.com.mauricio.goulart.model.Venda;
import br.com.mauricio.goulart.resources.Constantes;
import br.com.mauricio.goulart.service.VendaService;
import lombok.NoArgsConstructor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@NoArgsConstructor
@WebServlet(name = "VendaServlet", urlPatterns = {"/cadastro-venda"})
public class VendaServlet extends HttpServlet {
    VendaService vendaService = new VendaService();
    RequestDispatcher rd;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");

        List<Venda> vendas = vendaService.findAll();
        req.setAttribute("vendasList", vendas);

        rd = req.getRequestDispatcher("pages/Venda.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");

        switch (action) {
            case "Salvar":
                if (!vendaService.save(req)) {
                    req.setAttribute("errorMessage", Constantes.VENDA_ERROR_400);
                }
                doGet(req,resp);
                break;
            case "Deletar":
                vendaService.deleteById(req);
                doGet(req,resp);
                break;
        }
    }
}
