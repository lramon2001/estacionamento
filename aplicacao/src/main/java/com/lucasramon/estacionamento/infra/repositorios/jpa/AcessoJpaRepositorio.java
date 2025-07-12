package com.lucasramon.estacionamento.infra.repositorios.jpa;

import com.lucasramon.estacionamento.dominio.entidades.acesso.TipoAcesso;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasramon.estacionamento.infra.esquemas.AcessoEsquema;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AcessoJpaRepositorio extends JpaRepository<AcessoEsquema, Long> {

    @Query("SELECT a FROM AcessoEsquema a ORDER BY a.dataHora DESC")
    List<AcessoEsquema> obtemUltimosAcessos(Pageable pageable);

    @Query("SELECT COUNT(a) FROM AcessoEsquema a WHERE FUNCTION('DATE', a.dataHora) = :data AND a.tipoAcesso = :tipoAcesso")
    int contaPorDiaETipoAcesso(@Param("data") LocalDate data,
                               @Param("tipoAcesso") TipoAcesso tipoAcesso);

   
    @Query("SELECT COUNT(a) FROM AcessoEsquema a WHERE a.tipoAcesso = :tipoAcesso")
    int contaPorTipoAcesso(@Param("tipoAcesso") TipoAcesso tipoAcesso);

}
