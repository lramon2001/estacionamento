package com.lucasramon.estacionamento.infra.repositorios;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.lucasramon.estacionamento.dominio.entidades.veiculo.Veiculo;
import com.lucasramon.estacionamento.dominio.entidades.veiculo.VeiculoMensalista;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;
import com.lucasramon.estacionamento.infra.esquemas.VeiculoEsquema;
import com.lucasramon.estacionamento.infra.repositorios.jpa.VeiculoJpaRepository;

@Repository  
public class RepositorioDeVeiculosImpl implements RepositorioDeVeiculos {

     private final VeiculoJpaRepository jpaRepository;
    private final ModelMapper modelMapper;

    public RepositorioDeVeiculosImpl(VeiculoJpaRepository jpaRepository, ModelMapper modelMapper) {
        this.jpaRepository = jpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Veiculo buscarPorId(String placa) {
        VeiculoEsquema entity = jpaRepository.findById(placa)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado com placa: " + placa));
        return modelMapper.map(entity, Veiculo.class);
    }

    @Override
    public void criar(Veiculo veiculo) {
         VeiculoEsquema entity = VeiculoEsquema.builder()
        .placa(veiculo.getPlaca())
        .modelo(veiculo.getModelo())
        .cor(veiculo.getCor())
        .cpfProprietario(
            veiculo instanceof VeiculoMensalista
                ? ((VeiculoMensalista) veiculo).getCpfProprietario()
                : null)
        .build();

        this.jpaRepository.save(entity); 
    }

    @Override
    public void atualizar(String placa, Veiculo veiculo) {
        if (!jpaRepository.existsById(placa)) {
            throw new IllegalArgumentException("Veículo com placa " + placa + " não encontrado.");
        }
        jpaRepository.save(modelMapper.map(veiculo, VeiculoEsquema.class));
    }

    @Override
    public void remover(String placa) {
        jpaRepository.deleteById(placa);
    }

    @Override
    public boolean veiculoExiste(String placa) {
        return jpaRepository.existsByPlaca(placa);
    }

    @Override
    public Page<Veiculo> listar(int page, int size) {
        Page<VeiculoEsquema> veiculoPage = jpaRepository.findAll(org.springframework.data.domain.PageRequest.of(page, size));
        return veiculoPage.map(veiculoSchema -> modelMapper.map(veiculoSchema, Veiculo.class));
    }



   
   
    
}
