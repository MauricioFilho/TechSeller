package br.com.mauricio.goulart.controller;

import br.com.mauricio.goulart.model.Cliente;
import br.com.mauricio.goulart.service.ClienteService;
import br.com.mauricio.goulart.util.ClienteHtmlUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ClienteServlet", urlPatterns = {"/cadastro-cliente"})
public class ClienteServlet extends HttpServlet {

    private final ClienteService clienteService = new ClienteService();
    private final ClienteHtmlUtil clienteHtmlUtil = new ClienteHtmlUtil();
    private List<Cliente> clientes = new ArrayList<>();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        //gera o conteudo html
        writer.println(clienteHtmlUtil.getHtmlHeader());
        writer.println(clienteHtmlUtil.getHtmlCadastroForm());
        writer.println(clienteHtmlUtil.getHtmlTableColumn());
        if (!clientes.isEmpty()){
            clientes.forEach(cliente -> writer.println(clienteHtmlUtil.getHtmlTableRows(cliente)));
        } else {
            writer.println(clienteHtmlUtil.getHtmlTableEmptyRows());
        }
        writer.println(clienteHtmlUtil.getHtmlFooter());
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        //ações da tela
        if(action.equals("Salvar")) {
            clientes = clienteService.salvar(request, clientes);
            session.setAttribute("clientes", clientes);
            doGet(request, response);
        } else if(action.equals("Deletar") && request.getParameter("idCliente") != null){
            clientes = clienteService.deletar(request, clientes);
            session.setAttribute("clientes", clientes);
            doGet(request, response);
        }
    }
}
