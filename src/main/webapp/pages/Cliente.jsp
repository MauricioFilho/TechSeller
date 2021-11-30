<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="br.com.mauricio.goulart.model.Cliente"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../css/bootstrap-theme.css"/>
    <link rel="stylesheet" href="../css/bootstrap2-toggle.min.css"/>
    <link rel="stylesheet" href="../css/bootstrap.css"/>
    <link rel="stylesheet" href="../css/bootstrap-responsive.css"/>
    <title>Cadastro de clientes</title>
</head>
<body>
    <form action="cadastro-cliente" method="post">
        <div class="page-header checkbox">
            <h1>Cadastro Cliente</h1>
        </div>
        <div class="col-xs-3 checkbox">
            <div class="checkbox"><label>ID</label><input class="input-group" type="text" name="id" size="5"></div>
            <div class="checkbox"><label>Nome</label><input class="input-group" type="text" name="nome" size="35"></div>
            <div class="checkbox"><label>CPF</label><input class="input-group" type="text" name="cpfCnpj" size="9"></div>
            <div class="checkbox"><label>Telefone</label><input class="input-group" type="tel" name="telefone" size="13"></div>
            <div class="checkbox"><label>Email</label><input class="input-group" type="text" name="email" size="35" ></div>
            <input class="btn-group btn-success " name="action" type="submit" value="Salvar">
            <input class="btn-group btn-danger" name="action" type="submit"  value="Deletar">
            <%
                if(request.getAttribute("errorMessage") != null) {
                    out.print("<div class=checkbox> Erro ao inserir cliente -> " + request.getAttribute("errorMessage") + "</div>");
                }
            %>
        </div>
    </form>
    <form>
        <div class="checkbox">
            <table class="table table-bordered text-center">
                <caption><h2>Lista de usuarios</h2></caption>
                <tr >
                    <th>ID</th>
                    <th>Nome</th>
                    <th>CPF/CNPJ</th>
                    <th>Telefone</th>
                    <th>E-mail</th>
                </tr>
                <%
                    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientesList");
                    for (Cliente cliente : clientes) {
                        out.print("<tr><td>" + cliente.getId() + "</td>\n");
                        out.print("<td>" + cliente.getNome() + "</td>\n");
                        out.print("<td>" + cliente.getCpfCnpj() + "</td>\n");
                        out.print("<td>" + cliente.getTelefone() + "</td>\n");
                        out.print("<td>" + cliente.getEmail() + "</td></tr>\n");
                    }
                %>
            </table>
        </div>
    </form>
</body>
</html>