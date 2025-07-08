package com.lucasramon.estacionamento.dominio.casos_de_uso.proprietario;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.proprietario.Proprietario;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeProprietarios;

public class CadastrarProprietarioCasoDeUso implements InterfaceDeCasoDeUso<Proprietario, Void> {

    private final RepositorioDeProprietarios proprietarioRepository;

    public CadastrarProprietarioCasoDeUso(RepositorioDeProprietarios proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    private boolean validaProprietario(Proprietario proprietario) {
        if (proprietario == null) {
            throw new IllegalArgumentException("Proprietário não pode ser nulo");
        }
        if (proprietario.getCpf() == null || proprietario.getCpf().isEmpty()) {
            throw new IllegalArgumentException("CPF do proprietário é obrigatório");
        }
        if (proprietarioRepository.proprietarioExiste(proprietario.getCpf())) {
            throw new IllegalArgumentException("Proprietário já cadastrado com o CPF: " + proprietario.getCpf());
        }
        if (proprietario.getNome() == null || proprietario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do proprietário é obrigatório");
        }
        if (proprietario.getTelefone() == null || proprietario.getTelefone().isEmpty()) {
            throw new IllegalArgumentException("Telefone do proprietário é obrigatório");
        }
        if (proprietario.getEmail() == null || proprietario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email do proprietário é obrigatório");
        }
        return true;
    }

    public Void executar(Proprietario proprietario) {
        if (validaProprietario(proprietario)) {
            proprietarioRepository.criar(proprietario);
        }

        return null;

    }

}
