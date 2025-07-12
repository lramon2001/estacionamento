package com.lucasramon.estacionamento.dominio.casos_de_uso.faturamento;

import java.time.LocalDate;

public interface GerarPdfFaturamentoCasoDeUso {
    byte[] gerarPdf(LocalDate inicio, LocalDate fim);
}
