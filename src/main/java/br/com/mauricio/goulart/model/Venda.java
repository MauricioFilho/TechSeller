package br.com.mauricio.goulart.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Venda {
    private String id;
    private String quantidade;
    private String nome;
    private String valor;
    private Cliente cliente;

    public float getValorTotal() {
        return Float.parseFloat(valor) * Integer.parseInt(quantidade);
    }
}
