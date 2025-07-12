package com.lucasramon.estacionamento.infra.repositorios.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasramon.estacionamento.infra.esquemas.VeiculoEsquema;
import org.springframework.data.jpa.repository.Query;

public interface VeiculoJpaRepositorio extends JpaRepository<VeiculoEsquema, String> {
	boolean existsByPlaca(String placa);

	@Query("SELECT COUNT(v) FROM VeiculoEsquema v")
	int contaQuantidadeDeVeiculos();

	@Query("SELECT COUNT(v) FROM VeiculoEsquema v WHERE v.cpfProprietario IS NOT NULL")
	int contaVeiculosMensalistas();
}
