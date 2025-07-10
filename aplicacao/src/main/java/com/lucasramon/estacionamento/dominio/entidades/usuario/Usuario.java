package com.lucasramon.estacionamento.dominio.entidades.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    
    Long id;
    String nomeDeUsuario;
    String senha;
    String email;
    Regra  regra;
}
