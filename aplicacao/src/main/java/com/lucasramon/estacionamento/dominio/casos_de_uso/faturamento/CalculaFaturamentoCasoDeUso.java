package com.lucasramon.estacionamento.dominio.casos_de_uso.faturamento;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.acesso.Acesso;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeAcessos;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;
import com.lucasramon.estacionamento.dominio.util.ConstantesDoDominio;

public class CalculaFaturamentoCasoDeUso implements InterfaceDeCasoDeUso<LocalDate,Double>{

    private final RepositorioDeVeiculos repositorioDeVeiculos;
    private final RepositorioDeAcessos repositorioDeAcessos;

    public CalculaFaturamentoCasoDeUso(RepositorioDeVeiculos repositorioDeVeiculos,
            RepositorioDeAcessos repositorioDeAcessos) {
        this.repositorioDeVeiculos = repositorioDeVeiculos;
        this.repositorioDeAcessos = repositorioDeAcessos;
    }

    public Double executar(LocalDate dia) {
        double faturamentoMensalistas = repositorioDeAcessos.obtemAcessosPorDia(dia).stream()
                .filter(a -> repositorioDeVeiculos.ehVeiculoMensalista(a.getPlacaVeiculo())) 
                .mapToDouble(a -> ConstantesDoDominio.PRECO_MENSALISTA) 
                .sum();
        double faturamentoRotativos = repositorioDeAcessos.obtemAcessosPorDia(dia).stream()
                .filter(a -> !repositorioDeVeiculos.ehVeiculoMensalista(a.getPlacaVeiculo()))
                .mapToDouble(this::calcularFaturamentoRotativo)
                .sum();

        return faturamentoMensalistas + faturamentoRotativos;
    }

    private double calcularFaturamentoRotativo(Acesso acesso) {
        Function<Long, Double> calculaPreco = minutos -> {
            if (minutos <= 30)
                return ConstantesDoDominio.PRECO_MEIA_HORA;
            if (minutos <= 60)
                return ConstantesDoDominio.PRECO_PRIMEIRA_HORA;
            long horasAdicionais = (minutos - 60) / 60 + (minutos % 60 > 0 ? 1 : 0);
            return ConstantesDoDominio.PRECO_PRIMEIRA_HORA
                    + (horasAdicionais * ConstantesDoDominio.PRECO_HORA_ADICIONAL);
        };

        LocalDateTime horaEntrada = acesso.getDataHora();
        LocalDateTime horaSaida = LocalDateTime.now();
        long minutosEstacionado = Duration.between(horaEntrada, horaSaida).toMinutes();

        return calculaPreco.apply(minutosEstacionado);
    }

}
