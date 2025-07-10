package com.lucasramon.estacionamento.aplicacao.respostas;

import com.lucasramon.estacionamento.dominio.entidades.usuario.Regra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResposta  extends Resposta<UsuarioResposta> {
    private Long id;
    private String nomeDeUsuario;
    private String token;
    private String email;
    private Regra regra;

    public UsuarioResposta(String token) {
        this.token = token;
    }

}
