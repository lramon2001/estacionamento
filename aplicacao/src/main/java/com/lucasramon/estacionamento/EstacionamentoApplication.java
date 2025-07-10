package com.lucasramon.estacionamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucasramon.estacionamento.aplicacao.servicos.ServicoDeUsuario;

@SpringBootApplication
public class EstacionamentoApplication implements CommandLineRunner{

	@Autowired
	private ServicoDeUsuario servicoDeUsuario;

	public static void main(String[] args) {
		SpringApplication.run(EstacionamentoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		servicoDeUsuario.cadastrarUsuarioRaiz();
	}

}
