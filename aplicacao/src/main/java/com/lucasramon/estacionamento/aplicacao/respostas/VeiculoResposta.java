package com.lucasramon.estacionamento.aplicacao.respostas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeiculoResposta extends Resposta<VeiculoResposta> {
    private String placa;
    private String modelo;
    private String cor;
    private String cpfProprietario;
}
