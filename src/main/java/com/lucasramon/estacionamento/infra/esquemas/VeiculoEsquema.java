package com.lucasramon.estacionamento.infra.esquemas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "veiculos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VeiculoEsquema {
    
    @Id
    @Column(name = "placa", length = 7, nullable = false)
    private String placa;

    @Column(name = "modelo", length = 60)
    private String modelo;

    @Column(name = "cor", length = 30)
    private String cor;

    @Column(name = "cpf_proprietario", length = 11, nullable = true)
    private String cpfProprietario;
}
