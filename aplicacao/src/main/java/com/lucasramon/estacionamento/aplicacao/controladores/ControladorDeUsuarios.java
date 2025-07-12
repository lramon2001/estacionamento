package com.lucasramon.estacionamento.aplicacao.controladores;

import com.lucasramon.estacionamento.aplicacao.requisicoes.UsuarioRequisicao;
import com.lucasramon.estacionamento.aplicacao.respostas.UsuarioResposta;
import com.lucasramon.estacionamento.aplicacao.servicos.ServicoDeUsuario;
import com.lucasramon.estacionamento.aplicacao.util.ConstantesDaAplicacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ConstantesDaAplicacao.ROTA_VEICULOS_USUARIOS)
@PreAuthorize("hasRole('ADMINISTRADOR')")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class ControladorDeUsuarios implements InterfaceDeControlador<Long, UsuarioRequisicao, UsuarioResposta>
{
    @Autowired
    private ServicoDeUsuario servicoDeUsuario;

    @Override
    @PostMapping(consumes = ConstantesDaAplicacao.CONTEUDO_JSON, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<Void> cadastrar(@RequestBody UsuarioRequisicao usuarioRequisicao) {
        servicoDeUsuario.cadastrar(usuarioRequisicao);
        Link selfLink =  WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ControladorDeUsuarios.class).buscarPorId(usuarioRequisicao.getId()))
                .withSelfRel();
        return  ResponseEntity.created(selfLink.toUri()).build();
    }

    @Override
    @PutMapping(value = ConstantesDaAplicacao.ID_CAMINHO, consumes = ConstantesDaAplicacao.CONTEUDO_JSON, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<UsuarioResposta> editar(@PathVariable(ConstantesDaAplicacao.ID_PARAMETRO) Long id, @RequestBody UsuarioRequisicao usuarioRequisicao) {
        usuarioRequisicao.setId(id);
        servicoDeUsuario.editar(usuarioRequisicao);

        UsuarioResposta usuario = servicoDeUsuario.buscarPorId(id);

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ControladorDeUsuarios.class).buscarPorId(id))
                .withSelfRel();

        usuario.add(selfLink);

        return ResponseEntity.ok(usuario);
    }

    @Override
    @DeleteMapping(produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<Void> excluir(@RequestParam Long id) {
        servicoDeUsuario.excluir(id);

        Link listLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ControladorDeUsuarios.class).listar(0, 20))
                .withRel(ConstantesDaAplicacao.RECURSO_LISTAR);

        return ResponseEntity.noContent().location(listLink.toUri()).build();
    }

    @Override
    @GetMapping(value = ConstantesDaAplicacao.ID_CAMINHO, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<UsuarioResposta> buscarPorId(@PathVariable(ConstantesDaAplicacao.ID_PARAMETRO) Long id) {
        UsuarioResposta usuario = servicoDeUsuario.buscarPorId(id);

        if (usuario != null) {
            Link selfLink = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(ControladorDeUsuarios.class).buscarPorId(id)).withSelfRel();
            usuario.add(selfLink);

            return ResponseEntity.ok(usuario);
        }

        return ResponseEntity.notFound().build();      }

    @Override
    @GetMapping(produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<Page<UsuarioResposta>> listar(@RequestParam int pagina, @RequestParam int tamanho) {
        Page<UsuarioResposta> usuarios = servicoDeUsuario.listar(pagina, tamanho);
        return ResponseEntity.ok(usuarios);
    }
}
