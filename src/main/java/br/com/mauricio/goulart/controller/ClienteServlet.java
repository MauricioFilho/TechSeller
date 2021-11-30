package br.com.mauricio.goulart.controller;

import br.com.mauricio.goulart.model.Cliente;
import br.com.mauricio.goulart.resources.Constantes;
import br.com.mauricio.goulart.service.ClienteService;
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
@WebServlet(name = "ClienteServlet", urlPatterns = {"/cadastro-cliente"})
public class ClienteServlet extends HttpServlet {
    private final ClienteService clienteService = new ClienteService();
    RequestDispatcher rd;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");

        List<Cliente> clientes = clienteService.findAll();
        req.setAttribute("clientesList", clientes);

        rd = req.getRequestDispatcher("pages/Cliente.jsp");
        rd.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        switch (action) {
            case "Salvar":
                if (!clienteService.save(req)) {
                    req.setAttribute("errorMessage", Constantes.CLIENTE_ERROR_400);
                }
                doGet(req,resp);
                break;
            case "Deletar":
                clienteService.deleteById(req);
                doGet(req,resp);
                break;
            case "Vendas":
                resp.sendRedirect("/cadastro-vendas");
        }

    }
}