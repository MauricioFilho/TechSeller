package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.resources.Constantes;
import com.mysql.cj.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClienteService {

    private final EnderecoService enderecoService = new EnderecoService();

    private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

    private final Connection connection = getConexao();

    public Connection getConexao() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(Constantes.URL,
                    Constantes.USUARIO,
                    Constantes.SENHA);
        } catch (SQLException e) {
            log.error("Erro ao connectar com o banco de dados -> (" + e.getMessage() + ")");
            return null;
        }
    }


}
    // CODIGO ANTIGO
    /*public List<Cliente> salvar(HttpServletRequest request, List<Cliente> clientes) {


        Cliente clienteAlterado = null;
        if(!clientes.isEmpty()) {
            clienteAlterado = findCliente(clientes, request);
        }
        if(clienteAlterado != null) {
            Cliente clienteAlteracao = criarCliente(request);
            return update(clientes, clienteAlteracao, clienteAlterado);
        } else {
            Cliente cliente = criarCliente(request);
            if (!cliente.getId().isEmpty()) {
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public List<Cliente> deletar(HttpServletRequest req, List<Cliente> clientes) {
        String id =  Optional.of(req.getParameter("idCliente")).orElse("");
        if (!id.isEmpty()) {
            return clientes.stream().filter(c -> !c.getId().equals(id)).collect(Collectors.toList());
        } else {
            return clientes;
        }
    }

    private List<Cliente> update(List<Cliente> clientes, Cliente clienteAlteracao, Cliente clienteAlterado){
        int clienteIndex = clientes.indexOf(clienteAlterado);
        if (clienteIndex != -1) {
            clientes.set(clienteIndex, clienteAlteracao);
        }
        return clientes;
    }

    public Cliente findCliente(List<Cliente> clientes, HttpServletRequest req) {
        String id =  Optional.of(req.getParameter("idCliente")).orElse(null);
        return clientes.stream().filter(c -> c.getId().equals(id)).findAny().orElse(null);
    }

    private Cliente criarCliente(HttpServletRequest req) {
        return new Cliente(
                Optional.of(req.getParameter("idCliente")).orElse(null),
                Optional.of(req.getParameter("nomeCliente")).orElse(null),
                Optional.of(req.getParameter("cpfCliente")).orElse(null),
                Optional.of(req.getParameter("telefoneCliente")).orElse(null),
                Optional.of(req.getParameter("emailCliente")).orElse(null),
                Optional.of(enderecoService.criarEndereco(req)).orElse(null));
    }
}*/
