package com.lucasramon.estacionamento.dominio.casos_de_uso.usuario;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.usuario.Usuario;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeUsuarios;

public class EditarUsuarioCasoDeUso implements InterfaceDeCasoDeUso<Usuario, Void> {

    private final RepositorioDeUsuarios repositorioDeUsuarios;

    public EditarUsuarioCasoDeUso(RepositorioDeUsuarios repositorioDeUsuarios) {
        this.repositorioDeUsuarios = repositorioDeUsuarios;
    }

    @Override
    public Void executar(Usuario input) throws Exception {
        if (input == null || input.getId() == null || input.getId() <= 0) {
            throw new IllegalArgumentException("Usuário inválido.");
        }
        
        repositorioDeUsuarios.atualizar(input.getId(), input);
        return null;
    }

   
    
}
