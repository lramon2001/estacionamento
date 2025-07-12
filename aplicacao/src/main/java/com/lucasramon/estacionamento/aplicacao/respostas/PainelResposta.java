package com.lucasramon.estacionamento.aplicacao.respostas;

import java.util.List;

import com.lucasramon.estacionamento.dominio.entidades.acesso.Acesso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PainelResposta extends Resposta<PainelResposta>{
    private List<Acesso> ultimosAcessos;
    private int vagasDisponiveis;
    private int vagasOcupadas;
    private int totalDeVagas;
    private int veiculosAtivos;
    private int quantidadeDeMensalistas;
    private int numeroDeEntradasHoje;
    private int numeroDeSaidasHoje;
}
