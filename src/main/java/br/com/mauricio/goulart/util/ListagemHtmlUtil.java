package br.com.mauricio.goulart.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ListagemHtmlUtil {
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

    private final String htmlForm = "<form class=\"form-horizontal\" action=\"/listagem\" method=\"post\">" +
            "        <div class=\"page-header\" align=\"center\">\n" +
            "            <img src=\"../resources/img/logo.png\">\n" +
            "           <div align=\"left\">\n" +
            "               <h1>Listagem</h1>\n" +
            "           </div>\n" +
            "         </div>\n" +
            "        <div class=\"checkbox lead\">\n" +
            "                <input class=\"btn-group btn-success\" name=\"action\" type=\"submit\" value=\"Vendas\">\n" +
            "                <input class=\"btn-group btn-danger\" name=\"action\" type=\"submit\" value=\"Clientes\">\n" +
            "         </div>\n" +
            "    </form>";
}
