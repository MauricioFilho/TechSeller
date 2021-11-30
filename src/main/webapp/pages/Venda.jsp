<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="br.com.mauricio.goulart.model.Venda" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../css/bootstrap-theme.css"/>
    <link rel="stylesheet" href="../css/bootstrap2-toggle.min.css"/>
    <link rel="stylesheet" href="../css/bootstrap.css"/>
    <link rel="stylesheet" href="../css/bootstrap-responsive.css"/>
    <title>Cadastro de Vendas</title>
</head>
    <body>
        <form action="cadastro-vendas" method="post">
            <div class="page-header checkbox">
                <h1>Cadastro Venda</h1>
            </div>
            <div class="col-xs-3 checkbox">
                <div class="checkbox"><label>ID</label><input class="input-group" type="text" name="id" size="2"></div>
                <div class="checkbox"><label>Produto</label><input class="input-group" type="text" name="nome" size="35"></div>
                <div class="checkbox"><label>Quantidade</label><input class="input-group" type="text" name="quantidade" size="9"></div>
                <div class="checkbox"><label>Valor</label><input class="input-group" type="tel" name="valor" size="13"></div>
                <input class="btn-group btn-success " name="action" type="submit" value="Salvar">
                <input class="btn-group btn-danger" name="action" type="submit" value="Deletar">
                <%
                    if(request.getAttribute("errorMessage") != null) {
                        out.print("<div class=checkbox> Erro ao inserir venda -> " + request.getAttribute("errorMessage") + "</div>");
                    }
                %>
            </div>
        </form>
        <form>
            <div class="checkbox">
                <table class="table table-bordered text-center">
                    <caption><h2>Lista de vendas</h2></caption>
                    <tr >
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Quantidade</th>
                        <th>Valor</th>
                        <th>Total</th>
                    </tr>
                    <%
                        List<Venda> vendas = (List<Venda>) request.getAttribute("vendasList");
                        for (Venda venda : vendas) {
                            out.print("<tr><td>" + venda.getId() + "</td>\n");
                            out.print("<td>" + venda.getNomeProduto() + "</td>\n");
                            out.print("<td>" + venda.getQuantidade() + "</td>\n");
                            out.print("<td>" + venda.getValor() + "</td>\n");
                            out.print("<td>" + venda.getValorTotal() + "</td></tr>\n");
                        }
                    %>
                </table>
            </div>
        </form>
    </body>
</html>