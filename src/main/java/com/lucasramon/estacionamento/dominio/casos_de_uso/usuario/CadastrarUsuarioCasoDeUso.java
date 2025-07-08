package com.lucasramon.estacionamento.dominio.casos_de_uso.usuario;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.usuario.Usuario;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeUsuarios;

public class CadastrarUsuarioCasoDeUso implements InterfaceDeCasoDeUso<Usuario,Void>{

    private final RepositorioDeUsuarios repositorioDeUsuarios;

    public CadastrarUsuarioCasoDeUso(RepositorioDeUsuarios repositorioDeUsuarios) {
        this.repositorioDeUsuarios = repositorioDeUsuarios;
    }
    @Override
    public Void executar(Usuario input) throws Exception { 
        System.out.println(input.toString());
        repositorioDeUsuarios.criar(input);
        return null;
    }
    
}
