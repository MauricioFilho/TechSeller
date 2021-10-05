package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.model.Cliente;
import br.com.mauricio.goulart.model.Venda;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VendaService {
    public List<Venda> salvar(HttpServletRequest req, List<Venda> vendas, Cliente cliente) {
        Venda vendaAlterada = null;
        if (!vendas.isEmpty()) {
            vendaAlterada = findVenda(vendas, req);
        }
        if (vendaAlterada != null) {
            Venda vendaAlteracao = criarVenda(req, cliente);
            return update(vendas, vendaAlteracao, vendaAlterada);
        } else {
            Venda novaVenda = null;
            if (cliente != null) {
                novaVenda = criarVenda(req, cliente);
            }
            if (novaVenda != null) {
                vendas.add(novaVenda);
            }
        }
        return vendas;
    }

    public List<Venda> deletar(HttpServletRequest req, List<Venda> vendas) {
        String id =  Optional.of(req.getParameter("idVenda")).orElse(null);
        if (!id.isEmpty()) {
            return vendas.stream().filter(v -> !v.getId().equals(id)).collect(Collectors.toList());
        }
        return vendas;
    }

    private List<Venda> update(List<Venda> vendas, Venda vendaAlteracao, Venda vendaAlterada){
        int vendaIndex = vendas.indexOf(vendaAlterada);
        if (vendaIndex != -1) {
            vendas.set(vendaIndex, vendaAlteracao);
        }
        return vendas;
    }

    public Venda findVenda(List<Venda> vendas, HttpServletRequest req) {
        String id =  Optional.of(req.getParameter("idVenda")).orElse(null);
        return vendas.stream().filter(v -> v.getId().equals(id)).findAny().orElse(null);
    }

    private Venda criarVenda(HttpServletRequest req, Cliente cliente) {
        return new Venda(
                Optional.of(req.getParameter("idVenda")).orElse(null),
                Optional.of(req.getParameter("quantidadeVenda")).orElse(null),
                Optional.of(req.getParameter("nomeProduto")).orElse(null),
                Optional.of(req.getParameter("valorProduto")).orElse(null),
                Optional.of(cliente).orElse(null));
    }
}
