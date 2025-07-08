package com.lucasramon.estacionamento.dominio.casos_de_uso.proprietario;

import org.springframework.data.domain.Page;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.proprietario.Proprietario;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeProprietarios;
import com.lucasramon.estacionamento.dominio.util.Paginacao;

public class ListarProprietariosCasoDeUso implements InterfaceDeCasoDeUso<Paginacao, Page<Proprietario>> {

    private final RepositorioDeProprietarios proprietarioRepository;

    public ListarProprietariosCasoDeUso(RepositorioDeProprietarios proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    public Page<Proprietario> executar(Paginacao paginacao) throws Exception {
        if (paginacao.getPagina() < 0 || paginacao.getTamanho() <= 0) {
            throw new IllegalArgumentException("Página e tamanho devem ser positivos.");
        }
        return proprietarioRepository.listar(paginacao.getPagina(), paginacao.getTamanho());
    }
}
