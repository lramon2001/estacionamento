package com.lucasramon.estacionamento.dominio.entidades.veiculo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veiculo {
    private String placa;
    private String modelo;
    private String cor;
}
