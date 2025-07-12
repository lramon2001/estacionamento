package com.lucasramon.estacionamento.dominio.execoes;

import com.lucasramon.estacionamento.dominio.util.ConstantesDoDominio;

public class EstacionamentoFechado extends Exception {
    
    public EstacionamentoFechado() {
        super(ConstantesDoDominio.MENSAGEM_ESTACIONAMENTO_FECHADO);
    }

    public EstacionamentoFechado(String message) {
        super(message);
    }

    public EstacionamentoFechado(Throwable cause) {
        super(ConstantesDoDominio.MENSAGEM_ESTACIONAMENTO_FECHADO, cause);
    }

    public EstacionamentoFechado(String message, Throwable cause) {
        super(message, cause);
    }
}
