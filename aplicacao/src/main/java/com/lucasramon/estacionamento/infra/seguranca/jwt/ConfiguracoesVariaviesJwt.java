package com.lucasramon.estacionamento.infra.seguranca.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ConfiguracoesVariaviesJwt {
    
    @Value("${jwt.secret}")
    private  String segredoJwt;

    @Value("${jwt.expiration}")
    private  Long tempoExpiracaoJwt;

    @Value("${jwt.refreshSecret}")
    private  String segredoAtualizacaoJwt;

    @Value("${jwt.refreshExpiration}")
    private  Long tempoExpiracaoAtualizacaoJwt;
}
