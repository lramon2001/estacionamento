package com.lucasramon.estacionamento.aplicacao.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucasramon.estacionamento.aplicacao.conversores.ConversorVeiculo;
import com.lucasramon.estacionamento.aplicacao.requisicoes.VeiculoMensalistaRequisicao;
import com.lucasramon.estacionamento.aplicacao.requisicoes.VeiculoRequisicao;
import com.lucasramon.estacionamento.aplicacao.respostas.VeiculoResposta;
import com.lucasramon.estacionamento.dominio.casos_de_uso.veiculo.BuscarVeiculoPorIDCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.veiculo.CadastrarVeiculoMensalistaCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.veiculo.CadastrarVeiculoRotativoCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.veiculo.EditarVeiculoCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.veiculo.ExcluirVeiculoCasoDeUso;
import com.lucasramon.estacionamento.dominio.casos_de_uso.veiculo.ListarVeiculosCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.veiculo.Veiculo;
import com.lucasramon.estacionamento.dominio.entidades.veiculo.VeiculoMensalista;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeProprietarios;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;
import com.lucasramon.estacionamento.dominio.util.Paginacao;

@Service
public class ServicoDeVeiculos implements InterfaceDeServico<String,VeiculoRequisicao,VeiculoResposta> {

    private final CadastrarVeiculoMensalistaCasoDeUso cadastrarMensalistaUseCase;
    private final CadastrarVeiculoRotativoCasoDeUso cadastrarVeiculoRotativoUseCase;
    private final ListarVeiculosCasoDeUso listarVeiculosUseCase;
    private final EditarVeiculoCasoDeUso editarVeiculoUseCase;
    private final BuscarVeiculoPorIDCasoDeUso buscarVeiculoPorIDCasoDeUso;
    private final ExcluirVeiculoCasoDeUso excluirVeiculoCasoDeUso;

    @Autowired
    private ConversorVeiculo conversorVeiculo;
    
    public ServicoDeVeiculos(RepositorioDeVeiculos veiculoRepository,
            RepositorioDeProprietarios proprietarioRepository) {
        this.cadastrarMensalistaUseCase = new CadastrarVeiculoMensalistaCasoDeUso(veiculoRepository,
                proprietarioRepository);
        this.cadastrarVeiculoRotativoUseCase = new CadastrarVeiculoRotativoCasoDeUso(veiculoRepository);
        this.listarVeiculosUseCase = new ListarVeiculosCasoDeUso(veiculoRepository);
        this.editarVeiculoUseCase = new EditarVeiculoCasoDeUso(veiculoRepository);
        this.buscarVeiculoPorIDCasoDeUso = new BuscarVeiculoPorIDCasoDeUso(veiculoRepository);
        this.excluirVeiculoCasoDeUso = new ExcluirVeiculoCasoDeUso(veiculoRepository);
    }

    @Transactional
    private void cadastrarMensalista(VeiculoMensalista veiculo) {
        cadastrarMensalistaUseCase.executar(veiculo);
    }

    @Transactional
    private void cadastrarRotativo(Veiculo veiculo) {
        cadastrarVeiculoRotativoUseCase.executar(veiculo);
    }

    @Transactional
    public Page<VeiculoResposta> listar(int pagina, int tamanho) {
       Page<Veiculo> veiculos =  listarVeiculosUseCase.executar(Paginacao.builder()
                .pagina(pagina)
                .tamanho(tamanho)
                .build());
         return veiculos.map(conversorVeiculo::paraOutput);
    }

    @Transactional
    public void editar(VeiculoRequisicao veiculo) {
        editarVeiculoUseCase.executar(this.conversorVeiculo.paraEntidade(veiculo));
    }

    @Override
    public void cadastrar(VeiculoRequisicao input) {
        if(input instanceof VeiculoMensalistaRequisicao) {
            VeiculoMensalista veiculoMensalista = conversorVeiculo.paraEntidadeMensalista((VeiculoMensalistaRequisicao) input);
            cadastrarMensalista(veiculoMensalista);
        } else {
            Veiculo veiculoRotativo = conversorVeiculo.paraEntidade(input);
            cadastrarRotativo(veiculoRotativo);
        }
    }

   

    @Override
    public void excluir(String id) {
        try {
            excluirVeiculoCasoDeUso.executar(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir veículo com ID: " + id);
        }
    }

    @Override
    public VeiculoResposta buscarPorId(String id) {
        try {
            Veiculo veiculo = buscarVeiculoPorIDCasoDeUso.executar(id);
            return conversorVeiculo.paraOutput(veiculo);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar veículo por ID.");
        }
    }



}
