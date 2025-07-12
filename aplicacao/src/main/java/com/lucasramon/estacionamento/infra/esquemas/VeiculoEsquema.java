package com.lucasramon.estacionamento.infra.esquemas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
    @NotBlank
    private String modelo;

    @Column(name = "cor", length = 30)
    @NotBlank
    private String cor;

    @Column(name = "cpf_proprietario", length = 11, nullable = true)
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$", message = "CPF inválido")
    private String cpfProprietario;
}
