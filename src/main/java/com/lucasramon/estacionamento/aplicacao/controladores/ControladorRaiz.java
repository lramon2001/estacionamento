package com.lucasramon.estacionamento.aplicacao.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasramon.estacionamento.aplicacao.util.ConstantesDaAplicacao;

@RestController
public class ControladorRaiz {

    @GetMapping("/")
    public String raiz() {
        return ConstantesDaAplicacao.DESCRICAO_API;
    }

}
