package br.com.mauricio.goulart.util;

import br.com.mauricio.goulart.model.Venda;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VendaHtmlUtil {
    private final String htmlHeader = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "    <link rel=\"stylesheet\" href=\"../css/bootstrap-theme.css\"/>\n" +
            "    <link rel=\"stylesheet\" href=\"../css/bootstrap2-toggle.min.css\"/>\n" +
            "    <link rel=\"stylesheet\" href=\"../css/bootstrap.css\"/>\n" +
            "    <link rel=\"stylesheet\" href=\"../css/font-awesome.css\"/>\n" +
            "    <link rel=\"stylesheet\" href=\"../css/tv-rodape.css\"/>\n" +
            "    <link rel=\"stylesheet\" href=\"../css/tv.css\"/>\n" +
            "    <link rel=\"stylesheet\" href=\"../css/bootstrap-responsive.css\"/>\n" +
            "    <link rel=\"stylesheet\" href=\"../css/style.css\"/>\n" +
            "    <title>Cadastro Clientes</title>\n" +
            "</head></body>";

    private final String htmlFooter = "</body></html>";

    private final String htmlCadastroForm = "<form class=\"form-horizontal\" action=\"cadastro-vendas\" method=\"post\">\n" +
            "        <div class=\"page-header\">\n" +
            "            <h1>Cadastro Vendas</h1>\n" +
            "        </div>\n" +
            "        <div class=\"col-xs-3\">\n" +
            "            <div class=\"checkbox span4\"><label>C&oacute;digo</label><input class=\"input-group text-center\" type=\"text\" name=\"idVenda\" size=\"4\" autofocus></div>\n" +
            "            <div class=\"checkbox span5\"><label>Quantidade</label><input class=\"input-group\" type=\"text\" name=\"quantidadeVenda\" size=\"5\"></div>\n" +
            "            <div class=\"checkbox span6\"><label>Produto</label><input class=\"input-group\" type=\"text\" name=\"nomeProduto\" size=\"9\"></div>\n" +
            "            <div class=\"checkbox span7\"><label>Valor</label><input class=\"input-group\" type=\"tel\" name=\"valorProduto\" size=\"5\"></div>\n" +
            "            <div class=\"checkbox span3\">\n" +
            "                <input class=\"btn-group btn-success\" name=\"action\" type=\"submit\" value=\"Salvar\">\n" +
            "                <input class=\"btn-group btn-danger\" name=\"action\" type=\"submit\" value=\"Deletar\">\n" +
            "                <input class=\"btn-group btn-info\" name=\"action\" type=\"submit\" value=\"Clientes\">\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </form>";

    private final String htmlTableColumn = "<form class=\"form-horizontal\">" +
            "<div class=\"checkbox\">" + "<table class=\"table-bordered\">" +
            "<tr>" +
            "<th>ID</th>" +
            "<th>Quantidade</th>" +
            "<th>Produto</th>" +
            "<th>Valor(un)</th>" +
            "<th>Total</th>" +
            "</tr>";

    private final String htmlTableFooter = "</table></div></form>";

    public String getHtmlTableRows(Venda venda) {
        return "<tr><td>" + venda.getId() +
                "</td><td>" + venda.getQuantidade() +
                "</td><td>" + venda.getNome() +
                "</td><td>R$" + venda.getValor() +
                "</td><td>R$" + venda.getValorTotal() +
                "</td></tr>";
    }

    public String getHtmlTableEmptyRows() {
        return "<tr><td>Null" +
                "</td><td>Null" +
                "</td><td>Null" +
                "</td><td>Null" +
                "</td><td>Null" +
                "</td></tr>";
    }
}
