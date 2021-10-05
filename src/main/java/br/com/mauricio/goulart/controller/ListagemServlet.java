package br.com.mauricio.goulart.controller;

import br.com.mauricio.goulart.model.Cliente;
import br.com.mauricio.goulart.model.Venda;
import br.com.mauricio.goulart.util.ClienteHtmlUtil;
import br.com.mauricio.goulart.util.ListagemHtmlUtil;
import br.com.mauricio.goulart.util.VendaHtmlUtil;

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
import java.util.Optional;

@WebServlet(name = "ListagemServlet", urlPatterns = {"/listagem"})
public class ListagemServlet extends HttpServlet {

    ListagemHtmlUtil listagemHtmlUtil = new ListagemHtmlUtil();
    VendaHtmlUtil vendaHtmlUtil = new VendaHtmlUtil();
    ClienteHtmlUtil clienteHtmlUtil = new ClienteHtmlUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");
        HttpSession session = req.getSession(true);

        List<Cliente> clientes = Optional.ofNullable((List<Cliente>) session.getAttribute("clientes")).orElse(new ArrayList<>());
        List<Venda> vendas = Optional.ofNullable((List<Venda>) session.getAttribute("vendas")).orElse(new ArrayList<>());

        writer.println(listagemHtmlUtil.getHtmlHeader());
        writer.println(listagemHtmlUtil.getHtmlForm());
        writer.println(clienteHtmlUtil.getHtmlTableColumn());
        //Clientes table
        if (!clientes.isEmpty()) {
            clientes.forEach(cliente -> writer.println(clienteHtmlUtil.getHtmlTableRows(cliente)));
        } else {
            writer.println(clienteHtmlUtil.getHtmlTableEmptyRows());
        }

        //Vendas table
        writer.println(vendaHtmlUtil.getHtmlTableColumn());
        if (!vendas.isEmpty()) {
            vendas.forEach(venda -> writer.println(vendaHtmlUtil.getHtmlTableRowsWithCliente(venda)));
        } else {
            writer.println(vendaHtmlUtil.getHtmlTableEmptyRows());
        }
        writer.println(vendaHtmlUtil.getHtmlTableFooter());
        writer.println(listagemHtmlUtil.getHtmlFooter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "Clientes":
                resp.sendRedirect(req.getContextPath() + "/cadastro-cliente");
                break;
            case "Vendas":
                resp.sendRedirect(req.getContextPath() + "/cadastro-vendas");
                break;
        }
    }
}
