package com.lucasramon.estacionamento.infra.seguranca.filtros;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lucasramon.estacionamento.aplicacao.servicos.ServicoDeUsuario;
import com.lucasramon.estacionamento.infra.seguranca.jwt.ServicoDeJwt;
import com.lucasramon.estacionamento.infra.util.ConstantesDaInfra;

import io.jsonwebtoken.JwtException;

@Component
public class FiltroDeTokensDeAcesso extends OncePerRequestFilter {

    @Autowired
    private ServicoDeJwt servicoDeJwtImpl;

    @Autowired
    private ServicoDeUsuario servicoDeUsuario;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            var token = "";
            var nome = "";
            var cabecalho = request.getHeader(ConstantesDaInfra.CABECALHO_AUTORIZACAO);
            if (tokenEstaPresente(cabecalho)) {
                token = cabecalho.substring(ConstantesDaInfra.PREFIXO_DO_TOKEN.length());
                nome = servicoDeJwtImpl.obterNomeDeUsuarioPeloTokenDeAcesso(token);
            }
            if (nomeValido(nome)) {
                defineAutenticacao(request, nome);
            }

            filterChain.doFilter(request, response);
        } catch (JwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getLocalizedMessage());
        }
    }

    private void defineAutenticacao(HttpServletRequest request, String nome) {
        var detalhaesDoUsuario = servicoDeUsuario.loadUserByUsername(nome);
        var autenticacao = new UsernamePasswordAuthenticationToken(
                detalhaesDoUsuario,
                null,
                detalhaesDoUsuario.getAuthorities());
        autenticacao.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(autenticacao);
    }

    private boolean tokenEstaPresente(String cabecalho) {
        return cabecalho != null && cabecalho.startsWith(ConstantesDaInfra.PREFIXO_DO_TOKEN);
    }

    private boolean nomeValido(String nome) {
        return nome != null && !nome.isEmpty();
    }

}
