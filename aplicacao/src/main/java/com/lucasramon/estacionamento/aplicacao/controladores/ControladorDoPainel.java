package com.lucasramon.estacionamento.aplicacao.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasramon.estacionamento.aplicacao.conversores.ConversorDoPainel;
import com.lucasramon.estacionamento.aplicacao.respostas.PainelResposta;
import com.lucasramon.estacionamento.aplicacao.servicos.ServicoDePainel;
import com.lucasramon.estacionamento.aplicacao.util.ConstantesDaAplicacao;

@RestController
@RequestMapping(ConstantesDaAplicacao.ROTA_PAINEL)
public class ControladorDoPainel {

    @Autowired
    private ServicoDePainel servicoDePainel;

    @Autowired
    private ConversorDoPainel conversorDoPainel; 


    @GetMapping(value="",produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    ResponseEntity<PainelResposta> buscaDadosPainel(){
        return ResponseEntity.ok(conversorDoPainel.paraOutput(servicoDePainel.consultarPainel()));
    }
    
}
