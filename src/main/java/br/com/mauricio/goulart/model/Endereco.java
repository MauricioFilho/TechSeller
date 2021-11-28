package br.com.mauricio.goulart.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private int id;
    private String rua;
    private String cep;
    private String bairro;
    private String cidade;
    private String pais;
}
