package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.model.Endereco;

import javax.servlet.http.HttpServletRequest;

public class EnderecoService {

    public Endereco criarEndereco(HttpServletRequest request) {
        return new Endereco(
                request.getParameter("ruaCliente"),
                request.getParameter("cepCliente"),
                request.getParameter("bairroCliente"),
                request.getParameter("cidadeCliente"),
                request.getParameter("paisCliente"));
    }
}
