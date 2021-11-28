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
        resp.setContentType("text/html");

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();

    }
}
