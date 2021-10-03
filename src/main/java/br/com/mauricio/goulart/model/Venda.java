package br.com.mauricio.goulart.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Venda {
    private int id;
    private int quantidade;
    private String nome;
    private Float valor;
    private Cliente cliente;

    public float getValorTotal() {
        return valor * quantidade;
    }
}
