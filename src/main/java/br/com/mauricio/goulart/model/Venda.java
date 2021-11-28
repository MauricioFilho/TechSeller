package br.com.mauricio.goulart.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Venda {
    private int id;
    private int quantidade;
    private String nomeProduto;
    private BigDecimal valor;
    private Cliente cliente;

    public float getValorTotal() {
        return valor.floatValue() * quantidade;
    }
}
