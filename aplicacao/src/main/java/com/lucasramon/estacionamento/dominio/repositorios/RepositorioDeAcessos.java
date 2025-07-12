package com.lucasramon.estacionamento.dominio.repositorios;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.lucasramon.estacionamento.dominio.entidades.acesso.Acesso;
import com.lucasramon.estacionamento.dominio.entidades.acesso.TipoAcesso;

public interface RepositorioDeAcessos extends InterfaceDeRepositorio<Acesso, Long> {

    List<Acesso> obtemUltimosAcessos(Pageable pageable);

    int contaPorTipoAcesso(TipoAcesso tipoAcesso);

    int contaPorTipoAcessoEdia(TipoAcesso tipoAcesso,LocalDate dia);

}
