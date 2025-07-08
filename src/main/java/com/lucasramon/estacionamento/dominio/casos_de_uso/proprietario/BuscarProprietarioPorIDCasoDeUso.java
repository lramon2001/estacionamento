package com.lucasramon.estacionamento.dominio.casos_de_uso.proprietario;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.proprietario.Proprietario;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeProprietarios;

public class BuscarProprietarioPorIDCasoDeUso implements InterfaceDeCasoDeUso<String, Proprietario> {

    private final RepositorioDeProprietarios proprietarioRepository;

    public BuscarProprietarioPorIDCasoDeUso(RepositorioDeProprietarios proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    @Override
    public Proprietario executar(String cpf) throws Exception {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF do proprietário inválido.");
        }
        return proprietarioRepository.buscarPorId(cpf);
    }
    
}
