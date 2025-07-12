package com.lucasramon.estacionamento.infra.repositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.lucasramon.estacionamento.dominio.entidades.faturamento.Faturamento;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeFaturamentos;
import com.lucasramon.estacionamento.infra.esquemas.FaturamentoEsquema;
import com.lucasramon.estacionamento.infra.mapeadores.MapeadorDeFaturamento;
import com.lucasramon.estacionamento.infra.repositorios.jpa.FaturamentoJpaRepositorio;

@Repository
public class RepositorioDeFaturamentosImpl implements RepositorioDeFaturamentos {

     private final FaturamentoJpaRepositorio repositorio;
    private final MapeadorDeFaturamento mapeadorDeFaturamento;

    @Autowired
    public RepositorioDeFaturamentosImpl(FaturamentoJpaRepositorio repositorio, MapeadorDeFaturamento mapeadorDeFaturamento) {
        this.repositorio = repositorio;
        this.mapeadorDeFaturamento = mapeadorDeFaturamento;
    }

    @Override
    public void criar(Faturamento entidade) {
        if (entidade == null) {
            throw new IllegalArgumentException("Faturamento não pode ser nulo.");
        }
        if (entidade.getData() == null) {
            throw new IllegalArgumentException("Data de faturamento não pode ser nula.");
        }

        FaturamentoEsquema faturamentoEsquema = mapeadorDeFaturamento.paraEsquema(entidade);
        repositorio.save(faturamentoEsquema);
    }

    @Override
    public void atualizar(Integer id, Faturamento entidade) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do faturamento inválido.");
        }
        if (entidade == null) {
            throw new IllegalArgumentException("Faturamento não pode ser nulo.");
        }
        if (entidade.getData() == null) {
            throw new IllegalArgumentException("Data de faturamento não pode ser nula.");
        }

        FaturamentoEsquema faturamentoEsquema = mapeadorDeFaturamento.paraEsquema(entidade);
        faturamentoEsquema.setId(id);  
        repositorio.save(faturamentoEsquema);
    }

    @Override
    public void remover(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do faturamento inválido.");
        }
        repositorio.deleteById(id);
    }

    @Override
    public Faturamento buscarPorId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do faturamento inválido.");
        }
        FaturamentoEsquema faturamentoEsquema = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Faturamento não encontrado."));
        return mapeadorDeFaturamento.paraEntidade(faturamentoEsquema);
    }

    @Override
    public Page<Faturamento> listar(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Parâmetros de paginação inválidos.");
        }
        return repositorio.findAll(org.springframework.data.domain.PageRequest.of(page, size))
                .map(mapeadorDeFaturamento::paraEntidade);
    }

}
