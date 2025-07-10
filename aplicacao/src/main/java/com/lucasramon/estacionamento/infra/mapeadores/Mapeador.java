package com.lucasramon.estacionamento.infra.mapeadores;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public interface Mapeador<E,S> {

    E paraEntidade(S schema);

    S paraEsquema(E entity);

    default List<S> paraEsquemas(List<E> entities) {
        return entities.stream()
                .map(this::paraEsquema)
                .collect(Collectors.toList());
    }

    default List<E> paraEntidades(List<S> schemas) {
        return schemas.stream()
                .map(this::paraEntidade)
                .collect(Collectors.toList());
    }

    default Page<S> paraEsquema(Page<E> entidades) {
        List<S> conteudo = entidades.stream()
                .map(this::paraEsquema)
                .collect(Collectors.toList());
        return new PageImpl<>(conteudo, entidades.getPageable(), entidades.getTotalElements());
    }

    default Page<E> paraEntidade(Page<S> esquemas) {
        List<E> conteudo = esquemas.stream()
                .map(this::paraEntidade)
                .collect(Collectors.toList());
        return new PageImpl<>(conteudo, esquemas.getPageable(), esquemas.getTotalElements());
    }

    default List<S> paraEsquemas(Page<E> entidades) {
        return paraEsquemas(entidades.getContent());
    }

    default List<E> paraEntidades(Page<S> esquemas) {
        return paraEntidades(esquemas.getContent());
    }
    
}
