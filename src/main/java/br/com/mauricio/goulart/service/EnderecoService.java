package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.model.Endereco;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class EnderecoService {

    public Endereco criarEndereco(HttpServletRequest req) {
        return new Endereco(
                Optional.of(req.getParameter("ruaCliente")).orElse(null),
                Optional.of(req.getParameter("cepCliente")).orElse(null),
                Optional.of(req.getParameter("bairroCliente")).orElse(null),
                Optional.of(req.getParameter("cidadeCliente")).orElse(null),
                Optional.of(req.getParameter("paisCliente")).orElse(null));
    }
}
