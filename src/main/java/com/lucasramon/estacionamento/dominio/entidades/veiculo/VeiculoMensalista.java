package com.lucasramon.estacionamento.dominio.entidades.veiculo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VeiculoMensalista extends Veiculo {
    private String cpfProprietario;
}
