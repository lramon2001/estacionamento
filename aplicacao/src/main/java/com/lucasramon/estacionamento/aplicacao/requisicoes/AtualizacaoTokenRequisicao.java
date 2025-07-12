package com.lucasramon.estacionamento.aplicacao.requisicoes;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizacaoTokenRequisicao {
    @NotBlank
    private String tokenAtualizacao;
    
}
