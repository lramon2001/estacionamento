package com.lucasramon.estacionamento.dominio.repositorios;

import com.lucasramon.estacionamento.dominio.entidades.usuario.Usuario;

public interface RepositorioDeUsuarios extends InterfaceDeRepositorio<Usuario, Long> {

    boolean usuarioExiste(String nomeDeUsuario);

    Usuario buscarPorNomeDeUsuario(String nomeDeUsuario);

    void atualizarSenha(Long id, String novaSenha);

    boolean tabelaDeUsuariosEstaVazia();
    
}
