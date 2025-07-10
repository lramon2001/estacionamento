package com.lucasramon.estacionamento.aplicacao.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucasramon.estacionamento.aplicacao.requisicoes.ProprietarioRequisicao;
import com.lucasramon.estacionamento.aplicacao.respostas.ProprietarioResposta;
import com.lucasramon.estacionamento.aplicacao.servicos.ServicoDeProprietarios;
import com.lucasramon.estacionamento.aplicacao.util.ConstantesDaAplicacao;

@RestController
@RequestMapping(ConstantesDaAplicacao.ROTA_PROPRIETARIOS)
@PreAuthorize("hasRole('ADMINISTRADOR')")
public class ControladorDeProprietarios implements InterfaceDeControlador<String, ProprietarioRequisicao, ProprietarioResposta> {

    @Autowired
    private ServicoDeProprietarios servicoDeProprietarios;

    @PostMapping(consumes = ConstantesDaAplicacao.CONTEUDO_JSON, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<Void> cadastrar(@RequestBody ProprietarioRequisicao proprietarioRequisicao) {
        servicoDeProprietarios.cadastrar(proprietarioRequisicao);

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ControladorDeProprietarios.class).buscarPorId(proprietarioRequisicao.getCpf()))
                .withSelfRel();

        return ResponseEntity.created(selfLink.toUri()).build(); 
    }

    @GetMapping(produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<Page<ProprietarioResposta>> listar(@RequestParam int pagina, @RequestParam int tamanho) {
        Page<ProprietarioResposta> proprietarios = servicoDeProprietarios.listar(pagina, tamanho);
        return ResponseEntity.ok(proprietarios);  
    }

    @GetMapping(value = ConstantesDaAplicacao.ID_CAMINHO, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<ProprietarioResposta> buscarPorId(@PathVariable(ConstantesDaAplicacao.ID_PARAMETRO) String id) {
        ProprietarioResposta proprietario = servicoDeProprietarios.buscarPorId(id);

        if (proprietario != null) {
            Link selfLink = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(ControladorDeProprietarios.class).buscarPorId(id)).withSelfRel();
            proprietario.add(selfLink); 

            return ResponseEntity.ok(proprietario);  
        }

        return ResponseEntity.notFound().build();  
    }

    @PutMapping(value = ConstantesDaAplicacao.ID_CAMINHO, consumes = ConstantesDaAplicacao.CONTEUDO_JSON, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<ProprietarioResposta> editar(@PathVariable(ConstantesDaAplicacao.ID_PARAMETRO) String id, @RequestBody ProprietarioRequisicao proprietarioRequisicao) {
        proprietarioRequisicao.setCpf(id);
        servicoDeProprietarios.editar(proprietarioRequisicao);

        ProprietarioResposta proprietario = servicoDeProprietarios.buscarPorId(id);

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ControladorDeProprietarios.class).buscarPorId(id))
                .withSelfRel();

        proprietario.add(selfLink);  

        return ResponseEntity.ok(proprietario);  
    }

    @DeleteMapping(produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<Void> excluir(@RequestParam String cpf) {
        servicoDeProprietarios.excluir(cpf); 

        Link listLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ControladorDeProprietarios.class).listar(0, 20))
                .withRel(ConstantesDaAplicacao.RECURSO_LISTAR);

        return ResponseEntity.noContent().location(listLink.toUri()).build();  
    }
}
