package com.lucasramon.estacionamento.infra.seguranca.jwt;

import org.springframework.stereotype.Component;

@Component
public interface ServicoDeJwt {
    
    String gerarTokenDeAcesso(String nomeDeUsuario);
    
    String obterNomeDeUsuarioPeloTokenDeAcesso(String token);

    String gerarTokenDeAtualizacao(String nomeDeUsuario);
    
    String obterNomeDeUsuarioPeloTokenDeAtualizcao(String token);



}
