package com.lucasramon.estacionamento.aplicacao.conversores;

import org.springframework.stereotype.Component;

import com.lucasramon.estacionamento.aplicacao.requisicoes.AcessoRequisicao;
import com.lucasramon.estacionamento.aplicacao.respostas.AcessoResposta;
import com.lucasramon.estacionamento.dominio.entidades.acesso.Acesso;

@Component
public class ConversorDeAcesso implements Conversor<AcessoRequisicao, AcessoResposta, Acesso> {

    @Override
    public Acesso paraEntidade(AcessoRequisicao input) {
        return Acesso.builder()
                .id(input.getId())
                .dataHora(input.getDataHora())
                .placaVeiculo(input.getPlacaVeiculo())
                .tipoAcesso(input.getTipoAcesso())
                .build();
    }

    @Override
    public AcessoResposta paraOutput(Acesso entity) {
        return AcessoResposta.builder()
                .id(entity.getId())
                .dataHora(entity.getDataHora())
                .placaVeiculo(entity.getPlacaVeiculo())
                .tipoAcesso(entity.getTipoAcesso())
                .build();
    }
    
}
