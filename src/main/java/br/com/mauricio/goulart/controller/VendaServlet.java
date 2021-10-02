package br.com.mauricio.goulart.controller;

import br.com.mauricio.goulart.model.Cliente;
import br.com.mauricio.goulart.model.Venda;
import br.com.mauricio.goulart.service.ClienteService;
import br.com.mauricio.goulart.service.VendaService;
import br.com.mauricio.goulart.util.VendaHtmlUtil;
import lombok.NoArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@WebServlet(name = "VendaServlet", urlPatterns = {"/cadastro-vendas"})
public class VendaServlet extends HttpServlet {
    VendaService vendaService = new VendaService();
    VendaHtmlUtil vendaHtmlUtil = new VendaHtmlUtil();
    private List<Venda> vendas = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");

        writer.println(vendaHtmlUtil.getHtmlHeader());
        writer.println(vendaHtmlUtil.getHtmlCadastroForm());
        writer.println(vendaHtmlUtil.getHtmlTableColumn());
        if (!vendas.isEmpty()) {
                vendas.forEach(venda -> writer.println(vendaHtmlUtil.getHtmlTableRows(venda)));
        } else {
            writer.println(vendaHtmlUtil.getHtmlTableEmptyRows());
        }
        writer.println(vendaHtmlUtil.getHtmlTableFooter());
        writer.println(vendaHtmlUtil.getHtmlFooter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        switch (action) {
            case "Salvar":
                vendas = vendaService.salvar(req, vendas);
                session.setAttribute("vendas", vendas);
                doGet(req, resp);
                break;
            case "Deletar":
                vendas = vendaService.deletar(req, vendas);
                session.setAttribute("vendas", vendas);
                doGet(req, resp);
                break;
            case "Clientes":
                resp.sendRedirect("/cadastro-cliente");
        }
    }
}