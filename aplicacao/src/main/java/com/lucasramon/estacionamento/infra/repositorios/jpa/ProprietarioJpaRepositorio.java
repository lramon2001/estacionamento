package com.lucasramon.estacionamento.infra.repositorios.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasramon.estacionamento.infra.esquemas.ProprietarioEsquema;

public interface ProprietarioJpaRepositorio extends JpaRepository<ProprietarioEsquema, String> {

    boolean existsByCpf(String cpf);

    ProprietarioEsquema findByCpf(String cpf);

    void deleteByCpf(String cpf);
    
}
