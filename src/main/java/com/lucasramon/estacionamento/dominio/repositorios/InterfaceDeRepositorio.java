package com.lucasramon.estacionamento.dominio.repositorios;

import org.springframework.data.domain.Page;

public interface InterfaceDeRepositorio<T,ID> {
    
    void criar(T entidade);
    void atualizar(ID id, T entidade);
    void remover(ID id);
    T buscarPorId(ID id);
    Page<T> listar(int page, int size);
    
}
