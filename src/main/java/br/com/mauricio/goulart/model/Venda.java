package br.com.mauricio.goulart.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Venda {
    private int id;
    private int quantidade;
    private String nome;
    private Cliente cliente;
}
