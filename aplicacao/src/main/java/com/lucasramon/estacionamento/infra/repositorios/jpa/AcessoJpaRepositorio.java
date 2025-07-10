package com.lucasramon.estacionamento.infra.repositorios.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasramon.estacionamento.infra.esquemas.AcessoEsquema;

public interface AcessoJpaRepositorio extends JpaRepository<AcessoEsquema, Long> {
    
    
}
