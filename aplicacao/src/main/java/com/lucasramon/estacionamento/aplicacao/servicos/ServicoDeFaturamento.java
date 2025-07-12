package com.lucasramon.estacionamento.aplicacao.servicos;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lucasramon.estacionamento.dominio.casos_de_uso.faturamento.CalculaFaturamentoCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.faturamento.GerarPdfFaturamentoCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.faturamento.GravaFaturamentoCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.faturamento.Faturamento;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeAcessos;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeFaturamentos;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;

@Service
public class ServicoDeFaturamento {
    
    private final GerarPdfFaturamentoCasoDeUso gerarPdfFaturamentoCasoDeUso;
    private final CalculaFaturamentoCasoDeUso calculaFaturamentoCasoDeUso;
    private final GravaFaturamentoCasoDeUso   gravaFaturamentoCasoDeUso;

    public ServicoDeFaturamento(GerarPdfFaturamentoCasoDeUso gerarPdfFaturamentoCasoDeUso, RepositorioDeVeiculos repositorioDeVeiculos,RepositorioDeAcessos repositorioDeAcessos,RepositorioDeFaturamentos repositorioDeFaturamentos) {
        this.gerarPdfFaturamentoCasoDeUso = gerarPdfFaturamentoCasoDeUso;
        this.calculaFaturamentoCasoDeUso  = new CalculaFaturamentoCasoDeUso(repositorioDeVeiculos, repositorioDeAcessos);
        this.gravaFaturamentoCasoDeUso = new GravaFaturamentoCasoDeUso(repositorioDeFaturamentos);
    }



    public byte[] gerarPdf(LocalDate inicio, LocalDate fim){
        return this.gerarPdfFaturamentoCasoDeUso.gerarPdf(inicio, fim);
    }

    @Scheduled(cron = "0 55 23 * * *")
    public void gravaFaturamentoDiario(){
        Double totalFaturamento = this.calculaFaturamentoCasoDeUso.executar(LocalDate.now());

        Faturamento faturamento = Faturamento.builder().data(LocalDate.now())
        .totalFaturamento(totalFaturamento)
        .build();

        try {
            this.gravaFaturamentoCasoDeUso.executar(faturamento);
        } catch (Exception e) {
        }
        

    }


}
