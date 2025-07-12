package com.lucasramon.estacionamento.infra.esquemas;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proprietario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProprietarioEsquema {

        @Id
        @Column(name = "cpf", nullable = false, length = 11)
        private String cpf;

        @Column(name = "nome", nullable = false, length = 100)
        @NotBlank
        private String nome;

        @Column(name = "telefone", length = 20)
        @Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Telefone inválido")
        private String telefone;

        @Column(name = "email", length = 120)
        @Email
        private String email;

        @Column(name = "data_nascimento")
        @NotBlank
        private LocalDate dataNascimento;
}
