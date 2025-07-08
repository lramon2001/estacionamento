package com.lucasramon.estacionamento.dominio.casos_de_uso.usuario;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeUsuarios;

public class ExcluirUsuarioCasoDeUso implements InterfaceDeCasoDeUso<Long, Void> {

    private final RepositorioDeUsuarios repositorioDeUsuarios;

    public ExcluirUsuarioCasoDeUso(RepositorioDeUsuarios repositorioDeUsuarios) {
        this.repositorioDeUsuarios = repositorioDeUsuarios;
    }

    @Override
    public Void executar(Long idUsuario) throws Exception {
        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }
        
        repositorioDeUsuarios.remover(idUsuario);
        return null;
    }
    
}
