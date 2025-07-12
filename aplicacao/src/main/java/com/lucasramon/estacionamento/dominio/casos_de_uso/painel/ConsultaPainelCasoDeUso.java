package com.lucasramon.estacionamento.dominio.casos_de_uso.painel;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.lucasramon.estacionamento.dominio.casos_de_uso.InterfaceDeCasoDeUso;
import com.lucasramon.estacionamento.dominio.entidades.acesso.Acesso;
import com.lucasramon.estacionamento.dominio.entidades.acesso.TipoAcesso;
import com.lucasramon.estacionamento.dominio.modelos_de_dados.ConsultaPainel;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeAcessos;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;
import com.lucasramon.estacionamento.dominio.util.ConstantesDoDominio;


public class ConsultaPainelCasoDeUso implements InterfaceDeCasoDeUso<Void,ConsultaPainel> {

    private final RepositorioDeAcessos repositorioDeAcessos;

    private final RepositorioDeVeiculos repositorioDeVeiculos;

    
    public ConsultaPainelCasoDeUso(RepositorioDeAcessos repositorioDeAcessos,
            RepositorioDeVeiculos repositorioDeVeiculos) {
        this.repositorioDeAcessos = repositorioDeAcessos;
        this.repositorioDeVeiculos = repositorioDeVeiculos;
    }

    private List<Acesso> buscaUltimosAcessos( ){
        return this.repositorioDeAcessos.obtemUltimosAcessos(PageRequest.of(0, 5));
    }

    private int buscaTotalDeVagas(){
        return ConstantesDoDominio.QUANTIDADE_TOTAL_DE_VAGAS;
    }

    private int buscaTotalDeVagasDisponiveis(){
        int entradas = this.repositorioDeAcessos.contaPorTipoAcesso(TipoAcesso.ENTRADA);
        int saidas =  this.repositorioDeAcessos.contaPorTipoAcesso(TipoAcesso.SAIDA);;
        int estacionados = entradas - saidas;
        return ConstantesDoDominio.QUANTIDADE_TOTAL_DE_VAGAS - estacionados;
    }

    private int buscaQuantidadeDeVeiculosAtivos(){
        return this.repositorioDeVeiculos.contaVeiculosAtivos();
    }

    private int buscaQuantidadeDeMensalistas(){
        return this.repositorioDeVeiculos.contaVeiculosMensalistas();
    }

    private int buscaQuantidadeDeEntradasHoje(){
        return this.repositorioDeAcessos.contaPorTipoAcessoEdia(TipoAcesso.ENTRADA, LocalDate.now());
    }

    private int buscaQuantidadeDeSaidasHoje(){
        return this.repositorioDeAcessos.contaPorTipoAcessoEdia(TipoAcesso.ENTRADA, LocalDate.now());
    }

    @Override
    public ConsultaPainel executar(Void input) throws Exception {
        int vagasDisponiveis = this.buscaTotalDeVagasDisponiveis();

        return ConsultaPainel.builder().ultimosAcessos(this.buscaUltimosAcessos())
                .vagasDisponiveis(vagasDisponiveis)
                .vagasOcupadas(this.buscaTotalDeVagas() - vagasDisponiveis)
                .totalDeVagas(this.buscaTotalDeVagas())
                .veiculosAtivos(this.buscaQuantidadeDeVeiculosAtivos())
                .quantidadeDeMensalistas(this.buscaQuantidadeDeMensalistas())
                .numeroDeEntradasHoje(this.buscaQuantidadeDeEntradasHoje())
                .numeroDeSaidasHoje(this.buscaQuantidadeDeSaidasHoje())
                .build();
    }
    
}
