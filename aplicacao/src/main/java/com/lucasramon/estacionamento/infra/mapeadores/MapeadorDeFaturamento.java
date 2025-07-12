package com.lucasramon.estacionamento.infra.mapeadores;

import org.springframework.stereotype.Component;

import com.lucasramon.estacionamento.dominio.entidades.faturamento.Faturamento;
import com.lucasramon.estacionamento.infra.esquemas.FaturamentoEsquema;

@Component
public class MapeadorDeFaturamento implements Mapeador<Faturamento, FaturamentoEsquema> {

    @Override
    public Faturamento paraEntidade(FaturamentoEsquema schema) {
        return Faturamento.builder().id(schema.getId()).data(schema.getData())
                .totalFaturamento(schema.getTotalFaturamento()).build();
    }

    @Override
    public FaturamentoEsquema paraEsquema(Faturamento entity) {
        return FaturamentoEsquema.builder().id(entity.getId())
                .data(entity.getData())
                .totalFaturamento(entity.getTotalFaturamento()).build();
    }

}
