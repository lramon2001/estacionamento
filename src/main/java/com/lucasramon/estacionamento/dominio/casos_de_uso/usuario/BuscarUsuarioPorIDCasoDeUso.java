package com.lucasramon.estacionamento.dominio.casos_de_uso.usuario;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.usuario.Usuario;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeUsuarios;

public class BuscarUsuarioPorIDCasoDeUso implements InterfaceDeCasoDeUso<Long, Usuario> {

    private final RepositorioDeUsuarios repositorioDeUsuarios;

    public BuscarUsuarioPorIDCasoDeUso(RepositorioDeUsuarios repositorioDeUsuarios) {
        this.repositorioDeUsuarios = repositorioDeUsuarios;
    }

    @Override
    public Usuario executar(Long idUsuario) throws Exception {
        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }
        
        Usuario usuario = repositorioDeUsuarios.buscarPorId(idUsuario);
        if (usuario == null) {
            throw new Exception("Usuário não encontrado.");
        }
        
        return usuario;
    }
    
}
