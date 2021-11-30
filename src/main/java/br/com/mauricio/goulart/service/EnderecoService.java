package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.model.Endereco;
import br.com.mauricio.goulart.resources.Constantes;
import com.mysql.cj.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoService {

    private static final Logger log = LoggerFactory.getLogger(EnderecoService.class);

    private Connection connection = null;

    public boolean save (HttpServletRequest req) {
        String id = req.getParameter("id");

        if (!id.isEmpty()) {
            update(req, id);
            return true;
        }
        try {
            connection = this.doConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.INSERT_ENDERECO);
            populateStatement(stmt, req, "");
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            log.error("Erro ao salvar/criar endereco -> " + ex.getMessage());
            return false;
        } finally {
            closeConnection();
        }
    }

    private void update(HttpServletRequest req, String id) {
        try {
            connection = this.doConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.ALTER_ENDERECO);
            populateStatement(stmt, req, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            log.error("Erro ao alterar endereco -> " + ex.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void populateStatement(PreparedStatement stmt, HttpServletRequest req, String id) throws SQLException {
        stmt.setString(1, req.getParameter("rua"));
        stmt.setString(2, req.getParameter("cep"));
        stmt.setString(3, req.getParameter("bairro"));
        stmt.setString(4, req.getParameter("cidade"));
        stmt.setString(5, req.getParameter("pais"));
        if (!id.isEmpty()) {
            stmt.setInt(6, Integer.parseInt(id));
        }
    }

    public void deleteById(HttpServletRequest req) {
        try {
            connection = this.doConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.DELETE_ENDERECO);
            stmt.setString(1, req.getParameter("id"));
            stmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Erro ao deletar endereco -> " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public List<Endereco> findAll() {
        Statement statement;
        ResultSet resultSet;
        List<Endereco> enderecos = new ArrayList<>();
        try {
            connection = this.doConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Constantes.SELECT_ENDERECOS);

            while (resultSet.next()) {
                Endereco endereco = create(resultSet);
                enderecos.add(endereco);
            }
            return enderecos;
        } catch (SQLException ex) {
            log.error("Erro ao encontrar enderecos -> " + ex.getMessage());
            return null;
        } finally {
            closeConnection();
        }
    }

    public Endereco create(ResultSet resultSet) throws SQLException {
        return new Endereco(resultSet.getInt("id"),
                resultSet.getString("rua"),
                resultSet.getString("cep"),
                resultSet.getString("bairro"),
                resultSet.getString("cidade"),
                resultSet.getString("pais"));
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