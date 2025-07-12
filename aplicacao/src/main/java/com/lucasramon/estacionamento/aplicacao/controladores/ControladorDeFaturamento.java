package com.lucasramon.estacionamento.aplicacao.controladores;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasramon.estacionamento.aplicacao.requisicoes.GerarPdfFaturamentoRequisicao;
import com.lucasramon.estacionamento.aplicacao.servicos.ServicoDeFaturamento;
import com.lucasramon.estacionamento.aplicacao.util.ConstantesDaAplicacao;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;



@RestController
@RequestMapping(ConstantesDaAplicacao.ROTA_FATURAMENTO)
@PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('COMUM')")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class ControladorDeFaturamento {
    
    @Autowired
    private ServicoDeFaturamento servicoDeFaturamento;
   
    @PostMapping(ConstantesDaAplicacao.ROTA_GERAR_PDF)
    public ResponseEntity<byte[]> gerarPdf(@RequestBody @Valid GerarPdfFaturamentoRequisicao gerarPdfFaturamentoRequisicao) {

        byte[] pdfContent = servicoDeFaturamento.gerarPdf(gerarPdfFaturamentoRequisicao.getInicio(), gerarPdfFaturamentoRequisicao.getFim());

        HttpHeaders headers = new HttpHeaders();
        headers.add(ConstantesDaAplicacao.DISPOSICAO_DO_CONTEUDO, ConstantesDaAplicacao.ANEXO_NOME_ARQUIVO);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdfContent);
    }
}
