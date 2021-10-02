package br.com.mauricio.goulart.util;

import br.com.mauricio.goulart.model.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClienteHtmlUtil {
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

    private final String htmlCadastroForm = "<form class=\"form-horizontal\" action=\"cadastro-cliente\" method=\"post\">\n" +
            "        <div class=\"page-header\">\n" +
            "            <h1>Cadastro Cliente</h1>\n" +
            "        </div>\n" +
            "        <div class=\"col-xs-3\">\n" +
            "            <div class=\"checkbox span3\"><label>C&oacute;digo</label><input class=\"input-group text-center\" type=\"text\" name=\"idCliente\" size=\"4\" autofocus></div>\n" +
            "            <div class=\"checkbox span3\"><label>Nome</label><input class=\"input-group\" type=\"text\" name=\"nomeCliente\" size=\"35\"></div>\n" +
            "            <div class=\"checkbox span2\"><label>CPF</label><input class=\"input-group\" type=\"text\" name=\"cpfCliente\" size=\"9\"></div>\n" +
            "            <div class=\"checkbox span2\"><label>Telefone</label><input class=\"input-group\" type=\"tel\" name=\"telefoneCliente\" size=\"13\"></div>\n" +
            "            <div class=\"checkbox span3\"><label>Email</label><input class=\"input-group\" type=\"text\" name=\"emailCliente\" size=\"35\" ></div>\n" +
            "            <div class=\"checkbox span3\"><label>CEP</label><input class=\"input-group\" type=\"text\" name=\"cepCliente\" size=\"10\"></div>\n" +
            "            <div class=\"checkbox span\"><label>Cidade</label><input class=\"input-group\" type=\"text\" name=\"cidadeCliente\" size=\"13\"></div>\n" +
            "            <div class=\"checkbox span1\"><label>UF</label><input class=\"input-group\" type=\"text\" name=\"cepCliente\" size=\"2\"></div>\n" +
            "            <div class=\"checkbox span4\"><label>Rua</label><input class=\"input-group\" type=\"text\" name=\"ruaCliente\" size=\"35\"></div>\n" +
            "            <div class=\"checkbox span2\"><label>Bairro</label><input class=\"input-group\" type=\"text\" name=\"bairroCliente\" size=\"12\"></div>\n" +
            "            <div class=\"checkbox span1\"><label>Pa&iacute;s</label><input class=\"input-group\" type=\"text\" name=\"paisCliente\" size=\"13\">\n" +
            "            </div>\n" +
            "            <div class=\"checkbox span4\">\n" +
            "                <input class=\"btn-group btn-success\" name=\"action\" type=\"submit\" value=\"Salvar\">\n" +
            "                <input class=\"btn-group btn-danger\" name=\"action\" type=\"submit\" value=\"Deletar\">\n" +
            "                <input class=\"btn-group btn-info\" name=\"action\" type=\"submit\" value=\"Vendas\">\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </form>";

    private final String htmlTableColumn = "<form class=\"form-horizontal\">" +
            "<div class=\"checkbox\">" + "<table class=\"table-bordered\">" +
            "<tr>" +
            "<th>ID</th>" +
            "<th>Nome</th>" +
            "<th>CPF</th>" +
            "<th>Telefone</th>" +
            "<th>Email</th>" +
            "<th>CEP</th>" +
            "<th>Endere&ccedil;o</th>" +
            "</tr>";


    private final String htmlTableFooter = "</table></div></form>";

    public String getHtmlTableRows(Cliente cliente) {
        return "<tr><td>" + cliente.getId() +
                "</td><td>" + cliente.getNome() +
                "</td><td>" + cliente.getCpf() +
                "</td><td>" + cliente.getTelefone() +
                "</td><td>" + cliente.getEmail() +
                "</td><td>" + cliente.getEndereco().getCep() +
                "</td><td>" + cliente.getEndereco().getRua() +
                "</td></tr>";
    }

    public String getHtmlTableEmptyRows() {
        return "<tr><td>Null" +
                "</td><td>Null" +
                "</td><td>Null" +
                "</td><td>Null" +
                "</td><td>Null" +
                "</td><td>Null" +
                "</td><td>Null" +
                "</td></tr>";
    }
}
