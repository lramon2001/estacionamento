package com.lucasramon.estacionamento.dominio.casos_de_uso.acesso;

import java.time.LocalTime;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.acesso.Acesso;
import com.lucasramon.estacionamento.dominio.execoes.EstacionamentoFechado;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeAcessos;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;
import com.lucasramon.estacionamento.dominio.util.ConstantesDoDominio;

public class RegistrarAcessoCasoDeUso implements InterfaceDeCasoDeUso<Acesso, Void> {

    private final RepositorioDeAcessos acessoRepository;
    private final RepositorioDeVeiculos veiculoRepository;

    public RegistrarAcessoCasoDeUso(RepositorioDeAcessos acessoRepository, RepositorioDeVeiculos veiculoRepository) {
        this.acessoRepository = acessoRepository;
        this.veiculoRepository = veiculoRepository;
    }

    boolean validarEntrada(Acesso acesso) throws EstacionamentoFechado {
        if (acesso.getPlacaVeiculo() == null || acesso.getPlacaVeiculo().isEmpty()) {
            throw new IllegalArgumentException("Placa não pode ser nula ou vazia.");
        }

        LocalTime horaAcesso = acesso.getDataHora().toLocalTime();
        if (horaAcesso.isBefore(ConstantesDoDominio.HORA_ABERTURA_ESTACIONAMENTO)
                || horaAcesso.isAfter(ConstantesDoDominio.HORA_FECHAMENTO_ESTACIONAMENTO)) {
            throw new EstacionamentoFechado();
        }
        return veiculoRepository.veiculoExiste(acesso.getPlacaVeiculo());
    }

    @Override
    public Void executar(Acesso acesso) {
        try {
            validarEntrada(acesso);
        } catch (EstacionamentoFechado e) {
        }
        acessoRepository.criar(acesso);
        return null;
    }

}
