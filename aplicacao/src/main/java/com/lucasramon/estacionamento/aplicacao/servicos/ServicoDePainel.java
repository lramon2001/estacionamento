package com.lucasramon.estacionamento.aplicacao.servicos;

import org.springframework.stereotype.Service;

import com.lucasramon.estacionamento.dominio.casos_de_uso.painel.ConsultaPainelCasoDeUso;
import com.lucasramon.estacionamento.dominio.modelos_de_dados.ConsultaPainel;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeAcessos;
import com.lucasramon.estacionamento.dominio.repositorios.RepositorioDeVeiculos;

import lombok.RequiredArgsConstructor;

@Service
public class ServicoDePainel {

    private final ConsultaPainelCasoDeUso consultaPainelCasoDeUso;


    public ServicoDePainel(RepositorioDeAcessos repositorioDeAcessos,RepositorioDeVeiculos repositorioDeVeiculos) {
        this.consultaPainelCasoDeUso = new ConsultaPainelCasoDeUso(repositorioDeAcessos, repositorioDeVeiculos);
    }


    public ConsultaPainel consultarPainel(){
       try {
        return this.consultaPainelCasoDeUso.executar(null);
       } catch (Exception e) {
        e.printStackTrace();
       }
       return null;
    }
    
}
