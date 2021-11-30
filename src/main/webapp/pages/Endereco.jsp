<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="br.com.mauricio.goulart.model.Endereco" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../css/bootstrap-theme.css"/>
    <link rel="stylesheet" href="../css/bootstrap2-toggle.min.css"/>
    <link rel="stylesheet" href="../css/bootstrap.css"/>
    <link rel="stylesheet" href="../css/bootstrap-responsive.css"/>
    <title>Cadastro de Enderecos</title>
</head>
    <body>
        <form action="cadastro-endereco" method="post">
            <div class="page-header checkbox">
                <h1>Cadastro Endereco</h1>
            </div>
            <div class="col-xs-3 checkbox">
                <div class="checkbox"><label>ID</label><input class="input-group" type="text" name="id" size="2"></div>
                <div class="checkbox"><label>Rua</label><input class="input-group" type="text" name="rua" size="35"></div>
                <div class="checkbox"><label>CEP</label><input class="input-group" type="text" name="cep" size="9"></div>
                <div class="checkbox"><label>Bairro</label><input class="input-group" type="tel" name="bairro" size="13"></div>
                <div class="checkbox"><label>Cidade</label><input class="input-group" type="text" name="cidade" size="35" ></div>
                <div class="checkbox"><label>Pais</label><input class="input-group" type="text" name="pais" size="35" ></div>
                <input class="btn-group btn-success " name="action" type="submit" value="Salvar">
                <input class="btn-group btn-danger" name="action" type="submit" value="Deletar">
                <%
                    if(request.getAttribute("errorMessage") != null) {
                        out.print("<div class=checkbox> Erro ao inserir endereco -> " + request.getAttribute("errorMessage") + "</div>");
                    }
                %>
            </div>
        </form>
        <form>
            <div class="checkbox">
                <table class="table table-bordered text-center">
                    <caption><h2>Lista de enderecos</h2></caption>
                    <tr >
                        <th>ID</th>
                        <th>Rua</th>
                        <th>CEP</th>
                        <th>Bairro</th>
                        <th>Cidade</th>
                        <th>Pais</th>
                    </tr>
                    <%
                        List<Endereco> enderecos = (List<Endereco>) request.getAttribute("enderecosList");
                        for (Endereco endereco : enderecos) {
                            out.print("<tr><td>" + endereco.getId() + "</td>\n");
                            out.print("<td>" + endereco.getRua() + "</td>\n");
                            out.print("<td>" + endereco.getCep() + "</td>\n");
                            out.print("<td>" + endereco.getBairro() + "</td>\n");
                            out.print("<td>" + endereco.getCidade() + "</td>\n");
                            out.print("<td>" + endereco.getPais() + "</td></tr>\n");
                        }
                    %>
                </table>
            </div>
        </form>
    </body>
</html>