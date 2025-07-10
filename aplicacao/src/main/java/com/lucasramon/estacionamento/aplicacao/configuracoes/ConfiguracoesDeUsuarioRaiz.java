package com.lucasramon.estacionamento.aplicacao.configuracoes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lucasramon.estacionamento.dominio.entidades.usuario.Regra;
import com.lucasramon.estacionamento.dominio.entidades.usuario.Usuario;

@Component
public class ConfiguracoesDeUsuarioRaiz {


    @Value("${usuario.nome}")
    private  String nomeDeUsuario;
    @Value("${usuario.senha}")
    private  String senha;
    @Value("${usuario.email}")
    private  String email;

    public Usuario novoUsuarioRaiz() {
        return Usuario.builder()
                .nomeDeUsuario(nomeDeUsuario)
                .senha(senha)   
                .email(email)
                .regra(Regra.ADMINISTRADOR)
                .build();
    }

}
