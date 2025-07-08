package com.lucasramon.estacionamento.dominio.casos_de_uso.veiculo;


import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.veiculo.Veiculo;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;

public class BuscarVeiculoPorIDCasoDeUso implements InterfaceDeCasoDeUso<String, Veiculo> {

    private final RepositorioDeVeiculos veiculoRepository;

    public BuscarVeiculoPorIDCasoDeUso(RepositorioDeVeiculos veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @Override
    public Veiculo executar(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID do veículo inválido.");
        }
        return veiculoRepository.buscarPorId(id);
    }
    
}
