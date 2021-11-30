package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.model.Venda;
import br.com.mauricio.goulart.resources.Constantes;
import com.mysql.cj.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaService {

    private static final Logger log = LoggerFactory.getLogger(VendaService.class);

    private Connection connection = null;

    public boolean save (HttpServletRequest req) {
        String id = req.getParameter("id");

        if (!id.isEmpty()) {
            update(req, id);
            return true;
        }
        try {
            connection = this.doConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.INSERT_VENDA);
            populateStatement(stmt, req, "");
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            log.error("Erro ao salvar/criar venda -> " + ex.getMessage());
            return false;
        } finally {
            closeConnection();
        }
    }

    private void update (HttpServletRequest req, String id) {
        try {
            connection = this.doConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.ALTER_VENDA);
            populateStatement(stmt, req, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            log.error("Erro ao alterar venda -> " + ex.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void populateStatement (PreparedStatement stmt, HttpServletRequest req, String id) throws SQLException {
        stmt.setInt(1, Integer.parseInt(req.getParameter("quantidade")));
        stmt.setString(2, req.getParameter("nome"));
        stmt.setBigDecimal(3, new BigDecimal(req.getParameter("valor")));
        if (!id.isEmpty()) {
            stmt.setInt(4, Integer.parseInt(id));
        }
    }

    public void deleteById(HttpServletRequest req) {
        try {
            connection = doConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.DELETE_VENDA);
            stmt.setString(1, req.getParameter("id"));
            stmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Erro ao deletar venda -> " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public List<Venda> findAll() {
        try {
            connection = this.doConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Constantes.SELECT_VENDAS);

            List<Venda> vendas = new ArrayList<>();
            while (resultSet.next()) {
                Venda venda = create(resultSet);
                vendas.add(venda);
            }
            return vendas;
        } catch (SQLException ex) {
            log.error("Erro ao encontrar vendas -> " + ex.getMessage());
            return null;
        } finally {
            closeConnection();
        }
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

    public Venda create(ResultSet resultSet) throws SQLException {
        return new Venda(resultSet.getInt("id"),
                resultSet.getInt("quantidade"),
                resultSet.getString("nome_produto"),
                resultSet.getBigDecimal("valor"));
    }
}