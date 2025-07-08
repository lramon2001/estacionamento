package com.lucasramon.estacionamento.aplicacao.requisicoes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequisicao {
    
    String nome;
    String senha;

}
