package br.com.mauricio.goulart.model;

import lombok.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String genero;
    private Endereco endereco;


}
