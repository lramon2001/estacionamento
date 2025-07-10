package com.lucasramon.estacionamento.dominio.casos_de_uso.proprietario;


import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.proprietario.Proprietario;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeProprietarios;

public class EditarProprietarioCasoDeUso implements InterfaceDeCasoDeUso<Proprietario, Void> {
    private final RepositorioDeProprietarios proprietarioRepository;

    public EditarProprietarioCasoDeUso(RepositorioDeProprietarios proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    private void validarDados(Proprietario proprietario) {
        if (proprietario == null) {
            throw new IllegalArgumentException("Proprietário não pode ser nulo");
        }
        if (proprietario.getCpf() == null || proprietario.getCpf().isEmpty()) {
            throw new IllegalArgumentException("CPF do proprietário é obrigatório para edição");
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
    }

    public Void executar(Proprietario proprietario) {
        validarDados(proprietario);

        if (!proprietarioRepository.proprietarioExiste(proprietario.getCpf())) {
            throw new IllegalArgumentException("Proprietário com CPF " + proprietario.getCpf() + " não encontrado.");
        }

        proprietarioRepository.atualizar(proprietario.getCpf(), proprietario);
        return null;
    }
}
