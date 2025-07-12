package com.lucasramon.estacionamento.aplicacao.requisicoes;

import javax.validation.constraints.NotBlank;

import com.lucasramon.estacionamento.dominio.entidades.usuario.Regra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequisicao {
    
    Long id;
    @NotBlank
    String nomeDeUsuario;
    @NotBlank
    String senha;
    @NotBlank
    String email;
    
    Regra regra;
}
