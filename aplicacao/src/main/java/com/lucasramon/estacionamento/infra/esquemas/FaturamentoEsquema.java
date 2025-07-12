package com.lucasramon.estacionamento.infra.esquemas;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "faturamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaturamentoEsquema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data", nullable = false)
    @NotBlank
    private LocalDate data;

    @Column(name = "total_faturamento", nullable = false)
    @NotBlank
    private double totalFaturamento;
    
}
