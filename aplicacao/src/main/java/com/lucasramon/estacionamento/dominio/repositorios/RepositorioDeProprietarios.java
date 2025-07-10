package com.lucasramon.estacionamento.dominio.repositorios;


import com.lucasramon.estacionamento.dominio.entidades.proprietario.Proprietario;

public interface RepositorioDeProprietarios extends InterfaceDeRepositorio<Proprietario, String> {

    boolean proprietarioExiste(String cpf);

}
