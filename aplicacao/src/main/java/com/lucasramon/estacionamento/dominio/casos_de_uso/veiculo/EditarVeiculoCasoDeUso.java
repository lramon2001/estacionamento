package com.lucasramon.estacionamento.dominio.casos_de_uso.veiculo;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.veiculo.Veiculo;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;

public class EditarVeiculoCasoDeUso implements InterfaceDeCasoDeUso<Veiculo, Void> {

    private final RepositorioDeVeiculos veiculoRepository;

    public EditarVeiculoCasoDeUso(RepositorioDeVeiculos veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    private void validarDados(Veiculo veiculo) {
        if (veiculo == null) {
            throw new IllegalArgumentException("Veículo não pode ser nulo.");
        }
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("Placa do veículo é obrigatória.");
        }
        if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty()) {
            throw new IllegalArgumentException("Modelo do veículo é obrigatório.");
        }
        if (veiculo.getCor() == null || veiculo.getCor().isEmpty()) {
            throw new IllegalArgumentException("Cor do veículo é obrigatória.");
        }
    }

    public Void executar(Veiculo veiculo) {
        validarDados(veiculo);

        if (!veiculoRepository.veiculoExiste(veiculo.getPlaca())) {
            throw new IllegalArgumentException("Veículo com placa " + veiculo.getPlaca() + " não encontrado.");
        }

        veiculoRepository.atualizar(veiculo.getPlaca(), veiculo);

        return null;
    }

}
