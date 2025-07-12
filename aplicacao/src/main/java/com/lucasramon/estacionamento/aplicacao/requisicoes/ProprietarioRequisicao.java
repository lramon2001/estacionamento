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
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$", message = "CPF inválido")
    private String cpf;
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Telefone inválido")
    private String telefone;
    @Email
    private String email;
    @NotBlank
    private LocalDate dataNascimento;
}
