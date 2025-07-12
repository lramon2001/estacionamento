package com.lucasramon.estacionamento.aplicacao.requisicoes;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GerarPdfFaturamentoRequisicao {
    @NotBlank
    private LocalDate inicio;
    @NotBlank
    private LocalDate fim;
    
}
