package br.com.mauricio.goulart.service;

import br.com.mauricio.goulart.model.Venda;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VendaService {
    ClienteService clienteService = new ClienteService();

    public HttpServletResponse salvar(HttpServletRequest request, HttpServletResponse response){
        String keyString = "ClienteID="  + request.getParameter("idCliente");
        Venda venda = criarVenda(request);
        response.addCookie(new Cookie("VendaID=" + venda.getId(), venda.toString()));
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

    private Venda criarVenda(HttpServletRequest request) {
        return new Venda(
                Integer.parseInt(request.getParameter("idVenda")),
                Integer.parseInt(request.getParameter("quantidadeVenda")),
                request.getParameter("nomeProduto"),
                Integer.parseInt(request.getParameter("idCliente")));
    }
}
