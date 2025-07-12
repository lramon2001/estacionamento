package com.lucasramon.estacionamento.aplicacao.conversores;

import org.springframework.stereotype.Component;

import com.lucasramon.estacionamento.aplicacao.respostas.PainelResposta;
import com.lucasramon.estacionamento.dominio.modelos_de_dados.ConsultaPainel;

@Component
public class ConversorDoPainel {

    public PainelResposta paraOutput(ConsultaPainel consultaPainel){
        return PainelResposta.builder().ultimosAcessos(consultaPainel.getUltimosAcessos())
        .vagasDisponiveis(consultaPainel.getVagasDisponiveis())
        .vagasOcupadas(consultaPainel.getVagasOcupadas())
        .totalDeVagas(consultaPainel.getTotalDeVagas())
        .veiculosAtivos(consultaPainel.getVeiculosAtivos())
        .quantidadeDeMensalistas(consultaPainel.getQuantidadeDeMensalistas())
        .numeroDeEntradasHoje(consultaPainel.getNumeroDeEntradasHoje())
        .numeroDeSaidasHoje(consultaPainel.getNumeroDeSaidasHoje())
        .build();

    }
}
