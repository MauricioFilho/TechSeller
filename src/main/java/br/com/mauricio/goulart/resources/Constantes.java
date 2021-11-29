package br.com.mauricio.goulart.resources;

public class Constantes {
    /*DataBase*/
    public static final String URL = "jdbc:mysql://localhost:3306/tech_seller";
    public static final String USUARIO = "root";
    public static final String SENHA = "admin";

    /*Cliente*/
    public static final String SELECT_CLIENTES = "select * from cliente";
    public static final String INSERT_CLIENTE = "insert into cliente (nome, cpfCnpj, telefone, email) VALUES (?, ?, ?, ?)";
    public static final String DELETE_CLIENTE = "delete from cliente where id = ?";

    /*Endereco*/
    public static final String SELECT_ENDERECOS = "select * from endereco";
    public static final String INSERT_ENDERECO = "insert into endereco (rua, cep, bairro, cidade, pais) values (?, ?, ?, ?, ?)";
    public static final String DELETE_ENDERECO = "delete from endereco where id = ?";

    /*Venda*/
    public static final String SELECT_VENDAS = "select * from venda";
    public static final String INSERT_VENDA = "insert into venda (quantidade, nome_produto, valor) values (?, ?, ?)";
    public static final String DELETE_VENDA = "delete from venda where id = ?";
}
