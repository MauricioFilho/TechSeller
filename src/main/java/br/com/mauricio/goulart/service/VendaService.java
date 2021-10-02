package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.model.Venda;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class VendaService {
    public List<Venda> salvar(HttpServletRequest req, List<Venda> vendas) {
        Venda vendaAlterada = findVenda(vendas, req);
        if(vendaAlterada != null) {
            Venda vendaAlteracao = criarVenda(req);
            return update(vendas, vendaAlteracao, vendaAlterada);
        } else {
            vendas.add(criarVenda(req));
        }
        return vendas;
    }

    public List<Venda> deletar(HttpServletRequest req, List<Venda> vendas) {
        int id = Integer.parseInt(req.getParameter("idVenda"));
        return vendas.stream().filter(v -> v.getId() != id).collect(Collectors.toList());
    }

    private List<Venda> update(List<Venda> vendas, Venda vendaAlteracao, Venda vendaAlterada){
        int vendaIndex = vendas.indexOf(vendaAlterada);
        if (vendaIndex != -1) {
            vendas.set(vendaIndex, vendaAlteracao);
        }
        return vendas;
    }

    public Venda findVenda(List<Venda> vendas, HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("idVenda"));
        return vendas.stream().filter(v -> v.getId() ==  id).findAny().orElse(null);
    }

    private Venda criarVenda(HttpServletRequest req) {
        return new Venda(
                Integer.parseInt(req.getParameter("idVenda")),
                Integer.parseInt(req.getParameter("quantidadeVenda")),
                req.getParameter("nomeProduto"),
                Float.parseFloat(req.getParameter("valorProduto")));
    }
}
