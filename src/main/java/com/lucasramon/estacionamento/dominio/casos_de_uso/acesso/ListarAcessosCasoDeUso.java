package com.lucasramon.estacionamento.dominio.casos_de_uso.acesso;

import org.springframework.data.domain.Page;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.acesso.Acesso;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeAcessos;
import com.lucasramon.estacionamento.dominio.util.Paginacao;

public class ListarAcessosCasoDeUso implements InterfaceDeCasoDeUso<Paginacao, Page<Acesso>> {

    private final RepositorioDeAcessos acessoRepository;

    public ListarAcessosCasoDeUso(RepositorioDeAcessos acessoRepository) {
        this.acessoRepository = acessoRepository;
    }

    @Override
    public Page<Acesso> executar(Paginacao paginacao) throws Exception {
         if (paginacao.getPagina() < 0 || paginacao.getTamanho() <= 0) {
            throw new IllegalArgumentException("Página e tamanho devem ser positivos.");
        }
        return acessoRepository.listar(paginacao.getPagina(), paginacao.getTamanho());
    }
}
