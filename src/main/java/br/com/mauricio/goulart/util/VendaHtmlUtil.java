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
            "        <div class=\"page-header\" align=\"center\">\n" +
            "            <img src=\"../resources/img/logo.png\">\n" +
            "           <div align=\"left\">\n" +
            "               <h1>Cadastro Vendas</h1>\n" +
            "           </div>\n" +
            "        </div>\n" +
            "        <div class=\"col-xs-3\">\n" +
            "            <div class=\"checkbox span4\"><label>C&oacute;digo</label><input class=\"input-group text-center\" type=\"text\" name=\"idVenda\" size=\"4\" autofocus></div>\n" +
            "            <div class=\"checkbox span5\"><label>Quantidade</label><input class=\"input-group\" type=\"text\" name=\"quantidadeVenda\" size=\"5\"></div>\n" +
            "            <div class=\"checkbox span6\"><label>Produto</label><input class=\"input-group\" type=\"text\" name=\"nomeProduto\" size=\"30\"></div>\n" +
            "            <div class=\"checkbox span7\"><label>Valor</label><input class=\"input-group\" type=\"tel\" name=\"valorProduto\" size=\"5\"></div>\n" +
            "            <div class=\"checkbox span7\"><label>ID Cliente</label><input class=\"input-group\" type=\"tel\" name=\"idCliente\" size=\"5\"></div>\n" +
            "            <div class=\"checkbox span3\">\n" +
            "                <input class=\"btn-group btn-success\" name=\"action\" type=\"submit\" value=\"Salvar\">\n" +
            "                <input class=\"btn-group btn-danger\" name=\"action\" type=\"submit\" value=\"Deletar\">\n" +
            "                <input class=\"btn-group btn-info\" name=\"action\" type=\"submit\" value=\"Clientes\">\n" +
            "                <input class=\"btn-group btn-primary\" name=\"action\" type=\"submit\" value=\"Listagem\">\n" +
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
            "<th>Cliente</th>" +
            "<th>Email</th>" +
            "<th>CEP</th>" +
            "<th>Endere&ccedil;o</th>" +
            "</tr>";

    private final String htmlTableFooter = "</table></div></form>";

   /* public String getHtmlTableRowsWithCliente(Venda venda) {
        return "<tr><td>" + venda.getId() +
                "</td><td>" + venda.getQuantidade() +
                "</td><td>" + venda.getNome() +
                "</td><td>R$" + venda.getValor() +
                "</td><td>R$" + venda.getValorTotal() +
                "</td><td>" + venda.getCliente().getNome() +
                "</td><td>" + venda.getCliente().getEmail() +
                "</td><td>" + venda.getCliente().getEndereco().getCep() +
                "</td><td>" + venda.getCliente().getEndereco().getRua() +
                "</td></tr>";
    }*/

    public String getHtmlTableEmptyRows() {
        return "<tr><td>----" +
                "</td><td>----" +
                "</td><td>----" +
                "</td><td>----" +
                "</td><td>----" +
                "</td><td>----" +
                "</td><td>----" +
                "</td><td>----" +
                "</td><td>----" +
                "</td></tr>";
    }
}
