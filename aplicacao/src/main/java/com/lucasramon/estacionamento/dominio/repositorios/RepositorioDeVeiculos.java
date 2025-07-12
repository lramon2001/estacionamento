package com.lucasramon.estacionamento.dominio.repositorios;


import com.lucasramon.estacionamento.dominio.entidades.veiculo.Veiculo;

public interface RepositorioDeVeiculos extends InterfaceDeRepositorio<Veiculo, String> {

    boolean veiculoExiste(String placa);

    int contaVeiculosAtivos();

    int contaVeiculosMensalistas();

    boolean ehVeiculoMensalista(String placa);


}
