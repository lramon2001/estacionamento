package com.lucasramon.estacionamento.infra.repositorios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.lucasramon.estacionamento.dominio.entidades.usuario.Usuario;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeUsuarios;
import com.lucasramon.estacionamento.infra.mapeadores.MapeadorDeUsuario;
import com.lucasramon.estacionamento.infra.repositorios.jpa.UsuarioJpaRepositorio;

@Repository
public class RepositorioDeUsuariosImpl implements RepositorioDeUsuarios  {


    private final UsuarioJpaRepositorio jpaRepository;

    @Autowired
    private MapeadorDeUsuario mapeador;

    public RepositorioDeUsuariosImpl(UsuarioJpaRepositorio jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void criar(Usuario entidade) {
        
        if (entidade == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }
        if (entidade.getNomeDeUsuario() == null || entidade.getNomeDeUsuario().isEmpty()) {
            throw new IllegalArgumentException("Nome de usuário não pode ser vazio.");
        }
        if (entidade.getSenha() == null || entidade.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser vazia.");
        }

        jpaRepository.save(mapeador.paraEsquema(entidade));
    }

    @Override
    public void atualizar(Long id, Usuario entidade) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }
        if (entidade == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }
        if (entidade.getNomeDeUsuario() == null || entidade.getNomeDeUsuario().isEmpty()) {
            throw new IllegalArgumentException("Nome de usuário não pode ser vazio.");
        }
        if (entidade.getSenha() == null || entidade.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser vazia.");
        }
        jpaRepository.save(mapeador.paraEsquema(entidade));
    }

    @Override
    public void remover(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }
        jpaRepository.deleteById(id);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }
        return mapeador.paraEntidade(jpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado.")));
    }

    @Override
    public Page<Usuario> listar(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Parâmetros de paginação inválidos.");
        }
        return jpaRepository.findAll(org.springframework.data.domain.PageRequest.of(page, size))
                .map(mapeador::paraEntidade);
    }

    @Override
    public boolean usuarioExiste(String nomeDeUsuario) {
        if (nomeDeUsuario == null || nomeDeUsuario.isEmpty()) {
            throw new IllegalArgumentException("Nome de usuário não pode ser vazio.");
        }
        return jpaRepository.existsByNomeDeUsuario(nomeDeUsuario);
    }

    @Override
    public Usuario buscarPorNomeDeUsuario(String nomeDeUsuario) {
        if (nomeDeUsuario == null || nomeDeUsuario.isEmpty()) {
            throw new IllegalArgumentException("Nome de usuário não pode ser vazio.");
        }
        return mapeador.paraEntidade(jpaRepository.findByNomeDeUsuario(nomeDeUsuario.trim())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com nome de usuário: " + nomeDeUsuario)));
    }

    @Override
    public void atualizarSenha(Long id, String novaSenha) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }
        if (novaSenha == null || novaSenha.isEmpty()) {
            throw new IllegalArgumentException("Nova senha não pode ser vazia.");
        }
        
        Usuario usuario = buscarPorId(id);
        usuario.setSenha(novaSenha);
        jpaRepository.save(mapeador.paraEsquema(usuario));
    }

    @Override
    public boolean tabelaDeUsuariosEstaVazia() {
        return !jpaRepository.existsByIdIsNotNull();
    }
    
}
