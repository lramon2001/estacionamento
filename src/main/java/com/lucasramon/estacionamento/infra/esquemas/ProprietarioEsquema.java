package com.lucasramon.estacionamento.infra.esquemas;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @Column(name = "cpf", nullable = false,
            length = 11)                    
    private String cpf;

    @Column(name = "nome", nullable = false,
            length = 100)
    private String nome;

    @Column(name = "telefone", length = 20)   
    private String telefone;

    @Column(name = "email", length = 120)
    private String email;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
}
