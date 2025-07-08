package com.lucasramon.estacionamento.infra.repositorios.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasramon.estacionamento.infra.esquemas.VeiculoEsquema;

public interface VeiculoJpaRepository extends JpaRepository<VeiculoEsquema, String> {
	boolean existsByPlaca(String placa);
}
