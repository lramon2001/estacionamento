package com.lucasramon.estacionamento.aplicacao.requisicoes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoRequisicao {
    private String placa;
    private String modelo;
    private String cor;
}
