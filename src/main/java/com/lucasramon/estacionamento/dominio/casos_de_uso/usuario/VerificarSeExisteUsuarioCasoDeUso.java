package com.lucasramon.estacionamento.dominio.casos_de_uso.usuario;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeUsuarios;

public class VerificarSeExisteUsuarioCasoDeUso implements InterfaceDeCasoDeUso<Void,Boolean>{

    private final RepositorioDeUsuarios repositorioDeUsuarios;

    public VerificarSeExisteUsuarioCasoDeUso(RepositorioDeUsuarios repositorioDeUsuarios) {
        this.repositorioDeUsuarios = repositorioDeUsuarios;
    }

    @Override
    public Boolean executar(Void input) throws Exception {
        return this.repositorioDeUsuarios.tabelaDeUsuariosEstaVazia();
    }
    
}
