package com.lucasramon.estacionamento.aplicacao.conversores;

import org.springframework.stereotype.Component;

import com.lucasramon.estacionamento.aplicacao.requisicoes.VeiculoMensalistaRequisicao;
import com.lucasramon.estacionamento.aplicacao.requisicoes.VeiculoRequisicao;
import com.lucasramon.estacionamento.aplicacao.respostas.VeiculoResposta;
import com.lucasramon.estacionamento.dominio.entidades.veiculo.Veiculo;
import com.lucasramon.estacionamento.dominio.entidades.veiculo.VeiculoMensalista;

@Component
public class ConversorVeiculo implements Conversor<VeiculoRequisicao,VeiculoResposta,Veiculo> {

    @Override
    public Veiculo paraEntidade(VeiculoRequisicao input) {
       Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(input.getPlaca());
        veiculo.setModelo(input.getModelo());
        veiculo.setCor(input.getCor());
        
        return veiculo;
    }

    public VeiculoMensalista paraEntidadeMensalista(VeiculoMensalistaRequisicao input) {
        VeiculoMensalista veiculo = new VeiculoMensalista();
        veiculo.setPlaca(input.getPlaca());
        veiculo.setModelo(input.getModelo());
        veiculo.setCor(input.getCor());
        veiculo.setCpfProprietario(input.getCpfProprietario());
        return veiculo;
    }

    @Override
    public VeiculoResposta paraOutput(Veiculo entity) {
        VeiculoResposta resposta = new VeiculoResposta();
        resposta.setPlaca(entity.getPlaca());
        resposta.setModelo(entity.getModelo());
        resposta.setCor(entity.getCor());
        return resposta;
    }

   
    
}
