package com.lucasramon.estacionamento.infra.esquemas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lucasramon.estacionamento.dominio.entidades.usuario.Regra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEsquema {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Column(name = "nome_de_usuario", unique = true, nullable = false)
    String nomeDeUsuario;
    
    @Column(name = "senha", nullable = false)
    String senha;

    @Column(name = "email", unique = true, nullable = false)
    String email;

    @Column(name = "regra", nullable = false)
    @Enumerated(EnumType.STRING)
    Regra regra;
}
