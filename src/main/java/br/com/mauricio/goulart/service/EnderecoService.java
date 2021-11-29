package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.model.Cliente;
import br.com.mauricio.goulart.model.Endereco;
import br.com.mauricio.goulart.resources.Constantes;
import com.mysql.cj.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoService {

    private static final Logger log = LoggerFactory.getLogger(EnderecoService.class);

    private Connection connection = null;

    public void save() {
        try {
            connection = this.makeConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.INSERT_ENDERECO);
            stmt.setString(1, "Teste");
            stmt.setString(2, "88804080");
            stmt.setString(3, "Santa Barbara");
            stmt.setString(4, "Criciuma");
            stmt.setString(5, "Brasil");
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            log.error("Erro ao salvar/criar endereco -> " + ex.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void deleteById (int id) {
        try {
            connection = makeConnection();
            PreparedStatement stmt = connection.prepareStatement(Constantes.DELETE_ENDERECO);
            stmt.setInt(1, id);
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
            connection = this.makeConnection();
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

    public Endereco create(ResultSet resultSet) throws SQLException {
        return new Endereco(resultSet.getInt("id"),
                resultSet.getString("rua"),
                resultSet.getString("cep"),
                resultSet.getString("bairro"),
                resultSet.getString("cidade"),
                resultSet.getString("pais"));
    }
}
