package com.lucasramon.estacionamento.infra.repositorios;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.lucasramon.estacionamento.dominio.entidades.proprietario.Proprietario;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeProprietarios;
import com.lucasramon.estacionamento.infra.esquemas.ProprietarioEsquema;
import com.lucasramon.estacionamento.infra.repositorios.jpa.ProprietarioJpaRepositorio;

@Repository  
public class RepositorioDeProprietariosImpl implements RepositorioDeProprietarios{

    private final ProprietarioJpaRepositorio jpaRepository;
    private final ModelMapper modelMapper;

    public RepositorioDeProprietariosImpl(
            ProprietarioJpaRepositorio jpaRepository,
            ModelMapper modelMapper) {
        this.jpaRepository = jpaRepository;
        this.modelMapper  = modelMapper;
    }

    @Override
    public Proprietario buscarPorId(String cpf) {
        ProprietarioEsquema entity = jpaRepository.findById(cpf)
            .orElseThrow(() ->
                new IllegalArgumentException("Proprietário não encontrado com CPF: " + cpf));
        return modelMapper.map(entity, Proprietario.class);
    }

    @Override
    public void criar(Proprietario proprietario) {
        jpaRepository.save(modelMapper.map(proprietario, ProprietarioEsquema.class));
    }

    @Override
    public void atualizar(String cpf, Proprietario proprietario) {
        if (!jpaRepository.existsById(cpf)) {
            throw new IllegalArgumentException("Proprietário com CPF " + cpf + " não encontrado.");
        }
        jpaRepository.save(modelMapper.map(proprietario, ProprietarioEsquema.class));
    }

    @Override
    public void remover(String cpf) {
        jpaRepository.deleteById(cpf);
    }

    @Override
    public boolean proprietarioExiste(String cpf) {
        return jpaRepository.existsByCpf(cpf);
    }

    @Override
    public Page<Proprietario> listar(int page, int size) {
        Page<ProprietarioEsquema> proprietarioPage = jpaRepository.findAll(org.springframework.data.domain.PageRequest.of(page, size));
        return proprietarioPage.map(proprietarioSchema -> modelMapper.map(proprietarioSchema, Proprietario.class));
    }

    
}
