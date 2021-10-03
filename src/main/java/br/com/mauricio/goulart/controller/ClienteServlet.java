package br.com.mauricio.goulart.controller;

import br.com.mauricio.goulart.model.Cliente;
import br.com.mauricio.goulart.service.ClienteService;
import br.com.mauricio.goulart.util.ClienteHtmlUtil;
import lombok.NoArgsConstructor;

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
@WebServlet(name = "ClienteServlet", urlPatterns = {"/cadastro-cliente"})
public class ClienteServlet extends HttpServlet {
    private final ClienteService clienteService = new ClienteService();
    private final ClienteHtmlUtil clienteHtmlUtil = new ClienteHtmlUtil();
    private List<Cliente> clientes = new ArrayList<>();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");

        //gera o conteudo html
        writer.println(clienteHtmlUtil.getHtmlHeader());
        writer.println(clienteHtmlUtil.getHtmlCadastroForm());
        writer.println(clienteHtmlUtil.getHtmlTableColumn());
        if (!clientes.isEmpty()) {
            clientes.forEach(cliente -> writer.println(clienteHtmlUtil.getHtmlTableRows(cliente)));
        } else {
            writer.println(clienteHtmlUtil.getHtmlTableEmptyRows());
        }
        writer.println(clienteHtmlUtil.getHtmlTableFooter());
        writer.println(clienteHtmlUtil.getHtmlFooter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        //ações da tela
        switch (action) {
            case "Salvar":
                clientes = clienteService.salvar(req, clientes);
                session.setAttribute("clientes", clientes);
                doGet(req, resp);
                break;
            case "Deletar":
                clientes = clienteService.deletar(req, clientes);
                session.setAttribute("clientes", clientes);
                doGet(req, resp);
                break;
            case "Vendas":
                resp.sendRedirect( req.getContextPath() + "/cadastro-vendas");
                break;
            case "Listagem":
                resp.sendRedirect(req.getContextPath() + "/listagem");
                break;
        }
    }
}
