package com.lucasramon.estacionamento.dominio.entidades.faturamento;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Faturamento {
    
    private int id;
    private LocalDate data;
    private double totalFaturamento;
}
