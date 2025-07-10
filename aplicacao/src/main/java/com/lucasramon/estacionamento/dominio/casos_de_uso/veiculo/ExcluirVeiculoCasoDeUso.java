package com.lucasramon.estacionamento.dominio.casos_de_uso.veiculo;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;

public class ExcluirVeiculoCasoDeUso implements InterfaceDeCasoDeUso<String, Void> {

    private final RepositorioDeVeiculos veiculoRepository;

    public ExcluirVeiculoCasoDeUso(RepositorioDeVeiculos veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @Override
    public Void executar(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID do veículo é obrigatório.");
        }

        if (!veiculoRepository.veiculoExiste(id)) {
            throw new IllegalArgumentException("Veículo com ID " + id + " não encontrado.");
        }

        veiculoRepository.remover(id);
        return null;
    }
    
}
