package com.lucasramon.estacionamento.aplicacao.requisicoes;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VeiculoMensalistaRequisicao extends VeiculoRequisicao{
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$", message = "CPF inválido")
    private String cpfProprietario;
}
