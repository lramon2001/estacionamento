package com.lucasramon.estacionamento.dominio.casos_de_uso.acesso;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.acesso.Acesso;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeAcessos;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;

public class RegistrarAcessoCasoDeUso implements InterfaceDeCasoDeUso<Acesso, Void> {

    private final RepositorioDeAcessos acessoRepository;
    private final RepositorioDeVeiculos veiculoRepository;

    public RegistrarAcessoCasoDeUso(RepositorioDeAcessos acessoRepository, RepositorioDeVeiculos veiculoRepository) {
        this.acessoRepository = acessoRepository;
        this.veiculoRepository = veiculoRepository;
    }

    boolean validarEntrada(Acesso acesso) {
        if (acesso.getPlacaVeiculo() == null || acesso.getPlacaVeiculo().isEmpty()) {
            throw new IllegalArgumentException("Placa não pode ser nula ou vazia.");
        }
        return veiculoRepository.veiculoExiste(acesso.getPlacaVeiculo());
    }

    @Override
    public Void executar(Acesso acesso) {
        validarEntrada(acesso);
        acessoRepository.criar(acesso);
        return null;
    }

}
