package com.lucasramon.estacionamento.aplicacao.controladores;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucasramon.estacionamento.aplicacao.requisicoes.AcessoRequisicao;
import com.lucasramon.estacionamento.aplicacao.respostas.AcessoResposta;
import com.lucasramon.estacionamento.aplicacao.servicos.SevicoDeAcessos;
import com.lucasramon.estacionamento.aplicacao.util.ConstantesDaAplicacao;

@RestController
@RequestMapping(ConstantesDaAplicacao.ROTA_ACESSOS)
@PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('COMUM')")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class ControladorDeAcessos implements InterfaceDeControlador<Long, AcessoRequisicao, AcessoResposta> {

    @Autowired
    private SevicoDeAcessos servicoDeAcessos;

    @PostMapping(consumes = ConstantesDaAplicacao.CONTEUDO_JSON, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<Void> cadastrar(@RequestBody AcessoRequisicao acesso) {
        servicoDeAcessos.cadastrar(acesso);
        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ControladorDeAcessos.class).buscarPorId(acesso.getId()))
                .withSelfRel();
        return ResponseEntity.created(selfLink.toUri()).build();
    }

    @GetMapping(produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<Page<AcessoResposta>> listar(@RequestParam int pagina, @RequestParam int tamanho) {
        Page<AcessoResposta> acessos = servicoDeAcessos.listar(pagina, tamanho);
        return ResponseEntity.ok(acessos);
    }

    @GetMapping(value = ConstantesDaAplicacao.ID_CAMINHO, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<AcessoResposta> buscarPorId(@PathVariable(ConstantesDaAplicacao.ID_PARAMETRO) Long id) {
        AcessoResposta acesso = servicoDeAcessos.buscarPorId(id);
        if (acesso != null) {
            Link selfLink = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(ControladorDeAcessos.class).buscarPorId(id)).withSelfRel();
            acesso.add(selfLink);
            return ResponseEntity.ok(acesso);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = ConstantesDaAplicacao.ID_CAMINHO, consumes = ConstantesDaAplicacao.CONTEUDO_JSON, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<AcessoResposta> editar(@PathVariable(ConstantesDaAplicacao.ID_PARAMETRO) Long id,
            @RequestBody AcessoRequisicao acessoRequisicao) {
        acessoRequisicao.setId(id);
        servicoDeAcessos.editar(acessoRequisicao);

        AcessoResposta acesso = servicoDeAcessos.buscarPorId(id);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControladorDeAcessos.class).buscarPorId(id))
                .withSelfRel();

        acesso.add(selfLink);

        return ResponseEntity.ok(acesso);
    }

    @DeleteMapping(produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<Void> excluir(@RequestParam Long id) {
        servicoDeAcessos.excluir(id);
        Link listLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControladorDeAcessos.class).listar(0, 20))
                .withRel(ConstantesDaAplicacao.RECURSO_LISTAR);
        return ResponseEntity.noContent().location(listLink.toUri()).build();
    }

}
