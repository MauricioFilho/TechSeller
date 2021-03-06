package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.model.Cliente;
import br.com.mauricio.goulart.resources.Constantes;
import com.mysql.cj.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

    private Connection connection = null;

    public boolean save(HttpServletRequest req) {
        List<Cliente> clienteList = findAll();
        String id = req.getParameter("id");

        if (!id.isEmpty()) {
            update(req, id);
            return true;
        }

        if(clienteList.stream().anyMatch(c -> c.getCpfCnpj().equals(req.getParameter("cpfCnpj")))){
            log.warn("Aviso ao salvar/cliente -> Cliente com CpfCnpj: " + req.getParameter("cpfCnpj") + " ja se encontra na base!");
            return false;
        }

        try {
            connection = this.doConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.INSERT_CLIENTE);
            populateStatement(stmt, req, "");
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            log.error("Erro ao salvar/criar cliente -> " + ex.getMessage());
            return false;
        } finally {
            closeConnection();
        }
    }

    private void update(HttpServletRequest req, String id) {
        try {
            connection = this.doConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.ALTER_CLIENTE);
            populateStatement(stmt, req, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            log.error("Erro ao alterar cliente -> " + ex.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void deleteById(HttpServletRequest req) {
        try {
            connection = this.doConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.DELETE_CLIENTE);
            stmt.setString(1, req.getParameter("id"));
            stmt.executeUpdate();
            stmt.close();
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
            connection = this.doConnection();
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

    public void populateStatement(PreparedStatement stmt, HttpServletRequest req, String id) throws SQLException {
        stmt.setString(1, req.getParameter("nome"));
        stmt.setString(2, req.getParameter("cpfCnpj"));
        stmt.setString(3, req.getParameter("telefone"));
        stmt.setString(4, req.getParameter("email"));
        if (!id.isEmpty()) {
            stmt.setInt(5, Integer.parseInt(id));
        }
    }

    public Cliente create(ResultSet resultSet) throws SQLException {
        return new Cliente(resultSet.getInt("id"),
                resultSet.getString("nome"),
                resultSet.getString("cpfCnpj"),
                resultSet.getString("telefone"),
                resultSet.getString("email"));
    }

    public Connection doConnection() {
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


}