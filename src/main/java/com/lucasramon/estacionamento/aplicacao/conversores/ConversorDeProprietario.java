package com.lucasramon.estacionamento.aplicacao.conversores;


import org.springframework.stereotype.Component;

import com.lucasramon.estacionamento.aplicacao.requisicoes.ProprietarioRequisicao;
import com.lucasramon.estacionamento.aplicacao.respostas.ProprietarioResposta;
import com.lucasramon.estacionamento.dominio.entidades.proprietario.Proprietario;

@Component
public class ConversorDeProprietario implements Conversor<ProprietarioRequisicao, ProprietarioResposta,Proprietario> {

    @Override
    public Proprietario paraEntidade(ProprietarioRequisicao input) {
        return Proprietario.builder()
                .nome(input.getNome())
                .cpf(input.getCpf())
                .telefone(input.getTelefone())
                .email(input.getEmail())
                .dataNascimento(input.getDataNascimento())
                .build();
    }

    @Override
    public ProprietarioResposta paraOutput(Proprietario entity) {
        return ProprietarioResposta.builder()
                .nome(entity.getNome())
                .cpf(entity.getCpf())
                .telefone(entity.getTelefone())
                .email(entity.getEmail())
                .dataNascimento(entity.getDataNascimento())
                .build();
    }

   
    
}
