package com.lucasramon.estacionamento.dominio.modelos_de_dados;

import java.util.List;

import com.lucasramon.estacionamento.dominio.entidades.acesso.Acesso;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConsultaPainel {
    List<Acesso> ultimosAcessos;
    int vagasDisponiveis;
    int vagasOcupadas;
    int totalDeVagas;
    int veiculosAtivos;
    int quantidadeDeMensalistas;
    int numeroDeEntradasHoje;
    int numeroDeSaidasHoje;
}
