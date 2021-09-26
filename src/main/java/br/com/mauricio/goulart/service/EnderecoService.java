package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.model.Endereco;

import javax.servlet.http.HttpServletRequest;

public class EnderecoService {

    Endereco endereco = new Endereco();

    public Endereco criarEndereco(HttpServletRequest request) {
        endereco = new Endereco(
                Long.parseLong(request.getParameter("idEndereco")),
                request.getParameter("ruaCliente"),
                request.getParameter("cepCliente"),
                request.getParameter("bairroCliente"),
                request.getParameter("cidadeCliente"),
                request.getParameter("paisCliente"),
                request.getParameter("detalhesCliente"));

        return endereco;
    }
}
