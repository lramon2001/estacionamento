package com.lucasramon.estacionamento.dominio.entidades.acesso;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Acesso {

    Long id;
    LocalDateTime dataHora;
    String placaVeiculo;
    TipoAcesso tipoAcesso;
}
