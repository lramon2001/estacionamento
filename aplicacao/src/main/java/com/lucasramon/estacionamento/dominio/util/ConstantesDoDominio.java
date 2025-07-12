package com.lucasramon.estacionamento.dominio.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ConstantesDoDominio {

    public static int QUANTIDADE_TOTAL_DE_VAGAS = 200;

    public static LocalTime HORA_FECHAMENTO_ESTACIONAMENTO = LocalTime.of(23, 0);
    public static LocalTime HORA_ABERTURA_ESTACIONAMENTO = LocalTime.of(7, 0);
    public static final double PRECO_MEIA_HORA = 5.0;  
    public static final double PRECO_PRIMEIRA_HORA = 6.0; 
    public static final double PRECO_HORA_ADICIONAL = 2.0; 
    public static final double PRECO_MENSALISTA = 5.0; 

    public static final String MENSAGEM_ESTACIONAMENTO_FECHADO = "O estacionamento está fechado.";

    
}
