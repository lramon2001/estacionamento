package com.lucasramon.estacionamento.aplicacao.conversores;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.lucasramon.estacionamento.aplicacao.requisicoes.UsuarioRequisicao;
import com.lucasramon.estacionamento.aplicacao.respostas.UsuarioResposta;
import com.lucasramon.estacionamento.dominio.entidades.usuario.Usuario;

@Component
public class ConversorDeUsuario implements Conversor<UsuarioRequisicao, UsuarioResposta, Usuario> {

    @Override
    public Usuario paraEntidade(UsuarioRequisicao input) {
        if (input == null) {
            throw new IllegalArgumentException("Requisição de usuário não pode ser nula.");
        }

        return Usuario.builder()
                .nomeDeUsuario(input.getNomeDeUsuario())
                .senha(input.getSenha())
                .email(input.getEmail())
                .regra(input.getRegra())
                .build();
    }

    @Override
    public UsuarioResposta paraOutput(Usuario entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entidade de usuário não pode ser nula.");
        }

        return UsuarioResposta.builder()
                .id(entity.getId())
                .nomeDeUsuario(entity.getNomeDeUsuario())
                .email(entity.getEmail())
                .regra(entity.getRegra())
                .build();
    }

    public UserDetails paraUserDetails(Usuario usuario) {
        return User.builder()
                .username(usuario.getNomeDeUsuario())
                .password(usuario.getSenha())
                .roles(usuario.getRegra().name())
                .build();
    }

}
