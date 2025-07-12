package com.lucasramon.estacionamento.infra.repositorios.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasramon.estacionamento.infra.esquemas.FaturamentoEsquema;

public interface FaturamentoJpaRepositorio extends JpaRepository<FaturamentoEsquema,Integer>{
    
}
