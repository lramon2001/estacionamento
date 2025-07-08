package com.lucasramon.estacionamento.aplicacao.servicos;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lucasramon.estacionamento.aplicacao.conversores.ConversorDeAcesso;
import com.lucasramon.estacionamento.aplicacao.requisicoes.AcessoRequisicao;
import com.lucasramon.estacionamento.aplicacao.respostas.AcessoResposta;
import com.lucasramon.estacionamento.dominio.casos_de_uso.acesso.BuscarAcessoPorIDCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.acesso.EditarAcessoCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.acesso.ListarAcessosCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.acesso.RegistrarAcessoCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.acesso.RemoverAcessoCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.acesso.Acesso;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeAcessos;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;
import com.lucasramon.estacionamento.dominio.util.Paginacao;

@Service
public class SevicoDeAcessos implements InterfaceDeServico<Long, AcessoRequisicao, AcessoResposta> {

    private final RegistrarAcessoCasoDeUso registrarAcessoCasoDeUso;
    private final ListarAcessosCasoDeUso listarAcessosCasoDeUso;
    private final RemoverAcessoCasoDeUso removerAcessoCasoDeUso;
    private final EditarAcessoCasoDeUso editarAcessoUseCase;
    private final BuscarAcessoPorIDCasoDeUso buscarAcessoPorIDCasoDeUso;

    @Autowired
    private ConversorDeAcesso conversorDeAcesso;

    public SevicoDeAcessos(RepositorioDeAcessos acessoRepository, RepositorioDeVeiculos veiculoRepository) {
        this.registrarAcessoCasoDeUso = new RegistrarAcessoCasoDeUso(acessoRepository, veiculoRepository);
        this.listarAcessosCasoDeUso = new ListarAcessosCasoDeUso(acessoRepository);
        this.removerAcessoCasoDeUso = new RemoverAcessoCasoDeUso(acessoRepository);
        this.editarAcessoUseCase = new EditarAcessoCasoDeUso(acessoRepository);
        this.buscarAcessoPorIDCasoDeUso = new BuscarAcessoPorIDCasoDeUso(acessoRepository);
    }

    @Transactional
    public void cadastrar(AcessoRequisicao acessoRequisicao) {
        Acesso acesso = conversorDeAcesso.paraEntidade(acessoRequisicao); 
        registrarAcessoCasoDeUso.executar(acesso);
    }

    @Transactional
    public Page<AcessoResposta> listar(int pagina, int tamanho) {
        try {
            Page<Acesso> acessos = listarAcessosCasoDeUso
                    .executar(Paginacao.builder().pagina(pagina).tamanho(tamanho).build());
            return acessos.map(conversorDeAcesso::paraOutput); 
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar acessos: ", e);
        }
    }

    @Transactional
    public void excluir(Long id) {
        removerAcessoCasoDeUso.executar(id);
    }

    @Override
    public void editar(AcessoRequisicao acessoRequisicao) {
        try {
            Acesso acesso = conversorDeAcesso.paraEntidade(acessoRequisicao); 
            editarAcessoUseCase.executar(acesso);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao editar acesso ", e);
        }
    }

    @Override
    public AcessoResposta buscarPorId(Long id) {
        try {
            Acesso acesso = buscarAcessoPorIDCasoDeUso.executar(id);
            return conversorDeAcesso.paraOutput(acesso);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar acesso por ID", e);
        }
    }
}
