package com.lucasramon.estacionamento.dominio.casos_de_uso;

public interface InterfaceDeCasoDeUso<I,O> {

    O executar(I input) throws Exception;
    
}
