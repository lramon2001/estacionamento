package com.lucasramon.estacionamento.aplicacao.conversores;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public interface Conversor <I, O, E> {

    E paraEntidade(I input);

    O paraOutput(E entity);

    default List<O> paraOutputs(List<I> inputs) {
        return inputs.stream()
                .map(this::paraOutputFromInput)
                .collect(Collectors.toList());
    }

    default List<O> paraOutputsFromEntities(List<E> entities) {
        return entities.stream()
                .map(this::paraOutput)
                .collect(Collectors.toList());
    }

    default Page<O> paraOutputs(Page<I> inputs) {
        List<O> conteudo = inputs.stream()
                .map(this::paraOutputFromInput)
                .collect(Collectors.toList());
        return new PageImpl<>(conteudo, inputs.getPageable(), inputs.getTotalElements());
    }

    default Page<O> paraOutputsFromEntities(Page<E> entities) {
        List<O> conteudo = entities.stream()
                .map(this::paraOutput)
                .collect(Collectors.toList());
        return new PageImpl<>(conteudo, entities.getPageable(), entities.getTotalElements());
    }

    default Page<E> paraEntidades(Page<O> outputs) {
        List<E> conteudo = outputs.stream()
                .map(this::paraEntidadeFromOutput)
                .collect(Collectors.toList());
        return new PageImpl<>(conteudo, outputs.getPageable(), outputs.getTotalElements());
    }

    default List<E> paraEntidadesFromOutputs(List<O> outputs) {
        return outputs.stream()
                .map(this::paraEntidadeFromOutput)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    default E paraEntidadeFromOutput(O output) {
        return paraEntidade((I) output); 
    }

    default O paraOutputFromInput(I input) {
        return paraOutput(paraEntidade(input));
    }
}
