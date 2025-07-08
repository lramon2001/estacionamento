package com.lucasramon.estacionamento.dominio.casos_de_uso.acesso;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.acesso.Acesso;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeAcessos;

public class BuscarAcessoPorIDCasoDeUso implements InterfaceDeCasoDeUso<Long, Acesso> {

    private final RepositorioDeAcessos acessoRepository;

    public BuscarAcessoPorIDCasoDeUso(RepositorioDeAcessos acessoRepository) {
        this.acessoRepository = acessoRepository;
    }

    @Override
    public Acesso executar(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do acesso inválido.");
        }
        return acessoRepository.buscarPorId(id);
    }
    
}
