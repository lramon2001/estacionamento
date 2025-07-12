package com.lucasramon.estacionamento.aplicacao.controladores;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasramon.estacionamento.aplicacao.util.ConstantesDaAplicacao;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class ControladorRaiz {

    @GetMapping("/")
    public String raiz() {
        return ConstantesDaAplicacao.DESCRICAO_API;
    }

}
