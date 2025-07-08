package com.lucasramon.estacionamento.dominio.casos_de_uso.acesso;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeAcessos;

public class RemoverAcessoCasoDeUso implements InterfaceDeCasoDeUso<Long, Void> {

    private final RepositorioDeAcessos acessoRepository;

    public RemoverAcessoCasoDeUso(RepositorioDeAcessos acessoRepository) {
        this.acessoRepository = acessoRepository;
    }

    public Void executar(Long idAcesso) {
        if (idAcesso == null || idAcesso <= 0) {
            throw new IllegalArgumentException("ID do acesso inválido.");
        }

        acessoRepository.remover(idAcesso);
        return null;
    }
}
