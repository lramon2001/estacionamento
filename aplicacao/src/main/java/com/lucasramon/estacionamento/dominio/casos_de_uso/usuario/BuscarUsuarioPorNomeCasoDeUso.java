package com.lucasramon.estacionamento.dominio.casos_de_uso.usuario;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.usuario.Usuario;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeUsuarios;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarUsuarioPorNomeCasoDeUso implements InterfaceDeCasoDeUso<String, Usuario> {

    private final RepositorioDeUsuarios repositorioDeUsuarios;

    
    @Override
    public Usuario executar(String nomeUsuario) throws Exception {
        if (nomeUsuario == null || nomeUsuario.isEmpty()) {
            throw new IllegalArgumentException("Nome de usuário inválido.");
        }

        Usuario usuario = repositorioDeUsuarios.buscarPorNomeDeUsuario(nomeUsuario);
        if (usuario == null) {
            throw new Exception("Usuário não encontrado.");
        }

        return usuario;
    }
    
}
