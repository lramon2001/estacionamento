package com.lucasramon.estacionamento.aplicacao.requisicoes;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.lucasramon.estacionamento.dominio.entidades.acesso.TipoAcesso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcessoRequisicao {
    Long id;
    @NotBlank
    LocalDateTime dataHora;
    @NotBlank
    String placaVeiculo;
    
    TipoAcesso tipoAcesso;
}
