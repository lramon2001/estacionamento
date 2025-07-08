package com.lucasramon.estacionamento.infra.repositorios.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasramon.estacionamento.infra.esquemas.UsuarioEsquema;

public interface UsuarioJpaRepositorio extends JpaRepository<UsuarioEsquema, Long> {


    boolean existsByNomeDeUsuario(String username);
    Optional<UsuarioEsquema> findByNomeDeUsuario(String nomeDeUsuario);
    boolean existsByIdIsNotNull();  

    
}
