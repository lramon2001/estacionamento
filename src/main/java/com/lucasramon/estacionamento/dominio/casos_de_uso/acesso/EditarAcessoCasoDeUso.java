package com.lucasramon.estacionamento.dominio.casos_de_uso.acesso;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.acesso.Acesso;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeAcessos;

public class EditarAcessoCasoDeUso implements InterfaceDeCasoDeUso<Acesso, Void> {

    private final RepositorioDeAcessos acessoRepository;

    public EditarAcessoCasoDeUso(RepositorioDeAcessos acessoRepository) {
        this.acessoRepository = acessoRepository;
    }

    @Override
    public Void executar(Acesso acesso) throws Exception {
        if (acesso == null || acesso.getId() == null || acesso.getId() <= 0) {
            throw new IllegalArgumentException("Acesso inválido.");
        }
        acessoRepository.atualizar(acesso.getId(),acesso);
        return null;
    }
    
}
