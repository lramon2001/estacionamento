package com.lucasramon.estacionamento.dominio.casos_de_uso.usuario;

import org.springframework.data.domain.Page;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.usuario.Usuario;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeUsuarios;
import com.lucasramon.estacionamento.dominio.util.Paginacao;

public class ListarUsuariosCasoDeUso implements InterfaceDeCasoDeUso<Paginacao, Page<Usuario>> {

    private final RepositorioDeUsuarios repositorioDeUsuarios;

    public ListarUsuariosCasoDeUso(RepositorioDeUsuarios repositorioDeUsuarios) {
        this.repositorioDeUsuarios = repositorioDeUsuarios;
    }

    @Override
    public Page<Usuario> executar(Paginacao input) throws Exception {
        if (input.getPagina() < 0 || input.getTamanho() <= 0) {
            throw new IllegalArgumentException("Página e tamanho devem ser positivos.");
        }
        
        return repositorioDeUsuarios.listar(input.getPagina(), input.getTamanho());
    }
    

   
    
}
