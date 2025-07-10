package com.lucasramon.estacionamento.aplicacao.respostas;

import org.springframework.hateoas.RepresentationModel;

public abstract class Resposta<T extends RepresentationModel<? extends T>> extends RepresentationModel<T> {
	
}
