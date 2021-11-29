package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.model.Cliente;
import br.com.mauricio.goulart.model.Venda;
import br.com.mauricio.goulart.resources.Constantes;
import com.mysql.cj.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaService {

    private static final Logger log = LoggerFactory.getLogger(VendaService.class);

    private Connection connection = null;

    public void save() {
        try {
            connection = this.makeConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.INSERT_VENDA);
            stmt.setString(1, "10");
            stmt.setString(2, "Mouse");
            stmt.setString(3, "150");
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            log.error("Erro ao salvar/criar venda -> " + ex.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void deleteById (int id) {
        try {
            connection = makeConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.DELETE_VENDA);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Erro ao deletar venda -> " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public List<Venda> findAll() {
        try {
            connection = this.makeConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Constantes.SELECT_VENDAS);

            List<Venda> vendas = new ArrayList<>();
            while (resultSet.next()) {
                Venda venda = create(resultSet);
                vendas.add(venda);
            }
            return vendas;
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

    public Venda create(ResultSet resultSet) throws SQLException {
        return new Venda(resultSet.getInt("id"),
                resultSet.getInt("quantidade"),
                resultSet.getString("nome_produto"),
                resultSet.getBigDecimal("valor"));
    }
}
