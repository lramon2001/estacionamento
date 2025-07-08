package com.lucasramon.estacionamento.infra.repositorios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.lucasramon.estacionamento.dominio.entidades.acesso.Acesso;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeAcessos;
import com.lucasramon.estacionamento.infra.mapeadores.MapeadorDeAcesso;
import com.lucasramon.estacionamento.infra.repositorios.jpa.AcessoJpaRepositorio;

@Repository
public class RepositorioDeAcessosImpl implements RepositorioDeAcessos {

    private final AcessoJpaRepositorio repositorioJpa;

    @Autowired
    private MapeadorDeAcesso mapeadorDeAcesso;

    public RepositorioDeAcessosImpl(
            AcessoJpaRepositorio repositorioJpa) {
        this.repositorioJpa = repositorioJpa;
    }

    @Override
    public void criar(Acesso acesso) {
        repositorioJpa.save(mapeadorDeAcesso.paraEsquema(acesso));
    }

    @Override
    public void remover(Long idAcesso) {
        repositorioJpa.deleteById(idAcesso);
    }

    @Override
    public Page<Acesso> listar(int page, int size) {
        return this.mapeadorDeAcesso
                .paraEntidade(this.repositorioJpa.findAll(org.springframework.data.domain.PageRequest.of(page, size)));
    }

    @Override
    public void atualizar(Long id, Acesso acesso) {
       Acesso acessoExistente = buscarPorId(id);
        if (acessoExistente == null) {
            throw new IllegalArgumentException("Acesso não encontrado.");
        }
        acessoExistente.setDataHora(acesso.getDataHora());
        acessoExistente.setPlacaVeiculo(acesso.getPlacaVeiculo());
        acessoExistente.setTipoAcesso(acesso.getTipoAcesso());
        repositorioJpa.save(mapeadorDeAcesso.paraEsquema(acessoExistente));
    }

    @Override
    public Acesso buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do acesso inválido.");
        }
        return mapeadorDeAcesso.paraEntidade(repositorioJpa.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado.")));
    }

}
