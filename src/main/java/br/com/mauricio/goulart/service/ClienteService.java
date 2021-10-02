package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.model.Cliente;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteService {
    private final EnderecoService enderecoService = new EnderecoService();

    public List<Cliente> salvar(HttpServletRequest request, List<Cliente> clientes) {
        Cliente clienteAlterado = findCliente(clientes, request);
        if(clienteAlterado != null) {
            Cliente clienteAlteracao = criarCliente(request);
            return update(clientes, clienteAlteracao, clienteAlterado);
        } else {
            clientes.add(criarCliente(request));
        }
        return clientes;
    }

    public List<Cliente> deletar(HttpServletRequest request, List<Cliente> clientes) {
        int id = Integer.parseInt(request.getParameter("idCliente"));
        return clientes.stream().filter(c -> c.getId() != id).collect(Collectors.toList());
    }

    private List<Cliente> update(List<Cliente> clientes, Cliente clienteAlteracao, Cliente clienteAlterado){
        int clienteIndex = clientes.indexOf(clienteAlterado);
        if (clienteIndex != -1) {
            clientes.set(clienteIndex, clienteAlteracao);
        }
        return clientes;
    }


    public Cliente findCliente(List<Cliente> clientes, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("idCliente"));
        return clientes.stream().filter(c -> c.getId() ==  id).findAny().orElse(null);
    }

    private Cliente criarCliente(HttpServletRequest request) {
        return new Cliente(
                Integer.parseInt(request.getParameter("idCliente")),
                request.getParameter("nomeCliente"),
                request.getParameter("cpfCliente"),
                request.getParameter("telefoneCliente"),
                request.getParameter("emailCliente"),
                enderecoService.criarEndereco(request));
    }
}
