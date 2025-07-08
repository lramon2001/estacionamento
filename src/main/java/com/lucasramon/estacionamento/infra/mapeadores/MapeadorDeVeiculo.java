package com.lucasramon.estacionamento.infra.mapeadores;

import org.springframework.stereotype.Component;

import com.lucasramon.estacionamento.dominio.entidades.veiculo.Veiculo;
import com.lucasramon.estacionamento.infra.esquemas.VeiculoEsquema;

@Component
public class MapeadorDeVeiculo implements Mapeador<Veiculo,VeiculoEsquema>{

    @Override
    public Veiculo paraEntidade(VeiculoEsquema schema) {
        if (schema == null) {
            return null;
        }
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(schema.getPlaca());
        veiculo.setModelo(schema.getModelo());
        veiculo.setCor(schema.getCor());
        return veiculo;
    }

    @Override
    public VeiculoEsquema paraEsquema(Veiculo entity) {
        if (entity == null) {
            return null;
        }
        VeiculoEsquema schema = new VeiculoEsquema();
        schema.setPlaca(entity.getPlaca());
        schema.setModelo(entity.getModelo());
        schema.setCor(entity.getCor());
        return schema;
    }
    
}
