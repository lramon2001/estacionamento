package com.lucasramon.estacionamento.dominio.casos_de_uso.veiculo;

import com.lucasramon.estacionamento.dominio.entidades.veiculo.VeiculoMensalista;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeProprietarios;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;

public class CadastrarVeiculoMensalistaCasoDeUso extends CadastrarVeiculoCasoDeUso {

    private final RepositorioDeVeiculos veiculoRepository;

    private final RepositorioDeProprietarios proprietarioRepository;

    public CadastrarVeiculoMensalistaCasoDeUso(RepositorioDeVeiculos veiculoRepository,
            RepositorioDeProprietarios proprietarioRepository) {
        super(veiculoRepository);
        this.veiculoRepository = veiculoRepository;
        this.proprietarioRepository = proprietarioRepository;
    }

    private boolean validaExistenciaProprietario(VeiculoMensalista veiculo) {
        if (!proprietarioRepository.proprietarioExiste(veiculo.getCpfProprietario())) {
            throw new IllegalArgumentException("Proprietário não cadastrado.");
        }
        return true;
    }

    public void executar(VeiculoMensalista veiculo) {
        if (this.validaVeiculo(veiculo) && this.validaExistenciaVeiculo(veiculo)
                && this.validaExistenciaProprietario(veiculo)) {
            veiculoRepository.criar(veiculo);
        }
    }

}
