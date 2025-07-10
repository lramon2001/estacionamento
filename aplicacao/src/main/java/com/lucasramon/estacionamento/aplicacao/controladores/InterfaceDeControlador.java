package com.lucasramon.estacionamento.aplicacao.controladores;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface InterfaceDeControlador<ID,I,O> {

    ResponseEntity<Void> cadastrar(I entity);

    ResponseEntity<O> editar(ID id, I entity);

    ResponseEntity<Void> excluir(ID id);

    ResponseEntity<O> buscarPorId(ID id);

    ResponseEntity<Page<O>> listar(int pagina, int tamanho);
    
}
