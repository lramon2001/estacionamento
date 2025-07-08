package com.lucasramon.estacionamento.dominio.casos_de_uso.proprietario;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeProprietarios;

public class RemoverProprietarioCasoDeUso implements InterfaceDeCasoDeUso<String, Void> {
    private final RepositorioDeProprietarios proprietarioRepository;

    public RemoverProprietarioCasoDeUso(RepositorioDeProprietarios proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    public Void executar(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF do proprietário é obrigatório.");
        }

        if (!proprietarioRepository.proprietarioExiste(cpf)) {
            throw new IllegalArgumentException("Proprietário com CPF " + cpf + " não encontrado.");
        }

        proprietarioRepository.remover(cpf);
        return null;
    }
}
