package com.lucasramon.estacionamento.aplicacao.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.lucasramon.estacionamento.aplicacao.requisicoes.AtualizacaoTokenRequisicao;
import com.lucasramon.estacionamento.aplicacao.requisicoes.LoginRequisicao;
import com.lucasramon.estacionamento.aplicacao.respostas.TokenResposta;
import com.lucasramon.estacionamento.infra.seguranca.jwt.ServicoDeJwt;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicoDeLogin {

    private final AuthenticationManager authenticationManager;

    @Autowired
    private ServicoDeJwt servicoDeJwt;

    public TokenResposta entrar(LoginRequisicao loginRequisicao) {
        var nomeDeUsuarioESenhaToken = new UsernamePasswordAuthenticationToken(loginRequisicao.getNome(),
                loginRequisicao.getSenha());
        authenticationManager.authenticate(nomeDeUsuarioESenhaToken);

        return TokenResposta.builder().token(servicoDeJwt.gerarTokenDeAcesso(loginRequisicao.getNome()))
                .tokenAtualização(servicoDeJwt.gerarTokenDeAtualizacao(loginRequisicao.getNome())).build();
    }

    public TokenResposta gerarNovoToken(AtualizacaoTokenRequisicao atualizacaoTokenRequisicao) {
        var nomeDeUsuario = servicoDeJwt
                .obterNomeDeUsuarioPeloTokenDeAtualizcao(atualizacaoTokenRequisicao.getTokenAtualizacao());
        return TokenResposta.builder().token(servicoDeJwt.gerarTokenDeAcesso(
                nomeDeUsuario)).tokenAtualização(servicoDeJwt.gerarTokenDeAtualizacao(
                        nomeDeUsuario))
                .build();
    }

}
