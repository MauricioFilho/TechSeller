package br.com.mauricio.goulart.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private Long id;
    private String rua;
    private String cep;
    private String bairro;
    private String cidade;
    private String pais;
    private String detalhes;
}
