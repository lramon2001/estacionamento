package com.lucasramon.estacionamento.infra.mapeadores;

import org.springframework.stereotype.Component;

import com.lucasramon.estacionamento.dominio.entidades.proprietario.Proprietario;
import com.lucasramon.estacionamento.infra.esquemas.ProprietarioEsquema;

@Component
public class MapeadorDeProprietario implements Mapeador<Proprietario, ProprietarioEsquema> {
 
    @Override
    public Proprietario paraEntidade(ProprietarioEsquema schema) {
        if (schema == null) {
            return null;
        }
        Proprietario proprietario = new Proprietario();
        proprietario.setNome(schema.getNome());
        proprietario.setCpf(schema.getCpf());
        proprietario.setTelefone(schema.getTelefone());
        proprietario.setEmail(schema.getEmail());
        proprietario.setDataNascimento(schema.getDataNascimento());
        return proprietario;
    }
    
    @Override
    public ProprietarioEsquema paraEsquema(Proprietario entity) {
        if (entity == null) {
            return null;
        }
        ProprietarioEsquema schema = new ProprietarioEsquema();
        schema.setNome(entity.getNome());
        schema.setCpf(entity.getCpf());
        schema.setTelefone(entity.getTelefone());
        schema.setEmail(entity.getEmail());
        schema.setDataNascimento(entity.getDataNascimento());
        return schema;
    }
    
}
