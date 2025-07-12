package com.lucasramon.estacionamento.aplicacao.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasramon.estacionamento.aplicacao.requisicoes.AtualizacaoTokenRequisicao;
import com.lucasramon.estacionamento.aplicacao.requisicoes.LoginRequisicao;
import com.lucasramon.estacionamento.aplicacao.requisicoes.UsuarioRequisicao;
import com.lucasramon.estacionamento.aplicacao.respostas.TokenResposta;
import com.lucasramon.estacionamento.aplicacao.servicos.ServicoDeLogin;
import com.lucasramon.estacionamento.aplicacao.servicos.ServicoDeUsuario;
import com.lucasramon.estacionamento.aplicacao.util.ConstantesDaAplicacao;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ConstantesDaAplicacao.ROTA_AUTENTICACAO)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class ControladorDeAutenticacao {
    
    @Autowired
    private ServicoDeUsuario servicoDeUsuario;

    @Autowired
    private ServicoDeLogin servicoDeLogin;


    @PostMapping(value = "/registrar",consumes = ConstantesDaAplicacao.CONTEUDO_JSON, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<Void> registrar(@RequestBody @Valid UsuarioRequisicao dto) {
        System.out.println(dto.toString());
        servicoDeUsuario.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/entrar",consumes = ConstantesDaAplicacao.CONTEUDO_JSON, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<TokenResposta> entrar(@RequestBody @Valid LoginRequisicao loginRequisicao) {
        
        return ResponseEntity.ok(this.servicoDeLogin.entrar(loginRequisicao));
    }

     @PostMapping(value = "/gerar-novo-token",consumes = ConstantesDaAplicacao.CONTEUDO_JSON, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<TokenResposta> renovarToken(@RequestBody @Valid AtualizacaoTokenRequisicao loginRequisicao) {
        
        return ResponseEntity.ok(this.servicoDeLogin.gerarNovoToken(loginRequisicao));
    }
    
}
