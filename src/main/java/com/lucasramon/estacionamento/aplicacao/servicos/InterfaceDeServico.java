package com.lucasramon.estacionamento.aplicacao.servicos;

import org.springframework.data.domain.Page;

public interface InterfaceDeServico<ID,I,O> {

    void cadastrar(I input);

    void editar(I input);

    void excluir(ID id);

    O buscarPorId(ID id);

    Page<O> listar(int pagina, int tamanho);
    
}
