package com.lucasramon.estacionamento.dominio.casos_de_uso.faturamento;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.faturamento.Faturamento;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeFaturamentos;

public class GravaFaturamentoCasoDeUso implements InterfaceDeCasoDeUso<Faturamento,Void> {

    private final RepositorioDeFaturamentos faturamentosRepositorio;

    
    public GravaFaturamentoCasoDeUso(RepositorioDeFaturamentos faturamentosRepositorio) {
        this.faturamentosRepositorio = faturamentosRepositorio;
    }


    @Override
    public Void executar(Faturamento input) throws Exception {

        faturamentosRepositorio.criar(input);

        return null;
    }
    
}
