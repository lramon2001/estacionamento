package com.lucasramon.estacionamento.infra.seguranca.jwt;

import org.springframework.beans.factory.annotation.Value;


public class ConfiguracoesVariaviesJwt {
    
    @Value("${jwt.secret}")
    public static String segredoJwt;

    @Value("${jwt.expiration}")
    public static Long tempoExpiracaoJwt;

    @Value("${jwt.refreshSecret}")
    public static String segredoAtualizacaoJwt;

    @Value("${jwt.refreshExpiration}")
    public static Long tempoExpiracaoAtualizacaoJwt;
}
