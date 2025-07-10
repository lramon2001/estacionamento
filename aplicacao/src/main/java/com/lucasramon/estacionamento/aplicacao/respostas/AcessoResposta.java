package com.lucasramon.estacionamento.aplicacao.respostas;

import java.time.LocalDateTime;

import com.lucasramon.estacionamento.dominio.entidades.acesso.TipoAcesso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcessoResposta extends Resposta<AcessoResposta> {
    Long id;
    LocalDateTime dataHora;
    String placaVeiculo;
    TipoAcesso tipoAcesso;
}
