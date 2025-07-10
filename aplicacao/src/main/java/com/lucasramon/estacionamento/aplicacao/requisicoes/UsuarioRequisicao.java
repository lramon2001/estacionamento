package com.lucasramon.estacionamento.aplicacao.requisicoes;

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
    String nomeDeUsuario;
    String senha;
    String email;
    Regra regra;
}
