package com.lucasramon.estacionamento.infra.configuracoes;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguracao {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
