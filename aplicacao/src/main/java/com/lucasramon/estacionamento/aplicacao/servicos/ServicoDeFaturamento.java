package com.lucasramon.estacionamento.aplicacao.servicos;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasramon.estacionamento.dominio.casos_de_uso.faturamento.GerarPdfFaturamentoCasoDeUso;

@Service
public class ServicoDeFaturamento {
    
    @Autowired
    private GerarPdfFaturamentoCasoDeUso gerarPdfFaturamentoCasoDeUso;

    public byte[] gerarPdf(LocalDate inicio, LocalDate fim){
        return this.gerarPdfFaturamentoCasoDeUso.gerarPdf(inicio, fim);
    }


}
