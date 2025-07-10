package com.lucasramon.estacionamento.infra.mapeadores;


import org.springframework.stereotype.Component;

import com.lucasramon.estacionamento.dominio.entidades.acesso.Acesso;
import com.lucasramon.estacionamento.infra.esquemas.AcessoEsquema;

@Component
public class MapeadorDeAcesso implements Mapeador<Acesso, AcessoEsquema> {

       
    @Override
    public Acesso paraEntidade(AcessoEsquema schema) {
        if (schema == null) {
            return null;
        }
        Acesso acesso = new Acesso();
        acesso.setId(schema.getId());
        acesso.setDataHora(schema.getDataHora());
        acesso.setPlacaVeiculo(schema.getPlacaVeiculo() != null ? schema.getPlacaVeiculo() : null);
        acesso.setTipoAcesso(schema.getTipoAcesso() != null ? schema.getTipoAcesso() : null);
        return acesso;
    }

    @Override
    public AcessoEsquema paraEsquema(Acesso entity) {
        if (entity == null) {
            return null;
        }
        AcessoEsquema schema = new AcessoEsquema();
        schema.setId(entity.getId());
        schema.setDataHora(entity.getDataHora());
        schema.setPlacaVeiculo(entity.getPlacaVeiculo() != null ? entity.getPlacaVeiculo() : null);
        schema.setTipoAcesso(entity.getTipoAcesso() != null ? entity.getTipoAcesso() : null);
        return schema;
    }
    
}
