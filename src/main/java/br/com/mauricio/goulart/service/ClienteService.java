package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.model.Cliente;
import br.com.mauricio.goulart.resources.Constantes;
import com.mysql.cj.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

    private Connection connection = null;

    public void save() {
        try {
            connection = this.makeConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.INSERT_CLIENTE);
            stmt.setString(1, "Mauricio");
            stmt.setString(2, "5555452645");
            stmt.setString(3, "48998117006");
            stmt.setString(4, "mauricio@jkkk.com.br");
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            log.error("Erro ao salvar/criar cliente -> " + ex.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void deleteById (int id) {
        try {
            connection = makeConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.DELETE_CLIENTE);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Erro ao deletar cliente -> " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public List<Cliente> findAll() {
        Statement statement;
        ResultSet resultSet;
        List<Cliente> clientes = new ArrayList<>();
        try {
            connection = this.makeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Constantes.SELECT_CLIENTES);

            while (resultSet.next()) {
                Cliente cliente = create(resultSet);
                clientes.add(cliente);
            }
        return clientes;
        } catch (SQLException ex) {
            log.error("Erro ao encontrar clientes -> " + ex.getMessage());
            return null;
        } finally {
            closeConnection();
        }
    }

    public Connection makeConnection() {
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

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Erro ao fechar conexao com o banco -> " + e.getMessage());
            }
        }
    }

    public Cliente create(ResultSet resultSet) throws SQLException {
        return new Cliente(resultSet.getInt("id"),
                resultSet.getString("nome"),
                resultSet.getString("cpfCnpj"),
                resultSet.getString("telefone"),
                resultSet.getString("email"));
    }

    public static void main(String[] args) {
        ClienteService clienteService = new ClienteService();
        clienteService.save();
        clienteService.deleteById(2);
        List<Cliente> clientes = clienteService.findAll();
        clientes.forEach(System.out::println);
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
