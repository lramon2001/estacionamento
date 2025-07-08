package com.lucasramon.estacionamento.infra.mapeadores;

import org.springframework.stereotype.Component;

import com.lucasramon.estacionamento.dominio.entidades.usuario.Usuario;
import com.lucasramon.estacionamento.infra.esquemas.UsuarioEsquema;

@Component
public class MapeadorDeUsuario implements Mapeador<Usuario,UsuarioEsquema> {
    
    @Override
    public Usuario paraEntidade(UsuarioEsquema schema) {
        if (schema == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(schema.getId());
        usuario.setNomeDeUsuario(schema.getNomeDeUsuario());
        usuario.setSenha(schema.getSenha());
        usuario.setEmail(schema.getEmail());
        usuario.setRegra(schema.getRegra());
        return usuario;
    }

    @Override
    public UsuarioEsquema paraEsquema(Usuario entity) {
        if (entity == null) {
            return null;
        }
        UsuarioEsquema schema = new UsuarioEsquema();
        schema.setId(entity.getId());
        schema.setNomeDeUsuario(entity.getNomeDeUsuario());
        schema.setSenha(entity.getSenha());
        schema.setEmail(entity.getEmail());
        schema.setRegra(entity.getRegra());
        return schema;
    }
}
