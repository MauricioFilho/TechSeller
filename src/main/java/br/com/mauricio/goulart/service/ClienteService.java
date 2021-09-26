package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.model.Cliente;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClienteService {

    EnderecoService enderecoService = new EnderecoService();

    public HttpServletResponse salvar(HttpServletRequest request, HttpServletResponse response){
        Cliente cliente = criarCliente(request);
        popularCookies(cliente, response);
        return response;
    }

    public HttpServletResponse deletar(List<String> keysList, HttpServletRequest request, HttpServletResponse response){
        final List<Cookie> cookieList = findCookiesByKeyList(keysList, request);
        cookieList.forEach(cookie -> {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        });
        return response;
    }

    public HttpServletResponse update(Map<String, String> alteracaoCookieMap , HttpServletRequest request, HttpServletResponse response){
        final List<Cookie> cookieList = findCookiesByKeyList(new ArrayList<>(alteracaoCookieMap.keySet()), request);

        cookieList.forEach(cookie -> {
            cookie.setValue(alteracaoCookieMap.get(cookie.getName()));
            response.addCookie(cookie);
        });
        return response;
    }

    private List<Cookie> findAllCookies(HttpServletRequest request){
        return Arrays.stream(request.getCookies()).collect(Collectors.toList());
    }

    private List<Cookie> findCookiesByKeyList(List<String> keysList, HttpServletRequest request) {
        List<Cookie> cookieList = findAllCookies(request);
        for (String key: keysList) {
            cookieList = cookieList.stream()
                    .filter(c -> c.getName().equals(key))
                    .collect(Collectors.toList());
        }
        return cookieList;
    }

    private void popularCookies(Cliente cliente, HttpServletResponse response) {
        response.addCookie(new Cookie("idCliente", cliente.getId().toString()));
        response.addCookie(new Cookie("nomeCliente", cliente.getNome()));
        response.addCookie(new Cookie("cpfCliente", cliente.getCpf()));
        response.addCookie(new Cookie("telefoneCliente", cliente.getTelefone()));
        response.addCookie(new Cookie("emailCliente", cliente.getEmail()));
        response.addCookie(new Cookie("generoCliente", cliente.getGenero()));
        response.addCookie(new Cookie("idEndereco", cliente.getEndereco().getId().toString()));
        response.addCookie(new Cookie("ruaCliente", cliente.getEndereco().getRua()));
        response.addCookie(new Cookie("cepCliente", cliente.getEndereco().getCep()));
        response.addCookie(new Cookie("bairroCliente", cliente.getEndereco().getBairro()));
        response.addCookie(new Cookie("cidadeCliente", cliente.getEndereco().getCidade()));
        response.addCookie(new Cookie("paisCliente", cliente.getEndereco().getPais()));
        response.addCookie(new Cookie("detalhesCliente", cliente.getEndereco().getDetalhes()));
    }

    private Cliente criarCliente(HttpServletRequest request) {
        return new Cliente(
                Long.parseLong(request.getParameter("idCliente")),
                request.getParameter("nomeCliente"),
                request.getParameter("cpfCliente"),
                request.getParameter("telefoneCliente"),
                request.getParameter("emailCliente"),
                request.getParameter("generoCliente"),
                enderecoService.criarEndereco(request));
    }
}
