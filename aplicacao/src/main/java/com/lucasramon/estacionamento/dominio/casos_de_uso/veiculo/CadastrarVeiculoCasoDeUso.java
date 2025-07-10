package com.lucasramon.estacionamento.dominio.casos_de_uso.veiculo;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.veiculo.Veiculo;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;

public class CadastrarVeiculoCasoDeUso implements InterfaceDeCasoDeUso<Veiculo, Void> {

    private final RepositorioDeVeiculos veiculoRepository;

    public CadastrarVeiculoCasoDeUso(RepositorioDeVeiculos veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    protected boolean validaVeiculo(Veiculo veiculo) {
        if (veiculo == null) {
            throw new IllegalArgumentException("Veículo não pode ser nulo.");
        }
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("Placa do veículo não pode ser nula ou vazia.");
        }
        if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty()) {
            throw new IllegalArgumentException("Modelo do veículo não pode ser nulo ou vazio.");
        }
        if (veiculo.getCor() == null || veiculo.getCor().isEmpty()) {
            throw new IllegalArgumentException("Cor do veículo não pode ser nula ou vazia.");
        }
        return true;
    }

    protected boolean validaExistenciaVeiculo(Veiculo veiculo) {
        if (veiculoRepository.veiculoExiste(veiculo.getPlaca())) {
            throw new IllegalArgumentException("Veículo já cadastrado.");
        }
        return true;
    }

    public Void executar(Veiculo veiculo) {
        if (validaVeiculo(veiculo) && validaExistenciaVeiculo(veiculo)) {
            veiculoRepository.criar(veiculo);
        }
        return null;
    }

}
