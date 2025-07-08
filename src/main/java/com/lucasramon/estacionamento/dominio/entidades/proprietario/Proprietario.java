package com.lucasramon.estacionamento.dominio.entidades.proprietario;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proprietario {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;

}
