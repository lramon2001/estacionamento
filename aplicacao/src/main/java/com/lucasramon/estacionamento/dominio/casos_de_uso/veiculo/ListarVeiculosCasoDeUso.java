package com.lucasramon.estacionamento.dominio.casos_de_uso.veiculo;

import org.springframework.data.domain.Page;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.veiculo.Veiculo;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;
import com.lucasramon.estacionamento.dominio.util.Paginacao;

public class ListarVeiculosCasoDeUso implements InterfaceDeCasoDeUso<Paginacao, Page<Veiculo>> {

    private final RepositorioDeVeiculos veiculoRepository;

    public ListarVeiculosCasoDeUso(RepositorioDeVeiculos veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Page<Veiculo> executar(Paginacao paginacao) {
        if (paginacao.getPagina() < 0 || paginacao.getTamanho() <= 0) {
            throw new IllegalArgumentException("Página e tamanho devem ser positivos.");
        }
        return veiculoRepository.listar(paginacao.getPagina(), paginacao.getTamanho() );
    }

}
