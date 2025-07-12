package com.lucasramon.estacionamento.aplicacao.requisicoes;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProprietarioRequisicao {
    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @NotBlank
    private String telefone;
    @Email
    private String email;
    
    private LocalDate dataNascimento;
}
