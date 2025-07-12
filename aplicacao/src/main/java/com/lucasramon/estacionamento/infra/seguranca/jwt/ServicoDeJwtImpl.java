package com.lucasramon.estacionamento.infra.seguranca.jwt;

import java.util.Date;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class ServicoDeJwtImpl implements ServicoDeJwt {
    @Autowired
    ConfiguracoesVariaviesJwt configuracoesVariaviesJwt;

    @Override
    public String gerarTokenDeAcesso(String nomeDeUsuario) {
        return this.gerarToken(nomeDeUsuario, configuracoesVariaviesJwt.getSegredoJwt(),
                configuracoesVariaviesJwt.getTempoExpiracaoJwt());
    }

    @Override
    public String obterNomeDeUsuarioPeloTokenDeAcesso(String token) {
        return this.obterNomeDeUsuarioPeloToken(token, configuracoesVariaviesJwt.getSegredoJwt());
    }

    @Override
    public String gerarTokenDeAtualizacao(String nomeDeUsuario) {
        return this.gerarToken(nomeDeUsuario, configuracoesVariaviesJwt.getSegredoAtualizacaoJwt(),
                configuracoesVariaviesJwt.getTempoExpiracaoAtualizacaoJwt());
    }

    @Override
    public String obterNomeDeUsuarioPeloTokenDeAtualizcao(String token) {
        return this.obterNomeDeUsuarioPeloToken(token, configuracoesVariaviesJwt.getSegredoAtualizacaoJwt());

    }

    private String gerarToken(String nomeDeUsuario, String segredo, Long tempoExpiracaoAtualizacao) {
        var chave = Keys.hmacShaKeyFor(segredo.getBytes());
        var agora = Instant.now();
        var dataDeExpiracao = agora.plusSeconds(tempoExpiracaoAtualizacao);
        return Jwts.builder()
                .setSubject(nomeDeUsuario)
                .setIssuedAt(Date.from(agora))
                .setExpiration(Date.from(dataDeExpiracao))
                .signWith(chave)
                .compact();

    }

    private String obterNomeDeUsuarioPeloToken(String token, String segredo) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(segredo.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
