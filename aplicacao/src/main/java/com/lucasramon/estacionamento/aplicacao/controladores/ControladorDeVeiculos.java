package com.lucasramon.estacionamento.aplicacao.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
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

import com.lucasramon.estacionamento.aplicacao.requisicoes.VeiculoMensalistaRequisicao;
import com.lucasramon.estacionamento.aplicacao.requisicoes.VeiculoRequisicao;
import com.lucasramon.estacionamento.aplicacao.respostas.VeiculoResposta;
import com.lucasramon.estacionamento.aplicacao.servicos.ServicoDeVeiculos;
import com.lucasramon.estacionamento.aplicacao.util.ConstantesDaAplicacao;

@RestController
@RequestMapping(ConstantesDaAplicacao.ROTA_VEICULOS)
@PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('COMUM')")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class ControladorDeVeiculos implements InterfaceDeControlador<String, VeiculoRequisicao, VeiculoResposta> {

    @Autowired
    private ServicoDeVeiculos servicoDeVeiculos;

    @PostMapping(value = ConstantesDaAplicacao.ROTA_VEICULOS_MENSALISTAS, consumes = ConstantesDaAplicacao.CONTEUDO_JSON, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<Void> cadastrarMensalista(@RequestBody VeiculoMensalistaRequisicao veiculoMensalista) {
        servicoDeVeiculos.cadastrar(veiculoMensalista);

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ControladorDeVeiculos.class).listar(0, 20))
                .withRel(ConstantesDaAplicacao.RECURSO_LISTAR);

        return ResponseEntity.status(HttpStatus.CREATED).location(selfLink.toUri()).build(); 
    }

    @PostMapping(value = ConstantesDaAplicacao.ROTA_VEICULOS_ROTATIVOS, consumes = ConstantesDaAplicacao.CONTEUDO_JSON, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<Void> cadastrar(@RequestBody VeiculoRequisicao veiculo) {
        servicoDeVeiculos.cadastrar(veiculo);

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ControladorDeVeiculos.class).listar(0, 20))
                .withRel(ConstantesDaAplicacao.RECURSO_LISTAR);

        return ResponseEntity.status(HttpStatus.CREATED).location(selfLink.toUri()).build(); 
    }

    @GetMapping(value = "", produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<Page<VeiculoResposta>> listar(@RequestParam int pagina, @RequestParam int tamanho) {
        Page<VeiculoResposta> veiculos = servicoDeVeiculos.listar(pagina, tamanho);
        return ResponseEntity.ok(veiculos);  
    }

    @PutMapping(value = ConstantesDaAplicacao.ID_CAMINHO, consumes = ConstantesDaAplicacao.CONTEUDO_JSON, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<VeiculoResposta> editar(@PathVariable(ConstantesDaAplicacao.ID_PARAMETRO) String id, @RequestBody VeiculoRequisicao veiculo) {
        veiculo.setPlaca(id);
        servicoDeVeiculos.editar(veiculo);

        VeiculoResposta veiculoResposta = servicoDeVeiculos.buscarPorId(id);

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ControladorDeVeiculos.class).listar(0, 20))
                .withRel(ConstantesDaAplicacao.RECURSO_LISTAR);

        veiculoResposta.add(selfLink);  

        return ResponseEntity.ok(veiculoResposta); 
    }

    @Override
    @DeleteMapping(produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<Void> excluir(@RequestParam String id) {
            servicoDeVeiculos.excluir(id);
            return ResponseEntity.noContent().build();  
    }

    @Override
    @GetMapping(value = ConstantesDaAplicacao.ID_CAMINHO, produces = ConstantesDaAplicacao.CONTEUDO_JSON)
    public ResponseEntity<VeiculoResposta> buscarPorId(@PathVariable(ConstantesDaAplicacao.ID_PARAMETRO) String id) {
            VeiculoResposta veiculo = servicoDeVeiculos.buscarPorId(id);
            if (veiculo != null) {
                Link selfLink = WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder.methodOn(ControladorDeVeiculos.class).buscarPorId(id))
                        .withSelfRel();
                veiculo.add(selfLink); 
                return ResponseEntity.ok(veiculo);  
            }
            return ResponseEntity.notFound().build();  
    }
}
