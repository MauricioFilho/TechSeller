package br.com.mauricio.goulart.controller;

import br.com.mauricio.goulart.model.Cliente;
import br.com.mauricio.goulart.service.ClienteService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "clienteServlet", urlPatterns = "/cadastro-cliente")
public class ClienteController extends HttpServlet {
    private final ClienteService clienteService = new ClienteService();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/Cliente.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response = clienteService.salvar(request, response);

        processRequest(request, response);
    }
}
