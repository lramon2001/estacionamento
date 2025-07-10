package com.lucasramon.estacionamento.dominio.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Paginacao {
    private final int pagina;   
    private final int tamanho;  

    public Paginacao(int pagina, int tamanho) {
        if (pagina < 0) throw new IllegalArgumentException("Página deve ser ≥ 0");
        if (tamanho <= 0) throw new IllegalArgumentException("Tamanho deve ser > 0");
        this.pagina  = pagina;
        this.tamanho = tamanho;
    }

}
