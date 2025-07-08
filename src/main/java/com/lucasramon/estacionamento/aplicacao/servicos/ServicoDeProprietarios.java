package com.lucasramon.estacionamento.aplicacao.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucasramon.estacionamento.aplicacao.conversores.ConversorDeProprietario;
import com.lucasramon.estacionamento.aplicacao.requisicoes.ProprietarioRequisicao;
import com.lucasramon.estacionamento.aplicacao.respostas.ProprietarioResposta;
import com.lucasramon.estacionamento.dominio.casos_de_uso.proprietario.BuscarProprietarioPorIDCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.proprietario.CadastrarProprietarioCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.proprietario.EditarProprietarioCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.proprietario.RemoverProprietarioCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.proprietario.ListarProprietariosCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.proprietario.Proprietario;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeProprietarios;
import com.lucasramon.estacionamento.dominio.util.Paginacao;

@Service
public class ServicoDeProprietarios implements InterfaceDeServico<String,ProprietarioRequisicao,ProprietarioResposta>{

    private final CadastrarProprietarioCasoDeUso cadastrarProprietarioUseCase;
    private final ListarProprietariosCasoDeUso listarProprietariosUseCase;
    private final RemoverProprietarioCasoDeUso excluirProprietarioUseCase;
    private final EditarProprietarioCasoDeUso editarProprietarioUseCase;
    private final BuscarProprietarioPorIDCasoDeUso buscarProprietarioPorIDCasoDeUso;
    
    @Autowired
    private ConversorDeProprietario conversorDeProprietario;
    
    public ServicoDeProprietarios(RepositorioDeProprietarios proprietarioRepository) {
        this.cadastrarProprietarioUseCase = new CadastrarProprietarioCasoDeUso(proprietarioRepository);
        this.listarProprietariosUseCase = new ListarProprietariosCasoDeUso(proprietarioRepository);
        this.excluirProprietarioUseCase = new RemoverProprietarioCasoDeUso(proprietarioRepository);
        this.editarProprietarioUseCase = new EditarProprietarioCasoDeUso(proprietarioRepository);
        this.buscarProprietarioPorIDCasoDeUso = new BuscarProprietarioPorIDCasoDeUso(proprietarioRepository);
    }

    @Transactional
    public void cadastrar(ProprietarioRequisicao proprietario) {
        cadastrarProprietarioUseCase.executar(this.conversorDeProprietario.paraEntidade(proprietario));
    }

    @Transactional
    public Page<ProprietarioResposta> listar(int pagina, int tamanho) {
        Page<Proprietario> proprietarios;
        try {
            proprietarios = listarProprietariosUseCase.executar(Paginacao.builder().pagina(pagina).tamanho(tamanho).build());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar proprietários. " );
        }
        return proprietarios.map(conversorDeProprietario::paraOutput);
    }

    @Transactional
    public void excluir(String cpf) {
        excluirProprietarioUseCase.executar(cpf);
    }

    @Transactional
    public void editar(ProprietarioRequisicao proprietario) {
        editarProprietarioUseCase.executar(this.conversorDeProprietario.paraEntidade(proprietario));
    }


    @Override
    public ProprietarioResposta buscarPorId(String id) {
        try {
            return conversorDeProprietario.paraOutput(
                buscarProprietarioPorIDCasoDeUso.executar(id));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar proprietário por ID" );
        }
    }


}
